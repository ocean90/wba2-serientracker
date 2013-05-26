package de.fhkoeln.gm.serientracker.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	private Hasher() {}

	public static String createHash( String value ) {
		return createHash( value, 8 );
	}

	/**
	 * http://stackoverflow.com/a/421696
	 * @param value
	 * @param length
	 * @return
	 */
	public static String createHash( String value, int length ) {
		String hash = null;

		if ( length <= 0 || length > 32 )
			length = 8;

		try {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( value.getBytes() );
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger( 1, digest );
			hash  = bigInt.toString( 16 );

			while ( hash.length() < 32 )
				hash = "0" + hash;

			hash = hash.substring( 0, length );
		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace();
		}

		return hash;
	}
}
