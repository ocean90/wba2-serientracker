package de.fhkoeln.gm.serientracker.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Logger {

	public enum ErrorType {
		 E_NONE( 0 ), E_ERROR( 1 ), E_ALL( 2 );

		 private int code;

		 private ErrorType( int c ) {
			 code = c;
		 }

		 public int getCode() {
			 return code;
		 }
	}

	public static ErrorType enabled = ErrorType.E_ALL;

	private Logger() {}

	public static void log( String message ) {
		if ( enabled.getCode() == 2 )
			System.out.printf(
					"%s: %s::%s() - %s\n",
					getDateTime(),
					getClassName(),
					getMethodName(),
					message
			);
	}

	public static void err( String message ) {
		if ( enabled.getCode() == 1 || enabled.getCode() == 2 )
			System.err.printf(
					"%s: %s::%s() - %s\n",
					getDateTime(),
					getClassName(),
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

	private static String getClassName() {
		// getMethodName - log - X
		return Thread.currentThread().getStackTrace()[ 3 ].getClassName();
	}
}
