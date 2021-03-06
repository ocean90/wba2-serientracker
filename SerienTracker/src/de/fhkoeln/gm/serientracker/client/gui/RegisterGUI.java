package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.client.TrackerClient;
import de.fhkoeln.gm.serientracker.client.utils.HTTPClient;
import de.fhkoeln.gm.serientracker.client.utils.HTTPClient.HTTPMethod;
import de.fhkoeln.gm.serientracker.jaxb.Gender;
import de.fhkoeln.gm.serientracker.jaxb.Genre;
import de.fhkoeln.gm.serientracker.jaxb.ObjectFactory;
import de.fhkoeln.gm.serientracker.jaxb.Setting;
import de.fhkoeln.gm.serientracker.jaxb.Settings;
import de.fhkoeln.gm.serientracker.jaxb.User;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.XMPPConfig;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

/**
 * Provides the register GUI to add a new user.
 *
 * @author Dennis Meyer
 */
public class RegisterGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Holds the cardlayout
	private CardLayout registerCardLayout;

	// GUI components
	private JPanel RegisterPanels;
	private JPanel UserinformationPanel;
	private JPanel PrioritiesPanel;
	private JPanel genreBox;
	private JTextField inputUsername;
	private JPasswordField inputPassword;
	private JTextField inputFirstname;
	private JTextField inputLastname;
	private JTextField inputAge;
	private JTextField inputLocation;
	private JTextArea inputAbout;
	private JButton btnNextPage;
	private JButton btnCancel1;
	private JButton btnCancel2;
	private JButton btnSave;
	private JRadioButton male;
	private JRadioButton female;

	/**
	 * Constructor.
	 * Sets the UI look.
	 *
	 * @param Context context
	 */
	public RegisterGUI( ) {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e ) {
		}

		initComponents();
	}

	/**
	 * Sets up the GUI components.
	 */
	private void initComponents() {
		// Set frame title
		setTitle( "SERIENTRACKER | REGISTER" );

		// Disable resizing
		setResizable( false );

		// Set frame size
		setBounds( 0, 0, 600, 450 );
		// Center frame on screen
		setLocationRelativeTo( null );

		// Show login GUI when closing this frame
		this.addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent e ) {
				TrackerClient.showLogin();
			}
		} );


		/********
		 * PANELS
		 */

		// Use CardLayout as layout manager
		registerCardLayout = new CardLayout();
		RegisterPanels = new JPanel( registerCardLayout );
		add( RegisterPanels );

		// Get the content panels
		RegisterPanels.add( this.getUserinformationPanel(), "USER" );
		RegisterPanels.add( this.getPrioritiesPanel(), "PRIORITIES" );

		registerCardLayout.show( RegisterPanels, "USER" );
	}

	/**
	 * Returns the panel for a new series.
	 *
	 * @return JPanel
	 */
	private JPanel getUserinformationPanel() {
		// Set up the panel
		UserinformationPanel = new JPanel( new MigLayout( "", "[150][grow]" ) );

		// Print the labels
		UserinformationPanel.add( new JLabel( "Insert your information to set up a new account" ), "span, gapbottom 10" );

		UserinformationPanel.add( new JLabel( "Username: *" ), "cell 0 1" );
		UserinformationPanel.add( new JLabel( "Password: *" ), "cell 0 2" );
		UserinformationPanel.add( new JLabel( "Firstname:" ), "cell 0 3" );
		UserinformationPanel.add( new JLabel( "Lastname:" ), "cell 0 4" );
		UserinformationPanel.add( new JLabel( "Gender:" ), "cell 0 5" );
		UserinformationPanel.add( new JLabel( "Age:" ), "cell 0 6" );
		UserinformationPanel.add( new JLabel( "Location:" ), "cell 0 7" );
		UserinformationPanel.add( new JLabel( "About:" ), "cell 0 8" );

		UserinformationPanel.add( new JLabel( "Fields marked with * are required!" ), "cell 0 9 2 1" );

		// Input field for username
		inputUsername = new JTextField();
		UserinformationPanel.add( inputUsername, "cell 1 1, grow" );

		// Input field for password
		inputPassword = new JPasswordField();
		UserinformationPanel.add( inputPassword, "cell 1 2, grow" );

		// Input field for firstname
		inputFirstname = new JTextField();
		UserinformationPanel.add( inputFirstname, "cell 1 3, grow" );

		// Input field for lastname
		inputLastname = new JTextField();
		UserinformationPanel.add( inputLastname, "cell 1 4, grow" );

		// Input field for gender
		male = new JRadioButton( "Male" );
		male.setSelected( true );
		female = new JRadioButton( "Female" );
		ButtonGroup inputGender = new ButtonGroup();
		inputGender.add( male );
		inputGender.add( female );
		UserinformationPanel.add( male, "cell 1 5" );
		UserinformationPanel.add( female, "cell 1 5" );

		// Input field for age
		inputAge = new JTextField();
		UserinformationPanel.add( inputAge, "cell 1 6, width 50" );

		// Input field for location
		inputLocation = new JTextField();
		UserinformationPanel.add( inputLocation, "cell 1 7, grow" );

		// Input field for about
		inputAbout = new JTextArea();
		inputAbout.setRows( 5 );
		inputAbout.setLineWrap( true );
		JScrollPane inputAboutScoll = new JScrollPane( inputAbout );
		inputAboutScoll.setBorder( new JTextField().getBorder() ); // Workaround for same styling
		UserinformationPanel.add( inputAboutScoll, "cell 1 8, growx" );


		/********
		 * ACTIONS
		 */

		// Button for cancel
		btnCancel1 = new JButton( "Cancel" );
		btnCancel1.addActionListener( this );
		UserinformationPanel.add( btnCancel1, "cell 0 10, left, gaptop 15" );

		// Button for cancel
		btnNextPage = new JButton( "Next" );
		btnNextPage.addActionListener( this );
		UserinformationPanel.add( btnNextPage, "cell 1 10, right, gaptop 15" );

		return UserinformationPanel;
	}

	/**
	 * Returns the panel for a new season.
	 *
	 * @return JPanel
	 */
	private JPanel getPrioritiesPanel() {
		// Set up the panel
		PrioritiesPanel = new JPanel( new MigLayout( "gap 0 0", "[150][grow]" ) );

		// Print the labels
		PrioritiesPanel.add( new JLabel( "You will get information about series of your favorite Genres" ), "span" );
		PrioritiesPanel.add( new JLabel( "Chose your Priorities:" ), "cell 0 1,gaptop 10" );

		// Genres
		genreBox = new JPanel( new MigLayout( "ins 0, fill" ) );
		Genre[] genres = Genre.values();
		int i = 0;
		for ( Genre genre : genres ) {
			JCheckBox genreCheckbox = new JCheckBox();
			genreCheckbox.setText( genre.value() );
			genreCheckbox.setName( genre.value() );
			String constrain = ( ++i % 4 == 0 ) ? "wrap" : "";
			genreBox.add( genreCheckbox, constrain );
		}
		PrioritiesPanel.add( genreBox, "cell 0 2, gaptop 10" );


		/********
		 * ACTIONS
		 */

		// Button for cancel
		btnCancel2 = new JButton( "Cancel" );
		btnCancel2.addActionListener( this );
		PrioritiesPanel.add( btnCancel2, "cell 0 4, left, gaptop 15" );

		// Button for save and add new season
		btnSave = new JButton( "Create Account" );
		btnSave.addActionListener( this );
		PrioritiesPanel.add( btnSave, "cell 1 4, right, gaptop 15" );

		return PrioritiesPanel;
	}

	/**
	 * Action handler for button actions.
	 */
	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnSave ) {
			Logger.log( "REGISTER NEW USER" );
			this.newRegistrationActionPerformed();
		}
		else if ( e.getSource() == btnCancel1 ) {
			TrackerClient.showLogin();
		}
		else if ( e.getSource() == btnCancel2 ) {
			TrackerClient.showLogin();
		}
		else if ( e.getSource() == btnNextPage ) {
			// Goto Episode
			Logger.log( "Userinformation added" );
			this.checkInformationActionPerformed();
		}
	}

	/**
	 * Checks user input and opens the new panel on success.
	 */
	private void checkInformationActionPerformed(){
		// Check username
		String username = inputUsername.getText().trim();
		if ( username.length() < 3 || username.length() > 40 ) {
			errorDialog( "Username must have between 3 and 40 characters." );
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

		/**
		 * Input not required, but if then check restriction
		 */
		String firstname = inputFirstname.getText().trim();
		if ( firstname.length() != 0 ) {
			if ( firstname.length() < 2 || firstname.length() > 50 ) {
				errorDialog( "Firstname must have between 2 and 50 characters." );
				inputFirstname.requestFocusInWindow();
				return;
			}
		}

		/**
		 * Input not required, but if then check restriction
		 */
		String lastname = inputLastname.getText().trim();
		if ( lastname.length() != 0 ) {
			if ( lastname.length() < 2 || lastname.length() > 50 ) {
				errorDialog( "Lastname must have between 2 and 50 characters." );
				inputLastname.requestFocusInWindow();
				return;
			}
		}

		/**
		 * Input not required, but if then check restriction
		 */
		String location = inputLocation.getText().trim();
		if ( location.length() != 0 ) {
			if ( location.length() > 40 ) {
				errorDialog( "Location must be less than 40 characters." );
				inputLocation.requestFocusInWindow();
				return;
			}
		}

		/**
		 * Input not required, but if then check restriction
		 */
		String about = inputAbout.getText().trim();
		if ( about.length() != 0 ) {
			if ( about.length() > 200 ) {
				errorDialog( "About must be less than 200 characters." );
				inputAbout.requestFocusInWindow();
				return;
			}
		}

		// Show the new panel
		registerCardLayout.show( RegisterPanels, "PRIORITIES" );
	}

	/**
	 * Perfoms the register action.
	 */
	private void newRegistrationActionPerformed() {
		String username = inputUsername.getText().trim();
		char[] password = inputPassword.getPassword();

		// Create new Account from userinput
		ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
		connectionHandler.connect( XMPPConfig.hostname, XMPPConfig.port );
		boolean success = connectionHandler.register( username, String.valueOf( password ) );

		if ( ! success ) {
			Logger.err( "XMPP Error" );
			this.errorDialog( "There was an error while the registration!" );
			return;
		}

		success = this.saveUserData();

		if ( ! success ) {
			Logger.err( "REST Error" );
			this.errorDialog( "There was an error while the registration!" );
			return;
		}

		this.infoDialog( "The registration was successful.\nYou can log in with your data now." );
		TrackerClient.showLogin();
	}

	/**
	 * Handles the request to the REST API.
	 *
	 * @return boolean
	 */
	private boolean saveUserData() {
		// Get user object
		User user = new ObjectFactory().createUser();

		// Fill user object
		user.setFirstname( inputFirstname.getText() );
		user.setLastname( inputLastname.getText() );
		user.setUsername( inputUsername.getText().trim() );
		user.setAbout( inputAbout.getText() );
		if ( ! inputAge.getText().equals( "" ) )
			user.setAge( Integer.valueOf( inputAge.getText() ) );
		user.setLocation( inputLocation.getText() );
		Gender gender = male.isSelected() ? Gender.MALE : Gender.FEMALE;
		user.setGender( gender );

		// Default settings
		Settings settings = new ObjectFactory().createSettings();
		List<Setting> settingList = settings.getSetting();
		Setting notificationTyp = new Setting();
		notificationTyp.setKey( "notificationTyp" );
		notificationTyp.setValue( "minutes" );
		settingList.add( notificationTyp );
		Setting notificationMinutes = new Setting();
		notificationMinutes.setKey( "notificationMinutes" );
		notificationMinutes.setValue( "10" );
		settingList.add( notificationMinutes );
		user.setSettings( settings );

		// Set the HTTP request
		HTTPClient httpClient = new HTTPClient();
		httpClient.setMethod( HTTPMethod.POST );
		httpClient.setEntity( user );
		httpClient.setEndpoint( "/users/" );
		httpClient.execute();

		// Check for error
		if ( httpClient.hasError() ) {
			Logger.err( "HTTPClient has returned an error" );
			return false;
		}

		URI output = httpClient.getResponse().getLocation();
		Logger.log( "Response: " + output.toString() );

		return true;
	}

	/**
	 * Helper method to show an error dialog.
	 *
	 * @param String message
	 */
	private void errorDialog( String message ) {
		JOptionPane.showMessageDialog( null, message, "Error", JOptionPane.ERROR_MESSAGE );
	}

	/**
	 * Helper method to show an info dialog.
	 *
	 * @param String message
	 */
	private void infoDialog( String message ) {
		JOptionPane.showMessageDialog( null, message, "Info", JOptionPane.INFORMATION_MESSAGE );
	}
}
