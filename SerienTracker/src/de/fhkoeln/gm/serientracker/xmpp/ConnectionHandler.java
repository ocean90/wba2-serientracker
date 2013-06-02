package de.fhkoeln.gm.serientracker.xmpp;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;

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

	/**
	 * Login.
	 *
	 * @param String username
	 * @param char[] password
	 * @return boolean
	 */
	public boolean login( String username, char[] password ) {
		String _password = new String( password ); // Convert char array to string
		return this.login( username, _password );
	}

	/**
	 * Login.
	 *
	 * @param String username
	 * @param char[] password
	 * @return boolean
	 */
	public boolean login( String username, String password ) {
		try {
			SASLAuthentication.supportSASLMechanism( "PLAIN", 0 );
			this.cn.login( username, password );
			Logger.log( "Login successful" );
		} catch ( XMPPException e ) {
			return false;
		}

		// Init the Pub Sub Manager
		this.psh = new PubSubHandler();

		String t = "testNode";

		this.psh.unsubscribeFromNode( t ); // TODO
		this.psh.subscribeToNode( t );

		return true;
	}

	// TODO
	public void testPubSub() {
		String t = "testNode";
		LeafNode node = this.psh.getNode( t );

		Logger.log( "Sending message..." );
		SimplePayload payload = new SimplePayload( "message", "",  "<message>test</message>");
		PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>( "message:" + System.currentTimeMillis(), payload );
		node.publish( item );
	}

	/**
	 * Returns the current logged in user.
	 *
	 * @return String
	 */
	public String getJID() {
		if ( cn == null )
			return null;

		return cn.getUser();
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
