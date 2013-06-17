package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
import de.fhkoeln.gm.serientracker.utils.Logger;

public class SettingsGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private SessionStore session;

	private JPanel main;

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

	public SettingsGUI() {
		this.session = SessionStore.getInstance();

		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e ) {
		}

		initComponents();
	}

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
		settingTabs.addTab( "Notifcations", notificationsTab );

		notificationsTab.add( new JLabel( "Notifcations" ) );


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

	private JPanel getProfilTab() {
		profileTab = new JPanel( new MigLayout( "gap 0 0", "[30%][grow]" ));

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
		inputAbout.setRows( 3 );
		inputAbout.setText( this.session.getUser().getAbout() );
		JScrollPane inputAboutScoll = new JScrollPane( inputAbout );
		inputAboutScoll.setBorder( new JTextField().getBorder() ); // Workaround for same styling
		profileTab.add( inputAboutScoll, "cell 1 7, growx, gaptop 5" );

		return profileTab;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnSave ) {
			Logger.log( "SAVE" );
		} else if( e.getSource() == btnCancel ) {
			this.dispose();
		}
	}


}
