package org.api.configuration;

import org.api.controller.websocket.ChatMessage;
import org.api.controller.websocket.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketEventListner {

	@Autowired
	private SimpMessageSendingOperations sendingOperations;

	@EventListener
	public void handleWebSocketConnectListner(final SessionConnectEvent event) {
		log.info("New User Attempting to connect.");
	}
	
	
	@EventListener
	public void handleWebSocketConnectedListner(final SessionConnectedEvent event) {
		log.info("New User connection established");
	}

	@EventListener
	public void handleWebSocketDisconnectListner(final SessionDisconnectEvent event) {
		final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		final String username = (String) headerAccessor.getSessionAttributes().get("username");
		final ChatMessage chatMessage = ChatMessage.builder().type(MessageType.DISCONNECT).sender(username).build();
		log.info("Connection Disconnected For : " + chatMessage);
		sendingOperations.convertAndSend("/topic/public", chatMessage);
	}

}
