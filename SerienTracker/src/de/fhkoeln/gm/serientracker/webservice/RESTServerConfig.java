package de.fhkoeln.gm.serientracker.webservice;

public class RESTServerConfig {
	public static String hostname = "localhost";
	public static int port = 1337;

	private RESTServerConfig() {}

	public static String getServerURL() {
		return "http://" + hostname + ":" + port;
	}
}
