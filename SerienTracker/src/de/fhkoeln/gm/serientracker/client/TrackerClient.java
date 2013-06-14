package de.fhkoeln.gm.serientracker.client;

import de.fhkoeln.gm.serientracker.client.gui.LoginGUI;

import de.fhkoeln.gm.serientracker.client.gui.HomeGUI;
import de.fhkoeln.gm.serientracker.client.gui.StartGUI;
import de.fhkoeln.gm.serientracker.client.gui.RegisterGUI;
import de.fhkoeln.gm.serientracker.client.gui.RegisterGUI2;
import de.fhkoeln.gm.serientracker.client.gui.ProfileSettingGUI;
import de.fhkoeln.gm.serientracker.client.gui.GenreSettingGUI;
import de.fhkoeln.gm.serientracker.client.gui.MessageSettingGUI;
import de.fhkoeln.gm.serientracker.client.gui.SerieGUI;
import de.fhkoeln.gm.serientracker.client.gui.SeasonGUI;
import de.fhkoeln.gm.serientracker.client.gui.MySerieGUI;

import de.fhkoeln.gm.serientracker.client.gui.EditSerieGUI;



public class TrackerClient {

	static StartGUI startGUI = new StartGUI();
	static LoginGUI loginGUI = new LoginGUI();
	static HomeGUI homeGUI = new HomeGUI();
	static RegisterGUI registerGUI = new RegisterGUI();
	static RegisterGUI2 registerGUI2 = new RegisterGUI2();
	static ProfileSettingGUI profileSettingGUI = new ProfileSettingGUI();
	static GenreSettingGUI genreSettingGUI = new GenreSettingGUI();
	static MessageSettingGUI messageSettingGUI = new MessageSettingGUI();
	static SerieGUI serieGUI = new SerieGUI();
	static EditSerieGUI editSerieGUI = new EditSerieGUI();
	static MySerieGUI mySerieGUI = new MySerieGUI();
	static SeasonGUI seasonGUI = new SeasonGUI();


	
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
	public static void closeStartAndGotoLogin() {
		startGUI.setVisible( false );
		startGUI.dispose();
		loginGUI.setVisible( true );
	}
	
	/**
	 * Hide start GUI and show register GUI.
	 */
	public static void closeStartAndGotoRegister() {
		startGUI.setVisible( false );
		startGUI.dispose();
		registerGUI.setVisible( true );
	}

	/**
	 * Hide login GUI and show Home GUI.
	 */
	public static void closeLoginAndGotoHome() {
		loginGUI.setVisible( false );
		loginGUI.dispose();
		homeGUI.update();
		homeGUI.setVisible( true );
	}
	
	
	/**
	 * Hide register GUI and show Home GUI.
	 */
	public static void closeRegisterAndGotoHome() {
		registerGUI2.setVisible( false );
		registerGUI2.dispose();
		homeGUI.update();
		homeGUI.setVisible( true );
	}

	/**
	 * Hide register GUI and show register2 GUI.
	 */
	public static void gotoRegister2() {
		registerGUI.setVisible( false );
		registerGUI.dispose();
		registerGUI2.setVisible( true );		
	}

	/**
	 * Hide home GUI and show profileSetting GUI.
	 */
	public static void closeHomeAndGotoProfileSetting() {
		homeGUI.setVisible( false );
		homeGUI.dispose();
		profileSettingGUI.setVisible( true );
	}
	
	/**
	 * Hide profileSetting GUI and show home GUI.
	 */
	public static void closePSAndGotoHome() {
		profileSettingGUI.setVisible( false );
		profileSettingGUI.dispose();
		homeGUI.setVisible( true );
	}
	
	
	/**
	 * Hide profileSetting GUI and show genreSetting GUI.
	 */
	public static void closePSAndGotoGenreSetting() {
		profileSettingGUI.setVisible( false );
		profileSettingGUI.dispose();
		genreSettingGUI.setVisible( true );
	}
	
	/**
	 * Hide profileSetting GUI and show messageSetting GUI.
	 */
	public static void closePSAndGotoMessageSetting() {
		profileSettingGUI.setVisible( false );
		profileSettingGUI.dispose();
		messageSettingGUI.setVisible( true );
	}
	
	
	/**
	 * Hide messageSetting GUI and show home GUI.
	 */
	public static void closeMSAndGotoHome() {
		messageSettingGUI.setVisible( false );
		messageSettingGUI.dispose();
		homeGUI.setVisible( true );
	}
	
