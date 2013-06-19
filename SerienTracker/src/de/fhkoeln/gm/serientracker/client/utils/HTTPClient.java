package de.fhkoeln.gm.serientracker.client.utils;

import java.io.IOException;
import java.net.Socket;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;

public class HTTPClient {

	public enum HTTPMethod {
		GET, POST, PUT, DELETE;
	}

	private static final String RESTAPIROOT = RESTServerConfig.getServerURL();

	private String error;

	private HTTPMethod method;
	private String endpoint;
	private String accept = MediaType.APPLICATION_XML;
	private String type = MediaType.APPLICATION_XML;

	private WebResource client;
	private ClientResponse response;

	public HTTPClient() {
		Logger.log( "HTTPClient started" );

		// Check if REST API is reachable
		// Source: http://stackoverflow.com/a/3584332
		Socket socket = null;
		boolean reachable = false;
		try {
		    socket = new Socket( RESTServerConfig.hostname, RESTServerConfig.port );
		    reachable = true;
		} catch ( IOException e ) {}
		finally {
		    if ( socket != null ) {
		    	try {
		    		socket.close();
		    	} catch( IOException e ) {}
		    }
		}

		if ( reachable ) {
			this.client = Client.create().resource( RESTAPIROOT );
		} else {
			Logger.err( String.format( "REST server '%s' is not reachable", RESTAPIROOT ) );
			this.error = "The REST server is not reachable!";

		}

	}

	public void setMethod( HTTPMethod method ) {
		this.method = method;
	}

	public void setEndpoint( String endpoint ) {
		this.endpoint = endpoint;
	}

	public void setAccept( String accept ) {
		this.accept = endpoint;
	}

	public void setType( String type ) {
		this.type = type;
	}

	public String getErrorMessage() {
		return this.error;
	}

	public boolean hasError() {
		return this.error != null;
	}

	public ClientResponse getResponse() {
		return this.response;
	}

	public void execute() {
		if ( this.hasError() )
			return;

		switch ( this.method ) {
			case DELETE:
				this.delete();
				break;
			case GET:
				this.get();
				break;
			case POST:
				this.post();
				break;
			case PUT:
				this.put();
				break;
			default:
				this.error = "Method is not supported!";
				break;
		}
	}

	private WebResource getResource() {
		return client.path( this.endpoint );
	}

	private void get() {
		WebResource resource = this.getResource();

		Logger.log( "GET request for: " + resource.getURI().toString() );

		ClientResponse response = resource
				.accept( this.accept )
				.get( ClientResponse.class );

		if ( response.getStatus() != 200 ) {
			Logger.err( "Request failed! HTTP code: " + response.getStatus() );
			this.error = "Request failed with HTTP Code " + response.getStatus();
			return;
		}

		this.response = response ;
	}

	private void post() {
		// TODO
		return ;
	}

	private void put() {
		// TODO
		return ;
	}

	private void delete() {
		// TODO
		return ;
	}


}
