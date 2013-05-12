package minirestwebservice;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import jaxb.Images;
import jaxb.Seasons;
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

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public Serie getSingleSerie(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Series rawSeries = (Series) unMarshaller.unmarshal( this.file );

		List<Serie> series = rawSeries.getSerie();

		for ( Serie serie : series ) {
			if ( serie.getSerieID().intValue() == id ) {
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
	public String deleteSingleSerie(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		Series rawSeries = (Series) unMarshaller.unmarshal( this.file );

		List<Serie> series = rawSeries.getSerie();

		for ( Serie serie : series ) {
			if ( serie.getSerieID().intValue() == id ) {
				series.remove( serie );
				this.marshaller.marshal( rawSeries, this.file );
				return "<success>1</success";
			}

		}

		return "<success>0</success";
	}

}
