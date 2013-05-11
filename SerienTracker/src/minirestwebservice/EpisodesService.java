package minirestwebservice;

import java.io.File;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import jaxb.Episode;
import jaxb.Episodes;
import jaxb.ObjectFactory;

@Path( "/episodes" )
public class EpisodesService {

	private Unmarshaller unMarshaller;

	@GET @Produces( "application/xml" )
	public Episodes getAll() throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Episodes episodes = of.createEpisodes();

		JAXBContext jaxbContext = JAXBContext.newInstance( Episodes.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		episodes = (Episodes) unMarshaller.unmarshal( new File( "XML Examples/Episodes.xml" ) );

		return episodes;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public Episode getSingle(@PathParam("id") int id) throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Episodes episodes = of.createEpisodes();

		JAXBContext jaxbContext = JAXBContext.newInstance( Episodes.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		episodes = (Episodes) unMarshaller.unmarshal( new File( "XML Examples/Episodes.xml" ) );

		return episodes.getEpisode().get(id);
	}

	  // Episode nach ID ändern:
//	   @POST @Path("/{id}")
//	   public Episode updateEpisodeById(
//	         @PathParam("episodeID")    String episodeID,
//	         @FormParam("episodeNumber")  Integer episodeNumber,
//	         @FormParam("title") String title,
//	         @FormParam("overview") String overview,
//	   		 @FormParam("dateTime") String airdate ,
//	   		 @FormParam("images")  images,
//	   		 @FormParam("serieID") String serieID,
//	   		 @FormParam("seasonID") String seasonID )
//	   {}
//	   http://www.torsten-horn.de/techdocs/jee-rest.htm#JaxRsHelloWorld-Grizzly


}
