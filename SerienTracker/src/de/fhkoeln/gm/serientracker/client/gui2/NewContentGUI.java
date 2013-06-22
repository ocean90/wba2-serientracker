package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import de.fhkoeln.gm.serientracker.jaxb.Country;
import de.fhkoeln.gm.serientracker.jaxb.Genre;
import de.fhkoeln.gm.serientracker.jaxb.Network;
import de.fhkoeln.gm.serientracker.jaxb.Runtime;
import de.fhkoeln.gm.serientracker.jaxb.Weekday;
import de.fhkoeln.gm.serientracker.utils.Logger;

import net.miginfocom.swing.MigLayout;

/**
 * Provides the new content GUI for a new series, season and/or episdode
 *
 * @author Dennis Meyer
 */
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

	private JPanel genreBox;
	private JComboBox countryBox;
	private JComboBox runtimeBox;
	private JComboBox networkBox;
	private JComboBox airdayBox;


	private JButton btnSaveSeries;
	private JButton btnCancelSeries;
	private JButton btnSaveSeason;
	private JButton btnCancelSeason;
	private JButton btnSaveEpisode;
	private JButton btnCancelEpisode;

	private JButton btnSaveAndAddEpisode;
	private JButton btnAddSeason;

	/**
	 * Content Types: SERIES, SEASON, EPISODE
	 */
	public enum Context {
		SERIES, SEASON, EPISODE
	}

	// Holds the current context
	private Context context;

	private JButton btnSaveAndAddSeason;

	private JButton btnSeriesAddImages;

	private JComboBox seriesBox;

	private JButton btnSeasonAddImages;

	/**
	 * Constructor.
	 * Sets the UI look and context.
	 *
	 * @param Context context
	 */
	public NewContentGUI( Context context ) {
		this.context = context;

		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e ) {
		}

		initComponents();
	}

	/**
	 * Sets up the GUI components.
	 */
	private void initComponents() {
		// Set frame title
		setTitle( "SERIENTRACKER | NEW CONTENT" );

		// Disable resizing
		setResizable( false );


		/********
		 * PANELS
		 */

		// Use CardLayout as layout manager
		newContentCardLayout = new CardLayout();
		newContentPanels = new JPanel( newContentCardLayout );
		add( newContentPanels );

		// Get the content panels
		newContentPanels.add( this.getNewSeriesPanel(), "SERIES" );
		newContentPanels.add( this.getNewSeasonPanel(), "SEASON" );
		newContentPanels.add( this.getNewEpisodePanel(), "EPISODE" );

		// Based on the current context show a panel
		switch ( this.context ) {
			case EPISODE:
				Logger.log( "New episode" );
				// Set frame size
				setBounds( 0, 0, 600, 200 );
				// Center frame on screen
				setLocationRelativeTo( null );
				newContentCardLayout.show( newContentPanels, "EPISODE" );
				break;

			case SEASON:
				Logger.log( "New season" );
				// Set frame size
				setBounds( 0, 0, 600, 200 );
				// Center frame on screen
				setLocationRelativeTo( null );
				newContentCardLayout.show( newContentPanels, "SEASON" );
				break;

			case SERIES:
				Logger.log( "New series" );
				// Set frame size
				setBounds( 0, 0, 600, 700 );
				// Center frame on screen
				setLocationRelativeTo( null );
				newContentCardLayout.show( newContentPanels, "SERIES" );
				break;
			default:
				break;
		}
	}

	/**
	 * Returns the panel for a new series.
	 *
	 * @return JPanel
	 */
	private JPanel getNewSeriesPanel() {
		// Set up the panel
		newSeriesPanel = new JPanel( new MigLayout( "", "[150][grow]" ) );

		// Print the labels
		newSeriesPanel.add( new JLabel( "Title:" ), "cell 0 0" );
		newSeriesPanel.add( new JLabel( "Genres:" ), "cell 0 1, top" );
		newSeriesPanel.add( new JLabel( "Year:" ), "cell 0 2" );
		newSeriesPanel.add( new JLabel( "Firstaired:" ), "cell 0 3" );
		newSeriesPanel.add( new JLabel( "Country:" ), "cell 0 4" );
		newSeriesPanel.add( new JLabel( "Overview:" ), "cell 0 5" );
		newSeriesPanel.add( new JLabel( "Episoderuntime:" ), "cell 0 6" );
		newSeriesPanel.add( new JLabel( "Network:" ), "cell 0 7, gaptop 5" );
		newSeriesPanel.add( new JLabel( "Airday:" ), "cell 0 8" );
		newSeriesPanel.add( new JLabel( "Airtime:" ), "cell 0 9" );
		newSeriesPanel.add( new JLabel( "Images:" ), "cell 0 10" );

		// Input field for title
		inputTitle = new JTextField();
		newSeriesPanel.add( inputTitle, "cell 1 0, grow" );

		// Dropdown: Genres
		genreBox = new JPanel( new MigLayout( "ins 0, fill" ) );
		Genre[] genres = Genre.values();
		int i = 0;
		for ( Genre genre : genres ) {
			JCheckBox genreCheckbox = new JCheckBox();
			genreCheckbox.setText( genre.value() );
			genreCheckbox.setName( genre.value() );
			String constrain = ( ++i % 3 == 0 ) ? "wrap" : "";
			genreBox.add( genreCheckbox, constrain );
		}
		newSeriesPanel.add( genreBox, "cell 1 1" );

		// Input field for year
		inputYear = new JTextField();
		inputYear.setText( "2013" );
		newSeriesPanel.add( inputYear, "cell 1 2, width 50" );

		// Input field for firstaired
		inputFirstaired = new JTextField();
		newSeriesPanel.add( inputFirstaired, "cell 1 3, grow" );

		// Dropdown: Country
		countryBox = new JComboBox();
		Country[] countries = Country.values();
		for ( Country country : countries )
			countryBox.addItem( country.value() );
		newSeriesPanel.add( countryBox, "cell 1 4" );

		// Input field for overview
		inputOverview = new JTextArea();
		inputOverview.setRows( 5 );
		inputOverview.setLineWrap( true );
		JScrollPane inputOverviewScoll = new JScrollPane( inputOverview );
		inputOverviewScoll.setBorder( new JTextField().getBorder() ); // Workaround for same styling
		newSeriesPanel.add( inputOverviewScoll, "cell 1 5, growx" );

		// Dropdown: Runtime
		runtimeBox = new JComboBox();
		Runtime[] runtimes = Runtime.values();
		for ( Runtime runtime : runtimes )
			runtimeBox.addItem( runtime.value() );
		newSeriesPanel.add( runtimeBox, "cell 1 6" );

		// Dropdown: Network
		networkBox = new JComboBox();
		Network[] networks = Network.values();
		for ( Network network : networks )
			networkBox.addItem( network.value() );
		newSeriesPanel.add( networkBox, "cell 1 7" );

		// Dropdown: Airday
		airdayBox = new JComboBox();
		Weekday[] weekdays = Weekday.values();
		for ( Weekday weekday : weekdays )
			airdayBox.addItem( weekday.value() );
		newSeriesPanel.add( airdayBox, "cell 1 8" );

		// Input field for airtime
		inputAirtime = new JTextField();
		inputAirtime.setText( "00:00:00" );
		newSeriesPanel.add( inputAirtime, "cell 1 9, width 80" );


		/********
		 * ACTIONS
		 */

		// Button for add images
		btnSeriesAddImages = new JButton( "Add Images" );
		btnSeriesAddImages.addActionListener( this );
		btnSeriesAddImages.setEnabled( false );
		newSeriesPanel.add( btnSeriesAddImages, "cell 1 10" );

		// Button for cancel
		btnCancelSeries = new JButton( "Cancel" );
		btnCancelSeries.addActionListener( this );
		newSeriesPanel.add( btnCancelSeries, "cell 0 11, left, gaptop 25" );

		// Button for save
		btnSaveSeries = new JButton( "Save" );
		btnSaveSeries.addActionListener( this );
		newSeriesPanel.add( btnSaveSeries, "cell 1 11, right, gaptop 25" );

		// Button for save and new season
		btnSaveAndAddSeason = new JButton( "Save & Add Season" );
		btnSaveAndAddSeason.addActionListener( this );
		newSeriesPanel.add( btnSaveAndAddSeason, "cell 1 11, right, gaptop 25" );

		return newSeriesPanel;
	}

	/**
	 * Returns the panel for a new season.
	 *
	 * @return JPanel
	 */
	private JPanel getNewSeasonPanel() {
		// Set up the panel
		newSeasonPanel = new JPanel( new MigLayout( "gap 0 0", "[150][grow]" ) );

		// Print the labels
		newSeasonPanel.add( new JLabel( "Series:" ), "cell 0 0" );
		newSeasonPanel.add( new JLabel( "Seasonnumber:" ), "cell 0 1" );
		newSeasonPanel.add( new JLabel( "Images:" ), "cell 0 2" );


		// Dropdown: Series
		seriesBox = new JComboBox();
		newSeasonPanel.add( seriesBox, "cell 1 0" );

		// Input field for seasonnumber
		inputSeasonnumber = new JTextField();
		newSeasonPanel.add( inputSeasonnumber, "cell 1 1, width 50" );


		/********
		 * ACTIONS
		 */

		// Button for add images
		btnSeasonAddImages = new JButton( "Add Images" );
		btnSeasonAddImages.addActionListener( this );
		btnSeasonAddImages.setEnabled( false );
		newSeasonPanel.add( btnSeasonAddImages, "cell 1 2" );

		// Button for cancel
		btnCancelSeason = new JButton( "Cancel" );
		btnCancelSeason.addActionListener( this );
		newSeasonPanel.add( btnCancelSeason, "cell 0 3, left, gaptop 25" );

		// Button for save
		btnSaveSeason = new JButton( "Save" );
		btnSaveSeason.addActionListener( this );
		newSeasonPanel.add( btnSaveSeason, "cell 1 3, right, gaptop 25" );

		// Button for save and add new season
		btnSaveAndAddEpisode = new JButton( "Save & Add Episode" );
		btnSaveAndAddEpisode.addActionListener( this );
		newSeasonPanel.add( btnSaveAndAddEpisode, "cell 1 3, right, gaptop 25" );

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
		else if ( e.getSource() == btnSaveAndAddEpisode ) {
			// Goto Episode
			Logger.log( "Add Episode clicked" );
			newContentCardLayout.show( newContentPanels, "EPISODE" );
		}
	}


}
