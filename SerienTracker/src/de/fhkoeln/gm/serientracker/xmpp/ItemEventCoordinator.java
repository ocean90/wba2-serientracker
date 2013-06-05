package de.fhkoeln.gm.serientracker.xmpp;

import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import de.fhkoeln.gm.serientracker.utils.Logger;

public class ItemEventCoordinator implements ItemEventListener<Item> {

	@Override
	public void handlePublishedItems( ItemPublishEvent<Item> items ) {
		Logger.log( "Items received: " + items.getItems().size() );

		for ( Item item : items.getItems() ) {
			@SuppressWarnings("unchecked")
			PayloadItem<SimplePayload> data = ( PayloadItem<SimplePayload> ) item;
			Logger.log( "Payload: " + data.toXML() );
		}
	}

}
