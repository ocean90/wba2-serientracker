package de.fhkoeln.gm.serientracker.client.gui2;

import de.fhkoeln.gm.serientracker.jaxb.ObjectFactory;
import de.fhkoeln.gm.serientracker.jaxb.Serie;

/**
 * Controller class for saving new content.
 *
 * @author Dominik Schilling
 */
public class NewContentController {

	// Holds the new content view
	private NewContentGUI view;

	// Holds the ObjectFactory instance
	private ObjectFactory objectFactory;

	/**
	 * Constructor.
	 * Sets the view.
	 *
	 * @param NewContentGUI view
	 */
	public NewContentController( NewContentGUI view ) {
		// Save the view
		this.view = view;

		// Create the ObjectFactory instance
		this.objectFactory = new ObjectFactory();
	}

	/**
	 * Saves a new series.
	 */
	public void saveSeries() {
		Serie serie = this.objectFactory.createSerie();

		serie.setTitle( this.view.inputTitle.getText() );
	}

	/**
	 * Saves a new season.
	 */
	public void saveSeason() {

	}

	/**
	 * Saves a new episode.
	 */
	public void saveEpisode() {

	}
}
