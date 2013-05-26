package de.fhkoeln.gm.serientracker.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Logger {
	public static boolean enabled = true;
	private Logger() {}

	public static void log( String message ) {
		if ( enabled )
			System.out.printf( "%s: " + message + "\n", getDateTime() );
	}

	public static void err( String message ) {
		if ( enabled )
			System.err.printf( "%s: " + message + "\n", getDateTime() );
	}

	public static String getDateTime() {
		return new SimpleDateFormat( "yyyy.MM.dd HH:mm:ss.SSS").format( Calendar.getInstance().getTime() );
	}
}
