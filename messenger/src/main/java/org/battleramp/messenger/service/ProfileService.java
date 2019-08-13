package org.battleramp.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.battleramp.messenger.database.DatabaseClass;
import org.battleramp.messenger.model.ErrorMessage;
import org.battleramp.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService() {
		profiles.put("vickytauruss", new Profile(1, "vickytauruss", "Vikas", "Yadav"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		
		Profile profile = profiles.get(profileName);
		if(profile == null) {
			ErrorMessage errorMessage = new ErrorMessage("Data Not Found", 404, "battleramp.com");
			Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
			throw new WebApplicationException(response);
		}
		return profile;
	}

	public Profile addProfile(Profile Profile) {
		Profile.setId(profiles.size() + 1);
		profiles.put(Profile.getProfileName(), Profile);
		return Profile;
	}

	public Profile updateProfile(Profile Profile) {
		profiles.put(Profile.getProfileName(), Profile);
		return Profile;
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}

}
