package minirestwebservice;

import com.sun.jersey.api.client.*;

public class TestClient {
	public static void main( String[] args ) {

		String url = "http://localhost:4434/series/1";
		System.out.println( "URL: " + url );

		WebResource wrs = Client.create().resource( url );

		System.out.println( wrs.accept( "application/xml" ).get( String.class ) );
	}
}
