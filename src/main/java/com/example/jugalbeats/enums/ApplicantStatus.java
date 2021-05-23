package com.example.jugalbeats.enums;

public enum ApplicantStatus {
	APPLIED("Applied"),
	REJECTED("Rejected");
	
	private String value;

	public String getValue() {
		return value;
	}

	private ApplicantStatus(String value) {
		this.value = value;
	}
}
