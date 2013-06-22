package de.fhkoeln.gm.serientracker.client;

import javax.swing.SwingUtilities;

import de.fhkoeln.gm.serientracker.client.gui2.LoginGUI;
import de.fhkoeln.gm.serientracker.client.gui2.MainGUI;


/**
 * The main client for Serientracker.
 *
 * @author Dominik Schilling
 *
 */
public class TrackerClient {

	// Holds the login GUI instance
	static LoginGUI loginGUI;

	// Holds the main GUI instance
	static MainGUI mainGUI;

	/**
	 * The main method. Starts with the login GUI
	 *
	 * @param args
	 */
	public static void main( String[] args ) {
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				// Start with the login window
				TrackerClient.showLogin();
			}
		} );
	}

	/**
	 * Hide login GUI and show main GUI.
	 */
	public static void showMain() {
		loginGUI.dispose();
		mainGUI = new MainGUI();
		mainGUI.setVisible( true );
	}

	/**
	 * Hide main GUI and show login GUI.
	 */
	public static void showLogin() {
		if ( mainGUI != null )
			mainGUI.dispose();

		loginGUI = new LoginGUI();
		loginGUI.setVisible( true );
	}

}
