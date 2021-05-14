package com.example.jugalbeats.enums;

public enum MessageStatus {
    RECEIVED(0), DELIVERED(1);
    
    private int value;

	public int getValue() {
		return value;
	}
	private MessageStatus(int value) {
		this.value = value;
	}
}
