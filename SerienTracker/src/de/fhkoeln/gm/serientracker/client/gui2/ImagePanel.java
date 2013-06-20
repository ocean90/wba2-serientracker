package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import de.fhkoeln.gm.serientracker.utils.Logger;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image img;

	public ImagePanel( Image img ) {
		this.img = img;
		this._setSize();
	}

	public void setImage( Image img ) {
		this.img = img;
		this._setSize();
	}

	private void _setSize() {
		Dimension size = new Dimension( img.getWidth( null ), img.getHeight( null ) );

		Logger.log( "Width: " + size.width + " Height: " + size.height );

		this.setSize( size );
		this.setMaximumSize( size );
		this.setMinimumSize( size );
		this.setPreferredSize( size );
	}

	@Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        g.drawImage( this.img , 0, 0, null );
    }

}
