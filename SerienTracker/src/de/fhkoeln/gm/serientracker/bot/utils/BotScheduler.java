package de.fhkoeln.gm.serientracker.bot.utils;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import de.fhkoeln.gm.serientracker.utils.Logger;

/**
 * Wrapper class for the Quartz Scheduler.
 *
 * @author Dominik Schilling
 */
public class BotScheduler {

	// The scheduler instance
	private Scheduler scheduler = null;

	// Singleton class
	private static BotScheduler instance = null;

	/**
	 * Private constructor, call getInstance();
	 * Initialize the scheduler instance.
	 */
	private BotScheduler() {
		try {
	        // Grab the Scheduler instance from the Factory
			this.scheduler = StdSchedulerFactory.getDefaultScheduler();
			Logger.log( "Scheduler name: " + this.scheduler.getSchedulerName() );
		} catch ( SchedulerException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the instance of this class.
	 *
	 * @return BotScheduler
	 */
	public static BotScheduler getInstance() {
		if ( instance == null )
			instance = new BotScheduler();

		return instance;
	}

	/**
	 * Starts the scheduler.
	 */
	public void start() {
        try {
			this.scheduler.start();
		} catch ( SchedulerException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the scheduler.
	 */
	public void stop() {
        try {
			this.scheduler.shutdown();
		} catch ( SchedulerException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new job to the scheduler.
	 *
	 * @param JobDetail job
	 * @param Trigger trigger
	 * @return Boolean
	 */
	public boolean addJob( JobDetail job, Trigger trigger ) {
		try {
			this.scheduler.scheduleJob( job, trigger );
			return true;
		} catch ( SchedulerException e ) {
			e.printStackTrace();
			return false;
		}
	}
}
