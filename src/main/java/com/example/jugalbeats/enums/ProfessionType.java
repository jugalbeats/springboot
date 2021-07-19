package com.example.jugalbeats.enums;

public enum ProfessionType {

	DRUMMER(1, "Drummer"), SINGER(2, "Singer"), PLAYBACKSINGER(3, "Playback Singer"), GUITARIST(4, "Guitarist"),
	PIANOPLAYER(5, "Piano Player"), INSTRUMENTALIST(6, "Instrumentalists"), GROUP(7, "Group"), DANCE(8, "Dance"),
	MODELS(9, "Models"), VOICEOVERARTIST(10, "Voice over artist"), ACTOR(11, "Actor"), MAGICIANS(12, "Magicians"),
	CLOWNS(13, "Clowns"), ANCHORS(14, "Anchors"), COMEDIANS(15, "Comedians"), OTHERS(16, "Others");

	private String value;
	public int type;

	private ProfessionType(int type, String value) {
		this.type = type;
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static ProfessionType getEntityStatus(int type) {
		switch (type) {
		case 1:
			return ProfessionType.DRUMMER;
		case 2:
			return ProfessionType.SINGER;
		case 3:
			return ProfessionType.PLAYBACKSINGER;
		case 4:
			return ProfessionType.GUITARIST;
		case 5:
			return ProfessionType.PIANOPLAYER;
		case 6:
			return ProfessionType.INSTRUMENTALIST;
		case 7:
			return ProfessionType.GROUP;
		case 8:
			return ProfessionType.DANCE;
		case 9:
			return ProfessionType.MODELS;
		case 10:
			return ProfessionType.VOICEOVERARTIST;
		case 11:
			return ProfessionType.ACTOR;
		case 12:
			return ProfessionType.MAGICIANS;
		case 13:
			return ProfessionType.CLOWNS;
		case 14:
			return ProfessionType.ANCHORS;
		case 15:
			return ProfessionType.COMEDIANS;
		case 16:
			return ProfessionType.OTHERS;
		default:
			throw new IllegalArgumentException();
		}
	}
}
