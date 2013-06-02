package net.medsouz.miney.client.networking;

import net.medsouz.miney.client.MineyClient;
import net.medsouz.miney.client.data.FriendManager;
import net.medsouz.miney.client.data.MessageManager;

public class ThreadDataUpdater implements Runnable{

	@Override
	public void run() {
		while(MineyClient.isLoggedIn()){
			try {
				System.out.println("Requesting data...");
				FriendManager.updateFriends();
				MessageManager.updateMessages();
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
