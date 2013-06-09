package de.fhkoeln.gm.serientracker.xmpp.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import de.fhkoeln.gm.serientracker.jaxb.Message;
import de.fhkoeln.gm.serientracker.utils.Logger;

public class ItemEventCoordinator implements ItemEventListener<Item> {

	@Override
	public void handlePublishedItems( ItemPublishEvent<Item> items ) {
		Logger.log( "Items received: " + items.getItems().size() );

		for ( Item item : items.getItems() ) {
			@SuppressWarnings("unchecked")
			PayloadItem<SimplePayload> rawData = ( PayloadItem<SimplePayload> ) item;

			// Remove the <item> element wrapper and the namespace
			String xml = rawData.toXML()
					.replaceFirst( "<item id=''>", "")
					.replaceFirst( "</item>", "" )
					.replaceFirst( " xmlns=\"http://jabber.org/protocol/pubsub\"", "" );

			Message payload = null;
	    	try {
				JAXBContext jaxbContext = JAXBContext.newInstance( Message.class );
		    	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    	StringReader xmlString = new StringReader( xml );
		    	payload = (Message) unmarshaller.unmarshal( xmlString );
			} catch ( JAXBException e ) {
		    	Logger.err( "Payload Error" );
		    	e.printStackTrace();
			}

	    	if ( payload == null ) {
		    	Logger.err( "Payload Error" );
		    	break;
	    	}

	    	Logger.log( "Message Content: " + payload.getContent() );

		}
	}

}
