package org.battleramp.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.battleramp.messenger.model.Message;
import org.battleramp.messenger.model.Profile;

public class DatabaseClass {
	public static Map<Long, Message> messages = new HashMap<>();
	public static Map<String, Profile> profiles = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static void setMessages(Map<Long, Message> messages) {
		DatabaseClass.messages = messages;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

	public static void setProfiles(Map<String, Profile> profiles) {
		DatabaseClass.profiles = profiles;
	}

}
