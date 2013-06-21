package de.fhkoeln.gm.serientracker.client.utils;

import java.util.List;

import de.fhkoeln.gm.serientracker.client.gui2.MainGUI;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

/**
 * Controlls the subscriptions for specific ids like series ID or list ID.
 *
 * @author Dominik Schilling
 */
public class SubscriptionHandler {

	// Holds the connection
	private ConnectionHandler cn;

	// Holds the PubSub handler
	private PubSubHandler psh;

	// Holds the current session
	private SessionStore session;

	// Holds the listener
	private NotificationListener listener;

	/**
	 * Constructur.
	 * Fetches some instances.
	 */
	public SubscriptionHandler() {
		// Get connection instance
		this.cn = ConnectionHandler.getInstance();

		// Get PubSub instance
		this.psh = this.cn.getPubSubHandler();

		// Get session instance
		this.session = SessionStore.getInstance();

		// Get notification instance
		this.listener = new NotificationListener();

	}

	/**
	 * Checks if the user is already subscribed to an item.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean isSubscribed( String id ) {
		// Get the internal node ID
		String nodeID = this.getNodeID( id );

		Logger.log( "Check subscriptions for: " + nodeID );

		// Get users subscriptions
		List<String> subscriptions = this.psh.getUserSubscriptions();

		// Sanity check
		if ( subscriptions == null || subscriptions.size() == 0 )
			return false;

		// Check if node ID is already subscribed
		if ( ! subscriptions.contains( nodeID ) )
			return false;

		return true;
	}

	/**
	 * Subscribes the user to a node.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean subscribeTo( String id ) {
		// Get the internal node ID
		String nodeID = this.getNodeID( id );

		// Subscribe the user
		return this.psh.subscribeToNode( nodeID, this.listener );
	}

	/**
	 * Unsubscribes the user from a node.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean unsubscribeFrom( String id ) {
		// Get the internal node ID
		String nodeID = this.getNodeID( id );

		// Unsubscribe the user
		return this.psh.unsubscribeFromNode( nodeID, this.listener );
	}

	/**
	 * Checks the id and calls the specific method based on the node id.
	 * ss_ = series, ls_ = list and so on.
	 *
	 * @param String id
	 * @return String
	 */
	private String getNodeID( String id ) {
		if ( id.contains( "ss_" ) ) {
			// We have a series ID
			return this.getSeriesNodeID( id );
		} else if ( id.contains( "ls_" ) ) {
			// We have a list ID
			return this.getListNodeID( id );
		}

		return null;
	}

	/**
	 * Returns the internal node id for a series based on user settings.
	 *
	 * @param String seriesID
	 * @return String
	 */
	private String getSeriesNodeID( String seriesID ) {
		String nodeID = null;

		// Get user settings
		String settingNotificationTyp = this.session.getUserSetting( "notificationTyp" );
		if ( settingNotificationTyp == null || settingNotificationTyp.equals( "minutes" ) ) {
			String settingNotificationMinutes = this.session.getUserSetting( "notificationMinutes" );

			// Build the node ID
			nodeID = String.format( "series:%s:%s", seriesID, settingNotificationMinutes );
		}

		return nodeID;
	}

	/**
	 * Returns the internal node id for a lists.
	 *
	 * @param String ListID
	 * @return String
	 */
	private String getListNodeID( String ListID ) {
		String nodeID = null;

		// TODO

		return nodeID;
	}

}
