package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import de.fhkoeln.gm.serientracker.jaxb.Serie;

public class SeriesListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent( JList list, Object value,
			  int index, boolean isSelected, boolean cellHasFocus ) {

		// Get default styling
		super.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );

		Serie serie = (Serie) value;
		this.setText( serie.getTitle() );

		return this;
	}

}
