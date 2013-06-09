package de.fhkoeln.gm.serientracker.bot.jobs;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import de.fhkoeln.gm.serientracker.jaxb.Message;
import de.fhkoeln.gm.serientracker.jaxb.ObjectFactory;
import de.fhkoeln.gm.serientracker.utils.Logger;
import de.fhkoeln.gm.serientracker.xmpp.utils.ConnectionHandler;
import de.fhkoeln.gm.serientracker.xmpp.utils.PubSubHandler;

public class NotifcationJob implements Job {
	private PubSubHandler psh;

    public void execute( JobExecutionContext context ) throws JobExecutionException {
    	JobKey key = context.getJobDetail().getKey();
    	Logger.log( "Notification Job" );


    	ConnectionHandler ch = ConnectionHandler.getInstance();

    	if( ! ch.isConnected() ) {
    		Logger.err( "Connection not established!" );
    		return;
    	}

    	ObjectFactory factory = new ObjectFactory();
    	Message message = factory.createMessage();
    	message.setContent( "Hallo, es startet was" );
    	message.setAirdate(null);
    	message.setSerieID(null);
    	message.setSeasonID(null);
    	message.setEpisodeID(null);
    	message.setMessageID(null);

    	StringWriter notification = new StringWriter();
    	try {
			JAXBContext jaxb_context = JAXBContext.newInstance( Message.class );
	    	Marshaller marshaller = jaxb_context.createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_FRAGMENT, true ); // Marshall without namespace
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            marshaller.marshal( message, notification );
		} catch ( JAXBException e ) {}

    	this.psh = ch.getPubSubHandler();

    	LeafNode node = this.psh.getNode( "Science Fiction" );
		Logger.log( "Sending notification" );

        node.publish(
    		new PayloadItem<SimplePayload>(
				"",
				new SimplePayload(
					"message",              // Element name
					"",                     // Namespace
					notification.toString() // Payload
				)
    		)
        );

        /*JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String id = dataMap.getString("id");
        String users = dataMap.getString("users");

		Logger.log( String.format( "Notification Job: Key: %s ID: %s Users: %s", key.getName(), id, users ) );*/
    }

}
