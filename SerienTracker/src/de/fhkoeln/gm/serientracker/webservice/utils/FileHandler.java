package de.fhkoeln.gm.serientracker.webservice.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileHandler<T> {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private JAXBContext jaxbContext;
	private Class<T> type;

	public FileHandler( Class<T> type ) {
		this.type = type;

		try {
			// Init JAXB context
			this.jaxbContext = JAXBContext.newInstance( this.type );

			// Reading
			this.unMarshaller = this.jaxbContext.createUnmarshaller();

			// Writing
			this.marshaller   = this.jaxbContext.createMarshaller();
			this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads from a XML file.
	 *
	 * @param String path
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T readXML( String path ) {
		File file = this.maybeCreateFile( path );

		if ( file == null )
			return null;

		T content = null;
		try {
			content = (T) this.unMarshaller.unmarshal( file );
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}

		return content;
	}

	/**
	 * Checks if a file exists, if not it creates a new one.
	 *
	 * @param String path
	 * @return File
	 */
	public File maybeCreateFile( String path ) {
		if ( path == null )
			return null;

		// New file object
		File file = new File( path );

		// Check if file exists
		if ( file.exists() )
			return file;

		// File doesn't exist yet, create a new one
		try {
			file.createNewFile();
			this.writeXML( this.type.newInstance(), path );
		} catch ( Exception e ) {
			e.printStackTrace();

			return null;
		}

		return file;
	}

	/**
	 * Writes to a XML file.
	 *
	 * @param T object
	 * @param String path
	 * @return boolean
	 */
	public boolean writeXML( T object, String path ) {
		File file = this.maybeCreateFile( path );

		if ( file == null )
			return false;

		try {
			this.marshaller.marshal( object, file );
		} catch ( JAXBException e ) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

}
