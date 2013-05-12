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
import jaxb.User;
import jaxb.Users;
import jaxb.ObjectFactory;

@Path( "/users" )
public class UsersService {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private final File file = new File( "XML Examples/Series.xml" );

	@GET @Produces( "application/xml" )
	public Users getAllUsers() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Users.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Users users = (Users) unMarshaller.unmarshal( this.file );

		return users;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public User getSingle(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Users.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Users rawUsers = (Users) unMarshaller.unmarshal( this.file );

		List<User> users = rawUsers.getUser();

		for ( User user : users ) {
			if ( user.getUserID().intValue() == id ) {
				return user;
			}
		}

		return null;
	}

	
//	@Path( "/{id}" )
//	@POST @Produces( "application/xml" )
//	public String createSingleUser(@PathParam("id") int id) throws JAXBException {
//		
//		User newUser = new User();
//				
//		JAXBContext jaxbContext = JAXBContext.newInstance( Users.class );
//
//		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
//		this.marshaller   = jaxbContext.createMarshaller(); // Writing
//		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//		Users rawUsers = (Users) unMarshaller.unmarshal( this.file );
//		
//		newUser.setUsername(new Username());
//		newUser.setUserID(value);
//		newUser.setLocation(new Location());
//		newUser.setLastname(new Lastname);
//		newUser.setJoined(new Date());
//		newUser.setGender(new Gender());
//		newUser.setFirstname(new Firstname());
//		newUser.setAvatar(new Avataer());
//		newUser.setAge(new Age());
//		newUser.setAdmin(value);
//		newUser.setAbout(new About());
//	
//		
//	}
		
	
	@Path( "/{id}" )
	@DELETE @Produces( "application/xml" )
	public String deleteSingleUser(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Users.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		Users rawUsers = (Users) unMarshaller.unmarshal( this.file );

		List<User> users = rawUsers.getUser();

		for ( User user : users ) {
			if ( user.getUserID().intValue() == id ) {
				users.remove( user );
				this.marshaller.marshal( rawUsers, this.file );
				return "<success>1</success";
			}

		}

		return "<success>0</success";
	}
	
}
