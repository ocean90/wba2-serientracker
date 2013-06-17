package de.fhkoeln.gm.serientracker.client.utils;

import de.fhkoeln.gm.serientracker.jaxb.User;


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

	public void setUser( User user ) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

}
