package minirestwebservice;

import java.util.List;

import jaxb.Serie;
import jaxb.Series;

import com.sun.jersey.api.client.*;

public class TestClient {
	public static void main( String[] args ) {

		String url = "http://localhost:4434/series/";
		System.out.println( "URL: " + url );

		WebResource wrs = Client.create().resource( url );

		Series rawSeries = (Series) wrs.accept( "application/xml" ).get( Series.class );

		List<Serie> series = rawSeries.getSerie();

		for ( Serie serie : series ) {
			System.out.printf( "ID: %s - Title: %s\n", serie.getSerieID(), serie.getTitle() );
		}

		System.out.println( wrs.accept( "application/xml" ).get( Series.class ) );

		/*String url2 = "http://localhost:4434/series/ss_ahu198j4";
		System.out.println( "URL: " + url2 );

		WebResource wrs2 = Client.create().resource( url2 );

		System.out.println( wrs2.accept( "application/xml" ).get( Serie.class ).getTitle() );

		String url3 = "http://localhost:4434/series/ss_a90fw3je";
		System.out.println( "URL: " + url3 );

		WebResource wrs3 = Client.create().resource( url3 );

		System.out.println( wrs3.accept( "application/xml" ).delete( String.class ) );*/
		
		String url4 = "http://localhost:4434/series/ss_ahu198j4/seasons/snojowd939/episodes/r28zehiq";
		System.out.println( "URL: " + url4 );

		WebResource wrs4 = Client.create().resource( url4 );

		System.out.println( wrs4.accept( "application/xml" ).get( String.class ) );
	}
}
