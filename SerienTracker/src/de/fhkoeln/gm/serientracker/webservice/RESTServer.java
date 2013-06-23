package de.fhkoeln.gm.serientracker.webservice;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyServerFactory;

/**
 * The REST server.
 *
 * After starting the server it stops again when you press any key
 * in the console.
 *
 * @author Dominik Schilling
 */
public class RESTServer {

	/**
	 * Main Class.
	 *
	 * @param String[] args
	 */
	public static void main( String[] args ) {
		SelectorThread server = null;

		try {
			// Create the server instance
			server = GrizzlyServerFactory.create( RESTServerConfig.getServerURL() );
			System.out.println( "Service available at " + RESTServerConfig.getServerURL() );
			System.out.println( "Press any key to stop the service" );
			System.in.read();
		} catch ( Exception e ) {
		} finally {
			if ( server != null )
				server.stopEndpoint();
			System.out.println( "Service killed" );
		}

	}
}
