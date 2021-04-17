package com.example.jugalbeats.enums;

public enum ProfessionType {

	DRUMMER("Drummer"),
	SINGER("Singer"),
	PLAYBACKSINGER("Playback Singer"),
	GUITARIST("Guitarist"),
	PIANOPLAYER("Piano Player");
	
	private String value;

	public String getValue() {
		return value;
	}

	private ProfessionType(String value) {
		this.value = value;
	}
}
