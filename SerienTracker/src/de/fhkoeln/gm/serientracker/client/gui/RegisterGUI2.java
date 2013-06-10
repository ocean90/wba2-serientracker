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
		
		JLabel labelText = new JLabel( "Der Serientracker benachrichtigt dich über neue Ereignisse zu deinen favorisierten Serien. " +
				"Zudem hast du die Möglichkeit Informationen zu Serien eines bestimmten Genres zu erhalten. " );

		// Label for genre
		JLabel labelGenre = new JLabel( "Zu welchen Genres möchtest du Informationen erhalten? " );
	
		

		// Next button
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

		JCheckBox action = new JCheckBox("Action", false);
	    action.setFocusable(false);
	    action.addActionListener(this);
	    
	    JCheckBox adventure = new JCheckBox("Adventure", false);
	    adventure.setFocusable(false);
	    adventure.addActionListener(this);
	    
	    JCheckBox animation = new JCheckBox("Animation", false);
	    animation.setFocusable(false);
	    animation.addActionListener(this);
		
	    JCheckBox children = new JCheckBox("Children", false);
	    children.setFocusable(false);
	    children.addActionListener(this);
		
	    JCheckBox comedy = new JCheckBox("Comedy", false);
	    comedy.setFocusable(false);
	    comedy.addActionListener(this);
	    
	    JCheckBox crime = new JCheckBox("Crime", false);
	    crime.setFocusable(false);
	    crime.addActionListener(this);
	    
	    JCheckBox drama = new JCheckBox("Drama", false);
	    drama.setFocusable(false);
	    drama.addActionListener(this);
	    
	    JCheckBox documentary = new JCheckBox("Documentary", false);
	    documentary.setFocusable(false);
	    documentary.addActionListener(this);
	    
	    JCheckBox fantasy = new JCheckBox("Fantasy", false);
	    fantasy.setFocusable(false);
	    fantasy.addActionListener(this);
	    
	    JCheckBox gameshow = new JCheckBox("Game Show", false);
	    gameshow.setFocusable(false);
	    gameshow.addActionListener(this);
	    
	    JCheckBox historical = new JCheckBox("Historical", false);
	    historical.setFocusable(false);
	    historical.addActionListener(this);
	    
	    JCheckBox horror = new JCheckBox("Horror", false);
	    horror.setFocusable(false);
	    horror.addActionListener(this);
	    
	    JCheckBox mystery = new JCheckBox("Mystery", false);
	    mystery.setFocusable(false);
	    mystery.addActionListener(this);
	    
	    JCheckBox news = new JCheckBox("News", false);
	    news.setFocusable(false);
	    news.addActionListener(this);
	    
	    JCheckBox romance = new JCheckBox("Romance", false);
	    romance.setFocusable(false);
	    romance.addActionListener(this);
	    
	    JCheckBox sciencefiction = new JCheckBox("Scifi", false);
	    sciencefiction.setFocusable(false);
	    sciencefiction.addActionListener(this);
	    
	    JCheckBox sport = new JCheckBox("Sport", false);
	    sport.setFocusable(false);
	    sport.addActionListener(this);
	    
	    JCheckBox suspence = new JCheckBox("Suspence", false);
	    suspence.setFocusable(false);
	    suspence.addActionListener(this);
	    
	    JCheckBox thriller = new JCheckBox("Thriller", false);
	    thriller.setFocusable(false);
	    thriller.addActionListener(this);
	    
	    JCheckBox western = new JCheckBox("Western", false);
	    western.setFocusable(false);
	    western.addActionListener(this);
	    
		panel.add( action, "split4"  );
		panel.add( adventure );
		panel.add( animation);
		panel.add( children, "wrap");
		panel.add( comedy,"split4");
		panel.add( crime);
		panel.add( drama);
		panel.add( documentary,"wrap");
		panel.add( fantasy,"split4");
		panel.add( gameshow);
		panel.add( historical);
		panel.add( horror,"wrap");
		panel.add( mystery,"split4");
		panel.add( news);
		panel.add( romance);
		panel.add( sciencefiction, "wrap");
		panel.add( sport,"split4");
		panel.add( suspence);
		panel.add( thriller);
		panel.add( western, "wrap");

		

		panel.add( buttonNext );
	}

	/**
	 * Check user input and try to connect/login to the XMPP server.
	 *
	 * @param ActionEvent e
	 */
	public void gotoHome( ActionEvent e ) {	
		TrackerClient.closeRegisterAndGotoHome();


	};
	
	 public void actionPerformed(ActionEvent e) {

	        JCheckBox source = (JCheckBox) e.getSource();
	        boolean state = source.isSelected();     

	    }


}