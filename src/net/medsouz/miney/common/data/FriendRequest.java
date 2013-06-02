package net.medsouz.miney.common.data;

public class FriendRequest extends Message {

	public FriendRequest(String sent, long time, boolean read) {
		super("Friend Request", "", sent, time, -1, -1, read);
	}

}
