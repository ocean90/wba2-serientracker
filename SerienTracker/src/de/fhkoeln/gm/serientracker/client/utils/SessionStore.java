package de.fhkoeln.gm.serientracker.client.utils;

import de.fhkoeln.gm.serientracker.jaxb.User;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;


public class SessionStore {

	// Singleton class
	private static SessionStore instance;

	private User user;

	private SessionStore() {}

	/**
	 * Creates and returns the instance of this object.
	 *
	 * @return SessionStore
	 */
	public static SessionStore getInstance() {
		if ( instance == null )
			instance = new SessionStore();

		return instance;
	}

	public void destroySession() {
		this.user = null;

		// Close the connection
		ConnectionHandler ch = ConnectionHandler.getInstance();
		ch.disconnect();
	}

	public void setUser( User user ) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public boolean UserIsAdmin() {
		return this.user.isAdmin();
	}

}
