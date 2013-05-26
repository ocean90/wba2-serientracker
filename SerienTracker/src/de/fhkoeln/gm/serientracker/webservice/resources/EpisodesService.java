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

import de.fhkoeln.gm.serientracker.jaxb.Episode;
import de.fhkoeln.gm.serientracker.jaxb.Episodes;
import de.fhkoeln.gm.serientracker.utils.Hasher;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.Config;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;


/**
 * Service for:
 * GET     /episodes
 * POST    /episodes
 * GET     /episodes/{episodeID}
 * DELETE  /episodes/{episodeID}
 * PUT     /episodes/{episodeID}
 */

@Path( "/episodes" )
public class EpisodesService {

	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getEpisodes() {
		Logger.log( "GET episodes called." );

		// series/{serieID}/seasons/{seasonID}/episodes
		return Response.status( 409 ).build();
	}

	@POST
	@Consumes( MediaType.APPLICATION_XML )
	public Response addEpisode( Episode newEpisode ) {
		Logger.log( "POST episode called." );

		FileHandler<Episodes> filehandler = new FileHandler<Episodes>( Episodes.class );

		Episodes episodes = (Episodes) filehandler.readXML( "Database/Episodes.xml" );

		String id = "ep_" + Hasher.createHash( newEpisode.getTitle() );

		List<Episode> seasonsList = episodes.getEpisode();

		for ( Episode episode : seasonsList ) {
			if ( episode.getEpisodeID().equals( id ) )
				return Response.status( 409 ).build();
		}

		newEpisode.setEpisodeID( id );

		seasonsList.add( newEpisode );

		filehandler.writeXML( episodes, "Database/Episodes.xml" );

		URI location = null;
		try {
			location = new URI( Config.getServerURL() + "/episodes/" + id );
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return Response.created( location ).build();
	}

	@Path( "{episodeID}" )
	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Response getEpisode( @PathParam( "episodeID" ) String id) {
		Logger.log( "GET episode called." );

		FileHandler<Episodes> filehandler = new FileHandler<Episodes>( Episodes.class );

		Episodes episodes = (Episodes) filehandler.readXML( "Database/Episodes.xml" );

		List<Episode> episodesList = episodes.getEpisode();

		for ( Episode episode : episodesList ) {
			if ( episode.getEpisodeID().equals( id ) )
				return Response.ok().entity( episode ).build();
		}

		return Response.status( 404 ).build();
	}

	@Path( "{episodeID}" )
	@PUT
	@Consumes( MediaType.APPLICATION_XML )
	public Response updateEpisode( @PathParam( "episodeID" ) String id, Episode newEpisode ) {
		Logger.log( "PUT episode called." );

		FileHandler<Episodes> filehandler = new FileHandler<Episodes>( Episodes.class );

		Episodes episodes = (Episodes) filehandler.readXML( "Database/Episodes.xml" );

		List<Episode> episodesList = episodes.getEpisode();

		for ( Episode episode : episodesList ) {
			if ( episode.getEpisodeID().equals( id ) ) {
				episodesList.remove( episode );
				episodesList.add( newEpisode );
				filehandler.writeXML( episodes, "Database/Episodes.xml" );

				return Response.ok().entity( newEpisode ).build();
			}
		}

		return Response.status( 404 ).build();
	}

	@Path( "{episodeID}" )
	@DELETE
	public Response deleteEpisode( @PathParam( "episodeID" ) String id ) {
		Logger.log( "DELETE episode called." );

		FileHandler<Episodes> filehandler = new FileHandler<Episodes>( Episodes.class );

		Episodes episodes = (Episodes) filehandler.readXML( "Database/Episodes.xml" );

		List<Episode> episodesList = episodes.getEpisode();

		for ( Episode episode : episodesList ) {
			if ( episode.getEpisodeID().equals( id ) ) {
				episodesList.remove( episode );

				filehandler.writeXML( episodes, "Database/Episodes.xml" );

				return Response.noContent().build();
			}
		}

		return Response.status( 404 ).build();
	}

}
