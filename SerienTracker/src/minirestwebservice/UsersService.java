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

import jaxb.ObjectFactory;

import jaxb.User;
import jaxb.Users;

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
			if ( user.getUserID().equals(id) ) {
				return user;
			}
		}

		return null;
	}
	
	
	@POST @Produces( "application/xml" )
	public String createSingleUser(
			@FormParam( "username" ) String username,
			@FormParam( "lastname" ) String lastname,
			@FormParam( "firstname" ) String firstname,
			@FormParam( "gender" ) String gender,
			@FormParam( "age" ) Integer age,
			@FormParam( "location" ) String location,
			@FormParam( "about" ) String about,
			@FormParam( "joined" ) String joined,
			@FormParam( "avatar" ) String avatar,
			@FormParam( "admin" ) Boolean admin
			) throws JAXBException {

		User user = new ObjectFactory().createUser();

		user.setUsername( username );
		//Genres _genres = new ObjectFactory().createGenres();
		//serie.setGenres(_genres);
		user.setLastname( lastname );
		user.setFirstname( firstname );
		user.setGender(gender);
		user.setAge( age );
		user.setLocation (location );
		user.setAbout( about );
		user.setAvatar (avatar );
		user.setAdmin( admin );
		
		
		XMLGregorianCalendar now = null;
		try {
			now = DatatypeFactory.newInstance().newXMLGregorianCalendar( new GregorianCalendar() );
		} catch ( DatatypeConfigurationException e ) {
			e.printStackTrace();
		}

		String hash = "";
		try {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( username.getBytes() );
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

		String id = "us_" + hash;
		user.setUserID( id );
		user.setJoined( now );


		JAXBContext jaxbContext = JAXBContext.newInstance( Users.class );
		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Users rawUsers = (Users) unMarshaller.unmarshal( this.file );
		List<User> users = rawUsers.getUser();

		users.add( user );

		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		this.marshaller.marshal( rawUsers, this.file );
		return "<id>" + id + "</id>";
		
		//todo lists und subscription
	}


//	@Path( "/{id}" )
//	@PUT @Produces( "application/xml" )
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
			if ( user.getUserID().equals( id) ) {
				users.remove( user );
				this.marshaller.marshal( rawUsers, this.file );
				return "<success>1</success";
			}

		}

		return "<success>0</success";
	}

}
