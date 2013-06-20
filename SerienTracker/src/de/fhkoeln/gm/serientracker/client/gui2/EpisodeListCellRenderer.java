package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.Component;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import de.fhkoeln.gm.serientracker.client.utils.ImageLoader;
import de.fhkoeln.gm.serientracker.jaxb.Episode;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;

/**
 * Custom cell renderer for episodes.
 * Shows the episode title and an image, if exists.
 *
 * @author Dominik Schilling
 */
public class EpisodeListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	/**
	 * Renders an episode cell.
	 *
	 * @param JList list
	 * @param Object value
	 * @param int index
	 * @param boolean isSelected
	 * @param boolean cellHasFocus
	 * @return Component
	 */
	public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
		// Get default styling
		super.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );

		// Cast the value
		Episode episode = (Episode) value;

		// Set the title
		this.setText( episode.getTitle() );

		// Set the icon
		Image image = this.getEpisodeImage( episode );
		if ( image != null )
			this.setIcon( new ImageIcon( image ) );

		// Return the cell
		return this;
	}

	/**
	 * Fetches the attached image of the episode.
	 *
	 * @param Episode episode
	 * @return Image
	 */
	private Image getEpisodeImage( Episode episode ) {
		// Check if there is an image
		if ( episode.getImages() != null && episode.getImages().getImage().size() != 0 ) {
			// Build the image URL
			String imageURL = RESTServerConfig.getServerURL() + "/images/" +
					episode.getEpisodeID() + "/" + episode.getImages().getImage().get( 0 ).getSrc();

			// Load the image
			ImageLoader il = ImageLoader.getInstance();
			return il.loadImageFromUrl( imageURL, 160, 120 );
		}

		return null;
	}

}