	/**
	 * Hide messageSetting GUI and show profileSetting GUI.
	 */
	public static void closeMSAndGotoProfileSetting() {
		messageSettingGUI.setVisible( false );
		messageSettingGUI.dispose();
		profileSettingGUI.setVisible( true );
	}
	
	/**
	 * Hide messageSetting GUI and show genreSetting GUI.
	 */
	public static void closeMSAndGotoGenreSetting() {
		messageSettingGUI.setVisible( false );
		messageSettingGUI.dispose();
		genreSettingGUI.setVisible( true );
	}
	
	
	
	
	public static void closeGSAndGotoHome() {
		genreSettingGUI.setVisible( false );
		genreSettingGUI.dispose();
		homeGUI.setVisible( true );
	}
	
	public static void closeGSAndGotoProfileSetting() {
		genreSettingGUI.setVisible( false );
		genreSettingGUI.dispose();
		profileSettingGUI.setVisible( true );
	}
	
	public static void closeGSAndGotoMessageSetting() {
		genreSettingGUI.setVisible( false );
		genreSettingGUI.dispose();
		messageSettingGUI.setVisible( true );
	}
	
	public static void closeHomeAndGotoMySerie() {
		homeGUI.setVisible( false );
		homeGUI.dispose();
		mySerieGUI.setVisible( true );
	}
	
	public static void closeHomeAndGotoMyList() {
		homeGUI.setVisible( false );
		homeGUI.dispose();
		messageSettingGUI.setVisible( true );
	}
	
	public static void closeSerieAndGotoEditSerie() {
		serieGUI.setVisible( false );
		serieGUI.dispose();
		editSerieGUI.setVisible( true );
	}
	
	public static void closeSerieAndGotoHome() {
		serieGUI.setVisible( false );
		serieGUI.dispose();
		homeGUI.setVisible( true );
	}
	
	public static void closeSerieAndGotoProfileSetting() {
		serieGUI.setVisible( false );
		serieGUI.dispose();
		profileSettingGUI.setVisible( true );
	}
	
	public static void closeMySerieAndGotoHome() {
		mySerieGUI.setVisible( false );
		mySerieGUI.dispose();
		homeGUI.setVisible( true );
	}
	
	public static void closeMySerieAndGotoSerie() {
		mySerieGUI.setVisible( false );
		mySerieGUI.dispose();
		serieGUI.setVisible( true );
	}
	
	
	public static void closeMySerieAndGotoProfileSetting() {
		mySerieGUI.setVisible( false );
		mySerieGUI.dispose();
		profileSettingGUI.setVisible( true );
	}

	public static void closeSeasonAndGotoHome() {
		seasonGUI.setVisible( false );
		seasonGUI.dispose();
		homeGUI.setVisible( true );		
	}

	public static void closeSeasonAndGotoSerie() {
		seasonGUI.setVisible( false );
		seasonGUI.dispose();
		serieGUI.setVisible( true );			
	}

	public static void closeSeasonAndGotoProfileSetting() {
		seasonGUI.setVisible( false );
		seasonGUI.dispose();
		profileSettingGUI.setVisible( true );	
		
	}

	public static void closeSeasonAndGotoEditSeason() {
		seasonGUI.setVisible( false );
		seasonGUI.dispose();
		profileSettingGUI.setVisible( true );			
	}

	public static void closeSeasonAndGotoEditEpisode() {
		seasonGUI.setVisible( false );
		seasonGUI.dispose();
		profileSettingGUI.setVisible( true );			
	}

	public static void closeSerieAndGotoSeason() {
		serieGUI.setVisible( false );
		serieGUI.dispose();
		seasonGUI.setVisible( true );		
	}

	public static void closeEditSerieAndGotoGome() {
		// TODO Auto-generated method stub
		
	}

//	public static void closeEpisodeAndGotoEditEpisode() {
//		episodeGUI.setVisible( false );
//		episodeGUI.dispose();
//		editEpisodeGUI.setVisible( true );		
//	}
//
//	public static void closeEpisodeAndGotoSeason() {
//		episodeGUI.setVisible( false );
//		episodeGUI.dispose();
//		seasonGUI.setVisible( true );		
//	}
//	
	
}
