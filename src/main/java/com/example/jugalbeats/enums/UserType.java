package com.example.jugalbeats.enums;

public enum UserType {
	ARTIST("artist"),
	CLIENT("user");
	private String value;

	public String getValue() {
		return value;
	}

	private UserType(String value) {
		this.value = value;
	}

}
