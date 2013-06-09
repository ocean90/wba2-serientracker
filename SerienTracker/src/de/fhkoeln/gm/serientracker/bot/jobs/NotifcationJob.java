package de.fhkoeln.gm.serientracker.bot.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class NotifcationJob implements Job {
	private PubSubHandler psh;

    public void execute( JobExecutionContext context ) throws JobExecutionException {
    	JobKey key = context.getJobDetail().getKey();

    	ConnectionHandler ch = ConnectionHandler.getInstance();

    	if( ! ch.isConnected() ) {
    		Logger.err( "Connection not established!" );
    		return;
    	}

    	this.psh = ch.getPubSubHandler();

		for ( String node : this.psh.getAllNodes() )
			Logger.log(  String.format( "Node: %s", node ) );

        /*JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String id = dataMap.getString("id");
        String users = dataMap.getString("users");

		Logger.log( String.format( "Notification Job: Key: %s ID: %s Users: %s", key.getName(), id, users ) );*/
    }

}
