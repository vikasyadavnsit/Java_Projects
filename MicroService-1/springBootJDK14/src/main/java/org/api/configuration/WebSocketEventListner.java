package org.api.configuration;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketEventListner {

//	@Autowired
//	private SimpMessageSendingOperations sendingOperations;

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
		log.info("Connection Disconnected For : " + event.getSessionId());
	}

}
