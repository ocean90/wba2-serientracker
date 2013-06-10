package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

public class MessageSettingGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;

	private JComboBox existingNodes;

	public MessageSettingGUI() {
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
	 	genreButton.addActionListener( new ActionListener() {
	 	public void actionPerformed( ActionEvent e ) {
	 			gotoGenre( e );
	 		}
	 	});
	 	
	 	JButton messageButton = new JButton("Message");
//		messageButton.addActionListener( new ActionListener() {
//		public void actionPerformed( ActionEvent e ) {
//		 		gotoMessage( e );
//		 	}
//		 });
	 	    
	    
	    toolbar2.add(profileButton);
	    toolbar2.add(genreButton);
	    toolbar2.add(messageButton);
	   
	   
		
		// Content Panel
		JPanel panel = new JPanel(new MigLayout());
		setContentPane( panel );

		// Label for username
		labelUsername = new JLabel("Hello "+ this.ch.getAccountAttribute( "username" ));
		labelUsername.setHorizontalAlignment( SwingConstants.LEFT );

		panel.setLayout( new MigLayout() );
		panel.add( labelUsername); 
	
		panel.add(toolbar, "wrap");
		panel.add(toolbar2, "wrap");
		
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
		TrackerClient.closeMSAndGotoHome();
	}

	public void gotoProfile( ActionEvent e ) {	
		TrackerClient.closeMSAndGotoProfileSetting();
	}
	
	public void gotoGenre( ActionEvent e ) {	
		TrackerClient.closeMSAndGotoGenreSetting();
	}

}
