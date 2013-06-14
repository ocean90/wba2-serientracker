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

public class EpisodeGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;

	
	private JComboBox existingNodes;

	public EpisodeGUI() {
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
				gotoHome(e);
			}
		});
	    
	    JButton settingButton = new JButton("Setting");
	    settingButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	       			gotoSetting(e);
	           }
	           
	    });
	    
	    // Button for logout
	    JButton exitButton = new JButton("Logout");
	    exitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);}
	           
	    });
	    
	    // Button for edit Serie for Admin
	    JButton backButton = new JButton("Back to Season");
//	    backButton.addActionListener(new ActionListener() {
//	 		public void actionPerformed(ActionEvent e) {
//	 				gotoSeason(e);
//	 			}
//	 		});		
	    
	    toolbar.add(homeButton);
	    toolbar.add(settingButton);
	    toolbar.add(exitButton);
	   
	   
	   
		
		// Content Panel
		JPanel panel = new JPanel(new MigLayout());
		setContentPane( panel );

		// Label for username
		labelUsername = new JLabel("Hello "+ this.ch.getAccountAttribute( "username" ));
		labelUsername.setHorizontalAlignment( SwingConstants.LEFT );

		panel.setLayout( new MigLayout() );
		panel.add( labelUsername); 
		
		// Label for episodetitel
		JLabel labelEpisodeTitle = new JLabel( "Episodetitle" );
				
		// Label for serietitel
		JLabel labelTitle = new JLabel( "Title" );
		
		// Label for image
		JLabel labelImage = new JLabel( "Image" );
		
		// Label for season
		JLabel labelSeason = new JLabel( "Season x" );
				
		// Label for overview
		JLabel labelOverview = new JLabel( "Overview" );
			
		// Label for airtime
		JLabel labelAirtime = new JLabel( "Airtime" );
		
		// Label for network
		JLabel labelNetwork = new JLabel( "Network" );
		
		// Label for episoderuntime
		JLabel labelEpisodenumber = new JLabel( "Episode 23" );
				
		

		// Button for edit Serie for Admin
		JButton editEpisodeButton = new JButton("Edit Episode");
//		settingButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				gotoEditEpisode(e);
//			}
//		});		
		
		// Add items to panel
		panel.add(toolbar, "wrap");
		panel.add(backButton);
		panel.add(labelImage, "wrap"); // "cell column row width height"
		
		panel.add( labelEpisodeTitle); 
		
		panel.add( labelSeason, "wrap"); 
		panel.add( labelEpisodenumber, "wrap"); 
		panel.add( labelNetwork, "wrap"); 
		panel.add( labelAirtime, "wrap"); 
		panel.add( labelOverview, "wrap"); 

		panel.add( editEpisodeButton, "wrap");  
		
	}

//	protected void gotoEditEpisode(ActionEvent e) {
//		TrackerClient.closeEpisodeAndGotoEditEpisode();
//	}
//
//	protected void gotoSeason(ActionEvent e) {
//		TrackerClient.closeEpisodeAndGotoSeason();
//		
//	}

	protected void gotoSetting(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	protected void gotoHome(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	protected void gotoEditSeason(ActionEvent e) {
		TrackerClient.closeSeasonAndGotoEditSeason();
		
	}

	protected void gotoEditEpisode() {
		TrackerClient.closeSeasonAndGotoEditEpisode();
		
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
	
	public void gotoEditSerie( ActionEvent e ) {	
		TrackerClient.closeSerieAndGotoEditSerie();
	};
	
	
	
	

}
