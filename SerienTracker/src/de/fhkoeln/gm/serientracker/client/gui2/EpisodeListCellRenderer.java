package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import de.fhkoeln.gm.serientracker.jaxb.Episode;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;

public class EpisodeListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent( JList list, Object value,
			  int index, boolean isSelected, boolean cellHasFocus ) {

		// Get default styling
		super.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );

		Episode episode = (Episode) value;
		this.setText( episode.getTitle() );
		Image image = this.getEpisodeImage( episode );

		if ( image != null )
			this.setIcon( new ImageIcon( image ) );

		return this;
	}

	private Image getEpisodeImage( Episode episode ) {

		if ( episode.getImages() != null && episode.getImages().getImage().size() != 0 ) {
			String imageURL = RESTServerConfig.getServerURL() + "/images/" +
					episode.getEpisodeID() + "/" + episode.getImages().getImage().get( 0 ).getSrc();

			BufferedImage img;
			try {
				img = ImageIO.read( new URL( imageURL ) );
			} catch ( Exception e) {
				e.printStackTrace();
				return null;
			}

			return img.getScaledInstance( 120, 120, Image.SCALE_SMOOTH );
		}

		return null;
	}

}
