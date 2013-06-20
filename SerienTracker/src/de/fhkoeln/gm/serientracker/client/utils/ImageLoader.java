package de.fhkoeln.gm.serientracker.client.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import de.fhkoeln.gm.serientracker.utils.Hasher;
import de.fhkoeln.gm.serientracker.utils.Logger;

/**
 * Singleton class which can load an image from an URL.
 * Has a built-in cache.
 *
 * @author Dominik Schilling
 */
public class ImageLoader {

	// Internal cache
	private HashMap<String, Image> cache = new HashMap<String, Image>();

	// Singleton class
	private static ImageLoader instance;

	// Private constructor, call getInstance()
	private ImageLoader() {}

	/**
	 * Creates and returns the instance of this object.
	 *
	 * @return ImageLoader
	 */
	public static ImageLoader getInstance() {
		if ( instance == null )
			instance = new ImageLoader();

		return instance;
	}

	/**
	 * Loads images from an URL. Checks if it's already in cache.
	 * Cache key is url:width:height.
	 *
	 * @param String url
	 * @param int width
	 * @param int height
	 * @return Image
	 */
	public Image loadImageFromUrl( String url, int width, int height ) {
		// Set cache key
		String cacheKey = Hasher.createHash( String.format( "%s:%d:%d", url, width, height ), 32 );

		// Check if image exists in cache
		if ( cache.containsKey( cacheKey ) ) {
			Logger.log( "Cached image found, key: " + cacheKey );
			return cache.get( cacheKey );
		}

		// Load the image
		BufferedImage buffImage;
		try {
			buffImage = ImageIO.read( new URL( url ) );
		} catch ( Exception e ) {
			Logger.err( "Image couldn't be loaded: " + url );
			return null;
		}

		// Scale the image
		Image image = buffImage.getScaledInstance( width, height, Image.SCALE_SMOOTH );

		// Put the image into the cache
		cache.put( cacheKey, image );

		// Return the scaled and cached image
		return image;
	}
}
