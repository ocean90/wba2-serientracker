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
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

public class NotificationFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JPanel main;


	public NotificationFrame() {
		this.ch = ConnectionHandler.getInstance();

		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e ) {
		}

		initComponents();
	}

	public void initComponents() {
		// Set frame title
		setTitle( "SERIENTRACKER" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 200, 100 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		//setUndecorated( true );

		setAlwaysOnTop( true );
		//setAlwaysOnTop( false );

		setLayout( new MigLayout( "gap 0 0", "[grow]", "[][grow]" ) );


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
		Logger.log( "ACTION" );
	}

}
