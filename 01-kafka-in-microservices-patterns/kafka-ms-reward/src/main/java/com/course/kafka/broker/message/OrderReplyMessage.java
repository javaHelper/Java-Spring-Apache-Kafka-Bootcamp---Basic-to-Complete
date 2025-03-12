package com.course.kafka.broker.message;

public class OrderReplyMessage {

	private String replyMessage;

	@Override
	public String toString() {
		return "OrderReplyMessage [replyMessage=" + replyMessage + "]";
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

}
