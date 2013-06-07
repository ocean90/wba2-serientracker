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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import de.fhkoeln.gm.serientracker.jaxb.List;
import de.fhkoeln.gm.serientracker.jaxb.Lists;
import de.fhkoeln.gm.serientracker.utils.Hasher;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;
import de.fhkoeln.gm.serientracker.webservice.data.ListsDataHandler;


/**
 * Service for:
 * GET     /lists
 * POST    /lists
 * GET     /lists/{listID}
 * DELETE  /lists/{listID}
 * PUT     /lists/{listID}
 */

@Path( "/lists" )
public class ListsService {

	private ListsDataHandler dh = new ListsDataHandler();

	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getLists() {
		Logger.log( "GET lists called" );

		Lists lists = dh.getLists();

		if ( lists == null )
			return Response.status( 404 ).build();

		return Response.ok().entity( lists ).build();
	}

	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getGenreList( 
								@QueryParam( "type" ) String type,
								@QueryParam( "name" ) String name) {
		Logger.log( type );
		Logger.log( name );
		
		List list = dh.getListByType( type );
		
		if (type == "genre"){	
			list = dh.getListBySpecificGenre( name );
		}
		
		if ( list == null )
			return Response.status( 404 ).build();
		else
			return Response.ok().entity( list ).build();
	}
	
	
	@POST
	@Consumes( MediaType.APPLICATION_XML )
	public Response addList( List newList ) {
		Logger.log( newList.getName() );

		String id = "ls_" + Hasher.createHash( newList.getName() );

		if ( dh.ListExistsByID( id ) )
			return Response.status( 409 ).build();

		newList.setListID( id );

		if ( ! dh.addList( newList ) )
			return Response.status( 500 ).build();

		URI location = null;
		try {
			location = new URI( RESTServerConfig.getServerURL() + "/lists/" + id );
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return Response.created( location ).build();
	}

	
	
	@Path( "{listID}" )
	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getList( @PathParam( "listID" ) String id) {
		Logger.log( id );

		List list = dh.getListByID( id );

		if ( list == null )
			return Response.status( 404 ).build();
		else
			return Response.ok().entity( list ).build();
	}
	
	

	@Path( "{listID}" )
	@PUT
	@Consumes( MediaType.APPLICATION_XML )
	public Response updateList( @PathParam( "listID" ) String id, List newList ) {
		Logger.log( id );

		if ( ! dh.ListExistsByID( id ) )
			return Response.status( 404 ).build();

		if ( ! id.equals( newList.getListID() ) )
			return Response.status( 400 ).build();

		if ( ! dh.updateList( newList ) )
			return Response.status( 500 ).build();

		return Response.noContent().build();
	}

	@Path( "{ListID}" )
	@DELETE
	public Response deleteList( @PathParam( "listID" ) String id ) {
		Logger.log( id );

		if ( ! dh.ListExistsByID( id ) )
			return Response.status( 404 ).build();

		if ( ! dh.removeList( id ) )
			return Response.status( 500 ).build();

		return Response.noContent().build();
	}

}
