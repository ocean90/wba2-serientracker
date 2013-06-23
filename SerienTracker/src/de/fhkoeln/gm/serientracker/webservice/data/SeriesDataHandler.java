package de.fhkoeln.gm.serientracker.webservice.data;

import java.util.List;

import de.fhkoeln.gm.serientracker.jaxb.Serie;
import de.fhkoeln.gm.serientracker.jaxb.Series;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;

/**
 * Provides the data handler for the series resource.
 *
 * @author Dominik Schilling
 */
public class SeriesDataHandler {
	// Path to the series database
	private static final String XMLFILE = "Database/series.xml";

	// Holds the file handler instance
	private FileHandler<Series> filehandler;

	// Holds the raw data provided by the XML file
	private Series data;

	// Holds the list of series
	private List<Serie> series;

	/**
	 * Constructor. Inits file handler and loads the XMLFILE.
	 */
	public SeriesDataHandler() {
		Logger.log( "SeriesDataHandler called" );

		// Set the filehandler
		this.filehandler = new FileHandler<Series>( Series.class );

		// Get the data of the XML file
		this.data = (Series) filehandler.readXML( XMLFILE );

		// Get the series from the data object
		this.series = this.data.getSerie();
	}

	/**
	 * Saves object to XML file.
	 *
	 * @return boolean
	 */
	private boolean save() {
		Logger.log( "Series saved to file" );

		return this.filehandler.writeXML( this.data, XMLFILE );
	}

	/**
	 * Returns the series JAXB object.
	 *
	 * @return Series
	 */
	public Series getSeries() {
		return this.data;
	}

	/**
	 * Checks if a serie exists by ID.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean SerieExistsByID( String id ) {
		for ( Serie serie : this.series )
			if ( serie.getSerieID().equals( id ) )
				return true;

		return false;
	}

	/**
	 * Returns a serie by ID.
	 *
	 * @param String id
	 * @return Serie
	 */
	public Serie getSerieByID( String id ) {
		for ( Serie serie : this.series )
			if ( serie.getSerieID().equals( id ) )
				return serie;

		return null;
	}

	/**
	 * Adds a new serie.
	 *
	 * @param Serie serie
	 * @return boolean
	 */
	public boolean addSerie( Serie serie ) {
		this.series.add( serie );

		return this.save();
	}

	/**
	 * Updates an existing serie.
	 *
	 * @param Serie newSerie
	 * @return boolean
	 */
	public boolean updateSerie( Serie newSerie ) {
		String id = newSerie.getSerieID();

		int i = 0;
		for ( Serie serie : this.series ) {
			if ( serie.getSerieID().equals( id ) ) {
				// TODO: Only replace the fields of serie, otherwise seasons/episodes
				// can be accidentally deleted.
				this.series.set( i, newSerie );

				return this.save();
			}

			i++;
		}

		return false;
	}

	/**
	 * Removes an existing series.
	 *
	 * @param String id
	 * @return boolean
	 */
	public boolean removeSerie( String id ) {
		// Get the series by id
		Serie serie = getSerieByID( id );
		if ( serie == null )
			return false;

		this.series.remove( serie );

		return this.save();
	}

}
