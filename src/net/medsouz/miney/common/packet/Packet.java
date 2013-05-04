package net.medsouz.miney.common.packet;

public abstract class Packet {
	public static int getID() {
		return 0;
	}
	
	public static String getName() {
		return "NaN";
	}
	
	public abstract byte[] getData();
	public abstract Object readData(byte[] data);
}
