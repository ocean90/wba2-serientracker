package minirestwebservice;

import java.io.File;
import javax.ws.rs.*;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import jaxb.Episode;
import jaxb.Episodes;
import jaxb.Season;
import jaxb.Seasons;

@Path ( "/" )
public class SeriesSeasonsEpisodesService {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private final File file = new File( "XML Examples/Series.xml" );

	@Path ( "series/{id}/seasons")
	@GET @Produces( "application/xml" )
	public Seasons getAllSeasonsOfASerie() throws JAXBException {
		return null;
	}

	@Path ( "series/{serieId}/seasons/{seasonId}")
	@GET @Produces( "application/xml" )
	public Season getOneSeasonsOfASerie() throws JAXBException {
		return null;
	}

	@Path ( "series/{serieId}/seasons/{seasonId}/episodes")
	@GET @Produces( "application/xml" )
	public Episodes getAllEpisodesOfASeasonOfASerie() throws JAXBException {
		return null;
	}
	
	@Path ( "series/{serieId}/seasons/{seasonId}/episodes/{episodeId}")
	@GET @Produces( "application/xml" )
	public Episode getOneEpisodeOfASeasonOfASerie(
			@PathParam("serieId") String serieId,
			@PathParam("seasonId") String seasonId,
			@PathParam("episodeId") String episodeId
		) throws JAXBException {
		System.out.printf( "Serien ID: %s | Season ID: %s | Episoden ID: %s", serieId, seasonId, episodeId);
		return null;
	}


}
