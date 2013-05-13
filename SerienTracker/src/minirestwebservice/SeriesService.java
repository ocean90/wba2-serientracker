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

import jaxb.Genres;
import jaxb.ObjectFactory;
import jaxb.Serie;
import jaxb.Series;


@Path( "/series" )
public class SeriesService {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private final File file = new File( "XML Examples/Series.xml" );

	@GET @Produces( "application/xml" )
	public Series getAllSeries() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Series series = (Series) unMarshaller.unmarshal( this.file );

		return series;
	}
	
//	@GET @Produces( "application/xml" )
//	public Series getAllSeries(@QueryParam("genre") String genre) throws JAXBException {
//		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );
//		
//		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
//		Series genreSeries = (Series) unMarshaller.unmarshal( this.file );
//
//		
//		List<Serie> genreSeriesList = genreSeries.getSerie();
//		for ( Serie genreSerie : genreSerieList ) {
//			if ( genreSerie.getGenres.equals( genre ) ) {
//				return genreSerie;
//			} 
//
//		
//		return (Series) genreSeriesList;
//	}

	@POST @Produces( "application/xml" )
	public String createSingleSerie(
			@FormParam( "title" ) String title,
			@FormParam( "genres" ) String genres,
			@FormParam( "year" ) String year,
			@FormParam( "firstaired" ) String firstaired,
			@FormParam( "country" ) String country,
			@FormParam( "overview" ) String overview,
			@FormParam( "episoderuntime" ) String episoderuntime,
			@FormParam( "network" ) String network,
			@FormParam( "airday" ) String airday,
			@FormParam( "airtime" ) String airtime
			) throws JAXBException {

		Serie serie = new ObjectFactory().createSerie();

		serie.setTitle( title );
		//Genres _genres = new ObjectFactory().createGenres();
		//serie.setGenres(_genres);
		serie.setYear( Integer.valueOf( year ) );
		XMLGregorianCalendar now = null;
		try {
			now = DatatypeFactory.newInstance().newXMLGregorianCalendar( new GregorianCalendar() );
		} catch ( DatatypeConfigurationException e ) {
			e.printStackTrace();
		}
		serie.setFirstaired( now );

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

		String id = "ss_" + hash;
		serie.setSerieID( id );
		serie.setCountry( country );
		serie.setOverview( overview );
		serie.setEpisoderuntime( BigInteger.valueOf( Long.valueOf(episoderuntime ) ) );
		serie.setNetwork( network );
		serie.setAirday( airday );
		serie.setAirtime( now );

		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );
		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Series rawSeries = (Series) unMarshaller.unmarshal( this.file );
		List<Serie> series = rawSeries.getSerie();

		series.add( serie );

		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		this.marshaller.marshal( rawSeries, this.file );
		return "<id>" + id + "</id>";
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public Serie getSingleSerie(@PathParam("id") String id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Series rawSeries = (Series) unMarshaller.unmarshal( this.file );

		List<Serie> series = rawSeries.getSerie();

		for ( Serie serie : series ) {
			if ( serie.getSerieID().equals( id ) ) {
				return serie;
			}
		}

		return null;
	}

//	@Path( "/{id}" )
//	@POST @Produces( "application/xml" )
//	public String createSingleSerie(@PathParam("id") int id) throws JAXBException {
//
//		Serie newSerie = new Serie();
//
//		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );
//
//		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
//		this.marshaller   = jaxbContext.createMarshaller(); // Writing
//		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//		Series rawSeries = (Series) unMarshaller.unmarshal( this.file );
//
//
//
//		newSerie.setTitle(new Title());
//		newSerie.setGenres(createGenres());
//		newSerie.setYear(Integer value) ;
//		newSerie.setFirstaired(XMLGregorianCalendar value) ;
//		newSerie.setCountry(String value) ;
//		newSerie.setOverview(new Overview());
//		newSerie.setEpisoderuntime(BigInteger value) ;
//		newSerie.setNetwork(new Network) ;
//		newSerie.setAirday(new Airday());
//		newSerie.setAirtime(XMLGregorianCalendar value) ;
//		newSerie.setImages(Images value);
//		newSerie.setSeasons(Seasons value) ;
//		newSerie.setSerieID(BigInteger value);


	@Path( "/{id}" )
	@DELETE @Produces( "application/xml" )
	public String deleteSingleSerie(@PathParam("id") String id ) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		Series rawSeries = (Series) unMarshaller.unmarshal( this.file );

		List<Serie> series = rawSeries.getSerie();

		for ( Serie serie : series ) {
			if ( serie.getSerieID().equals( id ) ) {
				series.remove( serie );
				this.marshaller.marshal( rawSeries, this.file );
				return "<success>1</success>";
			}

		}

		return "<success>0</success>";
	}

}
