package de.fhkoeln.gm.serientracker.webservice;

import java.io.File;
import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;


import com.sun.jersey.api.client.*;

import de.fhkoeln.gm.serientracker.jaxb.Serie;
import de.fhkoeln.gm.serientracker.jaxb.Series;

/**
 * Simple test client.
 *
 * @author Dominik Schilling and Dennis Meyer
 */
public class TestClient {
	private static String host = "http://localhost:1337";

	public static void main( String[] args ) throws InterruptedException {
		testGetSeries();
		Thread.sleep(500);
		System.out.println( "===================" );
		testGetSerie();
		Thread.sleep(500);
		System.out.println( "===================" );
		testPostSerie();
		Thread.sleep(500);
		System.out.println( "===================" );
		testPutSerie();
		Thread.sleep(500);
		System.out.println( "===================" );
		testDeleteSerie();
		Thread.sleep(500);
		System.out.println( "===================" );
		testGetSeaonsOfSerie();
		Thread.sleep(500);
		System.out.println( "===================" );
		testGetEpisodeOfSeasonOfSerie();
	}

	public static void testGetSeries() {
		String url = host + "/series/";
		System.out.println( "GET: " + url );

		WebResource wrs = Client.create().resource( url );

		Series rawSeries = (Series) wrs.accept( "application/xml" ).get( Series.class );

		List<Serie> series = rawSeries.getSerie();

		for ( Serie serie : series )
			System.out.printf( "ID: %s - Title: %s\n", serie.getSerieID(), serie.getTitle() );
	}

	public static void testGetSerie() {
		String url = host + "/series/ss_0001wade";
		System.out.println( "GET: " + url );

		WebResource wrs = Client.create().resource( url );

		ClientResponse response = wrs
				.accept( MediaType.APPLICATION_XML )
				.get( ClientResponse.class );

		if ( response.getStatus() != 200 ) {
			System.err.println( "Failed: HTTP error code: " + response.getStatus() );
			return;
		}

		Serie output = response.getEntity( Serie.class );
		System.out.println( "Output from Server..." );
		System.out.println( output.getTitle() );
	}

	public static void testPostSerie() {
		String url = host + "/series/";
		System.out.println( "POST: " + url );

		WebResource wrs = Client.create().resource( url );

		ClientResponse response = wrs
				.accept( MediaType.APPLICATION_XML )
				.type( MediaType.APPLICATION_XML )
				.entity( new File( "XML Examples/Serie2.xml" ) )
				.post( ClientResponse.class );

		if ( response.getStatus() != 201 ) {
			System.err.println( "Failed: HTTP error code: " + response.getStatus() );
			return;
		}

		URI output = response.getLocation();
		System.out.println( "Output from Server..." );
		System.out.println( output.toString() );
	}

	public static void testPutSerie() {
		String url = host + "/series/ss_1d9139d0";
		System.out.println( "PUT: " + url );

		WebResource wrs = Client.create().resource( url );

		ClientResponse response = wrs
				.accept( MediaType.APPLICATION_XML )
				.type( MediaType.APPLICATION_XML )
				.entity( new File( "XML Examples/Serie2b.xml" ) )
				.put( ClientResponse.class );

		if ( response.getStatus() != 204 ) {
			System.err.println( "Failed: HTTP error code: " + response.getStatus() );
			return;
		}

		String output = "Empty";//response.getEntity( String.class );
		System.out.println( "Output from Server..." );
		System.out.println( output );
	}

	public static void testDeleteSerie() {
		String url = host + "/series/ss_1d9139d0";
		System.out.println( "DELETE: " + url );

		WebResource wrs = Client.create().resource( url );

		ClientResponse response = wrs
				.accept( MediaType.APPLICATION_XML )
				.delete( ClientResponse.class );

		if ( response.getStatus() != 204 ) {
			System.err.println( "Failed: HTTP error code: " + response.getStatus() );
			return;
		}

		String output = "Empty";//response.getEntity( String.class );
		System.out.println( "Output from Server..." );
		System.out.println( output );
	}

	public static void testGetSeaonsOfSerie() {
		String url = host + "/series/ss_0001wade/seaons";
		System.out.println( "GET: " + url );

		WebResource wrs = Client.create().resource( url );

		ClientResponse response = wrs
				.accept( MediaType.APPLICATION_XML )
				.get( ClientResponse.class );

		if ( response.getStatus() != 200 ) {
			System.err.println( "Failed: HTTP error code: " + response.getStatus() );
			return;
		}

		String output = response.getEntity( String.class );
		System.out.println( "Output from Server..." );
		System.out.println( output );
	}

	public static void testGetEpisodeOfSeasonOfSerie() {
		String url = host + "/series/ss_k3bzetz5/seaons/sn_4601wdfe/episodes/ep_2ewdsfe5";
		System.out.println( "GET: " + url );

		WebResource wrs = Client.create().resource( url );

		ClientResponse response = wrs
				.accept( MediaType.APPLICATION_XML )
				.get( ClientResponse.class );

		if ( response.getStatus() != 200 ) {
			System.err.println( "Failed: HTTP error code: " + response.getStatus() );
			return;
		}

		String output = response.getEntity( String.class );
		System.out.println( "Output from Server..." );
		System.out.println( output );
	}
}

