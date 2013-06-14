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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.jaxb.Genre;


import de.fhkoeln.gm.serientracker.client.TrackerClient;
import de.fhkoeln.gm.serientracker.xmpp.XMPPConfig;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;

public class RegisterGUI2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private ConnectionHandler ch;

	
	public RegisterGUI2() {
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
		JPanel panel = new JPanel(new MigLayout());
		setContentPane( panel );

		// Label for text
		JLabel labelUser = new JLabel( "Hallo _username von davor_ " );
		
		// TODO combined Textfield
		JLabel labelText = new JLabel( "Der Serientracker benachrichtigt dich über neue Ereignisse zu deinen favorisierten Serien. " );
		JLabel labelText2 = new JLabel( "Zudem hast du die Möglichkeit Informationen zu Serien eines bestimmten Genres zu erhalten. " );

		// Label for genre
		JLabel labelGenre = new JLabel( "Zu welchen Genres möchtest du Informationen erhalten? " );
	
		
		
		// Button for next page
		JButton buttonNext = new JButton( "Weiter" );
		buttonNext.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				gotoHome( e );
			}
		});


		// Add items to panel
		panel.setLayout( new MigLayout() );

		panel.add( labelUser,"wrap" );
		panel.add( labelText,"wrap" );
		panel.add( labelText2,"wrap" );

		
		// Add checkBox for each genre
		int i = 0;
		Genre[] genres = Genre.values();
		for ( Genre genre : genres ){			
			JCheckBox box = new JCheckBox(genre.name(), false);
			box.setFocusable(false);
			box.addActionListener(this);

			if(i == 3 || i == 7 || i == 11 || i == 15 || i == 19){
				panel.add( box, "wrap" );}
		
			else  {
				panel.add(box, "split 4") ;
				}
			
			i++;

		}
		panel.add( buttonNext );
		
	}

	
	/**
	 * Save user input into XML, connect with server and goto Homepage
	 *
	 * @param ActionEvent e
	 */
	public void gotoHome( ActionEvent e ) {	
		TrackerClient.closeRegisterAndGotoHome();


	};
	

	/**
	 * Check checkbox action
	 *
	 * @param ActionEvent e
	 */
	 public void actionPerformed(ActionEvent e) {
	        JCheckBox source = (JCheckBox) e.getSource();
	        boolean state = source.isSelected();     

	    }


}
