package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import de.fhkoeln.gm.serientracker.utils.Logger;

/**
 * Provides a panel for images.
 *
 * @author Dominik Schilling
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// Holds the image
	private Image img;

	/**
	 * Constructor.
	 */
	public ImagePanel() {}

	/**
	 * Constructor.
	 * Sets the image and calls the size calculator.
	 *
	 * @param Image img
	 */
	public ImagePanel( Image img ) {
		this.img = img;
		this._setSize();
	}

	/**
	 * Sets the image.
	 *
	 * @param Image img
	 */
	public void setImage( Image img ) {
		this.img = img;
		this._setSize();
	}

	/**
	 * Sets the size of the image panel.
	 */
	private void _setSize() {
		// Get the size of the image
		Dimension size = new Dimension( img.getWidth( null ), img.getHeight( null ) );

		Logger.log( "Width: " + size.width + " Height: " + size.height );

		// Set all sizes to the image size
		this.setSize( size );
		this.setMaximumSize( size );
		this.setMinimumSize( size );
		this.setPreferredSize( size );
	}

	/**
	 * Overrides the parent paint method to paint the image.
	 */
	@Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        g.drawImage( this.img , 0, 0, null );
    }

}
