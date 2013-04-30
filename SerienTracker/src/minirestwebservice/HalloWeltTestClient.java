package minirestwebservice;

import com.sun.jersey.api.client.*;

public class HalloWeltTestClient {
	public static void main( String[] args ) {

		String url = "http://localhost:4434/helloworld?name=Dennis";
		System.out.println( "URL: " + url );

		WebResource wrs = Client.create().resource( url );

		System.out.println( "\nTextausgabe:" );
		System.out.println( wrs.accept( "text/plain" ).get( String.class ) );
		System.out.println( "\nHTML-Ausgabe:" );
		System.out.println( wrs.accept( "text/html"  ).get( String.class ) );
	}
}
