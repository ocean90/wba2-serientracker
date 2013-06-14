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


		// Label for text
		JLabel labelText = new JLabel( "Was möchten sie machen?" );


		// Login button
		JButton buttonLogin = new JButton( "Anmelden" );
		buttonLogin.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoLogin( e );
			}
		});

		// Register button
		JButton buttonRegister = new JButton( "Registrieren" );
		buttonRegister.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoRegister( e );
			}
		});

		// Add items to panel
		panel.setLayout( new MigLayout() );
		panel.add( labelWelcome, "wrap"); // "cell column row width height"
		panel.add( labelText, "wrap " );
		panel.add( buttonLogin);
		panel.add( buttonRegister );

	}


	/**
	 * Goto Login page
	 *
	 * @param ActionEvent e
	 */
	public void gotoLogin( ActionEvent e ) {
		TrackerClient.closeStartAndGotoLogin();
	}

	
	/**
	 * Goto Register page
	 *
	 * @param ActionEvent e
	 */
	public void gotoRegister( ActionEvent e ) {
		TrackerClient.closeStartAndGotoRegister();
	}


}
