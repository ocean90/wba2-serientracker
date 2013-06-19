package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.client.TrackerClient2;
import de.fhkoeln.gm.serientracker.client.gui2.NewContentGUI.Context;
import de.fhkoeln.gm.serientracker.client.utils.SessionStore;
import de.fhkoeln.gm.serientracker.utils.Logger;

public class MainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private SessionStore session;

	private JPanel main;

	private JMenuBar menuBar;
	private JMenu mainMenu;
	private JMenuItem fullnameMenuItem;
	private JMenuItem settingsMenuItem;
	private JMenuItem logoutMenuItem;
	private JMenuItem closeMenuItem;
	private JMenu newContentMenu;
	private JMenuItem newSerieMenuItem;
	private JMenuItem newSeasonMenuItem;
	private JMenuItem newEpisodeMenuItem;

	public MainGUI() {
		this.session = SessionStore.getInstance();

		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e ) {
		}

		initComponents();
	}

	public void initComponents() {
		// Close when exit
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// Set frame title
		setTitle( "SERIENTRACKER" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 800, 600 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		setLayout( new MigLayout( "gap 0 0", "[grow]", "[][grow]" ) );


		/********
		 * MENUBAR
		 */

		menuBar = new JMenuBar();
		add( menuBar, "dock north" );

		menuBar.add( Box.createHorizontalGlue() ); // Push items to the right side

		/********
		 * NEW CONTENT MENU
		 */

		if ( this.session.UserIsAdmin() ) {
			newContentMenu = new JMenu();
			newContentMenu.setText( "+" );
			newContentMenu.setToolTipText( "Add new content" );
			menuBar.add( newContentMenu );

			newSerieMenuItem = new JMenuItem( );
			newSerieMenuItem.setText( "New Series" );
			newSerieMenuItem.addActionListener( this );
			newContentMenu.add( newSerieMenuItem );

			newSeasonMenuItem = new JMenuItem( );
			newSeasonMenuItem.setText( "New Season" );
			newSeasonMenuItem.addActionListener( this );
			newContentMenu.add( newSeasonMenuItem );

			newEpisodeMenuItem = new JMenuItem( );
			newEpisodeMenuItem.setText( "New Episode" );
			newEpisodeMenuItem.addActionListener( this );
			newContentMenu.add( newEpisodeMenuItem );
		}


		/********
		 * MAIN MENU
		 */

		mainMenu = new JMenu();
		mainMenu.setText( "Hello, " + session.getUser().getUsername() );
		menuBar.add( mainMenu );

		fullnameMenuItem = new JMenuItem( );
		fullnameMenuItem.setEnabled( false );
		fullnameMenuItem.setText( session.getUser().getFirstname() + " " + session.getUser().getLastname() );
		mainMenu.add( fullnameMenuItem );

		JSeparator sep1 = new JSeparator( JSeparator.HORIZONTAL );
		mainMenu.add( sep1 );

		settingsMenuItem = new JMenuItem( "Settings" );
		settingsMenuItem.addActionListener( this );
		mainMenu.add( settingsMenuItem );

		logoutMenuItem = new JMenuItem( "Logout" );
		logoutMenuItem.addActionListener( this );
		mainMenu.add( logoutMenuItem );

		JSeparator sep2 = new JSeparator( JSeparator.HORIZONTAL );
		mainMenu.add( sep2 );

		closeMenuItem = new JMenuItem( "Quit" );
		closeMenuItem.addActionListener( this );
		mainMenu.add( closeMenuItem );


		/********
		 * PANELS
		 */

		main = new JPanel( new MigLayout( "insets 0, fill" ) );
		add( main, "grow" );

		main.add( new SeriesListPanel(), "grow" );
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == settingsMenuItem ) {
			// Settings
			SettingsGUI settingsGUI = new SettingsGUI();
			settingsGUI.setVisible( true );
        } else if ( e.getSource() == logoutMenuItem ) {
        	// Logout
        	this.session.destroySession();
        	TrackerClient2.showLogin();
        } else if ( e.getSource() == closeMenuItem ) {
        	// Exit
        	this.session.destroySession();
        	this.dispose();
        } else if ( e.getSource() == newSerieMenuItem ) {
        	// New series
        	NewContentGUI newContentGUI = new NewContentGUI();
        	newContentGUI.setContext( Context.SERIES );
        	newContentGUI.setVisible( true );
        } else if ( e.getSource() == newSeasonMenuItem ) {
        	// New season
        	NewContentGUI newContentGUI = new NewContentGUI();
        	newContentGUI.setContext( Context.SEASON );
        	newContentGUI.setVisible( true );
        } else if ( e.getSource() == newEpisodeMenuItem ) {
        	// New episode
        	NewContentGUI newContentGUI = new NewContentGUI();
        	newContentGUI.setContext( Context.EPISODE );
        	newContentGUI.setVisible( true );
        } else {
        	Logger.err( "Action not implemented" );
        }
	}

}
