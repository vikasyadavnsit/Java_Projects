package org.api.controller.consume;

import java.util.concurrent.TimeUnit;

import org.api.java.generics.PostsWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Slf4j
@RestController
@RequestMapping("/consume-webclient")
public class ConsumeRestUsingWebClientController {

	@GetMapping("/getAllPosts")
	public Mono<PostsWrapper[]> getAllPosts() {
		log.info("Inside ConsumeRestUsingWebClientController : getAllPosts() ");
		// WebClient client =
		// WebClient.create("https://jsonplaceholder.typicode.com/posts");

		TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.doOnConnected(connection -> {
					connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
					connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				});

		WebClient client = WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
				.baseUrl("https://jsonplaceholder.typicode.com/posts")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		ResponseSpec posts = client.get().retrieve();
		return posts.bodyToMono(PostsWrapper[].class).log();

	}
}
