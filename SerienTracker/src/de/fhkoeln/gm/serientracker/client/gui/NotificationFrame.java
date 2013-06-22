package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.jaxb.Message;
import de.fhkoeln.gm.serientracker.utils.Logger;

/**
 * The frame for a notification popup.
 *
 * @author Dominik Schilling
 */
public class NotificationFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Holds the message
	private Message message;

	// GUI components
	private JPanel main;
	private JButton btnClose;
	private JButton btnViewDetails;

	/**
	 * Constructor.
	 * Sets message and UI look.
	 *
	 * @param Message message
	 */
	public NotificationFrame( Message message ) {
		this.message = message;

		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e ) {
		}

		initComponents();
	}

	/**
	 * Inits frame components.
	 */
	public void initComponents() {
		// Set frame title
		setTitle( "SERIENTRACKER | NOTIFICATION" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 300, 200 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		// Place frame on top
		setAlwaysOnTop( true );

		// Set layout
		setLayout( new MigLayout( "gap 0 0", "grow", "grow" ) );


		/********
		 * PANELS
		 */

		main = new JPanel( new MigLayout( "ins 0", "[150][150]", "[grow][]" ) );
		add( main, "cell 0 0, grow" ); // cell: col row


		/********
		 * MESSAGE
		 */

		this.messageComponents();
	}

	/**
	 * Inits the components for the message display.
	 */
	private void messageComponents() {
		// Display the message content
		JTextArea messageContent = new JTextArea( this.message.getContent() );
		messageContent.setLineWrap( true );
		messageContent.setWrapStyleWord( true );
		messageContent.setBackground( getBackground() );
		messageContent.setEditable( false );
		messageContent.setHighlighter( null );
		JScrollPane messageContentScroll = new JScrollPane( messageContent );
		messageContentScroll.setBorder( null );
		messageContentScroll.setBackground( getBackground() );
		main.add( messageContentScroll, "cell 0 0 2 1, grow" );

		// Close button
		btnClose = new JButton( "Close" );
		btnClose.addActionListener( this );
		main.add( btnClose, "cell 0 1, grow" );

		// Details button
		btnViewDetails = new JButton( "View Details" );
		btnViewDetails.addActionListener( this );
		main.add( btnViewDetails, "cell 1 1, grow" );
	}

	/**
	 * Event listener for button actions.
	 */
	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnClose ) {
			this.dispose();
		} else if ( e.getSource() == btnViewDetails ) {
			Logger.log( "View details clicked" );

			// TODO: Open the series overview panel

			this.dispose();
		}
	}

}
