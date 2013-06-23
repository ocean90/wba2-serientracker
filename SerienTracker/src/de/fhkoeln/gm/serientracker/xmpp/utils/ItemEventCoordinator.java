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

/**
 * Item event listener for published items.
 *
 * Just prints the message content to the console log.
 *
 * @author Dominik Schilling
 */
public class ItemEventCoordinator implements ItemEventListener<Item> {

	/**
	 * The handler for published items.
	 */
	@Override
	public void handlePublishedItems( ItemPublishEvent<Item> items ) {
		Logger.log( "Items received: " + items.getItems().size() );

		for ( Item item : items.getItems() ) {
			@SuppressWarnings("unchecked")
			String rawXML = ((PayloadItem<SimplePayload>) item).getPayload().toXML();

			// Remove the the namespace
			String xml = rawXML.replaceFirst( " xmlns=\"http://jabber.org/protocol/pubsub\"", "" );

			// Get a JAXB object of the XML data.
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
