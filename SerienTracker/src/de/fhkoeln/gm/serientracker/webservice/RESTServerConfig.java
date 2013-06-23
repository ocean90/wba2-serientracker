package de.fhkoeln.gm.serientracker.webservice;

/**
 * Holds the settings for the REST server
 *
 * @author Dominik Schilling
 */
public class RESTServerConfig {
	// Hostname of the server
	public static String hostname = "localhost";

	// Port number of the server
	public static int port = 1337;

	// Private constructor
	private RESTServerConfig() {}

	/**
	 * Returns the URL of the REST server.
	 *
	 * @return String
	 */
	public static String getServerURL() {
		return "http://" + hostname + ":" + port;
	}
}
