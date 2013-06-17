package de.fhkoeln.gm.serientracker.xmpp.utils;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import de.fhkoeln.gm.serientracker.utils.Logger;

public class ConnectionHandler {

	// Save the connection
	private Connection cn;

	// Save the account manager
	private AccountManager ac;

	// Save the pub sub handler
	private PubSubHandler psh;

	// Singleton class
	private static ConnectionHandler instance;

	private ConnectionHandler() {}

	/**
	 * Creates and returns the instance of this object.
	 *
	 * @return ConnectionHandler
	 */
	public static ConnectionHandler getInstance() {
		if ( instance == null )
			instance = new ConnectionHandler();

		return instance;
	}

	/**
	 * Returns the connection instance.
	 *
	 * @return Connection
	 */
	public Connection getConnection() {
		return this.cn;
	}

	/**
	 * Returns the PubSubHandler instance.
	 *
	 * @return PubSubHandler
	 */
	public PubSubHandler getPubSubHandler() {
		return this.psh;
	}

	/**
	 * Sets up a connection to the XMPP server.
	 *
	 * @param String hostname
	 * @param int port
	 * @return boolean
	 */
	public boolean connect( String hostname, int port ) {
		// Check if already connected
		if ( cn != null && cn.isConnected() )
			return true;

		try {
			//Connection.DEBUG_ENABLED = true;
			ConnectionConfiguration config = new ConnectionConfiguration( hostname, port );
			cn = new XMPPConnection( config );
			cn.connect();
			Logger.log( "Connection established" );
		} catch ( XMPPException e ) {
			return false;
		}

		ac = new AccountManager( cn );

		return true;
	}

	public void disconnect() {
		if ( cn != null && ! cn.isConnected() )
			return;

		cn.disconnect();
	}

	/**
	 * Returns connection status.
	 *
	 * @return boolean
	 */
	public boolean isConnected() {
		if ( cn != null )
			return this.cn.isConnected();

		return false;
	}

	/**
	 * Login.
	 *
	 * @param String username
	 * @param char[] password
	 * @param String resource
	 * @return boolean
	 */
	public boolean login( String username, char[] password, String resource ) {
		String _password = new String( password ); // Convert char array to string
		return this.login( username, _password, resource );
	}

	/**
	 * Login.
	 *
	 * @param String username
	 * @param String password
	 * @param String resource
	 * @return boolean
	 */
	public boolean login( String username, String password, String resource ) {
		try {
			SASLAuthentication.supportSASLMechanism( "PLAIN", 0 );
			this.cn.login( username, password, resource );
			Logger.log( "Login successful" );
		} catch ( XMPPException e ) {
			return false;
		}

		// Init the Pub Sub Manager
		this.psh = new PubSubHandler();

		return true;
	}

	/**
	 * Returns the current logged in user.
	 *
	 * @param
	 * @return String
	 */
	public String getJID( Boolean withResource ) {
		if ( cn == null )
			return null;

		if ( withResource == null || withResource ) {
			// Return user with resource name
			return cn.getUser();
		} else {
			// Return user without resource name
			String[] user = cn.getUser().split( "/" );

			return user[0];
		}
	}

	/**
	 * Returns a attribute of the current logged in user.
	 *
	 * @param String key
	 * @return String
	 */
	public String getAccountAttribute( String key ) {
		if ( ac == null )
			return null;

		return ac.getAccountAttribute( key );
	}
}
