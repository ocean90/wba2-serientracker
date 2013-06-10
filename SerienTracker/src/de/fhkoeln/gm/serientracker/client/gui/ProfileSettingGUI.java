package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;

import de.fhkoeln.gm.serientracker.client.TrackerClient;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class ProfileSettingGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;

	private JComboBox existingNodes;
	
	private JTextField inputUsername;
	private JPasswordField inputPassword;
	private JTextField inputFirstname;
	private JTextField inputLastname;
	private JTextField inputAge; //Dropdown auswahl
	private JTextField inputLocation;
	private JTextField inputAbout;
	private JTextField inputAvatar;
	

	public ProfileSettingGUI() {
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
		setResizable( true );

		JMenuBar menubar = new JMenuBar();
	    setJMenuBar(menubar);

	    JToolBar toolbar = new JToolBar();
	    JToolBar toolbar2 = new JToolBar();
	    
	    JButton homeButton = new JButton("Home");
	    homeButton.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
					gotoHome( e );
			}
		});
	    
	    JButton settingButton = new JButton("Setting");
//	    settingButton.addActionListener(new ActionListener() {
//	           public void actionPerformed(ActionEvent e) {
//	       			TrackerClient.closeHomeAndGotoSetting();
//	           }
//	           
//	    });
	    
	    JButton exitButton = new JButton("Logout");
	    exitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);}
	           
	    });
	    
	    
	    toolbar.add(homeButton);
	    toolbar.add(settingButton);
	    toolbar.add(exitButton);
	    
	    JButton profileButton = new JButton("Profile");
//	    profileButton.addActionListener( new ActionListener() {
//		public void actionPerformed( ActionEvent e ) {
//					gotoHome( e );
//			}
//		});
	    
	    JButton genreButton = new JButton("Genres");
	 	genreButton.addActionListener( new ActionListener() {
	 	public void actionPerformed( ActionEvent e ) {
	 			gotoGenre( e );
	 		}
	 	});
	 	
	 	JButton messageButton = new JButton("Message");
		messageButton.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
		 		gotoMessage( e );
		 	}
		 });
	 	    
	    
	    toolbar2.add(profileButton);
	    toolbar2.add(genreButton);
	    toolbar2.add(messageButton);
	   
	   
		
		// Content Panel
		JPanel panel = new JPanel(new MigLayout());
		setContentPane( panel );

		// Label for username
		labelUsername = new JLabel("Hello "+ this.ch.getAccountAttribute( "username" ));
		labelUsername.setHorizontalAlignment( SwingConstants.LEFT );

		panel.setLayout( new MigLayout() );
		panel.add( labelUsername); 
		panel.add(toolbar, "wrap");
		panel.add(toolbar2, "wrap");
		
		
		
		
		
		// Label for username
		JLabel labelUsername = new JLabel( "Username:" );
				
		// Label for password
		JLabel labelPassword = new JLabel( "Password:" );

		// Label for firstname
		JLabel labelFirstname = new JLabel( "Firstname:" );

		// Label for lastname
		JLabel labelLastname = new JLabel( "Lastname:" );
				
		// Label for gender
		JLabel labelGender = new JLabel( "Gender:" );
			
		// Label for age
		JLabel labelAge = new JLabel( "Age:" );
			
		// Label for age
		JLabel labelLocation = new JLabel( "Location:" );
				
		// Label for about
		JLabel labelAbout = new JLabel( "About:" );
				
		// Label for avatar
		JLabel labelAvatar = new JLabel( "Avatar:" );

		// Input field for username
		inputUsername = new JTextField(20);

		// Input field for password
		inputPassword = new JPasswordField(20);

		// Input field for fristname
		inputFirstname = new JTextField(20);
				
		// Input field for lastname
		inputLastname = new JTextField(20);
				
		// Input field for Age
		inputAge = new JTextField(20); //Dropdown auswahl
				
		// Input field for Location
		inputLocation = new JTextField(20);

		// Input field for About
		inputAbout = new JTextField(20);

		// Input field for Avatar
		inputAvatar = new JTextField(20);

		JCheckBox maleCB = new JCheckBox("Male", false);
		maleCB.setFocusable(false);
		maleCB.addActionListener(this);
			     
		JCheckBox femaleCB = new JCheckBox("Female", false);
		femaleCB.setFocusable(false);     
		femaleCB.addActionListener(this);

				

		// Next button
		JButton saveButton = new JButton( "Save" );
//		buttonNext.addActionListener( new ActionListener() {
//			public void actionPerformed( ActionEvent e ) {
//				gotoRegister2( e );
//			}
//		});
		
		
		panel.add( labelUsername );
		panel.add( inputUsername, "wrap" );

		panel.add( labelPassword );
		panel.add( inputPassword, "wrap"  );

		panel.add( labelFirstname );
		panel.add( inputFirstname, "wrap"  );
		
		panel.add( labelLastname );
		panel.add( inputLastname, "wrap"  );
		
		panel.add( labelGender );
		panel.add( femaleCB, "split2"  );
		panel.add( maleCB, "wrap"  );
		
		panel.add( labelAge );
		panel.add( inputAge, "wrap"  );
		
		panel.add( labelLocation );
		panel.add( inputLocation, "wrap"  );
		
		panel.add( labelAbout );
		panel.add( inputAbout, "wrap"  );
		
		panel.add( labelAvatar );
		panel.add( inputAvatar, "wrap"  );
		
		panel.add( saveButton, "split 2" );
		
	}

	/**
	 * Update GUI components
	 */
	public void update() {
		this.updateUserInfo();
		this.updateNodes();
	}

	private void updateUserInfo() {
		labelUsername.setText( "Hello "+ this.ch.getAccountAttribute( "username" ) );
	}

	private void updateNodes() {
		PubSubHandler psh = ConnectionHandler.getInstance().getPubSubHandler();
		for ( String node : psh.getAllNodes() )
			existingNodes.addItem( node );

	}

	public void pubsubtest() {

	}
	
	
	 public void actionPerformed(ActionEvent e) {

	        JCheckBox source = (JCheckBox) e.getSource();
	        boolean state = source.isSelected();     

	    }

	
	
	public void gotoHome( ActionEvent e ) {	
		TrackerClient.closePSAndGotoHome();
	}
	
	public void gotoGenre( ActionEvent e ) {	
		TrackerClient.closePSAndGotoGenreSetting();
	}

	public void gotoMessage( ActionEvent e ) {	
		TrackerClient.closePSAndGotoMessageSetting();
	}
}
