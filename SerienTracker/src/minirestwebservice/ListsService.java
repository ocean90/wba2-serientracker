package minirestwebservice;

import java.io.File;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import jaxb.List;
import jaxb.Lists;
import jaxb.ObjectFactory;

@Path( "/lists" )
public class ListsService {

	private Unmarshaller unMarshaller;

	@GET @Produces( "application/xml" )
	public Lists getAll() throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Lists lists = of.createLists();

		JAXBContext jaxbContext = JAXBContext.newInstance( Lists.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		lists = (Lists) unMarshaller.unmarshal( new File( "XML Examples/Lists.xml" ) );

		return lists;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public List getSingle(@PathParam("id") int id) throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Lists lists = of.createLists();

		JAXBContext jaxbContext = JAXBContext.newInstance( Lists.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		lists = (Lists) unMarshaller.unmarshal( new File( "XML Examples/Lists.xml" ) );

		return lists.getList().get(id);
	}

}
