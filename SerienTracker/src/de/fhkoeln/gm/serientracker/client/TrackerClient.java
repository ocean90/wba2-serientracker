package de.fhkoeln.gm.serientracker.client;

import de.fhkoeln.gm.serientracker.client.gui.LoginGUI;
import de.fhkoeln.gm.serientracker.client.gui.MainGUI;
import de.fhkoeln.gm.serientracker.client.gui.StartGUI;
import de.fhkoeln.gm.serientracker.client.gui.RegisterGUI;


public class TrackerClient {

	static StartGUI startGUI = new StartGUI();
	static LoginGUI loginGUI = new LoginGUI();
	static MainGUI mainGUI = new MainGUI();
	static RegisterGUI registerGUI = new RegisterGUI();
//	static RegisterGUI2 registerGUI2 = new RegisterGUI2();


	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		init();
	}

	/**
	 * Show the start GUI.
	 */
	public static void init() {
		startGUI.setVisible( true );
	}
	
	/**
	 * Hide start GUI and show login GUI.
	 */
	public static void closeStartandGotoLogin() {
		startGUI.setVisible( false );
		startGUI.dispose();
		loginGUI.setVisible( true );
	}
	
	/**
	 * Hide start GUI and show register GUI.
	 */
	public static void closeStartandGotoRegister() {
		startGUI.setVisible( false );
		startGUI.dispose();
		registerGUI.setVisible( true );
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
	
	/**
	 * Hide start GUI and show register GUI.
	 */
	public static void closeRegisterandGotoMain() {
		registerGUI.setVisible( false );
		registerGUI.dispose();
		mainGUI.update();
		mainGUI.setVisible( true );
	}

}
