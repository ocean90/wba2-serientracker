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
				loginGUI = new LoginGUI();
				loginGUI.setVisible( true );
			}
		} );
	}

	/**
	 * Hide login GUI and show Main GUI.
	 */
	public static void showMain() {
		loginGUI.setVisible( false );
		loginGUI.dispose();
		mainGUI = new MainGUI();
		mainGUI.setVisible( true );
	}



}
