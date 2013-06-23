package de.fhkoeln.gm.serientracker.xmpp;

import javax.swing.SwingUtilities;

import de.fhkoeln.gm.serientracker.xmpp.gui.LoginGUI;
import de.fhkoeln.gm.serientracker.xmpp.gui.MainGUI;

/**
 * A simple client for debbuging XMPP stuff.
 *
 * @author Dominik Schilling
 */
public class XMPPClient {

	// Holds the login GUI
	static LoginGUI loginGUI;

	// Holds the main GUI
	static MainGUI mainGUI;

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				loginGUI = new LoginGUI();
				mainGUI = new MainGUI();

				init();
			}
		} );
	}

	/**
	 * Show the login GUI.
	 */
	public static void init() {
		loginGUI.setVisible( true );
	}

	/**
	 * Hide login GUI and show Main GUI.
	 */
	public static void closeLogin() {
		loginGUI.setVisible( false );
		loginGUI.dispose();
		mainGUI.update();
		mainGUI.setVisible( true );
	}

}
