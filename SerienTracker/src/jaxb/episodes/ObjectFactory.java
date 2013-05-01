//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.01 um 02:31:17 PM CEST 
//


package jaxb.episodes;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.episodes package. 
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

    private final static QName _EpisodeID_QNAME = new QName("", "episodeID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.episodes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Episodes }
     * 
     */
    public Episodes createEpisodes() {
        return new Episodes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "episodeID")
    public JAXBElement<BigInteger> createEpisodeID(BigInteger value) {
        return new JAXBElement<BigInteger>(_EpisodeID_QNAME, BigInteger.class, null, value);
    }

}
