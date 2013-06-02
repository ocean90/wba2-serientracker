package de.fhkoeln.gm.serientracker.xmpp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.Subscription;

import de.fhkoeln.gm.serientracker.utils.Logger;

public class PubSubHandler {
	private PubSubManager psm;

	// Save the connection
	private ConnectionHandler cnh;

	/**
	 * Constructor.
	 * Creates PubSubManager instance.
	 * @param Connection cn
	 */
	public PubSubHandler() {
		this.cnh = ConnectionHandler.getInstance();

		this.psm = new PubSubManager( this.cnh.getConnection() );

		//this.deleteAllNodes(); // TODO
	}

	public LeafNode getNode( String name ) {
		return getNode( name, true );
	}

	private void deleteAllNodes() {
		for ( String node : this.getAllNodes() ) {
			try {
				this.psm.deleteNode( node );
			} catch ( XMPPException e ) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns a node by name. Calls createNode() if force = true and node doesn't
	 * exists.
	 *
	 * @param String name
	 * @param Boolean force
	 * @return LeafNode
	 */
	public LeafNode getNode( String name, Boolean force ) {
		LeafNode node = null;

		try {
			node = (LeafNode) this.psm.getNode( name );
			Logger.log( "Node exists: " + node.getId() );
		} catch ( XMPPException e ) {
			Logger.err( "Node doesn't exists" );

			if ( force )
				node = this.createNode( name );
		}

		return node;
	}

	/**
	 * Creates a new node.
	 *
	 * @param String name
	 * @return LeafNode
	 */
	public LeafNode createNode( String name ) {
		LeafNode node = null;

		try {
			ConfigureForm form = new ConfigureForm( FormType.submit );
			// Access
			form.setAccessModel( AccessModel.open );
			// With payload
			form.setDeliverPayloads( true );
			// Delete message
			form.setNotifyRetract( true );
			// Persistent data
			form.setPersistentItems( false );
			// Publish
			form.setPublishModel( PublishModel.open );
			// Create node with configuration
			node = (LeafNode) this.psm.createNode( name, form );


			Logger.log( "Node created: " + node.getId() );
		} catch ( XMPPException e ) {
			Logger.err( "Node creation failed" );
			e.printStackTrace();

			return null;
		}

		return node;
	}


	/**
	 * Get all nodes known to the xmpp server
	 *
	 * @return List
	 */
	public List<String> getAllNodes() {

		List<String> nodes = new ArrayList<String>();

		try {
			DiscoverItems itms = this.psm.discoverNodes( null );

			Iterator<DiscoverItems.Item> items = itms.getItems();

			while ( items.hasNext() )
				nodes.add( items.next().getNode() );

		} catch ( XMPPException e ) {
			e.printStackTrace();
		}

		return nodes;
	}

	// TODO: Gibt was anderes als eigentlich gedacht...
	public List<String> getSubscribers( String name ) {
		LeafNode node = this.getNode( name );

		List<String> users = new ArrayList<String>();

		try {
			for (Subscription subscriber : node.getSubscriptions() )
				users.add( subscriber.getJid() );
		} catch (XMPPException e) {
			e.printStackTrace();
		}

		return users;
	}

	public boolean subscribeToNode( String name ) {
		LeafNode node = this.getNode( name, false );

		if ( node == null )
			return false;

		try {
			node.addItemEventListener( new ItemEventCoordinator() );
			node.subscribe( this.cnh.getJID() );
		} catch ( XMPPException e ) {
			Logger.err( "Subscription failed" );

			return false;
		}

		Logger.log( this.cnh.getJID() + " subscriped to " + name );

		return true;
	}

	public boolean unsubscribeFromNode( String name ) {

		LeafNode node = this.getNode( name, false );

		if ( node == null )
			return false;

		try {
			node.unsubscribe( this.cnh.getJID() );
		} catch ( XMPPException e ) {
			Logger.err( "Unsubscription failed" );

			return false;
		}

		Logger.log( this.cnh.getJID() + " unsubscriped from " + name );

		return true;
	}
}
