package de.fhkoeln.gm.serientracker.webservice.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.fhkoeln.gm.serientracker.jaxb.User;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;

/**
 * Provides the data handler for the user resource.
 *
 * @author Dominik Schilling
 */
public class UsersDataHandler {
	// Path to the user files
	private static final String USERSXMLPATH = "Database/users";

	// Holds the file handler instance
	private FileHandler<User> filehandler;

	// Holds the user object in a map
	private Map<String, User> users;

	/**
	 * Constructor.
	 *
	 * Reads files of the path specified by USERSXMLPATH and saves them into
	 * the users map.
	 */
	public UsersDataHandler() {
		Logger.log( "UsersDataHandler called" );

		// Check the folder
		File folder = new File( USERSXMLPATH );
		if ( ! folder.exists() ) {
			Logger.err( "Fir doesn't exists: " + USERSXMLPATH );
			return;
		}

		// Get the files inside the folder
		File[] files = folder.listFiles();

		// Get the file handler instance
		this.filehandler = new FileHandler<User>( User.class );

		// Set the user map
		this.users = new HashMap<String, User>();

		// Parse each file
		for ( File file : files) {
			Logger.log( "Read file: " + file );

			// Sanity check: Is it a XML file?
			if ( ! file.getAbsolutePath().contains( ".xml" ) )
				continue;

			// Get the user object of the XML
			User user = filehandler.readXML( file.getAbsolutePath() );

			// Save the object into the users map
			this.users.put( user.getUserID(), user );
		}
	}

	/**
	 * Checks if a user exists.
	 *
	 * @param String
	 * @return boolean
	 */
	public boolean UserExistsByID( String id ) {
		return users.containsKey( id );
	}

	/**
	 * Returns a user by id.
	 *
	 * @param String id
	 * @return User
	 */
	public User getUserByID( String id ) {
		// Check if user exists
		if ( ! this.UserExistsByID( id ) )
			return null;

		// Return the user
		return this.users.get( id );
	}

	/**
	 * Crates a new user object and saves it into a XML file.
	 *
	 * @param User newUser
	 * @return boolean
	 */
	public boolean addUser( User newUser ) {
		// Set the file path and name
		String userFile = USERSXMLPATH + "/" + newUser.getUserID() + ".xml";
		Logger.log( "New user file: " + userFile );

		// Write user to file
		return this.filehandler.writeXML( newUser, userFile );
	}

	/**
	 * Removes a user and delets its user file.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean removeUser( String id ) {
		// Check if user exists
		if ( ! this.UserExistsByID( id ) )
			return false;

		// Set the file path and name
		String userFile = USERSXMLPATH + "/" + id + ".xml";
		File file = new File( userFile );

		//  Check if file exists
		if ( ! file.exists() )
			return false;

		// Delete the file
		if ( ! file.delete() )
			return false;

		return true;
	}
}
