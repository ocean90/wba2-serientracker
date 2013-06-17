package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.client.utils.SessionStore;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

public class MainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private SessionStore session;

	private JPanel main;

	private JMenuBar menuBar;
	private JMenu mainMenu;
	private JMenuItem fullnameMenuItem;
	private JMenuItem settingsMenuItem;
	private JMenuItem logoutMenuItem;

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
		add( menuBar, "dock north, gapbottom 15" );

		mainMenu = new JMenu();
		mainMenu.setText( "Hello, " + session.getUser().getUsername() );
		menuBar.add( Box.createHorizontalGlue() ); // Push it to the right side
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


		/********
		 * PANELS
		 */

		main = new JPanel( new MigLayout( "debug", "grow", "grow" ) );
		add( main, "cell 0 0, grow" ); // cell: col row


		/********
		 * MAIN
		 */

		main.add( new JLabel( "Main" ), "grow" );
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == settingsMenuItem ){
			SettingsGUI settingsGUI = new SettingsGUI();
			settingsGUI.setVisible( true );
        } else if ( e.getSource() == logoutMenuItem ){
    		Logger.log( "NOTIFY" );
        	try {
				Thread.sleep( 4 * 1000 );
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			NotificationFrame notify = new NotificationFrame();
			notify.setVisible( true );
        }
	}

}
