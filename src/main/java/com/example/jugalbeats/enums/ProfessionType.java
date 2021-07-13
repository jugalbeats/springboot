package com.example.jugalbeats.enums;

public enum ProfessionType {

	DRUMMER("Drummer"),
	SINGER("Singer"),
	PLAYBACKSINGER("Playback Singer"),
	GUITARIST("Guitarist"),
	PIANOPLAYER("Piano Player"),
	INSTRUMENTALIST("Instrumentalists"),
GROUP("Group"),DANCE("Dance"),MODELS("Models"),VOICEOVERARTIST("Voice over artist"),
ACTOR("Actor"),MAGICIANS("Magicians"),
CLOWNS("Clowns"),ANCHORS("Anchors"),COMEDIANS("Comedians");
	
	private String value;

	public String getValue() {
		return value;
	}

	private ProfessionType(String value) {
		this.value = value;
	}
}
