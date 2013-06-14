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

public class SearchGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;
	private JTextField inputSearch;

	
	private JComboBox existingNodes;

	public SearchGUI() {
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

		JLabel labelSearch = new JLabel( "Suche:" );
		inputSearch = new JTextField(20);
		
		JLabel labelEpisodesInfo = new JLabel( "Ihre nächsten Episoden:" );

		JLabel labelSerie = new JLabel( "Serien die Ihnen gefallen könnten:" );
		
		JLabel labelSerieCover = new JLabel( "Cover" );
		JLabel labelSerieCover2 = new JLabel( "Cover" );
		JLabel labelSerieCover3 = new JLabel( "Cover" );

		JLabel labelSerieTitle = new JLabel( "Title" );
		JLabel labelSerieTitle2 = new JLabel( "Title" );
		JLabel labelSerieTitle3 = new JLabel( "Title" );
		
		JLabel labelSerieEp = new JLabel( "Episode" );
		JLabel labelSerieEp2 = new JLabel( "Episode" );
		JLabel labelSerieEp3 = new JLabel( "Episode" );
		
		JLabel labelSerieDay = new JLabel( "Day" );
		JLabel labelSerieDay2 = new JLabel( "Day" );
		JLabel labelSerieDay3 = new JLabel( "Day" );
		

		

		JButton serieButton = new JButton( "Meine Serien" );
		serieButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoMySerie( e );
			}
		});
		
		JButton listButton = new JButton( "Meine Listen" );
		listButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoMyList( e );
			}
		});
		
		
		panel.setLayout( new MigLayout() );
		panel.add( labelUsername); 
		panel.add(toolbar,  "wrap");

		panel.add(labelSearch);
		panel.add(inputSearch, "wrap");
		
		panel.add(labelEpisodesInfo, "wrap");
		
		panel.add(labelSerieCover );
		panel.add(labelSerieCover2 );
		panel.add(labelSerieCover3, "wrap");
		
		panel.add(labelSerieTitle);
		panel.add(labelSerieTitle2);
		panel.add(labelSerieTitle3, "wrap");

		panel.add(labelSerieEp);
		panel.add(labelSerieEp2);
		panel.add(labelSerieEp3, "wrap");
		
		panel.add(labelSerieDay);
		panel.add(labelSerieDay2);
		panel.add(labelSerieDay3, "wrap");
		
		panel.add(labelSerie, "wrap");

		panel.add(serieButton, "wrap");
		panel.add(listButton, "wrap");
		
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
