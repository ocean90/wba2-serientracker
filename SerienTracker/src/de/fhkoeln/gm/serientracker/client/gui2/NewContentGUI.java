package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.fhkoeln.gm.serientracker.jaxb.Country;
import de.fhkoeln.gm.serientracker.jaxb.Genre;
import de.fhkoeln.gm.serientracker.jaxb.Network;
import de.fhkoeln.gm.serientracker.jaxb.Runtime;
import de.fhkoeln.gm.serientracker.jaxb.Weekday;
import de.fhkoeln.gm.serientracker.utils.Logger;

import net.miginfocom.swing.MigLayout;


public class NewContentGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private CardLayout newContentCardLayout;

	private JPanel newContentPanels;
	private JPanel newSeriesPanel;
	private JPanel newSeasonPanel;
	private JPanel newEpisodePanel;

	private JTextField inputTitle;
	private JTextField inputYear;
	private JTextField inputFirstaired;
	private JTextArea inputOverview;
	private JTextField inputAirtime;
	private JTextField inputSeasonnumber;

	private JTextField inputEpisodetitle;
	private JTextField inputEpisodenumber;
	private JTextField inputAirdate;
	private JTextArea inputEpisodeoverview;

	private JComboBox genreBox;
	private JComboBox countryBox;
	private JComboBox runtimeBox;
	private JComboBox networkBox;
	private JComboBox airdayBox;
	private JComboBox serieBox1;
	private JComboBox serieBox2;
	
	private JButton btnSaveSeries;
	private JButton btnCancelSeries;
	private JButton btnSaveSeason;
	private JButton btnCancelSeason;
	private JButton btnSaveEpisode;
	private JButton btnCancelEpisode;

	private JButton btnAddEpisode;
	private JButton btnAddSeason;

	public enum Context {
		SERIES, SEASON, EPISODE
	}

	private Context context;

	public NewContentGUI( Context context ) {
		this.context = context;
		// Set frame title
		setTitle( "SERIENTRACKER | NEW CONTENT" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 600, 600 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		setLayout( new MigLayout( "gap 0 0", "[grow]", "[30%][grow]" ) );

		newContentCardLayout = new CardLayout();
		newContentPanels= new JPanel (newContentCardLayout ) ;

		this.setLayout( newContentCardLayout );

		newContentPanels.add( this.getNewSeriesPanel(), "SERIES" );
		newContentPanels.add( this.getNewSeasonPanel(), "SEASON" );
		newContentPanels.add( this.getNewEpisodePanel(), "EPISODE" );


		switch ( this.context ) {
			case EPISODE:
				Logger.log( "New episode" );
				newContentCardLayout.show( newContentPanels, "EPISODE" );
				break;

			case SEASON:
				Logger.log( "New season" );
				newContentCardLayout.show( newContentPanels, "SEASON" );
				break;

			case SERIES:
				Logger.log( "New series" );
				newContentCardLayout.show( newContentPanels, "SERIES" );
				break;
			default:
				break;

		}
	}

	private JPanel getNewSeriesPanel() {
		newSeriesPanel = new JPanel( new MigLayout( "gap 0 0", "[30%][grow]" ) );
		
		newSeriesPanel.add( new JLabel( "Title:" ), "cell 0 0" );
		newSeriesPanel.add( new JLabel( "Genres:" ), "cell 0 1" );
		newSeriesPanel.add( new JLabel( "Year:" ), "cell 0 2" );
		newSeriesPanel.add( new JLabel( "Firstaired:" ), "cell 0 3" );
		newSeriesPanel.add( new JLabel( "Country:" ), "cell 0 4" );
		newSeriesPanel.add( new JLabel( "Overview:" ), "cell 0 5" );
		newSeriesPanel.add( new JLabel( "Episoderuntime:" ), "cell 0 6" );
		newSeriesPanel.add( new JLabel( "Network:" ), "cell 0 7, gaptop 5" );
		newSeriesPanel.add( new JLabel( "Airday:" ), "cell 0 8" );
		newSeriesPanel.add( new JLabel( "Airtime:" ), "cell 0 9" );
		newSeriesPanel.add( new JLabel( "Images:" ), "cell 0 10" );
		newSeriesPanel.add( new JLabel( "Seasons:" ), "cell 0 10" );

		// Input field for title
		inputTitle = new JTextField();
		newSeriesPanel.add( inputTitle, "cell 1 0, grow" );

		// Dropdown: Genres
		genreBox = new JComboBox();
		Genre[] genres = Genre.values();
		for ( Genre genre : genres ){
			genreBox.addItem( genre.value() );
			newSeriesPanel.add( genreBox, "cell 1 1" );
		}

		// Input field for year
		inputYear = new JTextField();
		newSeriesPanel.add( inputYear, "cell 1 2, grow" );

		// Input field for firstaired
		inputFirstaired = new JTextField();
		newSeriesPanel.add( inputFirstaired, "cell 1 3, grow" );

		// Dropdown: Country
		countryBox = new JComboBox();
		Country[] countries = Country.values();
		for ( Country country : countries ){
			countryBox.addItem( country.value() );
			newSeriesPanel.add( genreBox, "cell 1 4" );
		}

		// Input field for about overview
		inputOverview = new JTextArea();
		inputOverview.setRows( 5 );
		inputOverview.setLineWrap( true );
		JScrollPane inputOverviewScoll = new JScrollPane( inputOverview );
		inputOverviewScoll.setBorder( new JTextField().getBorder() ); // Workaround for same styling
		newSeriesPanel.add( inputOverviewScoll, "cell 1 5, growx, gaptop 5" );

		// Dropdown: Runtime
		runtimeBox = new JComboBox();
		Runtime[] runtimes = Runtime.values();
		for ( Runtime runtime : runtimes ){
			runtimeBox.addItem( runtime.value() );
			newSeriesPanel.add( runtimeBox, "cell 1 6" );
		}

		// Dropdown: Network
		networkBox = new JComboBox();
		Network[] networks = Network.values();
		for ( Network network : networks ){
			networkBox.addItem( network.value() );
			newSeriesPanel.add( networkBox, "cell 1 7" );
		}

		// Dropdown: Airday
		airdayBox = new JComboBox();
		Weekday[] weekdays = Weekday.values();
		for ( Weekday weekday : weekdays ){
			airdayBox.addItem( weekday.value() );
			newSeriesPanel.add( airdayBox, "cell 1 8" );
		}

		// Input field for airtime
		inputAirtime = new JTextField();
		newSeriesPanel.add( inputAirtime, "cell 1 9, grow" );

		// Button for new season
		btnAddSeason = new JButton( "Add Season" );
		btnAddSeason.addActionListener( this );
		newSeriesPanel.add( btnAddSeason, "cell 1 10" );

		/********
		 * ACTIONS
		 */

		btnCancelSeries = new JButton( "Cancel" );
		btnCancelSeries.addActionListener( this );
		newSeriesPanel.add( btnCancelSeries, "cell 0 11, right" );

		btnSaveSeries = new JButton( "Save" );
		btnSaveSeries.addActionListener( this );
		newSeriesPanel.add( btnSaveSeries, "cell 1 11, right" );

		return newSeriesPanel;
	}

	private JPanel getNewSeasonPanel() {
		newSeasonPanel = new JPanel( new MigLayout( "gap 0 0", "[30%][grow]" ) );

		newSeasonPanel.add( new JLabel( "Serie:" ), "cell 0 0" );
		newSeasonPanel.add( new JLabel( "Seasonnumber:" ), "cell 0 2" );
		newSeasonPanel.add( new JLabel( "Episodes:" ), "cell 0 3" );

		// Dropdown: Serie


		// Input field for seasonnumber
		inputSeasonnumber = new JTextField();
		newSeasonPanel.add( inputSeasonnumber, "cell 1 2, grow" );

		// Button for new season
		btnAddEpisode = new JButton( "Add Episode" );
		btnAddEpisode.addActionListener( this );
		newSeasonPanel.add( btnAddEpisode, "cell 1 3, right" );

		btnCancelSeason = new JButton( "Cancel" );
		btnCancelSeason.addActionListener( this );
		newSeasonPanel.add( btnCancelSeason, "cell 0 4, right" );

		btnSaveSeason = new JButton( "Save" );
		btnSaveSeason.addActionListener( this );
		newSeasonPanel.add( btnSaveSeason, "cell 1 4, right" );

		return newSeasonPanel;
	}


	private JPanel getNewEpisodePanel() {
		newEpisodePanel = new JPanel( new MigLayout( "gap 0 0", "[30%][grow]" ) );

		newEpisodePanel.add( new JLabel( "Serie:" ), "cell 0 0" );
		newEpisodePanel.add( new JLabel( "Season:" ), "cell 0 1" );
		newEpisodePanel.add( new JLabel( "Title:" ), "cell 0 3" );
		newEpisodePanel.add( new JLabel( "Number:" ), "cell 0 4" );
		newEpisodePanel.add( new JLabel( "Airdate:" ), "cell 0 5" );
		newEpisodePanel.add( new JLabel( "Overview:" ), "cell 0 6" );
		newEpisodePanel.add( new JLabel( "Images:" ), "cell 0 7" );

		// Input field for title
		inputEpisodetitle = new JTextField();
		newEpisodePanel.add( inputEpisodetitle, "cell 1 3, grow" );

		// Input field for episodenumber
		inputEpisodenumber = new JTextField();
		newEpisodePanel.add( inputEpisodenumber, "cell 1 4, grow" );

		// Input field for airdate
		inputAirdate = new JTextField();
		newEpisodePanel.add( inputAirdate, "cell 1 5, grow" );

		// Input field for about overview
		inputEpisodeoverview = new JTextArea();
		inputEpisodeoverview.setRows( 5 );
		inputEpisodeoverview.setLineWrap( true );
		JScrollPane inputEpisodeoverviewScoll = new JScrollPane( inputEpisodeoverview );
		inputEpisodeoverviewScoll.setBorder( new JTextField().getBorder() ); // Workaround for same styling
		newEpisodePanel.add( inputEpisodeoverviewScoll, "cell 1 6, growx, gaptop 5" );

		btnCancelEpisode = new JButton( "Cancel" );
		btnCancelEpisode.addActionListener( this );
		newEpisodePanel.add( btnCancelEpisode, "cell 0 6, right" );

		btnSaveEpisode = new JButton( "Save" );
		btnSaveEpisode.addActionListener( this );
		newEpisodePanel.add( btnSaveEpisode, "cell 1 6, right" );

		return newEpisodePanel;
	}


	public void setContext( Context context ) {
		this.context = context;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnSaveSeries ) {
			Logger.log( "SAVE SERIES" );
		}
		else if ( e.getSource() == btnCancelSeries ) {
			this.dispose();
		}
		else if ( e.getSource() == btnSaveSeason ) {
			Logger.log( "SAVE SEASON" );
		}
		else if ( e.getSource() == btnCancelSeason ) {
			this.dispose();
		}
		else if ( e.getSource() == btnSaveEpisode ) {
			Logger.log( "SAVE EPISODE" );
		}
		else if ( e.getSource() == btnCancelEpisode ) {
			this.dispose();
		}
		else if ( e.getSource() == btnAddSeason ) {
			// Goto Season
			Logger.log( "Add Season clicked" );
			newContentCardLayout.show( newContentPanels, "SEASON" );
		}
		else if ( e.getSource() == btnAddEpisode ) {
			// Goto Episode
			Logger.log( "Add Episode clicked" );
			newContentCardLayout.show( newContentPanels, "EPISODE" );
		}
	}


}
