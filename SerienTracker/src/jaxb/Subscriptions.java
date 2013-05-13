//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.13 um 10:30:59 AM CEST 
//


package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}genres" minOccurs="0"/>
 *         &lt;element ref="{}series" minOccurs="0"/>
 *         &lt;element ref="{}airdays" minOccurs="0"/>
 *         &lt;element ref="{}airtimes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "genres",
    "series",
    "airdays",
    "airtimes"
})
@XmlRootElement(name = "subscriptions")
public class Subscriptions {

    protected Genres genres;
    protected Series series;
    protected Airdays airdays;
    protected Airtimes airtimes;

    /**
     * Ruft den Wert der genres-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Genres }
     *     
     */
    public Genres getGenres() {
        return genres;
    }

    /**
     * Legt den Wert der genres-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Genres }
     *     
     */
    public void setGenres(Genres value) {
        this.genres = value;
    }

    /**
     * Ruft den Wert der series-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Series }
     *     
     */
    public Series getSeries() {
        return series;
    }

    /**
     * Legt den Wert der series-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Series }
     *     
     */
    public void setSeries(Series value) {
        this.series = value;
    }

    /**
     * Ruft den Wert der airdays-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Airdays }
     *     
     */
    public Airdays getAirdays() {
        return airdays;
    }

    /**
     * Legt den Wert der airdays-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Airdays }
     *     
     */
    public void setAirdays(Airdays value) {
        this.airdays = value;
    }

    /**
     * Ruft den Wert der airtimes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Airtimes }
     *     
     */
    public Airtimes getAirtimes() {
        return airtimes;
    }

    /**
     * Legt den Wert der airtimes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Airtimes }
     *     
     */
    public void setAirtimes(Airtimes value) {
        this.airtimes = value;
    }

}
