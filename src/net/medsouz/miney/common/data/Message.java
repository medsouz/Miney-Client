package net.medsouz.miney.common.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
	private String topic;
	private String message;
	private String sender;
	private long sentTime;
	private int messageID;
	private int parentID;
	
	public Message(String top, String msg, String sent, long time, int msgID, int pID){
		topic = top;
		message = msg;
		sender = sent;
		sentTime = time;
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
	
	public String getSentTime(){
		SimpleDateFormat df = new SimpleDateFormat("hh:mmaa MM/dd/yy");
		if(sentTime != 0){
			return df.format(new Date(sentTime));
		}else{
			return "";
		}
	}
}
