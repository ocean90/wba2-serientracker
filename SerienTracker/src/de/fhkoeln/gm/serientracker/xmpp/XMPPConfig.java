package de.fhkoeln.gm.serientracker.xmpp;

/**
 * Holds the settings for the XMPP server
 *
 * @author Dominik Schilling
 */
public class XMPPConfig {
	// Hostname of the server
	public static String hostname = "localhost";

	// Port number of the server
	public static int port = 5222;

	// Private constructor
	private XMPPConfig() {}

	/**
	 * Returns the URL of the XMPP server.
	 *
	 * @return String
	 */
	public static String getServerURL() {
		return hostname + ":" + port;
	}
}
