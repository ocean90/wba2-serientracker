package de.fhkoeln.gm.serientracker.xmpp.gui;

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

import de.fhkoeln.gm.serientracker.xmpp.Config;
import de.fhkoeln.gm.serientracker.xmpp.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.clients.UserClient;

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
		setBounds( 0, 0, 300, 220 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		// Content Panel
		JPanel panel = new JPanel();
		setContentPane( panel );
		panel.setLayout( null ); // Parent size

		// Label for username
		JLabel labelUsername = new JLabel( "Username:" );
		labelUsername.setBounds( 20, 20, 90, 16 );

		// Label for password
		JLabel labelPassword = new JLabel( "Password:" );
		labelPassword.setBounds( 20, 50, 90, 16 );

		// Label for hostname
		JLabel lableHostname = new JLabel( "Hostname:" );
		lableHostname.setBounds( 20, 95, 90, 16 );

		// Label for port
		JLabel labelPort = new JLabel( "Port:" );
		labelPort.setBounds( 20, 122, 90, 16);

		// Input field for username
		inputUsername = new JTextField();
		inputUsername.setText( "test" ); // TODO
		inputUsername.setBounds( 100, 15, 180, 25 );

		// Input field for password
		inputPassword = new JPasswordField();
		inputPassword.setText( "test" ); // TODO
		inputPassword.setBounds( 100, 45, 180, 25 );

		// Input field for hostname
		inputHostname = new JTextField();
		inputHostname.setText( Config.hostname );
		inputHostname.setBounds( 100, 90, 180, 25 );

		// Input field for port
		inputPort = new JTextField();
		inputPort.setText( String.valueOf( Config.port ) );
		inputPort.setBounds( 100, 120, 180, 25 );

		// Login button
		JButton buttonLogin = new JButton( "Login" );
		buttonLogin.setBounds( 183, 160, 100, 25 );
		buttonLogin.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				loginActionPerformed( e );
			}
		});

		// Register button
		JButton buttonRegister = new JButton( "Register" );
		buttonRegister.setBounds( 15, 160, 100, 25 );
		buttonRegister.setEnabled( false );

		// Add items to panel
		panel.add( labelUsername );
		panel.add( labelPassword );
		panel.add( lableHostname );
		panel.add( labelPort );
		panel.add( inputUsername );
		panel.add( inputPassword );
		panel.add( inputHostname );
		panel.add( inputPort );
		panel.add( buttonLogin );
		panel.add( buttonRegister );
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
		if ( this.ch.connect( hostname, port ) ) {
			// Try to login
			if ( this.ch.login( username, password ) ) {
				UserClient.closeLogin();
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
	 * Helper method to show an error dialog.
	 *
	 * @param String message
	 */
	private void errorDialog( String message ) {
		JOptionPane.showMessageDialog( null, message, "Error", JOptionPane.ERROR_MESSAGE );
	}

}
