package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import de.fhkoeln.gm.serientracker.client.utils.HTTPClient;
import de.fhkoeln.gm.serientracker.client.utils.HTTPClient.HTTPMethod;
import de.fhkoeln.gm.serientracker.jaxb.Serie;
import de.fhkoeln.gm.serientracker.jaxb.Series;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.webservice.RESTServerConfig;

public class SeriesListPanel extends JPanel implements ListSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JList seriesList;
	private DefaultListModel model = new DefaultListModel();
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

	public SeriesListPanel() {
		mainCardLayout = new CardLayout();

		this.setLayout( mainCardLayout );

		seriesOverview = new JPanel( new MigLayout( "", "[50%][grow]", "[][grow][]" ) );

		JLabel seriesOverviewTitle = new JLabel( "Series Overview" );
		seriesOverviewTitle.setHorizontalAlignment( JLabel.CENTER );
		seriesOverviewTitle.setFont( new Font( null, Font.BOLD, 20 ) );
		seriesOverview.add( seriesOverviewTitle, "span, grow, gapbottom 10" );

		listPanel = new JPanel( new MigLayout( "insets 0, fill" ) );
		seriesOverview.add( listPanel, "cell 0 1, grow" );
		infoPanel = new JPanel( new MigLayout( "insets 0, fill" ) );
		seriesOverview.add( infoPanel, "cell 1 1, grow" );

	    listPanel.add( this.getSeriesList(), "grow" );

		infoPanel.setBorder( BorderFactory.createTitledBorder( null,
				"Series Details", TitledBorder.LEFT, TitledBorder.TOP,
				new Font( "", Font.BOLD, 12 ) ) );

		infoPanel.add( this.getSeriesInfo(), "grow" );

		loadingStatus = new JProgressBar();
		loadingStatus.setIndeterminate( true );

		seriesOverview.add( loadingStatus, "cell 0 2 2 1, grow" );

		final SeriesListPanel self = this;
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				self.fetchSeries();
			}
		} );


		JPanel seriesDetails = new JPanel( new MigLayout( "debug", "grow", "grow" ) );
		seriesDetails.add( new JLabel( "Details" ), "center" );

	    this.add( this.getSeriesDetails(), "SERIESDETAILS" );


	    this.add( seriesOverview, "SERIESOVERVIEW" );
	    mainCardLayout.show( this, "SERIESOVERVIEW" );
	}

	private JScrollPane getSeriesList() {
		seriesList = new JList();
		seriesList.setCellRenderer( new SeriesListCellRenderer() );
		seriesList.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		seriesList.setLayoutOrientation( JList.VERTICAL );
		seriesList.setVisibleRowCount( 20 );
		seriesList.addListSelectionListener( this );
		seriesListScroll = new JScrollPane( seriesList );

		return seriesListScroll;
	}

	private JPanel getSeriesInfo() {

		this.updateSeriesInfo();

		return seriesInfo;
	}

	private void updateSeriesInfo() {
		if ( seriesInfo == null ) {
			seriesInfo = new JPanel( new MigLayout( "fill", "grow", "grow" ) );
			seriesInfo.setVisible( false );
		} else {
			seriesInfo.setVisible( false );
			seriesInfo.removeAll();
		}

		Serie serie = this.getSelectedSerie();
		if ( serie != null ) {
			ImageIcon image = this.getSeriesImage( 150, 150 );
			if ( image != null ) {
				JLabel seriesImage = new JLabel( image );
				seriesInfo.add( seriesImage, "wrap" );
			}

			seriesInfo.add( new JLabel( serie.getTitle() ), "wrap" );
			seriesInfo.add( new JLabel( "Year: " + serie.getYear() ), "wrap" );

			btnViewDetails = new JButton( "View Details" );
			btnViewDetails.addActionListener( this );
			seriesInfo.add( btnViewDetails, "bottom, right" );
		}

		seriesInfo.setVisible( true );

	}

	private JPanel getSeriesDetails() {
		this.updateSeriesDetails();

		return seriesDetails;
	}

	private void updateSeriesDetails() {
		if ( seriesDetails == null ) {
			seriesDetails = new JPanel( new MigLayout( "fill", "grow", "grow" ) );
			seriesDetails.setVisible( false );
		} else {
			seriesDetails.setVisible( false );
			seriesDetails.removeAll();
		}

		Serie serie = this.getSelectedSerie();
		if ( serie != null ) {
			ImageIcon image = this.getSeriesImage( 300, 300 );
			if ( image != null ) {
				JLabel seriesImage = new JLabel( image );
				seriesDetails.add( seriesImage, "wrap" );
			}

			seriesDetails.add( new JLabel( serie.getTitle() ), "wrap" );
			seriesDetails.add( new JLabel( "Year: " + serie.getYear() ), "wrap" );

			btnSubscribe = new JButton( "Subscribe" );
			btnSubscribe.addActionListener( this );
			seriesDetails.add( btnSubscribe, "bottom, right" );
		}

		btnBackToOverview = new JButton( "Overview" );
		btnBackToOverview.addActionListener( this );
		seriesDetails.add( btnBackToOverview, "bottom, right" );

		seriesDetails.setVisible( true );
	}

	private ImageIcon getSeriesImage( int width, int height ) {
		Serie serie = this.getSelectedSerie();

		if ( serie.getImages() != null && serie.getImages().getImage().size() != 0 ) {
			String image = RESTServerConfig.getServerURL() + "/images/" +
							serie.getSerieID() + "/" + serie.getImages().getImage().get( 0 ).getSrc();
			try {
				Image imageScaled = new ImageIcon( new URL( image ) ).getImage().getScaledInstance( width, height, Image.SCALE_DEFAULT );
				return new ImageIcon( imageScaled );
			} catch ( MalformedURLException e ) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private void fetchSeries() {
		Logger.log( "Fetching series..." );


		HTTPClient httpClient = new HTTPClient();
		httpClient.setMethod( HTTPMethod.GET );
		httpClient.setEndpoint( "/series/" );
		httpClient.execute();

		if ( httpClient.hasError() ) {
			Logger.err( "HTTPClient has returned an error" );
			return;
		}

		Series rawSeries = httpClient.getResponse().getEntity( Series.class );
		List<Serie> series = rawSeries.getSerie();

		Logger.log( series.size() + " series fetched" );

		for ( Serie serie : series )
			model.addElement( serie );

		seriesList.setModel( model );
		loadingStatus.setVisible( false );
	}

	private Serie getSelectedSerie() {
		return (Serie) seriesList.getSelectedValue();
	}

	@Override
	public void valueChanged( ListSelectionEvent e ) {
		if ( e.getSource() == seriesList && e.getValueIsAdjusting() ) {
			Serie serie = this.getSelectedSerie();
			Logger.log( "Clicked " + serie.getTitle() );
			this.updateSeriesInfo();
		}
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == btnViewDetails ) {
			Logger.log( "Details clicked" );
			this.updateSeriesDetails();
		    mainCardLayout.show( this, "SERIESDETAILS" );
		} else if ( e.getSource() == btnBackToOverview ) {
			Logger.log( "Back clicked" );
		    mainCardLayout.show( this, "SERIESOVERVIEW" );
		} else if ( e.getSource() == btnSubscribe ) {
			Logger.log( "Subscribe clicked" );
		}
	}
}
