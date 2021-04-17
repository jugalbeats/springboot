package com.example.jugalbeats.enums;

public enum UserType {
	ARTIST("artist"),
	CLIENT("client");
	private String value;

	public String getValue() {
		return value;
	}

	private UserType(String value) {
		this.value = value;
	}

}
