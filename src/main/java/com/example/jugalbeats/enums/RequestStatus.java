package com.example.jugalbeats.enums;


public enum RequestStatus {

ACCEPTED(1, "Accepted"), PENDING(2, "Pending"), REJECTED(3,"Rejected");
	
	public int type;
	public String value;

	private RequestStatus(int type, String value) {
			this.type = type;
			this.value = value;
		}

	public int getType() {
		return type;
	}


	public String getValue() {
		return value;
	}
	
	public static RequestStatus getRoomType(int type){
		switch (type) {
		case 1:
			return RequestStatus.ACCEPTED;
		case 2:
			return RequestStatus.PENDING;
		case 3:
			return RequestStatus.REJECTED;
		
		default:
			return RequestStatus.PENDING;
		}
	}
}
