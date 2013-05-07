package minirestwebservice;

import java.io.File;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import jaxb.User;
import jaxb.Users;
import jaxb.ObjectFactory;

@Path( "/users" )
public class UsersService {

	private Unmarshaller unMarshaller;

	@GET @Produces( "application/xml" )
	public Users getAll() throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Users users = of.createUsers();

		JAXBContext jaxbContext = JAXBContext.newInstance( Users.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		users = (Users) unMarshaller.unmarshal( new File( "XML Examples/Users.xml" ) );

		return users;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public User getSingle(@PathParam("id") int id) throws JAXBException {
		ObjectFactory of = new ObjectFactory();

		Users users = of.createUsers();

		JAXBContext jaxbContext = JAXBContext.newInstance( Users.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		
		users = (Users) unMarshaller.unmarshal( new File( "XML Examples/Users.xml" ) );

		return users.getUser().get(id);
	}

}
