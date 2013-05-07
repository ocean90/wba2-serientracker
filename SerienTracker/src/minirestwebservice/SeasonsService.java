package minirestwebservice;

import java.io.File;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import jaxb.Season;
import jaxb.Seasons;
import jaxb.ObjectFactory;

@Path( "/seasons" )
public class SeasonsService {

	private Unmarshaller unMarshaller;

	@GET @Produces( "application/xml" )
	public Seasons getAll() throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Seasons seasons = of.createSeasons();

		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		seasons = (Seasons) unMarshaller.unmarshal( new File( "XML Examples/Seasons.xml" ) );

		return seasons;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public Season getSingle(@PathParam("id") int id) throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Seasons seasons = of.createSeasons();

		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		seasons = (Seasons) unMarshaller.unmarshal( new File( "XML Examples/Seasons.xml" ) );

		return seasons.getSeason().get(id);
	}

}
