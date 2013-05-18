package minirestwebservice;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import jaxb.ObjectFactory;
import jaxb.Season;
import jaxb.Seasons;


@Path( "/seasons" )
public class SeasonsService {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private final File file = new File( "XML Examples/Series.xml" );

	@GET @Produces( "application/xml" )
	public Seasons getAllSeasons() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Seasons seasons = (Seasons) unMarshaller.unmarshal( this.file );

		return seasons;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public Season getSingleSeason(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Seasons rawSeasons = (Seasons) unMarshaller.unmarshal( this.file );

		List<Season> seasons = rawSeasons.getSeason();

		for ( Season season : seasons ) {
			if ( season.getSeasonID().equals( id ) ) {
				return season;
			}
		}

		return null;
	}

	
	@POST @Produces( "application/xml" )
	public String createSingleSeason(
			@FormParam( "seasonNumber" ) Integer seasonNumber,
			@FormParam( "serieID" ) String serieID
			) throws JAXBException {

		Season season = new ObjectFactory().createSeason();

		String hash = "";
		try {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( serieID.getBytes() );
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

		String id = "se_" + hash;
		season.setSeasonID( id );
		
		season.setSeasonNumber( seasonNumber );

		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );
		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Seasons rawSeasons = (Seasons) unMarshaller.unmarshal( this.file );
		List<Season> seasons = rawSeasons.getSeason();

		seasons.add( season );

		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		this.marshaller.marshal( rawSeasons, this.file );
		return "<id>" + id + "</id>";
		
		//todo images und episodes
	}
	
	
//	@Path( "/{id}" )
//	@POST @Produces( "application/xml" )
//	public String createSingleSeason(@PathParam("id") int id) throws JAXBException {
//
//		Season newSeason = new Season();
//
//		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );
//
//		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
//		this.marshaller   = jaxbContext.createMarshaller(); // Writing
//		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//		Series rawSeries = (Series) unMarshaller.unmarshal( this.file );
//
//
//
//		newSeason.setSerieID(value);
//		newSeason.setSeasonNumber(value);
//		newSeason.setSeasonID(value);
//		newSeason.setImages(value);
//		newSeason.setEpisodes(value);
//	}
//
	
	
	@Path( "/{id}" )
	@DELETE @Produces( "application/xml" )
	public String deleteSingleSeason(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		Seasons rawSeasons = (Seasons) unMarshaller.unmarshal( this.file );

		List<Season> seasons = rawSeasons.getSeason();

		for ( Season season : seasons ) {
			if ( season.getSeasonID().equals( id ) ) {
				seasons.remove( season );
				this.marshaller.marshal( rawSeasons, this.file );
				return "<success>1</success>";
			}

		}

		return "<success>0</success>";
	}


}
