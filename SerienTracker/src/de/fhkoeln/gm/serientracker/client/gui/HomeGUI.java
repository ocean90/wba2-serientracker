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

public class HomeGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;
	private JTextField inputSearch;

	
	private JComboBox existingNodes;

	public HomeGUI() {
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
		
		
		JLabel labelSearch = new JLabel( "Suche:" );
		inputSearch = new JTextField(20);
		
		panel.add(labelSearch, "wrap");

		JLabel labelEpisodes = new JLabel( "Ihre nächsten Episoden:" );
		panel.add(labelEpisodes, "wrap");
		
		JButton serieButton = new JButton( "Meine Serien" );
		serieButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoMySerie( e );
			}
		});
		
		panel.add(serieButton, "wrap");
		
		JButton listButton = new JButton( "Meine Listen" );
		listButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoMyList( e );
			}
		});

		panel.add(listButton, "wrap");
		
		JLabel labelSerie = new JLabel( "Serien die Ihnen gefallen könnten:" );
		panel.add(labelSerie, "wrap");
		
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
	
	public void gotoMySerie( ActionEvent e ) {	
		TrackerClient.closeHomeAndGotoMySerie();
	};
	
	public void gotoMyList( ActionEvent e ) {	
		TrackerClient.closeHomeAndGotoMyList();
	};
	
	

}
