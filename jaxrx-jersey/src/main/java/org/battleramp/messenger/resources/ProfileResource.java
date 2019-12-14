package org.battleramp.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.battleramp.messenger.model.Profile;
import org.battleramp.messenger.service.ProfileService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
  ProfileService profileService = new ProfileService();
  
  @GET
  public List<Profile> getProfiles(){
	  return profileService.getAllProfiles();
  }
  
  @POST
  public Profile addProfile(Profile profile) {
	  return profileService.addProfile(profile);
  }
  
  @GET
  @Path("/{profileName}")
  public Profile getProfile(@PathParam("profileName") String profileName) {
	  return profileService.getProfile(profileName);
  }
  
  @PUT
  @Path("/{profileName}")
  public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
	  profile.setProfileName(profileName);
	  return profileService.updateProfile(profile);
  }
  
  @DELETE
  @Path("/{profileName}")
  public Profile removeProfile(@PathParam("profileName") String profileName) {
	  return profileService.removeProfile(profileName);
  }
  
  
  
  
}
