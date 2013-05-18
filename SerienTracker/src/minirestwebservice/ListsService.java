package minirestwebservice;

import java.io.File;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jaxb.List;
import jaxb.Lists;
import jaxb.ObjectFactory;
import jaxb.User;
import jaxb.Users;

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

//	@Path( "/{id}" )
//	@GET @Produces( "application/xml" )
//	public List getSingle(@PathParam("id") int id) throws JAXBException {
//		ObjectFactory of = new ObjectFactory();
//
//		Lists lists = of.createLists();
//
//		JAXBContext jaxbContext = JAXBContext.newInstance( Lists.class );
//
//		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
//		lists = (Lists) unMarshaller.unmarshal( new File( "XML Examples/Lists.xml" ) );
//
//		return lists.getList().get(id);
//	}
	
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

	
	
//	@Path( "/{id}" )
//	@DELETE @Produces( "application/xml" )
//	public String deleteSingleList(@PathParam("id") int id) throws JAXBException {
//
//		JAXBContext jaxbContext = JAXBContext.newInstance( Lists.class );
//
//		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
//		this.marshaller   = jaxbContext.createMarshaller(); // Writing
//		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//		Lists rawLists = (Lists) unMarshaller.unmarshal( this.file );
//
//		List<List> lists = rawLists.getList();
//
//		for ( List list : lists ) {
//			if ( list.getListID().equals( id) ) {
//				lists.remove( list );
//				this.marshaller.marshal( rawLists, this.file );
//				return "<success>1</success";
//			}
//
//		}
//
//		return "<success>0</success";
//	}

	
}
