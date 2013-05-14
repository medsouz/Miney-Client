package net.medsouz.miney.common.data;

public enum DataType {
	unknown, friend, messages;
	
	public static DataType getType(int x){
		return (DataType)values()[x];
	}
}
