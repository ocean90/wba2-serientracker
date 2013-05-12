package minirestwebservice;

import java.io.File;
import java.util.List;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jaxb.Episode;
import jaxb.Episodes;
import jaxb.ObjectFactory;
import jaxb.Serie;
import jaxb.Series;


@Path( "/episodes" )
public class EpisodesService {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private final File file = new File( "XML Examples/Episodes.xml" );

	@GET @Produces( "application/xml" )
	public Episodes getAllEpisodes() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Episodes.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Episodes episodes = (Episodes) unMarshaller.unmarshal( this.file );

		return episodes;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public Episode getSingleEpisode(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Episodes.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Episodes rawEpisodes = (Episodes) unMarshaller.unmarshal( this.file );

		List<Episode> episodes = rawEpisodes.getEpisode();

		for ( Episode episode : episodes ) {
			if ( episode.getEpisodeID().intValue() == id ) {
				return episode;
			}
		}

		return null;
	}

	
	

//	@Path( "/{id}" )
//	@POST @Produces( "application/xml" )
//	public String createSingleEpisode(@PathParam("id") int id) throws JAXBException {
//		
//		Episode newEpisode = new Episode();
//				
//		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );
//
//		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
//		this.marshaller   = jaxbContext.createMarshaller(); // Writing
//		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//		Episodes rawEpisodes = (Episodes) unMarshaller.unmarshal( this.file );
//		
//		newEpisode.setTitle(value);
//		newEpisode.setSerieID(value);
//		newEpisode.setSeasonID(value);
//		newEpisode.setOverview(value);
//		newEpisode.setImages(value);
//		newEpisode.setEpisodeNumber(value);
//		newEpisode.setEpisodeID(value);
//		newEpisode.setAirdate(value);
//		
//	}

	@Path( "/{id}" )
	@DELETE @Produces( "application/xml" )
	public String deleteSingleEpisode(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Episodes.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		Episodes rawEpisodes = (Episodes) unMarshaller.unmarshal( this.file );

		List<Episode> episodes = rawEpisodes.getEpisode();

		for ( Episode episode : episodes ) {
			if ( episode.getEpisodeID().intValue() == id ) {
				episodes.remove( episode );
				this.marshaller.marshal( rawEpisodes, this.file );
				return "<success>1</success";
			}

		}

		return "<success>0</success";
	}
}
