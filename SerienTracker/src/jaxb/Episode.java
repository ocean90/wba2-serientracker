//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.01 um 01:52:13 PM CEST 
//


package jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{}number"/>
 *         &lt;element ref="{}name"/>
 *         &lt;element ref="{}overview"/>
 *         &lt;element ref="{}airdate"/>
 *         &lt;element ref="{}picture"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{}serieID use="required""/>
 *       &lt;attribute ref="{}seasonID use="required""/>
 *       &lt;attribute ref="{}episodeID use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "number",
    "name",
    "overview",
    "airdate",
    "picture"
})
@XmlRootElement(name = "episode")
public class Episode {

    protected int number;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String overview;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar airdate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String picture;
    @XmlAttribute(name = "serieID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger serieID;
    @XmlAttribute(name = "seasonID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger seasonID;
    @XmlAttribute(name = "episodeID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger episodeID;

    /**
     * Ruft den Wert der number-Eigenschaft ab.
     * 
     */
    public int getNumber() {
        return number;
    }

    /**
     * Legt den Wert der number-Eigenschaft fest.
     * 
     */
    public void setNumber(int value) {
        this.number = value;
    }

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der overview-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Legt den Wert der overview-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverview(String value) {
        this.overview = value;
    }

    /**
     * Ruft den Wert der airdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAirdate() {
        return airdate;
    }

    /**
     * Legt den Wert der airdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAirdate(XMLGregorianCalendar value) {
        this.airdate = value;
    }

    /**
     * Ruft den Wert der picture-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Legt den Wert der picture-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicture(String value) {
        this.picture = value;
    }

    /**
     * Ruft den Wert der serieID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSerieID() {
        return serieID;
    }

    /**
     * Legt den Wert der serieID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSerieID(BigInteger value) {
        this.serieID = value;
    }

    /**
     * Ruft den Wert der seasonID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSeasonID() {
        return seasonID;
    }

    /**
     * Legt den Wert der seasonID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSeasonID(BigInteger value) {
        this.seasonID = value;
    }

    /**
     * Ruft den Wert der episodeID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEpisodeID() {
        return episodeID;
    }

    /**
     * Legt den Wert der episodeID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEpisodeID(BigInteger value) {
        this.episodeID = value;
    }

}
