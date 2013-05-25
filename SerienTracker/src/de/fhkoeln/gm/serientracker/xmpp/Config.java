package de.fhkoeln.gm.serientracker.xmpp;

public class Config {
	public static String hostname = "localhost";
	public static int port = 5222;

	private Config() {}

	public static String getServerURL() {
		return hostname + ":" + port;
	}
}
