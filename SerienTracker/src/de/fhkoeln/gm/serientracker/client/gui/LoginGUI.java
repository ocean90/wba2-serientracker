package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;

import de.fhkoeln.gm.serientracker.client.TrackerClient;
import de.fhkoeln.gm.serientracker.xmpp.XMPPConfig;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JTextField inputUsername;
	private JPasswordField inputPassword;
	private JTextField inputHostname;
	private JTextField inputPort;

	public LoginGUI() {
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

	/**
	 * Set up the GUI components.
	 */
	public void initComponents() {
		// Close when exit
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// Set frame title
		setTitle( "LOGIN" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 600, 600 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		// Content Panel
		JPanel panel = new JPanel(new MigLayout());
		setContentPane( panel );

		// Label for username
		JLabel labelUsername = new JLabel( "Username:" );

		// Label for password
		JLabel labelPassword = new JLabel( "Passwort:" );

		// Label for hostname
		JLabel lableHostname = new JLabel( "Hostname:" );

		// Label for port
		JLabel labelPort = new JLabel( "Port:" );
		

		// Input field for username
		inputUsername = new JTextField(20);

		// Input field for password
		inputPassword = new JPasswordField(20);

		// Input field for hostname
		inputHostname = new JTextField(20);
		inputHostname.setText( XMPPConfig.hostname );

		// Input field for port
		inputPort = new JTextField();
		inputPort.setText( String.valueOf( XMPPConfig.port ) );

		// Login button
		JButton buttonNext = new JButton( "Weiter" );
		buttonNext.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				loginActionPerformed( e );
			}
		});

		

		panel.setLayout( new MigLayout() );
		panel.add( labelUsername );
		panel.add( inputUsername, "wrap" );
		panel.add( labelPassword );
		panel.add( inputPassword, "wrap" );
//		panel.add( lableHostname );
//		panel.add( inputHostname, "wrap" );
		
//		ßpanel.add( labelPort);
//		panel.add( inputPort, "wrap" );
		
		panel.add( buttonNext );
	}

	/**
	 * Check user input and try to connect/login to the XMPP server.
	 *
	 * @param ActionEvent e
	 */
	public void loginActionPerformed( ActionEvent e ) {
		// Check username
		String username = inputUsername.getText().trim();
		if ( username.length() <= 0 ) {
			errorDialog( "Name must not be empty." );
			inputUsername.requestFocusInWindow();
			return;
		}

		// Check password
		char[] password = inputPassword.getPassword();
		if ( password.length == 0 ) {
			errorDialog( "Password must not be empty." );
			inputPassword.requestFocusInWindow();
			return;
		}

		// Check hostname
		String hostname = inputHostname.getText().trim();
		if ( hostname.length() <= 0 ) {
			errorDialog( "Hostname must not be empty." );
			inputHostname.requestFocusInWindow();
			return;
		}

		// Check port
		int port = -1;
		try {
			port = Integer.parseInt( inputPort.getText() );
		} catch ( NumberFormatException e1 ) {}

		if ( port < 0 || port > 65535 ) {
			errorDialog( "Port must not be empty and between 0 and 65535." );
			inputPort.requestFocusInWindow();
			return;
		}

		// Try to connect to the server
		if ( this.ch.connect( XMPPConfig.hostname, XMPPConfig.port  ) ) {
			// Try to login
			if ( this.ch.login( username, password, "xmppclient" ) ) {
				TrackerClient.closeLoginAndGotoHome();
			} else {
				errorDialog( "Login failed." );
				return;
			}
		} else {
			errorDialog( "Connection failed." );
			return;
		}

	};

	/**
	 * Helper method to show an error dialog
	 *
	 * @param String message
	 */
	private void errorDialog( String message ) {
		JOptionPane.showMessageDialog( null, message, "Error", JOptionPane.ERROR_MESSAGE );
	}

}
