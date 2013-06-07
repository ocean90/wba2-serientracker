package de.fhkoeln.gm.serientracker.webservice;

public class RESTServerConfig {
	public static String hostname = "http://localhost";
	public static int port = 1337;

	private RESTServerConfig() {}

	public static String getServerURL() {
		return hostname + ":" + port;
	}
}
