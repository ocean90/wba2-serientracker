package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import de.fhkoeln.gm.serientracker.client.TrackerClient;
import de.fhkoeln.gm.serientracker.jaxb.Country;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class EditSerieGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;
	private JTextField inputTitle;
	private JTextField inputYear;
	private JTextField inputFirstaired;
	private JTextField inputOverview;
	private JTextField inputEpisoderuntime;
	private JTextField inputAirtime;




	
	private JComboBox existingNodes;

	public EditSerieGUI() {
		this.ch = ConnectionHandler.getInstance();

		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( ClassNotFoundException e ) {
		} catch ( InstantiationException e ) {
		} catch ( IllegalAccessException e ) {
		} catch ( UnsupportedLookAndFeelException e ) {
		}

		initComponents();
	}

	public void initComponents() {
		// Close when exit
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// Set frame title
		setTitle( "SERIENTRACKER" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 600, 600 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( true );

		JMenuBar menubar = new JMenuBar();
	    setJMenuBar(menubar);

	    JToolBar toolbar = new JToolBar();

	    JButton homeButton = new JButton("Home");
//	    homeButton.addActionListener( new ActionListener() {
//		public void actionPerformed( ActionEvent e ) {
//			gotoHome( e );
//			}
//		});
	    
	    JButton settingButton = new JButton("Setting");
	    settingButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	       			TrackerClient.closeHomeAndGotoProfileSetting();
	           }
	           
	    });
	    
	    JButton exitButton = new JButton("Logout");
	    exitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);}
	           
	    });
	    
	    toolbar.add(homeButton);
	    toolbar.add(settingButton);
	    toolbar.add(exitButton);
	   
	   
		
		// Content Panel
		JPanel panel = new JPanel(new MigLayout());
		setContentPane( panel );

		// Label for username
		labelUsername = new JLabel();
		labelUsername.setHorizontalAlignment( SwingConstants.LEFT );

		panel.setLayout( new MigLayout() );
		panel.add( labelUsername); 
		panel.add(toolbar,  "wrap");
		
		

		JButton backButton = new JButton( "Zurück" );
		backButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoSerieInfo( e );
			}
		});
		
		// Label for titel
		JLabel labelTitle = new JLabel( "Name:" );
				
		// Label for genres
		JLabel labelGenre = new JLabel( "Genre:" );
		
		// Genres
		JComboBox countries = new JComboBox();
		countries.setBounds( 130, 100, 200, 30 );
		Country[] country_values = Country.values();
			for ( Country country : country_values )
				countries.addItem( country.value() );
		
		

		// Label for year
		JLabel labelYear = new JLabel( "Jahr:" );

		// Label for firstaired
		JLabel labelFirstaired = new JLabel( "Erstaustrahlung:" );
				
		// Label for country
		JLabel labelCountry = new JLabel( "Produktionsland:" );
			
		// Label for overview
		JLabel labelOverview = new JLabel( "Beschreibung:" );
			
		// Label for episoderuntime
		JLabel labelEpisoderuntime = new JLabel( "Episodenlänge:" );
				
		// Label for network
		JLabel labelNetwork = new JLabel( "Sender:" );
				
		// Label for airday
		JLabel labelAirday = new JLabel( "Austrahlungstag:" );
		
		// Label for airtime
		JLabel labelAirtime = new JLabel( "Austrahlungszeit:" );
		

		// Label for images
		JLabel labelImages = new JLabel( "Bilder:" );

		
		
		// Input field for username
		inputTitle = new JTextField(20);
		
		// Input field for password
		inputYear = new JTextField(20);

		// Input field for fristname
		inputFirstaired = new JTextField(20);
				
		// Input field for lastname
		inputOverview = new JTextField(20);
				
		// Input field for Age
		inputEpisoderuntime = new JTextField(20); //Dropdown auswahl
				
		// Input field for Location
		inputAirtime = new JTextField(20);

		// Add items to panel
		panel.add( labelTitle );
		panel.add( inputTitle, "wrap" );

		panel.add( labelGenre, "wrap" );

		panel.add( labelYear );
		panel.add( inputYear, "wrap"  );
				
		panel.add( labelFirstaired );
		panel.add( inputFirstaired, "wrap"  );
				
		panel.add( labelCountry );
		panel.add( countries );

//				
//		panel.add( labelAge );
//		panel.add( inputAge, "wrap"  );
//				
//		panel.add( labelLocation );
//		panel.add( inputLocation, "wrap"  );
//				
//		panel.add( labelAbout );
//		panel.add( inputAbout, "wrap"  );
//				
//		panel.add( labelAvatar );
//		panel.add( inputAvatar, "wrap"  );
//				
//		panel.add( buttonNext, "split 2" );

		
	}

	/**
	 * Update GUI components
	 */
	public void update() {
		this.updateUserInfo();
		this.updateNodes();
	}

	private void updateUserInfo() {
		labelUsername.setText( "Hello "+ this.ch.getAccountAttribute( "username" ) );
	}

	private void updateNodes() {
		PubSubHandler psh = ConnectionHandler.getInstance().getPubSubHandler();
		for ( String node : psh.getAllNodes() )
			existingNodes.addItem( node );

	}

	public void pubsubtest() {

	}
	
	public void gotoSerieInfo( ActionEvent e ) {	
		TrackerClient.closeHomeAndGotoMySerie();
	};
	

	
	

}
