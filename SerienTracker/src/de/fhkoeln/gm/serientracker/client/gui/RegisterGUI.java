package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import net.miginfocom.swing.MigLayout;

import de.fhkoeln.gm.serientracker.client.TrackerClient;

public class RegisterGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	
	private JTextField inputUsername;
	private JPasswordField inputPassword;
	private JTextField inputFirstname;
	private JTextField inputLastname;
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
		JPanel panel = new JPanel(new MigLayout() );
		setContentPane( panel );

		// Label for username
		JLabel labelUsername = new JLabel( "Username:" );
		
		// Label for password
		JLabel labelPassword = new JLabel( "Password:" );

		// Label for firstname
		JLabel labelFirstname = new JLabel( "Firstname:" );

		// Label for lastname
		JLabel labelLastname = new JLabel( "Lastname:" );
		
		// Label for gender
		JLabel labelGender = new JLabel( "Gender:" );
	
		// Label for age
		JLabel labelAge = new JLabel( "Age:" );
	
		// Label for age
		JLabel labelLocation = new JLabel( "Location:" );
		
		// Label for about
		JLabel labelAbout = new JLabel( "About:" );
		
		// Label for avatar
		JLabel labelAvatar = new JLabel( "Avatar:" );

		// Input field for username
		inputUsername = new JTextField(20);

		// Input field for password
		inputPassword = new JPasswordField(20);

		// Input field for fristname
		inputFirstname = new JTextField(20);
		
		// Input field for lastname
		inputLastname = new JTextField(20);
		
		// Input field for Age
		inputAge = new JTextField(20); //Dropdown auswahl
		
		// Input field for Location
		inputLocation = new JTextField(20);

		// Input field for About
		inputAbout = new JTextField(20);

		// Input field for Avatar
		inputAvatar = new JTextField(20);

		//CheckBox for male
		JCheckBox maleCB = new JCheckBox("Male", false);
	    maleCB.setFocusable(false);
	    maleCB.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				maleChecked( e );
			}
		});
	     
	    //CheckBox for female
	    JCheckBox femaleCB = new JCheckBox("Female", false);
	    femaleCB.setFocusable(false);     
	    femaleCB.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				femaleChecked( e );
			}
		});

		

		// Next button
		JButton buttonNext = new JButton( "Weiter" );
		buttonNext.setBounds( 100, 300, 100, 25 );
		buttonNext.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoRegister2( e );
			}
		});


		// Add items to panel
		panel.setLayout( new MigLayout() );
		panel.add( labelUsername );
		panel.add( inputUsername, "wrap" );

		panel.add( labelPassword );
		panel.add( inputPassword, "wrap"  );

		panel.add( labelFirstname );
		panel.add( inputFirstname, "wrap"  );
		
		panel.add( labelLastname );
		panel.add( inputLastname, "wrap"  );
		
		panel.add( labelGender );
		panel.add( femaleCB, "split2"  );
		panel.add( maleCB, "wrap"  );
		
		panel.add( labelAge );
		panel.add( inputAge, "wrap"  );
		
		panel.add( labelLocation );
		panel.add( inputLocation, "wrap"  );
		
		panel.add( labelAbout );
		panel.add( inputAbout, "wrap"  );
		
		panel.add( labelAvatar );
		panel.add( inputAvatar, "wrap"  );
		
		panel.add( buttonNext, "split 2" );
	}

	/**
	 * Action Event for female checkBox, if checked, uncheck maleCB
	 *
	 * @param ActionEvent e
	 */
	protected void femaleChecked(ActionEvent e) {
	    JCheckBox source1 = (JCheckBox) e.getSource();
        boolean female = source1.isSelected() ;
        
        // male checkbox aus
        
	}

	/**
	 * Action Event for male checkBox, if checked, uncheck femaleCB
	 *
	 * @param ActionEvent e
	 */
	protected void maleChecked(ActionEvent e) {
		  JCheckBox source2 = (JCheckBox) e.getSource();
	        boolean male = source2.isSelected();  
	        
	        // female checkbox aus
	}

	/**
	 * Check user input and go to next register page
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
		
		TrackerClient.gotoRegister2();
	
	}
		
	


	

	/**
	 * Helper method to show an error dialog.
	 *
	 * @param String message
	 */
	private void errorDialog( String message ) {
		JOptionPane.showMessageDialog( null, message, "Error", JOptionPane.ERROR_MESSAGE );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
