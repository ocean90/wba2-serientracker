package de.fhkoeln.gm.serientracker.webservice.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.fhkoeln.gm.serientracker.jaxb.Serie;
import de.fhkoeln.gm.serientracker.jaxb.Series;
import de.fhkoeln.gm.serientracker.webservice.Config;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;
import de.fhkoeln.gm.serientracker.webservice.utils.Hasher;


/**
 * Service for:
 * GET     /series
 * POST    /series
 * GET     /series/{serieID}
 * DELETE  /series/{serieID}
 * GET     /series/{serieID}/seaons
 * GET     /series/{serieID}/seaons/{seaonID}
 * GET     /series/{serieID}/seaons/{seaonID}/episodes
 * GET     /series/{serieID}/seaons/{seaonID}/episodes/{episodeID}
 */

@Path( "/series" )
public class SeriesService {

	@GET @Produces( MediaType.APPLICATION_XML )
	public Series getSeries() {

		FileHandler<Series> filehandler = new FileHandler<Series>( Series.class );

		Series series = (Series) filehandler.readXML( "Database/Series.xml" );

		return series;
	}

	@POST @Consumes( MediaType.APPLICATION_XML )
	public Response addSerie( Serie newSerie ) {

		FileHandler<Series> filehandler = new FileHandler<Series>( Series.class );

		Series series = (Series) filehandler.readXML( "Database/Series.xml" );

		String id = "ss_" + Hasher.createHash( newSerie.getTitle() );

		List<Serie> seriesList = series.getSerie();

		for ( Serie serie : seriesList ) {
			if ( serie.getSerieID().equals( id ) )
				return Response.status( 409 ).build();
		}

		newSerie.setSerieID( id );

		series.getSerie().add( newSerie );

		filehandler.writeXML( series, "Database/Series.xml" );

		URI location = null;
		try {
			location = new URI( Config.getServerURL() + "/series/" + id );
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return Response.created( location ).build();
	}

	@Path( "{serieID}" )
	@GET @Produces( MediaType.APPLICATION_XML )
	public Response getSerie( @PathParam( "serieID" ) String id) {

		FileHandler<Series> filehandler = new FileHandler<Series>( Series.class );

		Series series = (Series) filehandler.readXML( "Database/Series.xml" );

		List<Serie> seriesList = series.getSerie();

		for ( Serie serie : seriesList ) {
			if ( serie.getSerieID().equals( id ) )
				return Response.ok().entity( serie ).build();
		}

		return Response.status( 404 ).build();
	}

	@Path( "{serieID}" )
	@DELETE
	public Response deleteSerie( @PathParam( "serieID" ) String id ) {

		FileHandler<Series> filehandler = new FileHandler<Series>( Series.class );

		Series series = (Series) filehandler.readXML( "Database/Series.xml" );

		List<Serie> seriesList = series.getSerie();

		for ( Serie serie : seriesList ) {
			if ( serie.getSerieID().equals( id ) ) {
				seriesList.remove( serie );

				filehandler.writeXML( series, "Database/Series.xml" );

				return Response.noContent().build();
			}
		}

		return Response.status( 404 ).build();
	}

	@Path ( "{serieID}/seaons")
	@GET @Produces( MediaType.APPLICATION_XML )
	public Response getSeasonsOfSerie(
			@PathParam( "serieID" ) String serieID
		) {
		System.out.printf( "Serien ID: %s", serieID );

		return Response.noContent().build();
	}

	@Path ( "{serieID}/seaons/{seasonID}")
	@GET @Produces( MediaType.APPLICATION_XML )
	public Response getSeasonOfSerie(
			@PathParam( "serieID" ) String serieID,
			@PathParam( "seasonID" ) String seasonID
		) {
		System.out.printf( "Serien ID: %s | Season ID: %s\n", serieID, seasonID );

		return Response.noContent().build();
	}

	@Path ( "{serieID}/seaons/{seasonID}/episodes")
	@GET @Produces( MediaType.APPLICATION_XML )
	public Response getEpisodesOfSeasonOfSerie(
			@PathParam( "serieID" ) String serieID,
			@PathParam( "seasonID" ) String seasonID
		) {
		System.out.printf( "Serien ID: %s | Season ID: %s\n", serieID, seasonID );

		return Response.noContent().build();
	}

	@Path ( "{serieID}/seaons/{seasonID}/episodes/{episodeID}")
	@GET @Produces( MediaType.APPLICATION_XML )
	public Response getEpisodeOfSeasonOfSerie(
			@PathParam( "serieID" ) String serieID,
			@PathParam( "seasonID" ) String seasonID,
			@PathParam( "episodeID" ) String episodeID
		) {
		System.out.printf( "Serien ID: %s | Season ID: %s | Episode ID: %s\n", serieID, seasonID, episodeID );

		return Response.noContent().build();
	}

}
