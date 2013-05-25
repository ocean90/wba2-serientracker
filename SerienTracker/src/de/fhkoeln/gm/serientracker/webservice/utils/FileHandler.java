package de.fhkoeln.gm.serientracker.webservice.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileHandler<T> {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private Class<T> type;

	public FileHandler( Class<T> type ) {
		this.type = type;


		try {
			JAXBContext jaxbContext = JAXBContext.newInstance( this.type );

			this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading

			this.marshaller   = jaxbContext.createMarshaller(); // Writing
			this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public T readXML( String path ) {
		File file = new File( path );

		T content = null;
		try {
			content = (T) this.unMarshaller.unmarshal( file );
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}

		return content;
	}

	public void writeXML( T object, String path ) {
		File file = new File( path );

		try {
			this.marshaller.marshal( object, file );
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}
	}

}
