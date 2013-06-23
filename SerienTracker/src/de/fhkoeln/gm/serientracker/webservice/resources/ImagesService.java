package de.fhkoeln.gm.serientracker.webservice.resources;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import de.fhkoeln.gm.serientracker.utils.Logger;


/**
 * Service for:
 * GET     /images/{id}/{filename}.jpg
 */

@Path( "/images" )
public class ImagesService {

	@Path( "{id}/{file}.jpg" )
	@GET
	@Produces( "image/jpeg" )
	public Response getImage( @PathParam( "id" ) String id, @PathParam( "file" ) String filename ) {
		Logger.log( id + "/" + filename + ".jpg" );

		String root = "Database/images";
		File file = new File( root + "/" + id + "/" + filename + ".jpg" );

		if ( ! file.exists() )
			return Response.status( 404 ).build();

		// Source: http://stackoverflow.com/a/9204824
		BufferedImage image;
		try {
			image = ImageIO.read( file );
		} catch ( IOException e ) {
			e.printStackTrace();
			return Response.status( 500 ).build();
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write( image, "jpg", baos );
		} catch ( IOException e ) {
			e.printStackTrace();
			return Response.status( 500 ).build();
		}
		byte[] imageData = baos.toByteArray();

		return Response.ok().entity( imageData ).build();
	}
}
