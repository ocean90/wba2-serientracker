package de.fhkoeln.gm.serientracker.client.gui2;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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

public class SeriesListPanel extends JPanel implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private JList seriesList;
	DefaultListModel model = new DefaultListModel();
	private JProgressBar loadingStatus;
	private JScrollPane seriesListScroll;
	private JPanel seriesOverview;
	private JPanel listPanel;
	private JPanel infoPanel;
	private JPanel seriesInfo;

	public SeriesListPanel() {
		final SeriesListPanel self = this;
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				self.fetchSeries();
			}
		} );

		CardLayout mainCardLayout = new CardLayout();

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
		seriesInfo = new JPanel( new MigLayout( "ins 0, fill" ) );

		JButton details = new JButton( "View Details" );
		details.setEnabled( false );
		seriesInfo.add( details, "bottom, right" );

		return seriesInfo;
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
		}
	}
}
