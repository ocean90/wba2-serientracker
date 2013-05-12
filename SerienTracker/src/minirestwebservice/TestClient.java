package minirestwebservice;

import com.sun.jersey.api.client.*;

public class TestClient {
	public static void main( String[] args ) {

		String url = "http://localhost:4434/series/";
		System.out.println( "URL: " + url );

		WebResource wrs = Client.create().resource( url );

		System.out.println( wrs.accept( "application/xml" ).get( String.class ) );

		String url2 = "http://localhost:4434/series/1";
		System.out.println( "URL: " + url2 );

		WebResource wrs2 = Client.create().resource( url2 );

		System.out.println( wrs2.accept( "application/xml" ).get( String.class ) );

		String url3 = "http://localhost:4434/series/2";
		System.out.println( "URL: " + url3 );

		WebResource wrs3 = Client.create().resource( url3 );

		System.out.println( wrs3.accept( "application/xml" ).delete( String.class ) );
	}
}
