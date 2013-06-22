package de.fhkoeln.gm.serientracker.webservice.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.fhkoeln.gm.serientracker.jaxb.User;
import de.fhkoeln.gm.serientracker.utils.Hasher;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;
import de.fhkoeln.gm.serientracker.webservice.data.SeriesDataHandler;
import de.fhkoeln.gm.serientracker.webservice.data.UsersDataHandler;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;


/**
 * Service for:
 * GET     /users
 * POST    /users
 * GET     /users/{userID}
 * DELETE  /users/{userID}
 * PUT     /users/{userID}
 */

@Path( "/users" )
public class UsersService {

	private UsersDataHandler dh = new UsersDataHandler();

	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getUsers() {
		Logger.log( "GET users called." );

		// TODO
		return Response.status( 409 ).build();
	}

	@POST
	@Consumes( MediaType.APPLICATION_XML )
	public Response addUser( User newUser ) {
		Logger.log( newUser.getUsername() );

		String id = "us_" + Hasher.createHash( newUser.getUsername() );

		if ( dh.UserExistsByID( id ) )
			return Response.status( 409 ).build();

		newUser.setUserID( id );

		if ( ! dh.addUser( newUser ) )
			return Response.status( 500 ).build();

		URI location = null;
		try {
			location = new URI( RESTServerConfig.getServerURL() + "/users/" + id );
		} catch ( URISyntaxException e ) {
			e.printStackTrace();
		}

		return Response.created( location ).build();
	}

	@Path( "{userID}" )
	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getUser( @PathParam( "userID" ) String id ) {
		Logger.log( "GET user called." );

		if ( ! dh.UserExistsByID( id ) )
			return Response.status( 404 ).build();

		User user = dh.getUserByID( id );

		return Response.ok().entity( user ).build();
	}

	@Path( "{userID}" )
	@PUT
	@Consumes( MediaType.APPLICATION_XML )
	public Response updateUser( @PathParam( "userID" ) String id, User newUser ) {
		Logger.log( "PUT user called." );

		// TODO
		return Response.status( 409 ).build();
	}

	@Path( "{userID}" )
	@DELETE
	public Response deleteUser( @PathParam( "userID" ) String id ) {
		Logger.log( "DELETE user called." );

		// TODO
		return Response.status( 409 ).build();
	}

}
