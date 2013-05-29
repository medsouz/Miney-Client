package net.medsouz.miney.common.packet;

public abstract class Packet {
	
	public abstract int getID();

	public abstract String getName();

	public abstract byte[] getData();

	public abstract Object readData(byte[] data);
}
