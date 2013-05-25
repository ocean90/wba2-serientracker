package de.fhkoeln.gm.serientracker.xmpp;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class ConnectionHandler {

	// Save the connection
    private Connection cn;

    // Save the account manager
    private AccountManager ac;

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
    		ConnectionConfiguration config = new ConnectionConfiguration( hostname, port, "ST" );
    		cn = new XMPPConnection( config );
			cn.connect();
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
			cn.login( username, password );
		} catch ( XMPPException e ) {
			return false;
		}

    	return true;
    }

    /**
     * Returns the current logged in user.
     *
     * @return String
     */
    public String getUser() {
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
