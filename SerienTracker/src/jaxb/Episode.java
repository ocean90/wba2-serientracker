//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.04.27 um 02:59:22 PM CEST 
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
 *         &lt;element ref="{}episode_title"/>
 *         &lt;element ref="{}episode_overview"/>
 *         &lt;element ref="{}episode_number"/>
 *         &lt;element ref="{}episode_airdate"/>
 *         &lt;element ref="{}episode_picture"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{}serie_id use="required""/>
 *       &lt;attribute ref="{}season_number use="required""/>
 *       &lt;attribute ref="{}episode_id use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "episodeTitle",
    "episodeOverview",
    "episodeNumber",
    "episodeAirdate",
    "episodePicture"
})
@XmlRootElement(name = "episode")
public class Episode {

    @XmlElement(name = "episode_title", required = true)
    protected String episodeTitle;
    @XmlElement(name = "episode_overview", required = true)
    protected String episodeOverview;
    @XmlElement(name = "episode_number", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger episodeNumber;
    @XmlElement(name = "episode_airdate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar episodeAirdate;
    @XmlElement(name = "episode_picture", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String episodePicture;
    @XmlAttribute(name = "serie_id", required = true)
    protected BigInteger serieId;
    @XmlAttribute(name = "season_number", required = true)
    protected BigInteger seasonNumber;
    @XmlAttribute(name = "episode_id", required = true)
    protected BigInteger episodeId;

    /**
     * Ruft den Wert der episodeTitle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpisodeTitle() {
        return episodeTitle;
    }

    /**
     * Legt den Wert der episodeTitle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpisodeTitle(String value) {
        this.episodeTitle = value;
    }

    /**
     * Ruft den Wert der episodeOverview-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpisodeOverview() {
        return episodeOverview;
    }

    /**
     * Legt den Wert der episodeOverview-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpisodeOverview(String value) {
        this.episodeOverview = value;
    }

    /**
     * Ruft den Wert der episodeNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEpisodeNumber() {
        return episodeNumber;
    }

    /**
     * Legt den Wert der episodeNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEpisodeNumber(BigInteger value) {
        this.episodeNumber = value;
    }

    /**
     * Ruft den Wert der episodeAirdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEpisodeAirdate() {
        return episodeAirdate;
    }

    /**
     * Legt den Wert der episodeAirdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEpisodeAirdate(XMLGregorianCalendar value) {
        this.episodeAirdate = value;
    }

    /**
     * Ruft den Wert der episodePicture-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpisodePicture() {
        return episodePicture;
    }

    /**
     * Legt den Wert der episodePicture-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpisodePicture(String value) {
        this.episodePicture = value;
    }

    /**
     * Ruft den Wert der serieId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSerieId() {
        return serieId;
    }

    /**
     * Legt den Wert der serieId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSerieId(BigInteger value) {
        this.serieId = value;
    }

    /**
     * Ruft den Wert der seasonNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSeasonNumber() {
        return seasonNumber;
    }

    /**
     * Legt den Wert der seasonNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSeasonNumber(BigInteger value) {
        this.seasonNumber = value;
    }

    /**
     * Ruft den Wert der episodeId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEpisodeId() {
        return episodeId;
    }

    /**
     * Legt den Wert der episodeId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEpisodeId(BigInteger value) {
        this.episodeId = value;
    }

}
