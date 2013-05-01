package minirestwebservice;

import java.io.File;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import jaxb.series.*;

@Path( "/series/" )
public class SeriesService {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;

	@GET @Produces( "application/xml" )
	public Series getAll() throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Series series = of.createSeries();

		JAXBContext jaxbContext = JAXBContext.newInstance( Series.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading

		series = (Series) unMarshaller.unmarshal( new File( "XML Examples/Series.xml" ) );

		return series;
	}
}