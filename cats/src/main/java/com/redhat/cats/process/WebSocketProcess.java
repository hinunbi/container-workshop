package com.redhat.cats.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketProcess {

	@Autowired
	private SimpMessagingTemplate template;

	public String trigger(String message) {
		this.template.convertAndSend("/topic/cats.data", message);
		return message;
	}
}
