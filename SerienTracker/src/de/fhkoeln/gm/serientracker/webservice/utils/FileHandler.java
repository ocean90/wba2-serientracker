package de.fhkoeln.gm.serientracker.webservice.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Provides an API to combine XML files with a JAXB object.
 * Set the typ param to the JAXB object.
 *
 * @author Dominik Schilling
 *
 * @param <T>
 */
public class FileHandler<T> {

	// Holds the marchaller instances
	private Unmarshaller unMarshaller;
	private Marshaller marshaller;

	// Holds the JAXB context
	private JAXBContext jaxbContext;

	// Holds the JAXB class
	private Class<T> type;

	// Should files be auto created if they doesn't exists?
	private boolean autoCreateFiles = true;

	/**
	 * Constructor.
	 *
	 * Sets the type, JAXB context and marshallers.
	 *
	 * @param Class<T> type
	 */
	public FileHandler( Class<T> type ) {
		this.type = type;

		try {
			// Init JAXB context
			this.jaxbContext = JAXBContext.newInstance( this.type );

			// Reading
			this.unMarshaller = this.jaxbContext.createUnmarshaller();

			// Writing
			this.marshaller = this.jaxbContext.createMarshaller();
			this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the autoCreateFiles variable. If true files will be created
	 * if not exists.
	 */
	public void setAutoCreateFiles( boolean value ) {
		this.autoCreateFiles = value;
	}

	/**
	 * Reads from a XML file.
	 *
	 * @param String path
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T readXML( String path ) {
		// Get the file
		File file = this.maybeCreateFile( path );

		// Check if file exits
		if ( file == null )
			return null;

		// Get the JAXB object of the file
		T content = null;
		try {
			content = (T) this.unMarshaller.unmarshal( file );
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}

		return content;
	}

	/**
	 * Checks if a file exists, if not it creates a new one if autoCreateFiles is true.
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

		// Check if files should be created
		if ( ! this.autoCreateFiles )
			return null;

		// File doesn't exist yet, create a new one with an enpty JAXB object
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
		// Get the file
		File file = this.maybeCreateFile( path );

		// Check if file exits
		if ( file == null )
			return false;

		// Write the JAXB object to XML file
		try {
			this.marshaller.marshal( object, file );
		} catch ( JAXBException e ) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

}
