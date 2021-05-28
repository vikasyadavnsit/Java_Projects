package org.api.controller.websocket;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class ChatMessage {

	private MessageType type;
	private String content;
	private String sender;
	private String time;
}
