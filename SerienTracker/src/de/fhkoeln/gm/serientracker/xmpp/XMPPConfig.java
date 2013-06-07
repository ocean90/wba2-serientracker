package de.fhkoeln.gm.serientracker.xmpp;

public class XMPPConfig {
	public static String hostname = "localhost";
	public static int port = 5222;

	private XMPPConfig() {}

	public static String getServerURL() {
		return hostname + ":" + port;
	}
}
