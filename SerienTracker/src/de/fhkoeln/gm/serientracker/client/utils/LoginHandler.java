package de.fhkoeln.gm.serientracker.client.utils;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import de.fhkoeln.gm.serientracker.jaxb.User;
import de.fhkoeln.gm.serientracker.utils.Hasher;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

public class LoginHandler {

	private String XMPPHostname;
	private int XMPPPort;
	private ConnectionHandler ch;
	private String username;
	private char[] password;
	private String error;

	public LoginHandler( String username, char[] password, String XMPPHostname, int XMPPPort ) {
		this.ch = ConnectionHandler.getInstance();
		this.XMPPHostname = XMPPHostname;
		this.XMPPPort = XMPPPort;
		this.username = username;
		this.password = password;
	}

	public void execute() {
		if ( this.login() ) {
			// Create a session
			SessionStore session = SessionStore.getInstance();
			// Fetch userdata
			User userdata = this.fetchUserData();

			if ( userdata == null ) {
				this.ch.disconnect();
				this.error = "Couldn't fetch userdata.";
				return;
			} else {
				session.setUser( userdata );
			}
		}
	}

	private boolean login() {
		// Try to connect to the server
		if ( ! this.ch.connect( XMPPHostname, XMPPPort ) ) {
			this.error = "Connection failed.";
			return false;
		}

		// Try to login
		if ( ! this.ch.login( username, password, "xmppclient" ) ) {
			this.error = "Login failed.";
			return false;
		}

		return true;
	}

	public String getErrorMessage() {
		return this.error;
	}

	public boolean hasError() {
		return this.error == null;
	}

	private User fetchUserData() {
		Logger.log( "Fetching userdata..." );

		String id = "us_" + Hasher.createHash( username );
		WebResource wrs = Client.create().resource( RESTServerConfig.getServerURL() + "/users/" + id );

		ClientResponse response = wrs
				.accept( MediaType.APPLICATION_XML )
				.get( ClientResponse.class );

		if ( response.getStatus() != 200 ) {
			Logger.err( "Failed: HTTP error code: " + response.getStatus() );
			return null;
		}

		User output = response.getEntity( User.class );
		Logger.log( "Userdata fetched for " + output.getFirstname() + " " + output.getLastname() );

		return output;
	}


}
