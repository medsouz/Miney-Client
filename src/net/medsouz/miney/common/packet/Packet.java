package net.medsouz.miney.common.packet;

public abstract class Packet {
	
	public int getID() {
		return -1;
	}

	public String getName() {
		return "NaN";
	}

	public abstract byte[] getData();

	public abstract Object readData(byte[] data);
}
