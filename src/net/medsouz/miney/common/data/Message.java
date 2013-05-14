package net.medsouz.miney.common.data;

public class Message {
	private String topic;
	private String message;
	private String sender;
	private int messageID;
	private int parentID;
	
	public Message(String top, String msg, String sent, int msgID, int pID){
		topic = top;
		message = msg;
		sender = sent;
		messageID = msgID;
		parentID = pID;
	}
	
	public String getTopic(){
		return topic;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getSender(){
		return sender;
	}
	
	public int getMessageID(){
		return messageID;
	}
	
	public int getParentID(){
		return parentID;
	}
}
