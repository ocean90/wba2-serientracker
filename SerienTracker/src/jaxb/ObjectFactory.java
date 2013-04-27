//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.04.27 um 02:59:22 PM CEST 
//


package jaxb;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EpisodePicture_QNAME = new QName("", "episode_picture");
    private final static QName _EpisodeOverview_QNAME = new QName("", "episode_overview");
    private final static QName _EpisodeTitle_QNAME = new QName("", "episode_title");
    private final static QName _EpisodeAirdate_QNAME = new QName("", "episode_airdate");
    private final static QName _EpisodeNumber_QNAME = new QName("", "episode_number");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Episode }
     * 
     */
    public Episode createEpisode() {
        return new Episode();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "episode_picture")
    public JAXBElement<String> createEpisodePicture(String value) {
        return new JAXBElement<String>(_EpisodePicture_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "episode_overview")
    public JAXBElement<String> createEpisodeOverview(String value) {
        return new JAXBElement<String>(_EpisodeOverview_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "episode_title")
    public JAXBElement<String> createEpisodeTitle(String value) {
        return new JAXBElement<String>(_EpisodeTitle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "episode_airdate")
    public JAXBElement<XMLGregorianCalendar> createEpisodeAirdate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_EpisodeAirdate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "episode_number")
    public JAXBElement<BigInteger> createEpisodeNumber(BigInteger value) {
        return new JAXBElement<BigInteger>(_EpisodeNumber_QNAME, BigInteger.class, null, value);
    }

}
