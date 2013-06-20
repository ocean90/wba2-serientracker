package de.fhkoeln.gm.serientracker.client.gui2;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.fhkoeln.gm.serientracker.utils.Logger;

import net.miginfocom.swing.MigLayout;

public class NewContentGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public enum Context {
		SERIES, SEASON, EPISODE
	}

	private Context context;

	public NewContentGUI( Context context ) {
		this.context = context;

		// Set frame title
		setTitle( "SERIENTRACKER | NEW CONTENT" );

		// Set minimum frame size (x, y, width, height)
		setBounds( 0, 0, 600, 600 );

		// Center frame on screen
		setLocationRelativeTo( null );

		// Disable resizing
		setResizable( false );

		setLayout( new MigLayout( "gap 0 0", "[grow]", "[30%][grow]" ) );

		// CardLayout
		// getNewSeriesPanel()
		// getNewSeasonPanel()
		// getNewEpisodePanel()

		switch ( this.context ) {
			case EPISODE:
				Logger.log( "New episode" );
				// CardLayout show x
				break;
			case SEASON:
				Logger.log( "New season" );
				break;
			case SERIES:
				Logger.log( "New series" );
				break;
			default:
				break;

		}
		add( new JLabel( "test" ) );
	}

	public void setContext( Context context ) {
		this.context = context;
	}
}
