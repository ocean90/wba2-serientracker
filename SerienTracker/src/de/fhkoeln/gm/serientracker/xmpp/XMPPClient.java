package de.fhkoeln.gm.serientracker.xmpp;

import javax.swing.SwingUtilities;

import de.fhkoeln.gm.serientracker.xmpp.gui.LoginGUI;
import de.fhkoeln.gm.serientracker.xmpp.gui.MainGUI;


public class XMPPClient {

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
