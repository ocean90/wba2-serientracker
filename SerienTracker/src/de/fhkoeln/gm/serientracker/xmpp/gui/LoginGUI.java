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

import net.miginfocom.swing.MigLayout;

import de.fhkoeln.gm.serientracker.xmpp.XMPPConfig;
import de.fhkoeln.gm.serientracker.xmpp.XMPPClient;
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
		setTitle( "XMPPCLIENT | LOGIN" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 300, 220 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		// Content Panel
		JPanel panel = new JPanel( new MigLayout( "", "[100][grow]", "[][][][][grow]" ) );
		add( panel );

		// Label for username
		JLabel labelUsername = new JLabel( "Username:" );

		// Label for password
		JLabel labelPassword = new JLabel( "Password:" );

		// Label for hostname
		JLabel lableHostname = new JLabel( "Hostname:" );

		// Label for port
		JLabel labelPort = new JLabel( "Port:" );

		// Input field for username
		inputUsername = new JTextField();
		inputUsername.setText( "test" ); // TODO

		// Input field for password
		inputPassword = new JPasswordField();
		inputPassword.setText( "test" ); // TODO

		// Input field for hostname
		inputHostname = new JTextField();
		inputHostname.setText( XMPPConfig.hostname );

		// Input field for port
		inputPort = new JTextField();
		inputPort.setText( String.valueOf( XMPPConfig.port ) );

		// Login button
		JButton buttonLogin = new JButton( "Login" );
		buttonLogin.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				loginActionPerformed( e );
			}
		});

		// Register button
		JButton buttonRegister = new JButton( "Register" );
		buttonRegister.setEnabled( false );

		// Add items to panel
		panel.add( labelUsername, "cell 0 0" );
		panel.add( labelPassword, "cell 0 1"  );
		panel.add( lableHostname, "cell 0 2" );
		panel.add( labelPort, "cell 0 3" );

		panel.add( inputUsername, "cell 1 0, grow" );
		panel.add( inputPassword, "cell 1 1, grow" );
		panel.add( inputHostname, "cell 1 2, grow" );
		panel.add( inputPort, "cell 1 3, grow" );

		panel.add( buttonRegister, "cell 0 4" );
		panel.add( buttonLogin, "cell 1 4, right" );
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
			if ( this.ch.login( username, password, "xmppclient" ) ) {
				XMPPClient.closeLogin();
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
