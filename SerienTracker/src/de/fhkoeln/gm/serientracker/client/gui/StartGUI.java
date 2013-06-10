package de.fhkoeln.gm.serientracker.client.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.client.TrackerClient;


public class StartGUI extends JFrame {

	private static final long serialVersionUID = 1L;


	public StartGUI() {
		initComponents();
	}

	/**
	 * Set up the GUI components.
	 */
	public void initComponents() {
		// Close when exit
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// Set frame title
		this.setTitle( "Serientracker" );

		// Set frame size
		this.setBounds( 0, 0, 600, 600 );

		// Center frame on screen
		this.setLocationRelativeTo( null );

		// Disable resizing
		this.setResizable( false );

		// Content Panel
		JPanel panel = new JPanel( new MigLayout() );
		this.setContentPane( panel );

		// Label for welcome
		JLabel labelWelcome = new JLabel( "Willkommen!" );
		labelWelcome.setBounds( 20, 20, 90, 16 );


		// Label for text
		JLabel labelText = new JLabel( "Was möchten sie machen?" );
		labelText.setBounds( 20, 40, 200, 16 );


		// Login button
		JButton buttonLogin = new JButton( "Anmelden" );
		buttonLogin.setBounds( 20, 60, 90, 16 );
		buttonLogin.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoLogin( e );
			}
		});

		// Register button
		JButton buttonRegister = new JButton( "Registrieren" );
		buttonRegister.setBounds( 150, 60, 90, 16 );
		buttonRegister.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoRegister( e );
			}
		});

		// Add items to panel
		panel.setLayout( new MigLayout() );
		panel.add( labelWelcome, "wrap" );
		panel.add( labelText, "wrap" );
		panel.add( buttonLogin );
		panel.add( buttonRegister );

	}


	/**
	 * Check user input and try to connect/login to the XMPP server.
	 *
	 * @param ActionEvent e
	 */
	public void gotoLogin( ActionEvent e ) {
		TrackerClient.closeStartandGotoLogin();
	}

	public void gotoRegister( ActionEvent e ) {
		TrackerClient.closeStartandGotoRegister();
	}


}
