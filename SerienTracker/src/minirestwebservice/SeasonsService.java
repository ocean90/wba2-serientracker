package minirestwebservice;

import java.io.File;
import java.util.List;

import javax.ws.rs.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jaxb.Season;
import jaxb.Seasons;
import jaxb.ObjectFactory;
import jaxb.Serie;
import jaxb.Series;

@Path( "/seasons" )
public class SeasonsService {

	private Unmarshaller unMarshaller;
	private Marshaller marshaller;
	private final File file = new File( "XML Examples/Series.xml" );

	@GET @Produces( "application/xml" )
	public Seasons getAllSeasons() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Seasons seasons = (Seasons) unMarshaller.unmarshal( this.file );

		return seasons;
	}

	@Path( "/{id}" )
	@GET @Produces( "application/xml" )
	public Season getSingleSeason(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		Seasons rawSeasons = (Seasons) unMarshaller.unmarshal( this.file );

		List<Season> seasons = rawSeasons.getSeason();

		for ( Season season : seasons ) {
			if ( season.getSeasonID().intValue() == id ) {
				return season;
			}
		}

		return null;
	}
	
//	@Path( "/{id}" )
//	@POST @Produces( "application/xml" )
//	public String createSingleSeason(@PathParam("id") int id) throws JAXBException {
//		
//		Season newSeason = new Season();
//				
//		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );
//
//		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
//		this.marshaller   = jaxbContext.createMarshaller(); // Writing
//		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//		Series rawSeries = (Series) unMarshaller.unmarshal( this.file );
//		
//
//		
//		newSeason.setSerieID(value);
//		newSeason.setSeasonNumber(value);
//		newSeason.setSeasonID(value);
//		newSeason.setImages(value);
//		newSeason.setEpisodes(value);
//	}
//	
	@Path( "/{id}" )
	@DELETE @Produces( "application/xml" )
	public String deleteSingleSeason(@PathParam("id") int id) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance( Seasons.class );

		this.unMarshaller = jaxbContext.createUnmarshaller(); // Reading
		this.marshaller   = jaxbContext.createMarshaller(); // Writing
		this.marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		Seasons rawSeasons = (Seasons) unMarshaller.unmarshal( this.file );

		List<Season> seasons = rawSeasons.getSeason();

		for ( Season season : seasons ) {
			if ( season.getSeasonID().intValue() == id ) {
				seasons.remove( season );
				this.marshaller.marshal( rawSeasons, this.file );
				return "<success>1</success";
			}

		}

		return "<success>0</success";
	}


}
