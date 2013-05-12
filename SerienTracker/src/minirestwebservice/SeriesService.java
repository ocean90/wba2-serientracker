package minirestwebservice;

import java.io.File;
import java.util.List;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jaxb.Serie;
import jaxb.Series;


@Path( "/series" )
public class SeriesService {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private final File file = new File( "XML Examples/Series.xml" );

	@GET @Produces( "application/xml" )
	public Series getAll() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Series series = (Series) unMarshaller.unmarshal( this.file );

		return series;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public Serie getSingle(@PathParam("id") int id) throws JAXBException {

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

	@Path( "/{id}" )
	@DELETE @Produces( "application/xml" )
	public String deleteSingle(@PathParam("id") int id) throws JAXBException {

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
