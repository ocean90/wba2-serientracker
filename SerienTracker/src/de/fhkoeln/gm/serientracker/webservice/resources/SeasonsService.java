package de.fhkoeln.gm.serientracker.webservice.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.Response;

import de.fhkoeln.gm.serientracker.jaxb.Season;
import de.fhkoeln.gm.serientracker.jaxb.Seasons;
import de.fhkoeln.gm.serientracker.utils.Hasher;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;


/**
 * Service for:
 * GET     /seasons
 * POST    /seasons
 * GET     /seasons/{seasonID}
 * DELETE  /seasons/{seasonID}
 * PUT     /seasons/{seasonID}
 */

@Path( "/seasons" )
public class SeasonsService {

	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getSeasons() {
		Logger.log( "GET seasons called." );

		// series/{serieID}/seasons
		return Response.status( 409 ).build();
	}

	@POST
	@Consumes( MediaType.APPLICATION_XML )
	public Response addSeason( Season newSeason ) {
		Logger.log( "POST season called." );

		FileHandler<Seasons> filehandler = new FileHandler<Seasons>( Seasons.class );

		Seasons seasons = (Seasons) filehandler.readXML( "Database/Seasons.xml" );

		String id = "sn_" + Hasher.createHash( "test" ); //newSeason.getSeasonNumber() ); TODO: Series title + " " + season number

		List<Season> seasonsList = seasons.getSeason();

		for ( Season season : seasonsList ) {
			if ( season.getSeasonID().equals( id ) )
				return Response.status( 409 ).build();
		}

		newSeason.setSeasonID( id );

		seasonsList.add( newSeason );

		filehandler.writeXML( seasons, "Database/Seasons.xml" );

		URI location = null;
		try {
			location = new URI( RESTServerConfig.getServerURL() + "/seasons/" + id );
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return Response.created( location ).build();
	}

	@Path( "{seasonID}" )
	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getSeason( @PathParam( "seasonID" ) String id) {
		Logger.log( "GET season called." );

		FileHandler<Seasons> filehandler = new FileHandler<Seasons>( Seasons.class );

		Seasons seasons = (Seasons) filehandler.readXML( "Database/Seasons.xml" );

		List<Season> seasonsList = seasons.getSeason();

		for ( Season season : seasonsList ) {
			if ( season.getSeasonID().equals( id ) )
				return Response.ok().entity( season ).build();
		}

		return Response.status( 404 ).build();
	}

	@Path( "{seasonID}" )
	@PUT
	@Consumes( MediaType.APPLICATION_XML )
	public Response updateSeason( @PathParam( "seasonID" ) String id, Season newSeason ) {
		Logger.log( "PUT season called." );

		FileHandler<Seasons> filehandler = new FileHandler<Seasons>( Seasons.class );

		Seasons seasons = (Seasons) filehandler.readXML( "Database/Seasons.xml" );

		List<Season> seasonsList = seasons.getSeason();

		for ( Season season : seasonsList ) {
			if ( season.getSeasonID().equals( id ) ) {
				seasonsList.remove( season );
				seasonsList.add( newSeason );
				filehandler.writeXML( seasons, "Database/Seasons.xml" );

				return Response.ok().entity( newSeason ).build();
			}
		}

		return Response.status( 404 ).build();
	}

	@Path( "{seasonID}" )
	@DELETE
	public Response deleteSeason( @PathParam( "seasonID" ) String id ) {
		Logger.log( "DELETE season called." );

		FileHandler<Seasons> filehandler = new FileHandler<Seasons>( Seasons.class );

		Seasons seasons = (Seasons) filehandler.readXML( "Database/Seasons.xml" );

		List<Season> seasonsList = seasons.getSeason();

		for ( Season season : seasonsList ) {
			if ( season.getSeasonID().equals( id ) ) {
				seasonsList.remove( season );

				filehandler.writeXML( seasons, "Database/Seasons.xml" );

				return Response.noContent().build();
			}
		}

		return Response.status( 404 ).build();
	}

}
