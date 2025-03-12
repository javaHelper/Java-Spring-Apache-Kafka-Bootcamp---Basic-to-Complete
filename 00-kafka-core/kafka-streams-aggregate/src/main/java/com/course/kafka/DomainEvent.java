package com.course.kafka;

import lombok.Data;

@Data
public class DomainEvent {
	String eventType;

	String boardUuid;
}
