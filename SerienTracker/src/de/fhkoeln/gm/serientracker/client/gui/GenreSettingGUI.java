package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;

import de.fhkoeln.gm.serientracker.client.TrackerClient;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class GenreSettingGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;
	private JLabel labelText;
	private JLabel labelText2;


	private JComboBox existingNodes;

	public GenreSettingGUI() {
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
	    JToolBar toolbar2 = new JToolBar();
	    
	    JButton homeButton = new JButton("Home");
	    homeButton.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
					gotoHome( e );
			}
		});
	    
	    JButton settingButton = new JButton("Setting");
//	    settingButton.addActionListener(new ActionListener() {
//	           public void actionPerformed(ActionEvent e) {
//	       			TrackerClient.closeHomeAndGotoSetting();
//	           }
//	           
//	    });
	    
	    JButton exitButton = new JButton("Logout");
	    exitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);}
	           
	    });
	    
	    
	    toolbar.add(homeButton);
	    toolbar.add(settingButton);
	    toolbar.add(exitButton);
	    
	    
	    JButton profileButton = new JButton("Profile");
	    profileButton.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
					gotoProfile( e );
			}
		});
	    
	    JButton genreButton = new JButton("Genres");
//	 	genreButton.addActionListener( new ActionListener() {
//	 	public void actionPerformed( ActionEvent e ) {
//	 			gotoGenre( e );
//	 		}
//	 	});
	 	
	    
	 	JButton messageButton = new JButton("Message");
		messageButton.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
		 		gotoMessage( e );
		 	}
		 });
	 	    
	    
	    toolbar2.add(profileButton);
	    toolbar2.add(genreButton);
	    toolbar2.add(messageButton);
	   
	   
		
		// Content Panel
		JPanel panel = new JPanel(new MigLayout());
		setContentPane( panel );

		// Label for username
		labelUsername = new JLabel("Hello "+ this.ch.getAccountAttribute( "username" ));
		labelUsername.setHorizontalAlignment( SwingConstants.LEFT );
		
		labelText = new JLabel("Sie haben die Möglichkeit Benachrichtigung zu Serien bestimmter Genres zu erhalten. ");
		labelText2 = new JLabel("An welchen Genres sind sie interessiert?");
		
		panel.setLayout( new MigLayout() );
		panel.add( labelUsername); 
		panel.add(toolbar, "wrap");
		panel.add(toolbar2,"wrap");
		panel.add(labelText, "wrap");
		panel.add(labelText2, "wrap");
		
		JCheckBox action = new JCheckBox("Action", false);
	    action.setFocusable(false);
	    action.addActionListener(this);
	    
	    JCheckBox adventure = new JCheckBox("Adventure", false);
	    adventure.setFocusable(false);
	    adventure.addActionListener(this);
	    
	    JCheckBox animation = new JCheckBox("Animation", false);
	    animation.setFocusable(false);
	    animation.addActionListener(this);
		
	    JCheckBox children = new JCheckBox("Children", false);
	    children.setFocusable(false);
	    children.addActionListener(this);
		
	    JCheckBox comedy = new JCheckBox("Comedy", false);
	    comedy.setFocusable(false);
	    comedy.addActionListener(this);
	    
	    JCheckBox crime = new JCheckBox("Crime", false);
	    crime.setFocusable(false);
	    crime.addActionListener(this);
	    
	    JCheckBox drama = new JCheckBox("Drama", false);
	    drama.setFocusable(false);
	    drama.addActionListener(this);
	    
	    JCheckBox documentary = new JCheckBox("Documentary", false);
	    documentary.setFocusable(false);
	    documentary.addActionListener(this);
	    
	    JCheckBox fantasy = new JCheckBox("Fantasy", false);
	    fantasy.setFocusable(false);
	    fantasy.addActionListener(this);
	    
	    JCheckBox gameshow = new JCheckBox("Game Show", false);
	    gameshow.setFocusable(false);
	    gameshow.addActionListener(this);
	    
	    JCheckBox historical = new JCheckBox("Historical", false);
	    historical.setFocusable(false);
	    historical.addActionListener(this);
	    
	    JCheckBox horror = new JCheckBox("Horror", false);
	    horror.setFocusable(false);
	    horror.addActionListener(this);
	    
	    JCheckBox mystery = new JCheckBox("Mystery", false);
	    mystery.setFocusable(false);
	    mystery.addActionListener(this);
	    
	    JCheckBox news = new JCheckBox("News", false);
	    news.setFocusable(false);
	    news.addActionListener(this);
	    
	    JCheckBox romance = new JCheckBox("Romance", false);
	    romance.setFocusable(false);
	    romance.addActionListener(this);
	    
	    JCheckBox sciencefiction = new JCheckBox("Scifi", false);
	    sciencefiction.setFocusable(false);
	    sciencefiction.addActionListener(this);
	    
	    JCheckBox sport = new JCheckBox("Sport", false);
	    sport.setFocusable(false);
	    sport.addActionListener(this);
	    
	    JCheckBox suspence = new JCheckBox("Suspence", false);
	    suspence.setFocusable(false);
	    suspence.addActionListener(this);
	    
	    JCheckBox thriller = new JCheckBox("Thriller", false);
	    thriller.setFocusable(false);
	    thriller.addActionListener(this);
	    
	    JCheckBox western = new JCheckBox("Western", false);
	    western.setFocusable(false);
	    western.addActionListener(this);
	    
	    
		panel.add( action,"split4"  );
		panel.add( adventure );
		panel.add( animation);
		panel.add( children, "wrap");
		panel.add( comedy,"split4");
		panel.add( crime);
		panel.add( drama);
		panel.add( documentary,"wrap");
		panel.add( fantasy,"split4");
		panel.add( gameshow);
		panel.add( historical);
		panel.add( horror,"wrap");
		panel.add( mystery,"split4");
		panel.add( news);
		panel.add( romance);
		panel.add( sciencefiction, "wrap");
		panel.add( sport,"split4");
		panel.add( suspence);
		panel.add( thriller);
		panel.add( western, "wrap");

		
		// Next button
		JButton saveButton = new JButton( "Save" );
//		buttonNext.addActionListener( new ActionListener() {
//			public void actionPerformed( ActionEvent e ) {
//				gotoRegister2( e );
//				}
//		});
		
		panel.add( saveButton, "split 2" );
		
		
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
	
	public void gotoHome( ActionEvent e ) {	
		TrackerClient.closeGSAndGotoHome();
	}
	
	public void gotoProfile( ActionEvent e ) {	
		TrackerClient.closeGSAndGotoProfileSetting();
	}

	public void gotoMessage( ActionEvent e ) {	
		TrackerClient.closeGSAndGotoMessageSetting();
	}
	
	 public void actionPerformed(ActionEvent e) {

	        JCheckBox source = (JCheckBox) e.getSource();
	        boolean state = source.isSelected();     

	    }
	
}
