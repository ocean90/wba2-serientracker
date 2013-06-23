package de.fhkoeln.gm.serientracker.client.utils;

import java.io.StringReader;

import javax.swing.SwingUtilities;
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

import de.fhkoeln.gm.serientracker.client.gui.NotificationFrame;
import de.fhkoeln.gm.serientracker.jaxb.Message;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

/**
 * The event listener for receiving node items.
 *
 * @author Dominik Schilling
 */
public class NotificationListener implements ItemEventListener<Item> {

	// Holds the payload
	private Message payload;

	/**
	 * Called when an item is published to a node.
	 */
	@Override
	public void handlePublishedItems( ItemPublishEvent<Item> notifications ) {
		Logger.log( "Notification received" );
		Logger.log( "Published Date: " + notifications.getPublishedDate() );
		Logger.log( "Delayed: " + notifications.isDelayed() );
		Logger.log( "Node ID: " + notifications.getNodeId() );

		// Check if it's a delayed notifcation, if true ignore the item.
		if ( notifications.isDelayed() )
			return;

		// Get the node of the item
		ConnectionHandler cn = ConnectionHandler.getInstance();
		PubSubHandler psh = cn.getPubSubHandler();
		LeafNode node = psh.getNode( notifications.getNodeId() );

		// Get the published item(s)
		for ( Item notification : notifications.getItems() ) {
			// Get the raw XML payload
			@SuppressWarnings("unchecked")
			String rawXML = ((PayloadItem<SimplePayload>) notification).getPayload().toXML();

			// Remove the the namespace for the raw payload otherwise the marshaller get's into
			// trouble.
			String xml = rawXML.replaceFirst( " xmlns=\"http://jabber.org/protocol/pubsub\"", "" );

			// Get the JAXB object of the payload
	    	try {
				JAXBContext jaxbContext = JAXBContext.newInstance( Message.class );
		    	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    	StringReader xmlString = new StringReader( xml );
		    	payload = (Message) unmarshaller.unmarshal( xmlString );
			} catch ( JAXBException e ) {
		    	Logger.err( "Payload Error" );
		    	//e.printStackTrace();
			}

	    	// Sanity check
	    	if ( payload == null ) {
		    	Logger.err( "Payload Error" );
		    	break;
	    	}

	    	// Delete the received item from the node, because it's now past.
	    	// TODO: Doesn't work: item-not-found(404)
	    	try {
				node.deleteItem( notification.getId() );
				Logger.log( "Item deleted: " + notification.getId() );
			} catch ( XMPPException e ) {
				Logger.err( "Couldn't delete item: " + notification.getId() );
			}

	    	Logger.log( "Message Content: " + payload.getContent() );

	    	// Display the notification frame with the payload
	    	final NotificationListener self = this;
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
			    	NotificationFrame frame = new NotificationFrame( self.payload );
			    	frame.setVisible( true );
				}
			} );
		}
	}

}
