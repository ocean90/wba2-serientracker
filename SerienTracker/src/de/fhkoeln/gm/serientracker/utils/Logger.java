package de.fhkoeln.gm.serientracker.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Logger {
	public static boolean enabled = true;
	private Logger() {}

	public static void log( String message ) {
		if ( enabled )
			System.out.printf(
					"%s: %s - %s\n",
					getDateTime(),
					getMethodName(),
					message
			);
	}

	public static void err( String message ) {
		if ( enabled )
			System.err.printf(
					"%s: %s - %s\n",
					getDateTime(),
					getMethodName(),
					message
			);
	}

	private static String getDateTime() {
		return new SimpleDateFormat( "yyyy.MM.dd HH:mm:ss.SSS").format( Calendar.getInstance().getTime() );
	}

	private static String getMethodName() {
		// getMethodName - log - X
		return Thread.currentThread().getStackTrace()[ 3 ].getMethodName();
	}
}
