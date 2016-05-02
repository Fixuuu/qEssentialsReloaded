package me.kavzaq.qEssentialsReloaded.utils.switches;

public class ChatSwitch {
	
	private static boolean chat;
	
	public static boolean switchChat(){
		chat = chat ? false : true;
		return chat;
	}
	
	public static boolean getChat()
	{
		return chat;
	}

}
