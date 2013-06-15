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

public class MySerieGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;
	private JTextField inputSearch;


	private JComboBox existingNodes;

	public MySerieGUI() {
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
			gotoHome( e );
			}
		});

	    JButton settingButton = new JButton("Setting");
	    settingButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	       			TrackerClient.closeMySerieAndGotoProfileSetting();
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
		labelUsername = new JLabel("Hello "+ this.ch.getAccountAttribute( "username" ) );
		labelUsername.setHorizontalAlignment( SwingConstants.LEFT );

		JLabel labelSearch = new JLabel( "Suche:" );
		inputSearch = new JTextField(20);

		JLabel labelEpisodesInfo = new JLabel( "Nächste Episode:" );
		labelEpisodesInfo.setHorizontalAlignment( SwingConstants.RIGHT);


		JLabel labelProgress = new JLabel( "Episode 6/12" );
		JLabel labelProgress2 = new JLabel( "Episode 6/12" );
		JLabel labelProgress3 = new JLabel( "Episode 6/12" );


		JLabel labelSerieTitle = new JLabel( "Title" );
		JLabel labelSerieTitle2 = new JLabel( "Title" );
		JLabel labelSerieTitle3 = new JLabel( "Title" );

		JLabel labelSerieEp = new JLabel( "Episode" );
		JLabel labelSerieEp2 = new JLabel( "Episode" );
		JLabel labelSerieEp3 = new JLabel( "Episode" );

		JLabel labelSerieDay = new JLabel( "Day" );
		JLabel labelSerieDay2 = new JLabel( "Day" );
		JLabel labelSerieDay3 = new JLabel( "Day" );

		JButton coverButton = new JButton("Cover");
		    coverButton.addActionListener(new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		               gotoSerie( e );}

		    });

		    JButton coverButton2 = new JButton("Cover");
		    coverButton2.addActionListener(new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		               gotoSerie( e );}

		    });

		    JButton coverButton3 = new JButton("Cover");
		    coverButton3.addActionListener(new ActionListener() {
		           public void actionPerformed(ActionEvent e) {
		               gotoSerie( e );}

		    });


		panel.setLayout( new MigLayout() );
		panel.add( labelUsername);
		panel.add(toolbar,  "wrap");

		panel.add(labelSearch);
		panel.add(inputSearch, "wrap");

		panel.add(labelEpisodesInfo, "wrap");

		panel.add(coverButton );
		panel.add(labelSerieTitle);
		panel.add(labelSerieEp, "wrap");
		panel.add(labelProgress);
		panel.add(labelSerieDay, "wrap");

		panel.add(coverButton2  );
		panel.add(labelSerieTitle2);
		panel.add(labelSerieEp2, "wrap");
		panel.add(labelProgress2);
		panel.add(labelSerieDay2, "wrap");

		panel.add( coverButton3  );
		panel.add(labelSerieTitle3);
		panel.add(labelSerieEp3, "wrap");
		panel.add(labelProgress3);
		panel.add(labelSerieDay3, "wrap");




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
		TrackerClient.closeMySerieAndGotoHome();
	};

	public void gotoSerie( ActionEvent e ) {
		TrackerClient.closeMySerieAndGotoSerie();
	};



}
