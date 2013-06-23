package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.client.utils.SessionStore;
import de.fhkoeln.gm.serientracker.jaxb.Gender;
import de.fhkoeln.gm.serientracker.jaxb.Weekday;
import de.fhkoeln.gm.serientracker.utils.Logger;

/**
 * The frame for user settings.
 *
 * @author Dominik Schilling
 */
public class SettingsGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Holds the session instance
	private SessionStore session;

	// GUI components
	private JTabbedPane settingTabs;
	private JPanel profileTab;
	private JPanel notificationsTab;
	private JTextField inputUsername;
	private JTextField inputFirstname;
	private JTextField inputLastname;
	private JPasswordField inputPassword;
	private JTextField inputAge;
	private JTextField inputLocation;
	private JTextArea inputAbout;
	private JButton btnSave;
	private JButton btnCancel;
	private JComboBox notificationTimes;
	private JRadioButton notificationTypMinutes;
	private ButtonGroup notificationTyp;
	private JRadioButton notificationTypDay;
	private JComboBox notificationDays;

	/**
	 * Constructor.
	 * Sets UI look and session instance.
	 */
	public SettingsGUI() {
		// Get session instance
		this.session = SessionStore.getInstance();

		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e ) {
		}

		initComponents();
	}

	/**
	 * Inits the GUI components
	 */
	public void initComponents() {
		// Set frame title
		setTitle( "SERIENTRACKER | SETTINGS" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 400, 500 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		setLayout( new MigLayout( "fill, wrap 1" ) );


		/********
		 * TABS
		 */

		settingTabs = new JTabbedPane();
		add( settingTabs, "grow" );

		settingTabs.addTab( "Profile", getProfilTab() );

		notificationsTab = new JPanel();
		settingTabs.addTab( "Notifcations", getNotificationsTab() );


		/********
		 * ACTIONS
		 */

		btnCancel = new JButton( "Cancel" );
		btnCancel.addActionListener( this );
		add( btnCancel, "cell 0 1, right" );

		btnSave = new JButton( "Save" );
		btnSave.addActionListener( this );
		add( btnSave, "cell 0 1, right" );
	}

	/**
	 * Returns the tab for profile settings.
	 *
	 * @return JPanel
	 */
	private JPanel getProfilTab() {
		// Set the panel
		profileTab = new JPanel( new MigLayout( "gap 0 0", "[30%][grow]" ) );

		// Print the labels
		profileTab.add( new JLabel( "Username:" ), "cell 0 0" );
		profileTab.add( new JLabel( "Firstname:" ), "cell 0 1" );
		profileTab.add( new JLabel( "Lastname:" ), "cell 0 2" );
		profileTab.add( new JLabel( "Password:" ), "cell 0 3" );
		profileTab.add( new JLabel( "Gender:" ), "cell 0 4" );
		profileTab.add( new JLabel( "Age:" ), "cell 0 5" );
		profileTab.add( new JLabel( "Location:" ), "cell 0 6" );
		profileTab.add( new JLabel( "About:" ), "cell 0 7, gaptop 5" );

		// Input field for username
		inputUsername = new JTextField();
		inputUsername.setEnabled( false );
		inputUsername.setText( this.session.getUser().getUsername() );
		profileTab.add( inputUsername, "cell 1 0, grow" );

		// Input field for firstname
		inputFirstname = new JTextField();
		inputFirstname.setText( this.session.getUser().getFirstname() );
		profileTab.add( inputFirstname, "cell 1 1, grow" );

		// Input field for lastname
		inputLastname = new JTextField();
		inputLastname.setText( this.session.getUser().getLastname() );
		profileTab.add( inputLastname, "cell 1 2, grow" );

		// Input field for password
		inputPassword = new JPasswordField();
		profileTab.add( inputPassword, "cell 1 3, grow" );

		// Input field for gender
		JRadioButton male = new JRadioButton( "Male" );
		JRadioButton female = new JRadioButton( "Female" );

		if ( this.session.getUser().getGender() == Gender.MALE )
			male.setSelected( true );
		else if ( this.session.getUser().getGender() == Gender.FEMALE )
			female.setSelected( true );

		ButtonGroup inputGender = new ButtonGroup();
		inputGender.add( male );
		inputGender.add( female );
		profileTab.add( male, "cell 1 4" );
		profileTab.add( female, "cell 1 4" );

		// Input field for age
		inputAge = new JTextField();
		inputAge.setText( this.session.getUser().getAge().toString() );
		profileTab.add( inputAge, "cell 1 5, width 50" );

		// Input field for location
		inputLocation = new JTextField();
		inputLocation.setText( this.session.getUser().getLocation() );
		profileTab.add( inputLocation, "cell 1 6, grow" );

		// Input field for about text
		inputAbout = new JTextArea();
		inputAbout.setRows( 5 );
		inputAbout.setLineWrap( true );
		inputAbout.setText( this.session.getUser().getAbout() );
		JScrollPane inputAboutScoll = new JScrollPane( inputAbout );
		inputAboutScoll.setBorder( new JTextField().getBorder() ); // Workaround for same styling
		profileTab.add( inputAboutScoll, "cell 1 7, growx, gaptop 5" );

		return profileTab;
	}

	/**
	 * Returns the panel for notification settings.
	 *
	 * @return JPanel
	 */
	private JPanel getNotificationsTab() {
		// Set the panel
		notificationsTab = new JPanel( new MigLayout( "gap 0 0", "[10][][]" ) );

		notificationsTab.add( new JLabel( "When should we send you the notifications?" ), "span, grow, gapbottom 20" );

		// Notification Type: Minutes
		notificationTypMinutes = new JRadioButton();
		notificationsTab.add( notificationTypMinutes, "cell 0 1" );

		// Dropdown: Notification times
		notificationTimes = new JComboBox();

		int[] notifcationTimeItems = { 5, 10, 15 };
		for ( int notifcationTimeItem : notifcationTimeItems )
			notificationTimes.addItem( notifcationTimeItem );

		notificationsTab.add( notificationTimes, "cell 1 1" );

		notificationsTab.add( new JLabel( "Minutes before broadcasting" ), "cell 2 1" );

		// Notification Type: Day
		notificationTypDay = new JRadioButton();
		notificationsTab.add( notificationTypDay, "cell 0 2" );

		notificationsTab.add( new JLabel( "Only on " ), "cell 1 2" );

		// Dropdown: Notification times
		notificationDays = new JComboBox();
		Weekday[] weekdays = Weekday.values();
		for ( Weekday weekday : weekdays )
			notificationDays.addItem( weekday.value() );
		notificationsTab.add( notificationDays, "cell 2 2" );

		notificationTyp = new ButtonGroup();
		notificationTyp.add( notificationTypMinutes );
		notificationTyp.add( notificationTypDay );

		// TODO: Disabled for now
		notificationTypDay.setEnabled( false );
		notificationDays.setEnabled( false );

		// Set user setting
		String settingNotificationTyp = this.session.getUserSetting( "notificationTyp" );
		if ( settingNotificationTyp == null || settingNotificationTyp.equals( "minutes" ) ) {
			notificationTypMinutes.setSelected( true );
		} else if ( settingNotificationTyp.equals( "days" ) ) {
			notificationTypDay.setSelected( true );
		}

		// Set user setting
		String settingNotificationMinutes = this.session.getUserSetting( "notificationMinutes" );
		if ( settingNotificationTyp == null ) {
			notificationTimes.setSelectedItem( 5 );
		} else {
			notificationTimes.setSelectedItem( Integer.valueOf( settingNotificationMinutes ) );
		}

		return notificationsTab;
	}

	/**
	 * Action handler for button actions.
	 */
	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnSave ) {
			// Save action
			Logger.log( "SAVE" );
		} else if ( e.getSource() == btnCancel ) {
			// Cancel action
			this.dispose();
		}
	}

}
