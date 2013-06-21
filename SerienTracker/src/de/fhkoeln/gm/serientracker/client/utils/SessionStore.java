package de.fhkoeln.gm.serientracker.client.utils;

import java.util.List;

import de.fhkoeln.gm.serientracker.jaxb.Setting;
import de.fhkoeln.gm.serientracker.jaxb.User;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;


public class SessionStore {

	// Store the user object
	private User user;

	// Prite constructor
	private SessionStore() {}

	// Singleton class
	private static SessionStore instance;

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

	/**
	 * Destroys the current session and disconnects the connetion.
	 */
	public void destroySession() {
		// Set user to null
		this.user = null;

		// Close the connection
		ConnectionHandler ch = ConnectionHandler.getInstance();
		ch.disconnect();
	}

	/**
	 * Add the logged in user to the session.
	 *
	 * @param User user
	 */
	public void setUser( User user ) {
		this.user = user;
	}

	/**
	 * Returns the current logged in user object.
	 *
	 * @return User
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Returns if the current logged in user is an admin.
	 *
	 * @return boolean
	 */
	public boolean UserIsAdmin() {
		return this.user.isAdmin();
	}

	/**
	 * Returns a user setting by key.
	 *
	 * @param String key
	 * @return String
	 */
	public String getUserSetting( String key ) {
		// Check if settings exist
		if ( this.user.getSettings() == null )
			return null;

		// Get the settings
		List<Setting> settings = this.user.getSettings().getSetting();

		// Check if the key is in the settings
		for ( Setting setting : settings ) {
			if ( setting.getKey().equals( key ) )
				return setting.getValue();
		}

		// Key not founded
		return null;
	}

}
