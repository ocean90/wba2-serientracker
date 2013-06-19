package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
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

public class SeriesListPanel extends JPanel implements ListSelectionListener {

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
	private JLabel seriesInfoImage;
	private Serie selectedModel;

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

	    /*
		JPanel panel2 = new JPanel( new MigLayout( "debug", "grow", "grow" ) );
		panel2.add( new JLabel( "panel 2" ), "center" );

	    this.add( panel2, "2" );*/


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

		this.updateSeriesInfo( null );

		return seriesInfo;
	}

	private void updateSeriesInfo( Serie serie ) {
		if ( seriesInfo == null ) {
			seriesInfo = new JPanel( new MigLayout( "debug, gap 0 0", "grow", "grow" ) );
			seriesInfo.setVisible( false );
		} else {
			seriesInfo.setVisible( false );
			seriesInfo.removeAll();
		}

		if ( serie != null ) {
			if ( serie.getImages() != null && serie.getImages().getImage().size() != 0 ) {
				String image = RESTServerConfig.getServerURL() + "/images/" +
								serie.getSerieID() + "/" + serie.getImages().getImage().get( 0 ).getSrc();
				try {
					Image imageScaled = new ImageIcon( new URL( image ) ).getImage().getScaledInstance( 150, 150, Image.SCALE_DEFAULT );
					seriesInfoImage = new JLabel( new ImageIcon( imageScaled ) );
					seriesInfo.add( seriesInfoImage, "wrap" );
				} catch ( MalformedURLException e ) {
					e.printStackTrace();
				}
			}

			seriesInfo.add( new JLabel( serie.getTitle() ), "wrap" );
			seriesInfo.add( new JLabel( "Year: " + serie.getYear() ), "wrap" );

			JButton details = new JButton( "View Details" );
			seriesInfo.add( details, "bottom, right" );
		}

		seriesInfo.setVisible( true );

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

	@Override
	public void valueChanged( ListSelectionEvent e ) {
		if ( e.getSource() == seriesList && e.getValueIsAdjusting() ) {
			Serie serie = (Serie) seriesList.getSelectedValue();
			Logger.log( "Clicked " + serie.getTitle() );
			this.updateSeriesInfo( serie );
		}
	}
}
