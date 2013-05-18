package minirestwebservice;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import jaxb.Episode;
import jaxb.Episodes;
import jaxb.ObjectFactory;



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
			if ( episode.getEpisodeID().equals( id ) ) {
				return episode;
			}
		}

		return null;
	}

	@POST @Produces( "application/xml" )
	public String createSingleEpisode(
			@FormParam( "episodeNumber" ) int episodeNumber,
			@FormParam( "title" ) String title,
			@FormParam( "overview" ) String overview,
			@FormParam( "airdate" ) String airdate,
			@FormParam( "images" ) String images,
			@FormParam( "serieID" ) String serieID,
			@FormParam( "seasonID" ) String seasonID
			) throws JAXBException {

		Episode episode = new ObjectFactory().createEpisode();

		episode.setTitle( title );
		
		XMLGregorianCalendar newDate = null;
		try {
			newDate = DatatypeFactory.newInstance().newXMLGregorianCalendar( new GregorianCalendar() );
		} catch ( DatatypeConfigurationException e ) {
			e.printStackTrace();
		}
		episode.setAirdate( newDate );

		String hash = "";
		try {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( title.getBytes() );
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger( 1, digest );
			hash = bigInt.toString( 16 );
			while ( hash.length() < 32 ) {
				hash = "0" + hash;
			}
			hash = hash.substring(0, 8);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String id = "ep_" + hash;
		episode.setEpisodeID( id );
		
		episode.setEpisodeNumber( episodeNumber );
		episode.setOverview( overview );
		episode.setSerieID( serieID );
		episode.setSeasonID( seasonID );
		
		// images todo

		JAXBContext jaxbContext = JAXBContext.newInstance( Episodes.class );
		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Episodes rawEpisodes = (Episodes) unMarshaller.unmarshal( this.file );
		List<Episode> episodes = rawEpisodes.getEpisode();

		episodes.add( episode );

		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		this.marshaller.marshal( rawEpisodes, this.file );
		return "<id>" + id + "</id>";
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
			if ( episode.getEpisodeID().equals( id ) ) {
				episodes.remove( episode );
				this.marshaller.marshal( rawEpisodes, this.file );
				return "<success>1</success>";
			}
		}

		return "<success>0</success>";
	}
}
