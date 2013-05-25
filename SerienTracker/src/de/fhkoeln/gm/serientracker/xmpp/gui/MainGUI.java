package de.fhkoeln.gm.serientracker.xmpp.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import de.fhkoeln.gm.serientracker.xmpp.ConnectionHandler;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;

	public MainGUI() {
		this.ch = ConnectionHandler.getInstance();

		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( ClassNotFoundException e ) {
		} catch ( InstantiationException e ) {
		} catch ( IllegalAccessException e ) {
		} catch ( UnsupportedLookAndFeelException e ) {
		}

		initComponents();
	}

	public void initComponents() {
		// Close when exit
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// Set frame title
		setTitle( "SERIENTRACKER" );

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
		labelUsername = new JLabel();
		labelUsername.setHorizontalAlignment( SwingConstants.RIGHT );
		labelUsername.setBounds( 0, 0, 600, 20 );
		labelUsername.setBorder( new EmptyBorder( 10, 0, 0, 10 ) );
		panel.add( labelUsername );
	}

	/**
	 * Update GUI components
	 */
	public void update() {
		labelUsername.setText( "Hello "+ this.ch.getAccountAttribute( "username" ) );
	}

}
