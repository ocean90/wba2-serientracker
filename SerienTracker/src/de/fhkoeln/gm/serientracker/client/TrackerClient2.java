package de.fhkoeln.gm.serientracker.client;

import javax.swing.SwingUtilities;

import de.fhkoeln.gm.serientracker.client.gui2.LoginGUI;
import de.fhkoeln.gm.serientracker.client.gui2.MainGUI;



public class TrackerClient2 {

	static LoginGUI loginGUI;
	static MainGUI mainGUI;

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				// Start with the login window
				TrackerClient2.showLogin();
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
