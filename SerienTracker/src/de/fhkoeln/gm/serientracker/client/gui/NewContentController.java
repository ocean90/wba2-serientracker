package de.fhkoeln.gm.serientracker.client.gui;

import java.awt.Component;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import javax.swing.JCheckBox;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import de.fhkoeln.gm.serientracker.client.utils.HTTPClient;
import de.fhkoeln.gm.serientracker.client.utils.HTTPClient.HTTPMethod;
import de.fhkoeln.gm.serientracker.jaxb.Country;
import de.fhkoeln.gm.serientracker.jaxb.Runtime;
import de.fhkoeln.gm.serientracker.jaxb.Network;
import de.fhkoeln.gm.serientracker.jaxb.Weekday;
import de.fhkoeln.gm.serientracker.jaxb.Genre;
import de.fhkoeln.gm.serientracker.jaxb.Genres;
import de.fhkoeln.gm.serientracker.jaxb.ObjectFactory;
import de.fhkoeln.gm.serientracker.jaxb.Serie;
import de.fhkoeln.gm.serientracker.utils.Logger;

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

	// Holds the last error message
	private String error;

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
		Serie serie = this.preparedSerie();

		HTTPClient httpClient = new HTTPClient();
		httpClient.setMethod( HTTPMethod.POST );
		httpClient.setEntity( serie );
		httpClient.setEndpoint( "/series/" );
		httpClient.execute();

		if ( httpClient.hasError() ) {
			Logger.err( "HTTPClient has returned an error" );
			this.error = httpClient.getErrorMessage();
			return;
		}

		URI output = httpClient.getResponse().getLocation();
		Logger.log( "Response: " + output.toString() );
	}

	/**
	 * Prepares a series.
	 *
	 * @return Serie
	 */
	private Serie preparedSerie() {
		Serie serie = this.objectFactory.createSerie();

		// Title
		serie.setTitle( this.view.seriesTitle.getText() );

		// Genres
		Genres genres = this.objectFactory.createGenres();
		List<Genre> genreList = genres.getGenre();
		for ( Component comp : this.view.seriesGenreRelations.getComponents() ) {
			if ( comp instanceof JCheckBox ) {
				JCheckBox genreCheckbox = (JCheckBox) comp;
				if ( genreCheckbox.isSelected() ) {
					genreList.add( Genre.fromValue( genreCheckbox.getName() ) );
				}
			}
		}

		// Year
		serie.setYear( Integer.valueOf( this.view.seriesYear.getText() ) );

		// Firstaired
		XMLGregorianCalendar firstaired = null;
		String firstairedInput = this.view.seriesFirstAired.getText();
		if ( ! firstairedInput.equals( "" ) ) {
			try {
				firstaired = DatatypeFactory.newInstance().newXMLGregorianCalendar( firstairedInput );
			} catch ( DatatypeConfigurationException e ) {
				e.printStackTrace();
			}
		}
		serie.setFirstaired( firstaired );

		// Country
		serie.setCountry( Country.fromValue( (String) this.view.seriesCountry.getSelectedItem() ) );

		// Overview
		serie.setOverview( this.view.seriesOverview.getText() );

		// Runtime
		serie.setEpisoderuntime( Runtime.fromValue( (BigInteger) this.view.seriesRuntime.getSelectedItem() ) );

		// Network
		serie.setNetwork( Network.fromValue( (String) this.view.seriesNetwork.getSelectedItem() ) );

		// Airday
		serie.setAirday( Weekday.fromValue( (String) this.view.seriesAirday.getSelectedItem() ) );

		// Airtime TODO
		//serie.setAirtime(value)

		return serie;
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

	/**
	 * Returns the latest error message.
	 *
	 * @return String
	 */
	public String getErrorMessage() {
		return this.error;
	}

	/**
	 * Checks if an error exists.
	 *
	 * @return boolean
	 */
	public boolean hasError() {
		return this.error != null;
	}
}
