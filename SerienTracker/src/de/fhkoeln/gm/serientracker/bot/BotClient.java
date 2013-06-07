package de.fhkoeln.gm.serientracker.bot;

import java.io.IOException;
import java.util.Timer;

import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.XMPPConfig;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

public class BotClient {

	// Username for bot user
	final private String BOT_LOGIN = "bot";

	// Passwort for bot user
	final private String BOT_PW = "bot";

	// Save the connection
	private ConnectionHandler ch;

	// Save the timer
	private Timer timer;

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

		// Set up the timer for notications
		//this.initTimer();
		//this.initShedulder();

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
			if ( this.ch.login( BOT_LOGIN, BOT_PW ) ) {
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
			this.timer.cancel();
			Logger.log( "Bot killed" );
		}
	}

	/**
	 * Inits the timer for the notifciations.
	 */
	/*private void initTimer() {
	    this.timer = new Timer();
	    this.timer.schedule( new NotifcationJob(), 1000, 2000 ); // Delay = 1s, Period = 2s
	}*/

}
