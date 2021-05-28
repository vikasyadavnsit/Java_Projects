package org.api.controller.websocket;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

	@MessageMapping("/message")
	@SendTo("/chats/userUpdates")
	public Greeting greeting(HelloMessage message) throws Exception {
		// Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}

	@MessageExceptionHandler
	@SendToUser("/chats/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
}
