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
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import de.fhkoeln.gm.serientracker.client.TrackerClient;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class SerieGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;

	
	private JComboBox existingNodes;

	public SerieGUI() {
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
	    homeButton.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
				TrackerClient.closeSerieAndGotoHome();
			}
		});
	    
	    JButton settingButton = new JButton("Setting");
	    settingButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	       			TrackerClient.closeSerieAndGotoProfileSetting();
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
		
		// Label for titel
		JLabel labelTitle = new JLabel( "Titel" );
				
		// Label for genres
		JLabel labelGenre = new JLabel( "Genre" );
		
		// Label for year
		JLabel labelYear = new JLabel( "Jahr" );

		// Label for firstaired
		JLabel labelFirstaired = new JLabel( "Erstaustrahlung" );
				
		// Label for country
		JLabel labelCountry = new JLabel( "Produktionsland" );
			
		// Label for overview
		JLabel labelOverview = new JLabel( "Beschreibung" );
			
		// Label for episoderuntime
		JLabel labelEpisoderuntime = new JLabel( "Episodenlänge" );
				
		// Label for network
		JLabel labelNetwork = new JLabel( "Sender " );
				
		// Label for airday
		JLabel labelAirday = new JLabel( "Austrahlungstag" );
		
		// Label for airtime
		JLabel labelAirtime = new JLabel( "Austrahlungszeit" );
		
		// Label for images
		JLabel labelImages = new JLabel( "Cover" );
		
		// Label for seasons
		JLabel labelSeasons = new JLabel( "Seasons" );
		
		// Label for next Episode
		JLabel labelNextEp = new JLabel( "Next Episode" );
		
		// Button for episode
		JButton episodeButton = new JButton("Image");
		episodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrackerClient.closeSerieAndGotoEpisode();	}
						           
		});
		
		JLabel labelNextEpTitle = new JLabel( "Title" );
		JLabel labelNextEpAirdate = new JLabel( "Airdate" );

		
		
		// Label for last seen Episode
		JLabel labelLastEp = new JLabel( "Last seen Episode" );
		// Button for episode
		JButton episodeButton2 = new JButton("Image");
		episodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrackerClient.closeSerieAndGotoEpisode();}
								           
		});
		
		JLabel labelLastEpTitle = new JLabel( "Title" );
		JLabel labelLastEpAirdate = new JLabel( "Airdate" );
	
		
		
		// Button for add Season
		JButton addSeasonButton = new JButton("Add Season");
		settingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrackerClient.closeHomeAndGotoProfileSetting();
		     }
		           
		});
		
		// Button for add Season
		JButton seasonButton = new JButton("1");
		settingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoSeason(e);
				    }
				           
				});
		
//		// Button for add Serie to List for User
//		JButton addSerieButton = new JButton("Add to List");
//		settingButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				TrackerClient.closeHomeAndGotoProfileSetting();
//			}
//		});
		
		// Button for edit Serie for Admin
		JButton editSerieButton = new JButton("Edit Serie");
		settingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoEditSerie(e);
			}
		});		
		
//		// Button for edit Serie for Admin
//		JButton backButton = new JButton("Back");
//		backButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//					TrackerClient.closeHomeAndGotoProfileSetting();
//				}
//				});		


		
		panel.add(toolbar, "cell 4 0");
		panel.add(labelImages, "cell 0 2 2 "); // "cell column row width height"
		
		
//		panel.add( addSerieButton, "wrap"); if admin ==false
		panel.add( editSerieButton, "cell 4 4"); 


		panel.add( labelTitle, "cell 1 5"); 
		panel.add( labelYear, "cell 2 5"); 
		panel.add( labelCountry, "cell 3 5"); 
		panel.add( labelOverview, "cell 0 7"); 
		panel.add( labelGenre, "cell 3 7");  
		panel.add( labelFirstaired, "cell 3 8");
		panel.add( labelEpisoderuntime, "cell 3 9"); 
		panel.add( labelNetwork, "cell 1 13"); 
		panel.add( labelAirday, "cell 2 13"); 
		panel.add( labelAirtime, "cell 3 13"); 
		
		panel.add( labelSeasons, "cell 0 16"); 
		panel.add( seasonButton, "cell 1 16"); 

		panel.add( addSeasonButton, "cell 3 16");
		panel.add( labelLastEp, "cell 0 18");  
		panel.add( labelNextEp, "cell 4 18");
		
		panel.add( episodeButton, "cell 0 19");  
		panel.add( labelLastEpTitle, "cell 1 19");  
		panel.add( episodeButton2, "cell 3 19");  

		panel.add( labelNextEpTitle, "cell 4 19");  
		panel.add( labelLastEpAirdate, "cell 1 20");  
		panel.add( labelNextEpAirdate, "cell 4 20");  


		
	}

//	protected void gotoEpisode(ActionEvent e) {
//		TrackerClient.closeSerieAndGotoEpisode();		
//	}

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
	
	public void gotoEditSerie( ActionEvent e ) {	
		TrackerClient.closeSerieAndGotoEditSerie();
	};
	
	public void gotoSeason( ActionEvent e ) {	
		TrackerClient.closeSerieAndGotoSeason();
	};
	
	

}
