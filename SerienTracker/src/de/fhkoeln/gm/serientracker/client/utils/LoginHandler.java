package de.fhkoeln.gm.serientracker.client.utils;

import de.fhkoeln.gm.serientracker.client.utils.HTTPClient.HTTPMethod;
import de.fhkoeln.gm.serientracker.jaxb.User;
import de.fhkoeln.gm.serientracker.utils.Hasher;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

/**
 * Wrapper class for the connection and login functions.
 *
 * @author Dominik Schilling
 */
public class LoginHandler {

	// Holds parameters
	private String XMPPHostname;
	private int XMPPPort;
	private String username;
	private char[] password;

	// Holds the connection instance
	private ConnectionHandler ch;

	// Holds the latest error message
	private String error;

	/**
	 * Constructor.
	 *
	 * @param String username
	 * @param char[] password
	 * @param String XMPPHostname
	 * @param int XMPPPort
	 */
	public LoginHandler( String username, char[] password, String XMPPHostname, int XMPPPort ) {
		// Get connection instance
		this.ch = ConnectionHandler.getInstance();

		// Set args
		this.XMPPHostname = XMPPHostname;
		this.XMPPPort = XMPPPort;
		this.username = username;
		this.password = password;
	}

	/**
	 * Starts the login mechanism.
	 */
	public void execute() {
		if ( this.login() ) {
			// Create a session
			SessionStore session = SessionStore.getInstance();
			// Fetch userdata
			User userdata = this.fetchUserData();

			if ( this.hasError() ) {
				this.ch.disconnect();
			} else {
				session.setUser( userdata );
			}
		} else {
			this.ch.disconnect();
		}
	}

	/**
	 * Perfoms the connection check and the login action.
	 *
	 * @return boolean
	 */
	private boolean login() {
		// Try to connect to the server
		if ( ! this.ch.connect( XMPPHostname, XMPPPort ) ) {
			this.error = "Connection failed.";
			return false;
		}

		// Try to login
		if ( ! this.ch.login( username, password, "trackerclient" ) ) {
			this.error = "Login failed.";
			return false;
		}

		return true;
	}

	/**
	 * Returns the latests error message.
	 *
	 * @return String
	 */
	public String getErrorMessage() {
		return this.error;
	}

	/**
	 * Checks if an error message exists.
	 */
	public boolean hasError() {
		return this.error != null;
	}

	/**
	 * Fetches the user data from the REST API.
	 *
	 * @return User
	 */
	private User fetchUserData() {
		Logger.log( "Fetching userdata..." );

		// Set the user id
		String id = "us_" + Hasher.createHash( username );

		// Make the request
		HTTPClient httpClient = new HTTPClient();
		httpClient.setMethod( HTTPMethod.GET );
		httpClient.setEndpoint( "/users/" + id );
		httpClient.execute();

		if ( httpClient.hasError() ) {
			Logger.err( "HTTPClient has returned an error" );

			// Check for status code, if 404 the user doesn't exists at REST side
			if ( httpClient.getResponse().getStatus() == 404 ) {
				this.error = "The userdata for user '" + username + "' doesn't exists!";
			} else {
				this.error = httpClient.getErrorMessage();
			}

			return null;
		}

		// Get the user object
		User user = httpClient.getResponse().getEntity( User.class );
		Logger.log( "Userdata fetched for " + user.getFirstname() + " " + user.getLastname() );

		return user;
	}

}
