package de.fhkoeln.gm.serientracker.xmpp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import de.fhkoeln.gm.serientracker.xmpp.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.PubSubHandler;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;

	private JComboBox existingNodes;

	public MainGUI() {
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
		setResizable( false );

		// Content Panel
		JPanel panel = new JPanel();
		setContentPane( panel );
		panel.setLayout( null ); // Parent size

		// Label for username
		labelUsername = new JLabel();
		labelUsername.setHorizontalAlignment( SwingConstants.RIGHT );
		labelUsername.setBounds( 0, 0, 600, 20 );
		labelUsername.setBorder( new EmptyBorder( 10, 0, 0, 10 ) );

		// Label for existing nodes
		JLabel labelExistingNodes = new JLabel();
		labelExistingNodes.setText( "Existing Nodes:" );
		labelExistingNodes.setBounds( 30, 30, 120, 30 );

		// Existing nodes
		existingNodes = new JComboBox();
		existingNodes.setBounds( 130, 30, 200, 30 );

		// Send a test message
		JButton buttonTest = new JButton( "Send test message" );
		buttonTest.setBounds( 330, 30, 200, 30 );
		final MainGUI self = this;
		buttonTest.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				ConnectionHandler.getInstance().testPubSub();
				self.updateNodes();
			}
		});

		panel.add( labelUsername );
		panel.add( labelExistingNodes );
		panel.add( existingNodes );
		panel.add( buttonTest );
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

}
