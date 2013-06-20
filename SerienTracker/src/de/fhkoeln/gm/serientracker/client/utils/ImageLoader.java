package de.fhkoeln.gm.serientracker.client.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import de.fhkoeln.gm.serientracker.utils.Hasher;
import de.fhkoeln.gm.serientracker.utils.Logger;


public class ImageLoader {

	private static ImageLoader instance;

	private HashMap<String, Image> cache = new HashMap<String, Image>();

	private ImageLoader(){}

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

	public Image loadImageFromUrl( String url, int width, int height ) {

		String cacheKey = Hasher.createHash( String.format( "%s:%d:%d", url, width, height ), 32 );

		if ( cache.containsKey( cacheKey ) ) {
			Logger.log( "Cached image found, key: " + cacheKey );
			return cache.get( cacheKey );
		}

		BufferedImage buffImage;
		try {
			buffImage = ImageIO.read( new URL( url ) );
		} catch ( Exception e ) {
			Logger.err( "Image couldn't be loaded: " + url );
			return null;
		}

		Image image = buffImage.getScaledInstance( width, height, Image.SCALE_SMOOTH );

		cache.put( cacheKey, image );

		return image;
	}
}
