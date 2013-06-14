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

public class SeasonGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;

	
	private JComboBox existingNodes;

	public SeasonGUI() {
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
				TrackerClient.closeSeasonAndGotoHome();
			}
		});
	    
	    JButton settingButton = new JButton("Setting");
	    settingButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	       			TrackerClient.closeSeasonAndGotoProfileSetting();
	           }
	           
	    });
	    
	    JButton exitButton = new JButton("Logout");
	    exitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);}
	           
	    });
	    
	 // Button for edit Serie for Admin
	 JButton backButton = new JButton("Back to Serie");
	 backButton.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 				TrackerClient.closeSeasonAndGotoSerie();
	 			}
	 		});		
	    
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
		
		// Label for titel
		JLabel labelTitle = new JLabel( "Titel" );
				
		// Label for image
		JLabel labelImage = new JLabel( "Image" );
		
		// Label for season
		JLabel labelSeason = new JLabel( "Season x" );
				
		// Label for overview
		JLabel labelOverview = new JLabel( "Beschreibung" );
			
		// Label for episoderuntime
		JLabel labelEpisodes = new JLabel( "Episodes 23" );
				
		
		// Button for add Season
		JButton addEpisodeButton = new JButton("Add Episode");
		settingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoEditEpisode();
		     }
		           
		});

		// Button for edit Serie for Admin
		JButton editSeasonButton = new JButton("Edit Season");
		settingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoEditSeason(e);
			}
		});		
		
//		// Button for edit Serie for Admin
//		JButton backButton = new JButton("Back");
//		backButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//					TrackerClient.closeHomeAndGotoProfileSetting();
//				}
//				});		


		
		panel.add(toolbar, "wrap");
		panel.add(backButton);
		panel.add(editSeasonButton);
		panel.add(labelImage, "wrap"); // "cell column row width height"
		
		

		panel.add( labelSeason); 
		panel.add( labelTitle, "wrap"); 
		panel.add( labelOverview); 
		panel.add( labelEpisodes, "wrap"); 
		
		panel.add( addEpisodeButton, "wrap");  
		
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
