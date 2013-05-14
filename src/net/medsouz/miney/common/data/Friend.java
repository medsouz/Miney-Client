package net.medsouz.miney.common.data;

public class Friend {
	private String username;
	private boolean online;
	private String status;
	
	public Friend(String name, boolean on, String stat){
		username = name;
		online = on;
		status = stat;
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
}
