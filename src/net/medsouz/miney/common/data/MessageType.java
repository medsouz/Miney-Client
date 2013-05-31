package net.medsouz.miney.common.data;

public enum MessageType {
	message, friendRequest;
	
	public static MessageType getType(int x){
		return (MessageType)values()[x];
	}
}
