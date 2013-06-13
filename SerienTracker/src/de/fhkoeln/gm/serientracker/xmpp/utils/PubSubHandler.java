package de.fhkoeln.gm.serientracker.xmpp.utils;

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

	// Save the connection
	private ConnectionHandler cnh;

	// Save the Pub Sub mananger
	private PubSubManager psm;

	/**
	 * Constructor.
	 * Creates PubSubManager instance.
	 *
	 * @param Connection cn
	 */
	public PubSubHandler() {
		// Get the connection instance
		this.cnh = ConnectionHandler.getInstance();

		// Init a new Pub Sub manager
		this.psm = new PubSubManager( this.cnh.getConnection() );

		this.deleteAllNodes(); // TODO: Entfernen, nur für Testzwecke
	}

	/**
	 * Returns a node by name. Calls createNode() if node doesn't
	 * exists.
	 *
	 * @param String name
	 * @return LeafNode
	 */
	public LeafNode getNode( String name ) {
		return getNode( name, true );
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

			// Node doesn't exists and force is true, create a new node
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
			// Node configuration
			ConfigureForm form = new ConfigureForm( FormType.submit );
			// Access
			form.setAccessModel( AccessModel.open );
			// Publish
			form.setPublishModel( PublishModel.open );
			// With payload
			form.setDeliverPayloads( true );
			// Delete message
			form.setNotifyRetract( true );
			// Persistent data
			form.setPersistentItems( false );
			// Create new node with configuration
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
	 * Deletes all nodes.
	 *
	 */
	private void deleteAllNodes() {
		for ( String node : this.getAllNodes() ) {
			try {
				this.psm.deleteNode( node );
			} catch ( XMPPException e ) {
				if ( e.getXMPPError().getCode() == 403 )
					Logger.err( "Not allowed to the delete the node '" + node + "'" );
				else
					e.printStackTrace();
			}
		}
	}


	/**
	 * Get all nodes known to the XMPP server.
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

	/**
	 * Subscribes the current user to a node.
	 *
	 * @param String name
	 * @return Boolean
	 */
	public boolean subscribeToNode( String name ) {
		LeafNode node = this.getNode( name, false );

		if ( node == null )
			return false;

		try {
			// Add the event listener
			node.addItemEventListener( new ItemEventCoordinator() );

			// Subscribe the user
			node.subscribe( this.cnh.getJID( false ) );

			Logger.log( this.cnh.getJID( false ) + " subscriped to " + name );
		} catch ( XMPPException e ) {
			Logger.err( "Subscription failed" );

			return false;
		}

		return true;
	}

	/**
	 * Unsubscribes the current user from a node.
	 *
	 * @param String name
	 * @return Boolean
	 */
	public boolean unsubscribeFromNode( String name ) {
		LeafNode node = this.getNode( name, false );

		if ( node == null )
			return false;

		try {
			// Unsubscribe the user
			node.unsubscribe( this.cnh.getJID( false ) );

			Logger.log( this.cnh.getJID( false ) + " unsubscriped from " + name );
		} catch ( XMPPException e ) {
			Logger.err( "Unsubscription failed" );

			return false;
		}

		return true;
	}
}
