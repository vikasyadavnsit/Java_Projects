package org.battleramp.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {

	@GET
	public String getAllComments() {
		return "All Comments";
	}
	
	@GET
	@Path("/{commentId}")
	public String getComment(@PathParam("commentId") String commentId , @PathParam("messageId") String messageId) {
		return "Message ID:" + messageId +  " Comment:" + commentId;
	}
}
