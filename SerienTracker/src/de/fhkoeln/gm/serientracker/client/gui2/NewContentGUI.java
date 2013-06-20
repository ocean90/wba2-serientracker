package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.fhkoeln.gm.serientracker.jaxb.Country;
import de.fhkoeln.gm.serientracker.jaxb.Genre;
import de.fhkoeln.gm.serientracker.jaxb.Network;
import de.fhkoeln.gm.serientracker.jaxb.Shows;

import de.fhkoeln.gm.serientracker.jaxb.Runtime;
import de.fhkoeln.gm.serientracker.jaxb.Weekday;
import de.fhkoeln.gm.serientracker.utils.Logger;

import net.miginfocom.swing.MigLayout;

public class NewContentGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	
	private JTabbedPane newContentTabs;
	private JPanel newSeriesPanel;
	private JPanel newSeasonPanel;
	private JPanel newEpisodePanel;
	
	private JPanel newSerieTab;
	private JPanel newSeasonTab;
	private JPanel newEpisodeTab;

	
	private JTextField inputTitle;
	private JTextField inputYear;
	private JTextField inputFirstaired;
	private JTextArea inputOverview;
	private JTextField inputAirtime;

	private JTextField inputSeriestitle;
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



	private JButton btnSave;
	private JButton btnCancel;
	
	public enum Context {
		SERIES, SEASON, EPISODE
	}

	private Context context;

	public NewContentGUI( Context context ) {
		this.context = context;
		initComponents();
		}
	
	public void initComponents() {
		// Set frame title
		setTitle( "SERIENTRACKER | NEW CONTENT" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 600, 600 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		setLayout( new MigLayout( "gap 0 0", "[grow]", "[30%][grow]" ) );

		
		/********
		 * TABS
		 */

		newContentTabs = new JTabbedPane();
		add( newContentTabs, "grow" );

		newContentTabs.addTab( "New serie", getNewSeriesPanel() );

		newSeasonPanel = new JPanel();
		newContentTabs.addTab( "New season", getNewSeasonPanel() );
		
		newEpisodePanel = new JPanel();
		newContentTabs.addTab( "New episode", getNewEpisodePanel() );
		

		/********
		 * ACTIONS
		 */

		btnCancel = new JButton( "Cancel" );
		btnCancel.addActionListener( this );
		add( btnCancel, "cell 0 1, right" );

		btnSave = new JButton( "Save" );
		btnSave.addActionListener( this );
		add( btnSave, "cell 0 1, right" );
		
		switch ( this.context ) {
			case EPISODE:
				Logger.log( "New episode" );
				// CardLayout show x
				break;
			case SEASON:
				Logger.log( "New season" );
				break;
			case SERIES:
				Logger.log( "New series" );
				break;
			default:
				break;

		}
		add( new JLabel( "test" ) );
	}

	private JPanel getNewSeriesPanel() {
		newSerieTab = new JPanel( new MigLayout( "gap 0 0", "[30%][grow]" ) );

		newSerieTab.add( new JLabel( "Title:" ), "cell 0 0" );
		newSerieTab.add( new JLabel( "Genres:" ), "cell 0 1" );
		newSerieTab.add( new JLabel( "Year:" ), "cell 0 2" );
		newSerieTab.add( new JLabel( "Firstaired:" ), "cell 0 3" );
		newSerieTab.add( new JLabel( "Country:" ), "cell 0 4" );
		newSerieTab.add( new JLabel( "Overview:" ), "cell 0 5" );
		newSerieTab.add( new JLabel( "Episoderuntime:" ), "cell 0 6" );
		newSerieTab.add( new JLabel( "Network:" ), "cell 0 7, gaptop 5" );
		newSerieTab.add( new JLabel( "Airday:" ), "cell 0 8" );
		newSerieTab.add( new JLabel( "Airtime:" ), "cell 0 9" );
		newSerieTab.add( new JLabel( "Images:" ), "cell 0 10" );

		
		// Input field for title
		inputTitle = new JTextField();
		newSerieTab.add( inputTitle, "cell 1 0, grow" );
		
		// Dropdown: Genres
		genreBox = new JComboBox();
		Genre[] genres = Genre.values();
		for ( Genre genre : genres ){			
			genreBox.addItem( genre.value() );
			newSerieTab.add( genreBox, "cell 1 1" );
		}

		// Input field for year
		inputYear = new JTextField();
		newSerieTab.add( inputYear, "cell 1 2, grow" );

		// Input field for firstaired
		inputFirstaired = new JTextField();
		newSerieTab.add( inputFirstaired, "cell 1 3, grow" );
		
		// Dropdown: Country
		countryBox = new JComboBox();
		Country[] countries = Country.values();
		for ( Country country : countries ){			
			countryBox.addItem( country.value() );
			newSerieTab.add( genreBox, "cell 1 4" );
		}

		// Input field for about overview
		inputOverview = new JTextArea();
		inputOverview.setRows( 5 );
		inputOverview.setLineWrap( true );
		JScrollPane inputOverviewScoll = new JScrollPane( inputOverview );
		inputOverviewScoll.setBorder( new JTextField().getBorder() ); // Workaround for same styling
		newSerieTab.add( inputOverviewScoll, "cell 1 5, growx, gaptop 5" );

		// Dropdown: Runtime
		runtimeBox = new JComboBox();
		Runtime[] runtimes = Runtime.values();
		for ( Runtime runtime : runtimes ){			
			runtimeBox.addItem( runtime.value() );
			newSerieTab.add( runtimeBox, "cell 1 6" );
		}

		// Dropdown: Network
		networkBox = new JComboBox();
		Network[] networks = Network.values();
		for ( Network network : networks ){			
			networkBox.addItem( network.value() );
			newSerieTab.add( networkBox, "cell 1 7" );
		}
		
		// Dropdown: Airday
		airdayBox = new JComboBox();
		Weekday[] weekdays = Weekday.values();
		for ( Weekday weekday : weekdays ){			
			airdayBox.addItem( weekday.value() );
			newSerieTab.add( airdayBox, "cell 1 8" );
		}

		// Input field for airtime
		inputAirtime = new JTextField();
		newSerieTab.add( inputAirtime, "cell 1 9, grow" );
	
		
		return newSerieTab;
	}

	private JPanel getNewSeasonPanel() {
		newSeasonTab = new JPanel( new MigLayout( "gap 0 0", "[30%][grow]" ) );

		newSeasonTab.add( new JLabel( "Serie:" ), "cell 0 0" );
		newSeasonTab.add( new JLabel( "Seasonnumber:" ), "cell 0 2" );
				

		
		// Input field for title
		inputSeriestitle = new JTextField();
		inputSeriestitle.setEnabled( false );
		newSeasonTab.add( inputSeriestitle, "cell 1 0, grow" );
		
		// Dropdown: Serie
		
		
		// Input field for seasonnumber
		inputSeasonnumber = new JTextField();
		newSeasonTab.add( inputSeasonnumber, "cell 1 2, grow" );

		
		
		return newSeasonTab;
	}	
	

	private JPanel getNewEpisodePanel() {
		newEpisodeTab = new JPanel( new MigLayout( "gap 0 0", "[30%][grow]" ) );

		newEpisodeTab.add( new JLabel( "Serie:" ), "cell 0 0" );
		newEpisodeTab.add( new JLabel( "Season:" ), "cell 0 1" );
		newEpisodeTab.add( new JLabel( "Title:" ), "cell 0 3" );
		newEpisodeTab.add( new JLabel( "Number:" ), "cell 0 4" );
		newEpisodeTab.add( new JLabel( "Airdate:" ), "cell 0 5" );
		newEpisodeTab.add( new JLabel( "Overview:" ), "cell 0 6" );
		newEpisodeTab.add( new JLabel( "Images:" ), "cell 0 7" );


		
		// Input field for title
		inputEpisodetitle = new JTextField();
		inputEpisodetitle.setEnabled( false );
		newEpisodeTab.add( inputEpisodetitle, "cell 1 3, grow" );
		
		// Input field for episodenumber
		inputEpisodenumber = new JTextField();
		newEpisodeTab.add( inputEpisodenumber, "cell 1 4, grow" );
		
		// Input field for airdate
		inputAirdate = new JTextField();
		newEpisodeTab.add( inputAirdate, "cell 1 5, grow" );
				
	
		// Input field for about overview
		inputEpisodeoverview = new JTextArea();
		inputEpisodeoverview.setRows( 5 );
		inputEpisodeoverview.setLineWrap( true );
		JScrollPane inputEpisodeoverviewScoll = new JScrollPane( inputEpisodeoverview );
		inputEpisodeoverviewScoll.setBorder( new JTextField().getBorder() ); // Workaround for same styling
		newEpisodeTab.add( inputEpisodeoverviewScoll, "cell 1 6, growx, gaptop 5" );

		
		return newEpisodeTab;
	}	
	
	
	public void setContext( Context context ) {
		this.context = context;
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnSave ) {
			Logger.log( "SAVE" );
		} else if ( e.getSource() == btnCancel ) {
			this.dispose();
		}
	}

	
}
