package de.fhkoeln.gm.serientracker.bot;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;

import org.quartz.JobDetail;
import org.quartz.Trigger;

import de.fhkoeln.gm.serientracker.bot.jobs.NotifcationJob;
import de.fhkoeln.gm.serientracker.bot.jobs.ProfilerJob;
import de.fhkoeln.gm.serientracker.bot.utils.BotScheduler;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.XMPPConfig;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

/**
 * The bot client which handles the cronjobs and notifcation schedules.
 *
 * @author Dominik Schilling
 */
public class BotClient {

	// Username for bot user
	final private String BOT_LOGIN = "bot";

	// Passwort for bot user
	final private String BOT_PW = "bot";

	// Save the connection
	private ConnectionHandler ch;

	// Save the scheduler
	private BotScheduler scheduler;

	/**
	 * Starts the bot.
	 *
	 * @param args
	 */
	public static void main( String[] args ) {
		new BotClient();
	}

	/**
	 * Constructor.
	 */
	public BotClient() {
		// Get connection instance
		this.ch = ConnectionHandler.getInstance();

		// Try to login in
		if ( ! this.login() ) {
			Logger.log( "Bot not started" );
			return;
		}

		// Set up the scheduler
		this.initShedulder();

		// Set process to idle mode
		this.idle();
	}

	/**
	 * Login the bot user.
	 *
	 * @return boolean
	 */
	private boolean login() {
		// Try to connect to the server
		if ( this.ch.connect( XMPPConfig.hostname , XMPPConfig.port ) ) {
			// Try to login
			if ( this.ch.login( BOT_LOGIN, BOT_PW, "botclient" ) ) {
				return true;
			} else {
				Logger.err( "Login failed" );
				return false;
			}
		} else {
			Logger.err( "Connection failed" );
			return false;
		}
	}

	/**
	 * Holds the process alive until user clicks a key
	 * in the console.
	 */
	private void idle() {
		Logger.log( "Bot runs" );
		Logger.log( "Press any key to stop the bot..." );
		try {
			// Wait for user input
			System.in.read();
		} catch ( IOException e ) {
		} finally {
			// Stop the notification timer
			this.scheduler.stop();
			Logger.log( "Bot killed" );
		}
	}

	private void initShedulder() {
		this.scheduler = BotScheduler.getInstance();
		this.scheduler.start();

		/*
		 * Register a profiler job which runs every minute.
		 * Can be used to clean up things, check files , etc.
		 */
		JobDetail profilerJob = newJob( ProfilerJob.class)
				.withIdentity( "bot:profiler", "bot:intern" )
				.build();

		Trigger profilerTrigger = newTrigger()
				.withIdentity( "bot:profiler:trigger", "bot:intern" )
				.withSchedule( cronSchedule( "0/10 * * * * ?" ) ) // Every 10 seconds
				.build();

		this.scheduler.addJob( profilerJob, profilerTrigger );


		/*
		 * Register the notifier job.
		 */
		JobDetail notfierJob = newJob( NotifcationJob.class)
				.withIdentity( "bot:notifier", "bot:notification:series" )
				.build();

		Trigger notifierTrigger = newTrigger()
				.withIdentity( "bot:notifier:trigger", "bot:notification:series" )
				//.withSchedule( cronSchedule( "0 0/1 * * * ?" ) ) // Every Minute
				.withSchedule( cronSchedule( "0/15 * * * * ?" ) ) // Every 15 seconds
				.build();

		this.scheduler.addJob( notfierJob, notifierTrigger );

	}

}
