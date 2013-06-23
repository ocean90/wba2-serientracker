package de.fhkoeln.gm.serientracker.xmpp.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.miginfocom.swing.MigLayout;

import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;

import de.fhkoeln.gm.serientracker.jaxb.Message;
import de.fhkoeln.gm.serientracker.jaxb.ObjectFactory;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

/**
 * Provides the main GUI.
 *
 * @author Dominik Schilling
 */
public class MainGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Holds the connection instance
	private ConnectionHandler ch;

	// GUI components
	private JPanel sidebar;
	private JPanel main;
	private JComboBox coboxExistingNodes;
	private JTextArea txtarNodeInfo;
	private JTextArea testNodePayload;
	private JScrollPane scrollPaneNodeInfo;

	/**
	 * Constructor.
	 * Sets UI look and connection instance.
	 */
	public MainGUI() {
		// Get the connection instance
		this.ch = ConnectionHandler.getInstance();

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
		setTitle( "XMPPCLIENT | DEBUG WINDOW" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 800, 400 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		setLayout( new MigLayout( "gap 0 0, fill", "[300][500]", "grow" ) );


		/********
		 * PANELS
		 */

		sidebar = new JPanel( new MigLayout() );
		main = new JPanel( new MigLayout( "", "grow", "grow" ) );

		add( sidebar, "cell 0 0, grow"); // cell: col row
		add( main, "cell 1 0, grow" );


		/********
		 * SIDEBAR
		 */
		sidebar.setBorder( BorderFactory.createTitledBorder( null,
				"Nodes", TitledBorder.LEFT, TitledBorder.TOP,
				new Font( "", Font.BOLD, 12 ) ) );

		// Label: existing nodes
		JLabel labelExistingNodes = new JLabel();
		labelExistingNodes.setText( "Choose a node:" );
		sidebar.add( labelExistingNodes, "cell 0 0" );

		// Dropdown: existing nodes
		coboxExistingNodes = new JComboBox();
		sidebar.add( coboxExistingNodes, "cell 1 0 2 1, grow" );

		// Button: get node info
		JButton btnNodeInfo = new JButton( "Info" );
		btnNodeInfo.setActionCommand( "NODEINFO" );
		btnNodeInfo.addActionListener( this );
		sidebar.add( btnNodeInfo, "cell 0 1, grow" );

		// Button: Subscribe
		JButton btnNodeSubscribe = new JButton( "Subscribe" );
		btnNodeSubscribe.setActionCommand( "NODESUBSCRIBE" );
		btnNodeSubscribe.addActionListener( this );
		sidebar.add( btnNodeSubscribe, "cell 1 1, grow" );

		// Button: Unubscribe
		JButton btnNodeUnsubscribe = new JButton( "Unsubscribe" );
		btnNodeUnsubscribe.setActionCommand( "NODEUNSUBSCRIBE" );
		btnNodeUnsubscribe.addActionListener( this );
		sidebar.add( btnNodeUnsubscribe, "cell 2 1, grow" );

		// Button: Delete all nodes
		JButton btnDeleteAllNodes = new JButton( "Delete all nodes" );
		btnDeleteAllNodes.setActionCommand( "DELETENODES" );
		btnDeleteAllNodes.addActionListener( this );
		sidebar.add( btnDeleteAllNodes, "cell 0 2, gaptop 20" );

		// Button: refresh nodes list
		JButton btnRefreshNodes = new JButton( "Refresh nodes" );
		btnRefreshNodes.setActionCommand( "REFRESHNODES" );
		btnRefreshNodes.addActionListener( this );
		sidebar.add( btnRefreshNodes, "cell 1 2, gaptop 20" );

		// Panel for sending test item to node
		JPanel testNodeItemPanel = new JPanel( new MigLayout( "fill" ) );
		testNodeItemPanel.setBorder( BorderFactory.createTitledBorder( null,
				"Send Notification", TitledBorder.LEFT, TitledBorder.TOP,
				new Font( "", Font.BOLD, 12 ) ) );

		sidebar.add( testNodeItemPanel, "cell 0 3 3 1, gaptop 20, grow" );


		// TextArea: Test node item payload
		testNodePayload = new JTextArea();
		testNodePayload.setLineWrap( true );
		testNodePayload.setRows( 6 );
		JScrollPane testNodePayloadScroll = new JScrollPane( testNodePayload );
		testNodeItemPanel.add( testNodePayloadScroll, "grow, wrap" );

		// Button: Send test item
		JButton btnSendPayload = new JButton( "Send" );
		btnSendPayload.setActionCommand( "SENDPAYLOAD" );
		btnSendPayload.addActionListener( this );
		testNodeItemPanel.add( btnSendPayload, "right" );


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
		txtarNodeInfo.setFont( new Font( "Monospace", Font.PLAIN, 10 ) );
		scrollPaneNodeInfo = new JScrollPane( txtarNodeInfo );
		main.add( scrollPaneNodeInfo, "grow" );
	}

	/**
	 * Update GUI components
	 */
	public void update() {
		this.updateNodes();
	}

	/**
	 * Updates the nodes list.
	 */
	private void updateNodes() {
		// Remove all items from list
		coboxExistingNodes.removeAllItems();

		// Get nodes and add to list
		PubSubHandler psh = this.ch.getPubSubHandler();
		for ( String node : psh.getAllNodes() )
			coboxExistingNodes.addItem( node );

	}

	/**
	 * Action handler for button actions.
	 *
	 * @param ActionEvent e
	 */
	@Override
	public void actionPerformed( ActionEvent e ) {
		String selectedNode = (String) coboxExistingNodes.getSelectedItem();

		if ( e.getActionCommand().equals( "NODEINFO" ) ) {
			// Show node info
			this.showNodeInfo( selectedNode );
		} else if ( e.getActionCommand().equals( "NODESUBSCRIBE" ) ) {
			// TODO: Functionality
			txtarNodeInfo.setText( "Subscribed to " + selectedNode );
		} else if ( e.getActionCommand().equals( "NODEUNSUBSCRIBE" ) ) {
			// TODO: Functionality
			txtarNodeInfo.setText( "Unsubscribed from " + selectedNode );
		} else if ( e.getActionCommand().equals( "DELETENODES" ) ) {
			// Delete node
			this.deleteNodes();
			txtarNodeInfo.setText( "Nodes deleted" );
		} else if ( e.getActionCommand().equals( "REFRESHNODES" ) ) {
			// Fetch the nodes again
			this.updateNodes();
			txtarNodeInfo.setText( "Nodes refreshed" );
		} else if ( e.getActionCommand().equals( "SENDPAYLOAD" ) ) {
			// Send a test item to node
			this.sendPayload();
			txtarNodeInfo.setText( "Payload send" );
		}
	}

	/**
	 * Fetches node info and displays it.
	 *
	 * @param String nodeName
	 */
	private void showNodeInfo( String nodeName ) {
		PubSubHandler psh = this.ch.getPubSubHandler();

		String nodeInfo = psh.getNodeInfo( nodeName );
		txtarNodeInfo.setText( nodeInfo );
		txtarNodeInfo.setCaretPosition( 0 ); // Scroll back to top
	}

	/**
	 * Deletes all nodes and updates the node list.
	 */
	private void deleteNodes() {
		PubSubHandler psh = this.ch.getPubSubHandler();

		psh.deleteAllNodes();
		this.updateNodes();
	}

	/**
	 * Sends a test item to the selected node.
	 */
	private void sendPayload() {
		// Create a new message object
		ObjectFactory factory = new ObjectFactory();
		Message message = factory.createMessage();
		message.setContent( testNodePayload.getText() );

		StringWriter notification = new StringWriter();
		try {
			JAXBContext jaxb_context = JAXBContext.newInstance( Message.class );
			Marshaller marshaller = jaxb_context.createMarshaller();
			marshaller.setProperty( Marshaller.JAXB_FRAGMENT, true ); // Marshall without namespace
			marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
			marshaller.marshal( message, notification );
		} catch ( JAXBException e ) {
			return;
		}

		// Get selected node
		String selectedNode = (String) coboxExistingNodes.getSelectedItem();

		// Publish item to node
		PubSubHandler psh = this.ch.getPubSubHandler();
		LeafNode node = psh.getNode( selectedNode );
		Logger.log( "Sending notification" );

		node.publish(
			new PayloadItem<SimplePayload>(
				null,
				new SimplePayload(
					"message",              // Element name
					"",                     // Namespace
					notification.toString() // Payload
				)
			)
		);

		testNodePayload.setText( null );
	}

}
