package de.fhkoeln.gm.serientracker.bot.utils;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class BotScheduler {
	private Scheduler scheduler = null;

	/**
	 * Constructor.
	 * Initialize the scheduler instance.
	 */
	public BotScheduler() {
		try {
	        // Grab the Scheduler instance from the Factory
			this.scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch ( SchedulerException e ) {
			e.printStackTrace();
		}
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
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}
}
