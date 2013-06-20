package de.fhkoeln.gm.serientracker.bot.jobs;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import de.fhkoeln.gm.serientracker.jaxb.Episode;
import de.fhkoeln.gm.serientracker.jaxb.Season;
import de.fhkoeln.gm.serientracker.jaxb.Serie;
import de.fhkoeln.gm.serientracker.jaxb.Series;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.utils.FileHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

/**
 * This job does some checks like scanning the series database to create
 * new events.
 *
 * @author Dominik Schilling
 */
public class ProfilerJob implements Job {

	// Where is the series database
	final String SERIES_DATABASE = "Database/series.xml";

	/**
	 * Called when the job is in fired.
	 */
	public void execute( JobExecutionContext context ) throws JobExecutionException {
		ConnectionHandler ch = ConnectionHandler.getInstance();
		PubSubHandler pubSubHandler = ch.getPubSubHandler();

		if( ! ch.isConnected() ) {
			Logger.err( "Connection not established!" );
			return;
		}

		/*
		 * TODO
		 * series.xml auslesen
		 * alle episoden holen
		 * datum prüfen, wenn vergangenheit ignoren
		 * ansonsten node (3 ausführungen) anlegen, wenn nicht vorhanden
		 * dann prüfen ob event schon vorhanden, wenn nicht anlegen
		 * Alte löschen?
		 */

		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch ( SchedulerException e ) {
			Logger.err( "Scheduler failure");
			return;
		}

		List<Episode> futureEpisodes = this.getFutureEpisodes();


		for ( Episode episode : futureEpisodes ) {
			String seriesID = episode.getSerieID();

			String nodeID = "series:" + seriesID;
			if ( ! pubSubHandler.nodeExists( nodeID ) ) {
				Logger.err( "Node doesn't exists: " + nodeID );
			}
		}
	}

	/**
	 * Scans the series database for future episodes.
	 *
	 * @return List
	 */
	private List<Episode> getFutureEpisodes() {
		FileHandler<Series> filehandler = new FileHandler<Series>( Series.class );
		Series series = (Series) filehandler.readXML( SERIES_DATABASE );

		// Check for error
		if ( series == null || series.getSerie() == null )
			return null;

		// Get current date
		GregorianCalendar now = new GregorianCalendar();

		// The list for future episodes
		List<Episode> futureEpisodes = new ArrayList<Episode>();

		for ( Serie serie : series.getSerie() ) {
			// Check seasons
			if ( serie.getSeasons() == null )
				continue;

			// Get seasons
			for ( Season season : serie.getSeasons().getSeason() ) {
				// Check episodes
				if ( season.getEpisodes() == null )
					continue;

				// Get episodes
				for ( Episode episode : season.getEpisodes().getEpisode() ) {
					GregorianCalendar episodeDate = episode.getAirdate().toGregorianCalendar();
					Logger.log(
							episode.getTitle() + " | Airdate: " +
							episode.getAirdate().toGregorianCalendar().getTimeInMillis() + " | Now: " +
							now.getTimeInMillis() + " | Diff: " +
							episodeDate.compareTo( now )
					);

					// Check if the airdate is in the future
					if ( episodeDate.compareTo( now ) == 1 ) {
						// It is, add it to the future list
						futureEpisodes.add( episode );
					}
				}
			}
		}

		return futureEpisodes;

	}

}
