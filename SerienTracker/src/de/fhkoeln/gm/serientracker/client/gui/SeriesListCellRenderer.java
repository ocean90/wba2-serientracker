package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import de.fhkoeln.gm.serientracker.jaxb.Serie;

/**
 * Custom cell renderer for series.
 * Shows the series title.
 *
 * @author Dominik Schilling
 */
public class SeriesListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	/**
	 * Renders an series cell.
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
		Serie serie = (Serie) value;

		// Set the text
		this.setText( serie.getTitle() );

		// Return the cell
		return this;
	}

}
