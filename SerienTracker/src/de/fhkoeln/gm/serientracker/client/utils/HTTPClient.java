package de.fhkoeln.gm.serientracker.client.utils;

import java.io.IOException;
import java.net.Socket;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;

/**
 * HTTP wrapper for the REST API.
 *
 * @author Dominik Schilling
 */
public class HTTPClient {

	/**
	 * HTTP Methods: GET, POST, PUT, DELETE
	 */
	public enum HTTPMethod {
		GET, POST, PUT, DELETE;
	}

	private static final String RESTAPIROOT = RESTServerConfig.getServerURL();

	// Holds the last error message
	private String error;

	// Holds the HTTP methode
	private HTTPMethod method;

	// Holds the API endpoint
	private String endpoint;

	// Holds the accept type
	private String accept = MediaType.APPLICATION_XML;

	// Holds the request type
	private String type = MediaType.APPLICATION_XML;

	// Holds the entity
	private Object entity;

	// Holds the client instance
	private WebResource client;

	// Holds the server reponse
	private ClientResponse response;

	/**
	 * Constructor.
	 * Checks if the REST API is reachable.
	 */
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

	/**
	 * Sets the HTTP method.
	 *
	 * @param HTTPMethod method
	 */
	public void setMethod( HTTPMethod method ) {
		this.method = method;
	}

	/**
	 * Sets the API endpoint.
	 *
	 * @param String endpoint
	 */
	public void setEndpoint( String endpoint ) {
		this.endpoint = endpoint;
	}

	/**
	 * Sets the accept type.
	 *
	 * @param String accept
	 */
	public void setAccept( String accept ) {
		this.accept = accept;
	}

	/**
	 * Sets the request type.
	 *
	 * @param String type
	 */
	public void setType( String type ) {
		this.type = type;
	}

	/**
	 * Sets the entity.
	 *
	 * @param String type
	 */
	public void setEntity( Object entity ) {
		this.entity = entity;
	}

	/**
	 * Returns the latest error message.
	 *
	 * @return String
	 */
	public String getErrorMessage() {
		return this.error;
	}

	/**
	 * Checks if an error exists.
	 *
	 * @return boolean
	 */
	public boolean hasError() {
		return this.error != null;
	}

	/**
	 * Returns the server response.
	 *
	 * @return ClientResponse
	 */
	public ClientResponse getResponse() {
		return this.response;
	}

	/**
	 * Executes the HTTP request based on the HTTP methode.
	 */
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

	/**
	 * Returns the resource of the REST API.
	 *
	 * @return WebResource
	 */
	private WebResource getResource() {
		return client.path( this.endpoint );
	}

	/**
	 * Implements the HTTP GET method.
	 */
	private void get() {
		// Get the resource
		WebResource resource = this.getResource();

		Logger.log( "GET request for: " + resource.getURI().toString() );

		// Do the request and save the response
		ClientResponse _response = resource
				.accept( this.accept )
				.get( ClientResponse.class );

		// Check response status code
		if ( _response.getStatus() != 200 ) {
			Logger.err( "Request failed! HTTP code: " + _response.getStatus() );
			this.error = "Request failed with HTTP Code " + _response.getStatus();
			return;
		}

		// Set the response
		this.response = _response;
	}

	/**
	 * Implements the HTTP POST method.
	 */
	private void post() {
		// Get the resource
		WebResource resource = this.getResource();

		Logger.log( "POST request for: " + resource.getURI().toString() );

		// Do the request and save the response
		ClientResponse _response = resource
				.accept( this.accept )
				.type( this.type )
				.entity( this.entity )
				.post( ClientResponse.class );

		// Check response status code
		if ( _response.getStatus() != 201 ) {
			Logger.err( "Request failed! HTTP code: " + _response.getStatus() );
			this.error = "Request failed with HTTP Code " + _response.getStatus();
			return;
		}

		// Set the response
		this.response = _response;
	}

	/**
	 * Implements the HTTP PUT method.
	 */
	private void put() {
		// TODO
		return ;
	}

	/**
	 * Implements the HTTP DELETE method.
	 */
	private void delete() {
		// TODO
		return ;
	}

}
