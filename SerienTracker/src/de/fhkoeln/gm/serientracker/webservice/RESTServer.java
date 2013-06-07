package de.fhkoeln.gm.serientracker.webservice;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyServerFactory;

public class RESTServer {
	public static void main( String[] args ) throws Exception {
		SelectorThread server = null;

		try {
			server = GrizzlyServerFactory.create( RESTServerConfig.getServerURL() );
			System.out.println( "Service available at " + RESTServerConfig.getServerURL() );
			System.out.println( "Press any key to stop the service" );
			System.in.read();
		} finally {
			if ( server != null )
				server.stopEndpoint();
			System.out.println( "Service killed" );
		}

	}
}
