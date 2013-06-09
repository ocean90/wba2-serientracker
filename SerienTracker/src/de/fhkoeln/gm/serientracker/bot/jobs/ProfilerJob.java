package de.fhkoeln.gm.serientracker.bot.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

public class ProfilerJob implements Job {

    public void execute( JobExecutionContext context ) throws JobExecutionException {
    	Logger.log( "Profiler Job" );

    	ConnectionHandler ch = ConnectionHandler.getInstance();

    	if( ! ch.isConnected() ) {
    		Logger.err( "Connection not established!" );
    		return;
    	}

    }

}
