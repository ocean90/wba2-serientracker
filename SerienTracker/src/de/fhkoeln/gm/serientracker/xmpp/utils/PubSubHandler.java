package de.fhkoeln.gm.serientracker.xmpp.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;
import org.jivesoftware.smackx.packet.DiscoverInfo.Feature;
import org.jivesoftware.smackx.packet.DiscoverInfo.Identity;
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
	}

	/**
	 * Returns a node by node ID.
	 * exists.
	 *
	 * @param String name
	 * @param Boolean force
	 * @return LeafNode
	 */
	public LeafNode getNode( String nodeID ) {
		LeafNode node = null;

		try {
			node = (LeafNode) this.psm.getNode( nodeID );
			Logger.log( "Node exists: " + node.getId() );
		} catch ( XMPPException e ) {
			Logger.err( "Node doesn't exists" );
		}

		return node;
	}

	/**
	 * Checks if a node exists.
	 *
	 * @param String nodeID
	 * @return boolean
	 */
	public boolean nodeExists( String nodeID ) {
		LeafNode node = null;

		try {
			node = (LeafNode) this.psm.getNode( nodeID );
		} catch ( XMPPException e ) {
			return false;
		}

		return node != null;
	}

	/**
	 * Creates a new node.
	 *
	 * @param String nodeID
	 * @param String nodeTitle
	 * @return LeafNode
	 */
	public LeafNode createNode( String nodeID, String nodeTitle ) {
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
			// An frindly name
			form.setTitle( nodeTitle );

			// Create new node with configuration
			node = (LeafNode) this.psm.createNode( nodeID, form );
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
	public void deleteAllNodes() {
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

	/**
	 * Subscribes the current user to a node.
	 *
	 * @param String nodeID
	 * @return Boolean
	 */
	public boolean subscribeToNode( String nodeID ) {
		LeafNode node = this.getNode( nodeID );

		if ( node == null )
			return false;

		try {
			// Add the event listener
			node.addItemEventListener( new ItemEventCoordinator() );

			// Subscribe the user
			node.subscribe( this.cnh.getJID( false ) );

			Logger.log( this.cnh.getJID( false ) + " subscriped to " + nodeID );
		} catch ( XMPPException e ) {
			Logger.err( "Subscription failed" );

			return false;
		}

		return true;
	}

	/**
	 * Unsubscribes the current user from a node.
	 *
	 * @param String nodeID
	 * @return Boolean
	 */
	public boolean unsubscribeFromNode( String nodeID ) {
		LeafNode node = this.getNode( nodeID );

		if ( node == null )
			return false;

		try {
			// TODO
			// node.removeItemEventListener( listener );

			// Unsubscribe the user
			node.unsubscribe( this.cnh.getJID( false ) );

			Logger.log( this.cnh.getJID( false ) + " unsubscriped from " + nodeID );
		} catch ( XMPPException e ) {
			Logger.err( "Unsubscription failed" );

			return false;
		}

		return true;
	}

	public String getNodeTitle( String nodeID ) {
		LeafNode node = this.getNode( nodeID );

		if ( node == null )
			return null;

		ConfigureForm nodeConfig;
		try {
			nodeConfig = node.getNodeConfiguration();
		} catch ( XMPPException e ) {
			return null;
		}

		return nodeConfig.getTitle();
	}

	/**
	 * Returns information about a node.
	 *
	 * @param String nodeID
	 * @return String
	 */
	public String getNodeInfo( String nodeID ) {
		ServiceDiscoveryManager sdm = ServiceDiscoveryManager.getInstanceFor( this.cnh.getConnection() );
		String nodeInfo = "";

		nodeInfo += "Title: " + this.getNodeTitle( nodeID ) + "\n\n";

		try {
			String entity = "pubsub." + this.cnh.getConnection().getServiceName();
			DiscoverInfo discoInfo = sdm.discoverInfo( entity, nodeID );
			Logger.log( "DiscoverInfo: " + entity + " Node: " + nodeID );

			Iterator<Identity> identities = discoInfo.getIdentities();

			while ( identities.hasNext() ) {
				DiscoverInfo.Identity identity = (DiscoverInfo.Identity) identities.next();

				nodeInfo += "Name:\t" + identity.getName() + "\n" +
							"Type:\t" + identity.getType() + "\n" +
							"Category:\t" + identity.getCategory() + "\n\n";

				LeafNode node = this.getNode( nodeID );
				Collection<PacketExtension> nodeExtensions = node.discoverInfo().getExtensions();

				for ( Iterator<PacketExtension> extensions = nodeExtensions.iterator(); extensions.hasNext();) {
					PacketExtension packetExtension = (PacketExtension) extensions.next();
					nodeInfo += "Extension:\t" + packetExtension.toXML() + "\n";
				}

				nodeInfo += "\n";

				for ( Iterator<Feature> nodeFeatures = node.discoverInfo().getFeatures(); nodeFeatures.hasNext();) {
					Feature nodeFeature = (Feature) nodeFeatures.next();
					nodeInfo += "Feature:\t" + nodeFeature.getVar() + "\n";
				}

				// TODO: Gibt nicht alle Abonnenten aus?
				List<Subscription> subscriptions = node.getSubscriptions();

				if ( ! subscriptions.isEmpty() ) {
					nodeInfo += "Subscriber:\n";

					for ( Subscription subscription : subscriptions )
						nodeInfo += "\t" + subscription.getJid() + "\n";
				}
			}

		} catch ( XMPPException e ) {
			Logger.err( "Node discovery failed" );
			e.printStackTrace();
		}

		return nodeInfo;
	}
}
