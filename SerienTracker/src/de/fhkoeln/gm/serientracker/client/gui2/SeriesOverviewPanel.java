package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.client.utils.HTTPClient;
import de.fhkoeln.gm.serientracker.client.utils.HTTPClient.HTTPMethod;
import de.fhkoeln.gm.serientracker.client.utils.ImageLoader;
import de.fhkoeln.gm.serientracker.jaxb.Episode;
import de.fhkoeln.gm.serientracker.jaxb.Episodes;
import de.fhkoeln.gm.serientracker.jaxb.Season;
import de.fhkoeln.gm.serientracker.jaxb.Serie;
import de.fhkoeln.gm.serientracker.jaxb.Series;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;

/**
 * Provides the series/seasons/episodes overview.
 *
 * @author Dominik Schilling
 */
public class SeriesOverviewPanel extends JPanel implements ListSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private JList seriesList;
	private JProgressBar loadingStatus;
	private JScrollPane seriesListScroll;
	private JPanel seriesOverview;
	private JPanel listPanel;
	private JPanel infoPanel;
	private JPanel seriesInfo;
	private CardLayout mainCardLayout;
	private JButton btnViewDetails;
	private JPanel seriesDetails;
	private JButton btnSubscribe;
	private JButton btnBackToOverview;
	private JList episodesList;

	private JPanel seasonButtons;

	// Constructor
	public SeriesOverviewPanel() {
		mainCardLayout = new CardLayout();

		this.setLayout( mainCardLayout );

		this.add( this.getSeriesOverviewPanel(), "SERIESOVERVIEW" );
	    this.add( this.getSeriesDetailsPanel(), "SERIESDETAILS" );

	    mainCardLayout.show( this, "SERIESOVERVIEW" );
	}

	/**
	 * Builds the panel for the series overview with the series list.
	 *
	 * @return JPanel
	 */
	private JPanel getSeriesOverviewPanel() {
		// Set up the panel layout
		seriesOverview = new JPanel( new MigLayout( "", "[50%][grow]", "[][grow][]" ) );

		// Panel headline
		JLabel seriesOverviewTitle = new JLabel( "Series Overview" );
		seriesOverviewTitle.setHorizontalAlignment( JLabel.CENTER );
		seriesOverviewTitle.setFont( new Font( null, Font.BOLD, 20 ) );
		seriesOverview.add( seriesOverviewTitle, "span, grow, gapbottom 10" );

		// List panel for series
		listPanel = new JPanel( new MigLayout( "insets 0, fill" ) );
		seriesList = new JList();
		seriesList.setCellRenderer( new SeriesListCellRenderer() );
		seriesList.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		seriesList.setLayoutOrientation( JList.VERTICAL );
		seriesList.setVisibleRowCount( 20 );
		seriesList.addListSelectionListener( this );
		seriesListScroll = new JScrollPane( seriesList );
		listPanel.add( seriesListScroll, "grow" );
		seriesOverview.add( listPanel, "cell 0 1, grow" );

		// Info panel for short series info
		infoPanel = new JPanel( new MigLayout( "insets 0, fill" ) );
		infoPanel.setBorder( BorderFactory.createTitledBorder( null,
				"Series Details", TitledBorder.LEFT, TitledBorder.TOP,
				new Font( "", Font.BOLD, 12 ) ) );
		seriesInfo = new JPanel( new MigLayout( "fill", "grow", "grow" ) );
		infoPanel.add( seriesInfo, "grow" );
		seriesOverview.add( infoPanel, "cell 1 1, grow" );

		// Progress bar
		loadingStatus = new JProgressBar();
		loadingStatus.setIndeterminate( true );
		seriesOverview.add( loadingStatus, "cell 0 2 2 1, grow" );

		// Fetch the series via REST API
		final SeriesOverviewPanel self = this;
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				self.fetchSeries();
			}
		} );

		return seriesOverview;
	}

	/**
	 * Repaints the series info box based on the current selection.
	 */
	private void updateSeriesShortInfo() {
		// Remove all existing components
		seriesInfo.removeAll();

		// Get the selected series
		Serie serie = this.getSelectedSerie();
		if ( serie != null ) {
			// Check for an image
			Image image = this.getSeriesImage( 150, 200 );
			if ( image != null ) {
				ImagePanel seriesImage = new ImagePanel( image );
				seriesInfo.add( seriesImage, "wrap" );
			}

			// Display title
			JLabel seriesTitle = new JLabel( serie.getTitle() );
			seriesTitle.setFont( new Font( null, Font.BOLD, 14 ) );
			seriesInfo.add( seriesTitle, "wrap" );

			// Display year
			seriesInfo.add( new JLabel( "Year: " + serie.getYear() ), "wrap" );

			// Add the View Details button, which opens the details panel
			btnViewDetails = new JButton( "View Details" );
			btnViewDetails.addActionListener( this );
			seriesInfo.add( btnViewDetails, "bottom, right" );
		}

		// Repaint the panel
		seriesInfo.validate();
		seriesInfo.repaint();
	}

	/**
	 * Builds the panel for the series details.
	 *
	 * @return JPanel
	 */
	private JPanel getSeriesDetailsPanel() {
		// Set up the panel layout
		seriesDetails = new JPanel( new MigLayout( "", "[300][grow][]", "[40][20][20][150][20][140][30]" ) );

		// Repaint the content of the panel
		this.updateSeriesDetails();

		// Return the panel
		return seriesDetails;
	}

	/**
	 * Repaints the series info box based on the current selection.
	 */
	private void updateSeriesDetails() {
		// Remove all existing components
		seriesDetails.removeAll();

		// Get the selected series
		Serie serie = this.getSelectedSerie();
		if ( serie != null ) {
			// Check for an image
			Image image = this.getSeriesImage( 300, 400 );
			if ( image != null ) {
				ImagePanel seriesImage = new ImagePanel( image );
				seriesDetails.add( seriesImage, "cell 0 0 1 6, gapright 10" ); // cell: col row colspan rowspan
			}

			// Display title
			JLabel seriesTitle = new JLabel( serie.getTitle() );
			seriesTitle.setFont( new Font( null, Font.BOLD, 20 ) );
			seriesDetails.add( seriesTitle, "cell 1 0, top" );

			// Add a (Un)Subscribe button
			btnSubscribe = new JButton( "Subscribe" );
			btnSubscribe.addActionListener( this );
			seriesDetails.add( btnSubscribe, "cell 2 0, top, right" );

			// Display year and network
			seriesDetails.add( new JLabel( "Year: " + serie.getYear() ), "cell 1 1, width 20%" );
			seriesDetails.add( new JLabel( "Network: " + serie.getNetwork() ), "cell 1 1" );

			// Display country and runtime
			seriesDetails.add( new JLabel( "Country: " + serie.getCountry().value() ), "cell 1 2, width 20%" );
			seriesDetails.add( new JLabel( "Runtime: " + serie.getEpisoderuntime().value() ), "cell 1 2" );

			// Display the overview
			JTextArea seriesDescription = new JTextArea( serie.getOverview() );
			seriesDescription.setFont( new Font( null, Font.ITALIC, 14 ) );
			seriesDescription.setLineWrap( true );
			seriesDescription.setWrapStyleWord( true );
			seriesDescription.setBackground( getBackground() );
			seriesDescription.setEditable( false );
			seriesDescription.setHighlighter( null );
			JScrollPane seriesDescriptionScroll = new JScrollPane( seriesDescription );
			seriesDescriptionScroll.setBorder( null );
			seriesDescriptionScroll.setBackground( getBackground() );
			seriesDetails.add( seriesDescriptionScroll, "cell 1 3 2 1, gaptop 10, grow" );

			// Check for seaons
			if ( serie.getSeasons() != null && serie.getSeasons().getSeason().size() != 0 ) {
				// Seaons exists
				seriesDetails.add( new JLabel( "Season: " ), "cell 1 4 2 1" );

				// Add a button for each season
				seasonButtons = new JPanel();
				boolean first = true;
				for ( Season season : serie.getSeasons().getSeason() ) {
					JButton btnSeason = new JButton();
					btnSeason.setText( String.valueOf( season.getSeasonNumber() ) );
					btnSeason.setName( String.valueOf( season.getSeasonNumber() ) );
					btnSeason.setActionCommand( "SEASONSWITCH:" + season.getSeasonNumber() );
					btnSeason.addActionListener( this );
					// Disable the first button because it's the active one
					if ( first ) {
						btnSeason.setEnabled( false );
						first = false;
					}
					seasonButtons.add( btnSeason );
				}
				seriesDetails.add( seasonButtons, "cell 1 4 2 1" );


				// Horizontal list panel for the episodes
				episodesList = new JList();
				episodesList.setCellRenderer( new EpisodeListCellRenderer() );
				episodesList.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
				episodesList.setLayoutOrientation( JList.HORIZONTAL_WRAP );
				episodesList.setVisibleRowCount( 1 );
				episodesList.addListSelectionListener( this );
				episodesList.setBackground( getBackground() );
				seriesListScroll = new JScrollPane( episodesList );
				seriesListScroll.setBorder( null );
				seriesListScroll.setBackground( getBackground() );
				seriesDetails.add( seriesListScroll, "cell 1 5 2 1, grow" );

				// Load the images of the episodes
				final SeriesOverviewPanel self = this;
				SwingUtilities.invokeLater( new Runnable() {
					public void run() {
						self.updateEpisodelist( 1 );
					}
				} );
			}

		}

		// Add a Back To Overview button which closes this panel
		btnBackToOverview = new JButton( "Back To Overview" );
		btnBackToOverview.addActionListener( this );
		seriesDetails.add( btnBackToOverview, "cell 0 6 3 1, right" );

		// Repaint the panel
		seriesInfo.validate();
		seriesInfo.repaint();
	}

	/**
	 * Repaints the episodes list based on the season selection.
	 *
	 * @param seasonNumber
	 */
	private void updateEpisodelist( int seasonNumber ) {
		// Get the selected series
		Serie serie = this.getSelectedSerie();

		// Get the episodes for the selected season
		Episodes episodes = serie.getSeasons().getSeason().get( seasonNumber - 1 ).getEpisodes();

		if ( episodes == null )
			return;

		// Build the new list model for the list
		DefaultListModel episodesModel = new DefaultListModel();
		for ( Episode episode : episodes.getEpisode() )
			episodesModel.addElement( episode );

		// Set the new model
		episodesList.setModel( episodesModel );
	}

	/**
	 * Returns the image for the selected series.
	 *
	 * @param int width
	 * @param int height
	 * @return Image
	 */
	private Image getSeriesImage( int width, int height ) {
		// Get the selected series
		Serie serie = this.getSelectedSerie();

		// Check if there is an image
		if ( serie.getImages() != null && serie.getImages().getImage().size() != 0 ) {
			// Build the image URL
			String imageURL = RESTServerConfig.getServerURL() + "/images/" +
							serie.getSerieID() + "/" + serie.getImages().getImage().get( 0 ).getSrc();

			// Load the image
			ImageLoader il = ImageLoader.getInstance();
			return il.loadImageFromUrl( imageURL, width, height );
		}

		return null;
	}

	/**
	 * Fetches the series from the REST API.
	 */
	private void fetchSeries() {
		Logger.log( "Fetching series..." );

		// Build the request
		HTTPClient httpClient = new HTTPClient();
		httpClient.setMethod( HTTPMethod.GET );
		httpClient.setEndpoint( "/series/" );
		httpClient.execute();

		// Check for an error
		if ( httpClient.hasError() ) {
			Logger.err( "HTTPClient has returned an error" );
			return;
		}

		Series rawSeries = httpClient.getResponse().getEntity( Series.class );
		List<Serie> series = rawSeries.getSerie();

		Logger.log( series.size() + " series fetched" );

		// Build the new list model for the list
		DefaultListModel seriesModel = new DefaultListModel();
		for ( Serie serie : series )
			seriesModel.addElement( serie );

		// Set the new model
		seriesList.setModel( seriesModel );

		// Hode the loading status
		loadingStatus.setVisible( false );
	}

	/**
	 * Returns the current selected series.
	 *
	 * @return Serie
	 */
	private Serie getSelectedSerie() {
		return (Serie) seriesList.getSelectedValue();
	}

	/**
	 * Event handler for the list panels.
	 */
	@Override
	public void valueChanged( ListSelectionEvent e ) {
		if ( e.getSource() == seriesList && e.getValueIsAdjusting() ) {
			// Series list
			Serie serie = this.getSelectedSerie();
			Logger.log( "Clicked " + serie.getTitle() );
			this.updateSeriesShortInfo();
		}
	}

	/**
	 * Event handler for button clicks.
	 */
	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnViewDetails ) {
			// More Details
			Logger.log( "Details clicked" );
			this.updateSeriesDetails();
		    mainCardLayout.show( this, "SERIESDETAILS" );
		} else if ( e.getSource() == btnBackToOverview ) {
			// Back To Overview
			Logger.log( "Back clicked" );
		    mainCardLayout.show( this, "SERIESOVERVIEW" );
		} else if ( e.getSource() == btnSubscribe ) {
			// (Un)Subscribe
			Logger.log( "Subscribe clicked" );
		} else if ( e.getActionCommand().contains( "SEASONSWITCH:" ) ) {
			// Season switch
			String[] command = e.getActionCommand().split( ":" );
			Logger.log( "Season switch to " + command[1] );

			// Disable the selected season button
			for ( Component comp : seasonButtons.getComponents() ) {
				if ( comp == e.getSource() ) {
					( (JButton) comp).setEnabled( false );
				} else {
					( (JButton) comp).setEnabled( true );
				}
			}

			this.updateEpisodelist( Integer.valueOf( command[1] ) );
		}
	}
}
