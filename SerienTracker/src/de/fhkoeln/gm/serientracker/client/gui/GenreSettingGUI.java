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
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;

import de.fhkoeln.gm.serientracker.client.TrackerClient;
import de.fhkoeln.gm.serientracker.jaxb.Genre;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class GenreSettingGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JLabel labelUsername;
	private JLabel labelText;
	private JLabel labelText2;


	private JComboBox existingNodes;

	public GenreSettingGUI() {
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
	    profileButton.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
					gotoProfile( e );
			}
		});
	    
	    JButton genreButton = new JButton("Genres");
//	 	genreButton.addActionListener( new ActionListener() {
//	 	public void actionPerformed( ActionEvent e ) {
//	 			gotoGenre( e );
//	 		}
//	 	});
	 	
	    
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
		
		labelText = new JLabel("Sie haben die Möglichkeit Benachrichtigung zu Serien bestimmter Genres zu erhalten. ");
		labelText2 = new JLabel("An welchen Genres sind sie interessiert?");
		
		panel.setLayout( new MigLayout() );
		panel.add( labelUsername); 
		panel.add(toolbar, "wrap");
		panel.add(toolbar2,"wrap");
		panel.add(labelText, "wrap");
		panel.add(labelText2, "wrap");
		
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
		
		// Next button
		JButton saveButton = new JButton( "Save" );
//		buttonNext.addActionListener( new ActionListener() {
//			public void actionPerformed( ActionEvent e ) {
//				gotoRegister2( e );
//				}
//		});
		
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
	
	public void gotoHome( ActionEvent e ) {	
		TrackerClient.closeGSAndGotoHome();
	}
	
	public void gotoProfile( ActionEvent e ) {	
		TrackerClient.closeGSAndGotoProfileSetting();
	}

	public void gotoMessage( ActionEvent e ) {	
		TrackerClient.closeGSAndGotoMessageSetting();
	}
	
	 public void actionPerformed(ActionEvent e) {

	        JCheckBox source = (JCheckBox) e.getSource();
	        boolean state = source.isSelected();     

	    }
	
}
