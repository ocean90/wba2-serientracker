//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.01 um 02:38:50 PM CEST 
//


package jaxb.seasons;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.seasons package. 
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

    private final static QName _SeasonID_QNAME = new QName("", "seasonID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.seasons
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Seasons }
     * 
     */
    public Seasons createSeasons() {
        return new Seasons();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "seasonID")
    public JAXBElement<BigInteger> createSeasonID(BigInteger value) {
        return new JAXBElement<BigInteger>(_SeasonID_QNAME, BigInteger.class, null, value);
    }

}
