package org.battleramp.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.battleramp.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		/*
		 * Response response =
		 * client.target("http://localhost:8080/advanced-jaxrx/webapi/messages/1").
		 * request().get(); Message message = response.readEntity(Message.class);
		 * System.out.println(message.getMessage());
		 */
		WebTarget baseTarget = client.target("http://localhost:8080/advanced-jaxrx/webapi");
		WebTarget messageTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messageTarget.path("{messageId}");

		WebTarget target = singleMessageTarget.resolveTemplate("messageId", "1");
		Builder builder = target.request(MediaType.APPLICATION_JSON);
		Message message = builder.get(Message.class);
		System.out.println(message.getMessage());

		Message newMessage = new Message(4, "new Message", "myself");
		Response postResponse = messageTarget.request().post(Entity.json(newMessage));
		System.out.println(postResponse.readEntity(Message.class).getMessage());

	}
}
