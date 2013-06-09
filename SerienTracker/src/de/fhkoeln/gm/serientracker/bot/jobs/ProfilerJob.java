package de.fhkoeln.gm.serientracker.bot.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class ProfilerJob implements Job {
	private PubSubHandler psh;

    public void execute( JobExecutionContext context ) throws JobExecutionException {
    	Logger.log( "Profiler Job" );

    	ConnectionHandler ch = ConnectionHandler.getInstance();

    	if( ! ch.isConnected() ) {
    		Logger.err( "Connection not established!" );
    		return;
    	}

    	this.psh = ch.getPubSubHandler();

		for ( String node : this.psh.getAllNodes() )
			Logger.log(  String.format( "Node: %s", node ) );

    }

}
