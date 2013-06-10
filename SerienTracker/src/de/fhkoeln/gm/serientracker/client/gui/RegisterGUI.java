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

import de.fhkoeln.gm.serientracker.client.TrackerClient;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	
	private JTextField inputUsername;
	private JPasswordField inputPassword;
	private JTextField inputFirstname;
	private JTextField inputLastname;
	private JTextField inputGender; //Checkbox
	private JTextField inputAge; //Dropdown auswahl
	private JTextField inputLocation;
	private JTextField inputAbout;
	private JTextField inputAvatar;


	public RegisterGUI() {
		initComponents();
	}

	/**
	 * Set up the GUI components.
	 */
	public void initComponents() {
		// Close when exit
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// Set frame title
		setTitle( "Register" );

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
		JLabel labelUsername = new JLabel( "Username:" );
		labelUsername.setBounds( 100, 20, 90, 16 );

		
		// Label for password
		JLabel labelPassword = new JLabel( "Password:" );
		labelUsername.setBounds( 20, 50, 90, 16 );


		// Label for firstname
		JLabel labelFirstname = new JLabel( "Firstname:" );
		labelUsername.setBounds( 20, 80, 90, 16 );


		// Label for lastname
		JLabel labelLastname = new JLabel( "Lastname:" );
		labelUsername.setBounds( 20, 110, 90, 16 );

		
		// Label for gender
		JLabel labelGender = new JLabel( "Gender:" );
		labelUsername.setBounds( 20, 140, 90, 16 );

		
		// Label for age
		JLabel labelAge = new JLabel( "Age:" );
		labelUsername.setBounds( 20, 170, 90, 16 );

		
		// Label for age
		JLabel labelLocation = new JLabel( "Location:" );
		labelUsername.setBounds( 20, 200, 90, 16 );

		
		// Label for about
		JLabel labelAbout = new JLabel( "About:" );
		labelUsername.setBounds( 20, 230, 90, 16 );

		
		// Label for avatar
		JLabel labelAvatar = new JLabel( "Avatar:" );
		labelUsername.setBounds( 20, 260, 90, 16 );

		

		// Input field for username
		inputUsername = new JTextField();
		inputUsername.setBounds( 100, 20, 90, 16 );


		// Input field for password
		inputPassword = new JPasswordField();
		inputPassword.setBounds( 100, 50, 90, 16 );


		// Input field for fristname
		inputFirstname = new JTextField();
		inputFirstname.setBounds( 100, 80, 90, 16 );

		
		// Input field for lastname
		inputLastname = new JTextField();
		inputLastname.setBounds( 100, 110, 90, 16 );

		
		// Input field for gender
		inputGender = new JTextField(); //Checkbox
		inputGender.setBounds( 100, 1400, 90, 16 );

		
		// Input field for Age
		inputAge = new JTextField(); //Dropdown auswahl
		inputAge.setBounds( 100, 170, 90, 16 );

		
		// Input field for Location
		inputLocation = new JTextField();
		inputLocation.setBounds( 100, 200, 90, 16 );

		
		// Input field for About
		inputAbout = new JTextField();
		inputAbout.setBounds( 100, 230, 90, 16 );

		
		// Input field for Avatar
		inputAvatar = new JTextField();
		inputAvatar.setBounds( 100, 260, 90, 16 );

		
		

		// Next button
		JButton buttonNext = new JButton( "Weiter" );
		buttonNext.setBounds( 100, 300, 100, 25 );
		buttonNext.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoRegister2( e );
			}
		});


		// Add items to panel
		panel.add( labelUsername );
		panel.add( inputUsername );

		panel.add( labelPassword );
		panel.add( inputPassword );

		panel.add( labelFirstname );
		panel.add( inputFirstname );
		
		panel.add( labelLastname );
		panel.add( inputLastname );
		
		panel.add( labelGender );
		panel.add( inputGender );
		
		panel.add( labelAge );
		panel.add( inputAge );
		
		panel.add( labelLocation );
		panel.add( inputLocation );
		
		panel.add( labelAbout );
		panel.add( inputAbout );
		
		panel.add( labelAvatar );
		panel.add( inputAvatar );
		
		panel.add( buttonNext );
	}

	/**
	 * Check user input and try to connect/login to the XMPP server.
	 *
	 * @param ActionEvent e
	 */
	public void gotoRegister2( ActionEvent e ) {
		// Check username
		String username = inputUsername.getText().trim();
		if ( username.length() <= 0 ) {
			errorDialog( "Username must not be empty." );
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
		
		TrackerClient.closeRegisterandGotoMain();
	
	}
		



	

	/**
	 * Helper method to show an error dialog.
	 *
	 * @param String message
	 */
	private void errorDialog( String message ) {
		JOptionPane.showMessageDialog( null, message, "Error", JOptionPane.ERROR_MESSAGE );
	}

}
