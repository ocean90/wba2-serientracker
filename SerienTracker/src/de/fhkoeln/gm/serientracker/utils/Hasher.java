package de.fhkoeln.gm.serientracker.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Simple MD5 hasher. Based on an idea of http://stackoverflow.com/a/421696.
 *
 * @author Dominik Schiling
 */
public class Hasher {
	// Private constructor
	private Hasher() {}

	/**
	 * Creates a MD5 hash of a string and returns the first 8 chars.
	 *
	 * @param String value
	 * @return String
	 */
	public static String createHash( String value ) {
		return createHash( value, 8 );
	}

	/**
	 * Creates a MD5 hash of a string Lets you specify the length of chars which
	 * should be returned.
	 *
	 * @param String value
	 * @param int length
	 * @return String
	 */
	public static String createHash( String value, int length ) {
		String hash = null;

		// Sanity check
		if ( length <= 0 || length > 32 )
			length = 8;

		// Create the hash
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
