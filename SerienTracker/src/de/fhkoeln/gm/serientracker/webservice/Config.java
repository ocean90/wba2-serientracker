package de.fhkoeln.gm.serientracker.webservice;

public class Config {
	public static String hostname = "http://localhost";
	public static int port = 1337;

	private Config() {}

	public static String getServerURL() {
		return hostname + ":" + port;
	}
}
