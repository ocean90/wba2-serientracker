package de.fhkoeln.gm.serientracker.webservice.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.fhkoeln.gm.serientracker.jaxb.User;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;

public class UsersDataHandler {
	private static final String USERSXMLPATH = "Database/users";

	private FileHandler<User> filehandler;

	private Map<String, User> users;

	public UsersDataHandler() {
		Logger.log( "UsersDataHandler called" );

		File folder = new File( USERSXMLPATH );
		if ( ! folder.exists() ) {
			Logger.err( "File error, dir doesn't exists" );
			return;
		}

		File[] files = folder.listFiles();
		this.filehandler = new FileHandler<User>( User.class );
		this.users = new HashMap<String, User>();

		for ( File file : files) {
			Logger.log( "Read file: " + file );

			if ( ! file.getAbsolutePath().contains( ".xml" ) )
				continue;

			User user = filehandler.readXML( file.getAbsolutePath() );

			this.users.put( user.getUserID(), user );
		}
	}

	public boolean UserExistsByID( String id ) {
		return users.containsKey( id );
	}

	public User getUserByID( String id ) {
		if ( ! this.UserExistsByID( id ) )
			return null;

		return this.users.get( id );
	}

	public boolean addUser( User newUser ) {
		String userFile = USERSXMLPATH + "/" + newUser.getUserID() + ".xml";
		Logger.log( "User created: " + userFile );

		return this.filehandler.writeXML( newUser, userFile );
	}

	public boolean removeUser( String id ) {
		if ( ! this.UserExistsByID( id ) )
			return false;

		String userFile = USERSXMLPATH + "/" + id + ".xml";
		File file = new File( userFile );

		if ( ! file.exists() )
			return false;

		if ( ! file.delete() )
			return false;

		return true;
	}
}
