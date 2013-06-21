package de.fhkoeln.gm.serientracker.client.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import de.fhkoeln.gm.serientracker.jaxb.Message;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class NotificationListener implements ItemEventListener<Item> {

	@SuppressWarnings("unchecked")
	@Override
	public void handlePublishedItems( ItemPublishEvent<Item> notifications ) {
		Logger.log( "Notification received" );
		Logger.log( "Published Date: " + notifications.getPublishedDate() );
		Logger.log( "Delayed: " + notifications.isDelayed() );
		Logger.log( "Node ID: " + notifications.getNodeId() );


		// Check if it's a delayed notifcation, if true
		// ignore it.
		if ( notifications.isDelayed() )
			return;

		ConnectionHandler cn = ConnectionHandler.getInstance();
		PubSubHandler psh = cn.getPubSubHandler();
		LeafNode node = psh.getNode( notifications.getNodeId() );

		for ( Item notification : notifications.getItems() ) {
			String rawXML = ((PayloadItem<SimplePayload>) notification).getPayload().toXML();

			// Remove the the namespace
			String xml = rawXML.replaceFirst( " xmlns=\"http://jabber.org/protocol/pubsub\"", "" );

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

	    	/* Doesn't work: item-not-found(404)
	    	try {
				node.deleteItem( notification.getId() );
				Logger.log( "Item deleted: " + notification.getId() );
			} catch ( XMPPException e ) {
				Logger.err( "Couldn't delete item: " + notification.getId() );
				e.printStackTrace();
			}
			*/

	    	Logger.log( "Message Content: " + payload.getContent() );
		}
	}

}
