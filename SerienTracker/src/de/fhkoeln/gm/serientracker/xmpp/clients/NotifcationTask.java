package de.fhkoeln.gm.serientracker.xmpp.clients;

import java.util.TimerTask;

import de.fhkoeln.gm.serientracker.utils.Logger;

public class NotifcationTask extends TimerTask {

	@Override
	public void run() {
		Logger.log( "Notification Task" );
	}

}
