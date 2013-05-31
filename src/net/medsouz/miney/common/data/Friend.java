package net.medsouz.miney.common.data;

public class Friend {
	private String username;
	private boolean online;
	private String status;
	private boolean request;
	
	public Friend(String name, boolean on, String stat, boolean req){
		username = name;
		online = on;
		status = stat;
		request = req;
		if(req){
			status = "Request Pending";
		}
	}
	
	public String getUsername(){
		return username;
	}
	
	public boolean isOnline(){
		return online;
	}
	
	public String getStatus(){
		return status;
	}
	
	public boolean isFriendRequest(){
		return request;
	}
}
