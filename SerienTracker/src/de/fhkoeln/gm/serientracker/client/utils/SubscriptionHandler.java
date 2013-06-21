package de.fhkoeln.gm.serientracker.client.utils;

import java.util.List;

import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class SubscriptionHandler {

	private ConnectionHandler cn;
	private PubSubHandler psh;
	private SessionStore session;

	public SubscriptionHandler() {
		this.cn = ConnectionHandler.getInstance();
		this.psh = this.cn.getPubSubHandler();
		this.session = SessionStore.getInstance();
	}


	public boolean isSubscribed( String id ) {
		String nodeID = this.getNodeID( id );

		Logger.log( "Check subscriptions for: " + nodeID );

		// Get users subscriptions
		List<String> subscriptions = this.psh.getUserSubscriptions();

		// Sanity check
		if ( subscriptions == null || subscriptions.size() == 0 )
			return false;

		// Check if user is subscribed
		if ( ! subscriptions.contains( nodeID ) )
			return false;

		return true;
	}

	public boolean subscribeTo( String id ) {
		String nodeID = this.getNodeID( id );

		return this.psh.subscribeToNode( nodeID );
	}

	public boolean unsubscribeFrom( String id ) {
		String nodeID = this.getNodeID( id );

		return this.psh.unsubscribeFromNode( nodeID );
	}

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

	private String getSeriesNodeID( String seriesID ) {
		String nodeID = null;

		// Get user settings
		String settingNotificationTyp = this.session.getUserSetting( "notificationTyp" );
		if ( settingNotificationTyp == null || settingNotificationTyp.equals( "minutes" ) ) {
			String settingNotificationMinutes = this.session.getUserSetting( "notificationMinutes" );
			nodeID = String.format( "series:%s:%s", seriesID, settingNotificationMinutes );
		}

		return nodeID;
	}

	private String getListNodeID( String ListID ) {
		String nodeID = null;
		// TODO
		return nodeID;
	}

}
