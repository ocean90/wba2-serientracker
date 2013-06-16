package de.fhkoeln.gm.serientracker.xmpp.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class MainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ConnectionHandler ch;

	private JPanel top;
	private JPanel sidebar;
	private JPanel main;

	private JLabel lblUsername;
	private JComboBox coboxExistingNodes;
	private JTextArea txtarNodeInfo;

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
		setTitle( "SERIENTRACKER - XMPP Client" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 800, 400 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		setLayout( new MigLayout( "gap 0 0", "[300][grow]", "[][grow]" ) );


		/********
		 * PANELS
		 */

		top = new JPanel( new MigLayout( "ins 0", "grow", "grow" ) );
		sidebar = new JPanel( new MigLayout( "ins 0", "grow" ) );
		main = new JPanel( new MigLayout( "ins 0", "grow", "grow" ) );

		add( top, "cell 0 0 2 1, grow" ); // cell: col row colspan rowspan
		add( sidebar, "cell 0 1, gapright 10, grow"); // cell: col row
		add( main, "cell 1 1, grow" );


		/********
		 * TOP
		 */

		// Label: username
		lblUsername = new JLabel();
		lblUsername.setHorizontalAlignment( JLabel.RIGHT );
		top.add( lblUsername, "grow" );


		/********
		 * SIDEBAR
		 */
		sidebar.setBorder( BorderFactory.createTitledBorder( null,
				"Nodes", TitledBorder.LEFT, TitledBorder.TOP,
				new Font( "", Font.BOLD, 12 ) ) );

		// Label: existing nodes
		JLabel labelExistingNodes = new JLabel();
		labelExistingNodes.setText( "Choose a node:" );
		sidebar.add( labelExistingNodes, "right" );

		// Dropdown: existing nodes
		coboxExistingNodes = new JComboBox();
		sidebar.add( coboxExistingNodes, "spanx 2, grow, wrap" );

		// Button: get node info
		JButton btnNodeInfo = new JButton( "Info" );
		btnNodeInfo.setActionCommand( "NODEINFO" );
		btnNodeInfo.addActionListener( this );
		sidebar.add( btnNodeInfo, "sizegroup button" );

		JButton btnNodeSubscribe = new JButton( "Subscribe" );
		btnNodeSubscribe.setActionCommand( "NODESUBSCRIBE" );
		btnNodeSubscribe.addActionListener( this );
		sidebar.add( btnNodeSubscribe, "sizegroup button" );

		JButton btnNodeUnsubscribe = new JButton( "Unsubscribe" );
		btnNodeUnsubscribe.setActionCommand( "NODEUNSUBSCRIBE" );
		btnNodeUnsubscribe.addActionListener( this );
		sidebar.add( btnNodeUnsubscribe, "sizegroup button" );


		/********
		 * MAIN
		 */
		main.setBorder( BorderFactory.createTitledBorder( null,
				"Output", TitledBorder.LEFT, TitledBorder.TOP,
				new Font( "", Font.BOLD, 12 ) ) );


		// TextArea: display node info
		txtarNodeInfo = new JTextArea();
		txtarNodeInfo.setEditable( false );
		txtarNodeInfo.setLineWrap( true );
		JScrollPane scrollPaneNodeInfo = new JScrollPane( txtarNodeInfo );
		main.add( scrollPaneNodeInfo, "grow" );
	}

	/**
	 * Update GUI components
	 */
	public void update() {
		this.updateUserInfo();
		this.updateNodes();
	}

	private void updateUserInfo() {
		lblUsername.setText( "Hello "+ this.ch.getAccountAttribute( "name" ) );
	}

	private void updateNodes() {
		PubSubHandler psh = this.ch.getPubSubHandler();
		for ( String node : psh.getAllNodes() )
			coboxExistingNodes.addItem( node );

	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		String selectedNode = (String) coboxExistingNodes.getSelectedItem();

		if ( e.getActionCommand().equals( "NODEINFO" ) ) {
			this.showNodeInfo( selectedNode );
		} else if ( e.getActionCommand().equals( "NODESUBSCRIBE" ) ) {
			// TODO: Functionality
			txtarNodeInfo.setText( "Subscribed to " + selectedNode );
		} else if ( e.getActionCommand().equals( "NODEUNSUBSCRIBE" ) ) {
			// TODO: Functionality
			txtarNodeInfo.setText( "Unsubscribed from " + selectedNode );
		}
	}

	private void showNodeInfo( String nodeName ) {
		PubSubHandler psh = this.ch.getPubSubHandler();

		String nodeInfo = psh.getNodeInfo( nodeName );
		txtarNodeInfo.setText( nodeInfo );
	}

}
