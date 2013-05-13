//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.12 um 08:12:10 PM CEST 
//


package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;choice minOccurs="0">
 *         &lt;sequence>
 *           &lt;element ref="{}seasonNumber"/>
 *           &lt;element ref="{}episodes"/>
 *           &lt;element ref="{}images"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute ref="{}serieID use="required""/>
 *       &lt;attribute ref="{}seasonID use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "seasonNumber",
    "episodes",
    "images"
})
@XmlRootElement(name = "season")
public class Season {

    protected Integer seasonNumber;
    protected Episodes episodes;
    protected Images images;
    @XmlAttribute(name = "serieID", required = true)
    protected String serieID;
    @XmlAttribute(name = "seasonID", required = true)
    protected String seasonID;

    /**
     * Ruft den Wert der seasonNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    /**
     * Legt den Wert der seasonNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeasonNumber(Integer value) {
        this.seasonNumber = value;
    }

    /**
     * Ruft den Wert der episodes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Episodes }
     *     
     */
    public Episodes getEpisodes() {
        return episodes;
    }

    /**
     * Legt den Wert der episodes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Episodes }
     *     
     */
    public void setEpisodes(Episodes value) {
        this.episodes = value;
    }

    /**
     * Ruft den Wert der images-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Images }
     *     
     */
    public Images getImages() {
        return images;
    }

    /**
     * Legt den Wert der images-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Images }
     *     
     */
    public void setImages(Images value) {
        this.images = value;
    }

    /**
     * Ruft den Wert der serieID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieID() {
        return serieID;
    }

    /**
     * Legt den Wert der serieID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieID(String value) {
        this.serieID = value;
    }

    /**
     * Ruft den Wert der seasonID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonID() {
        return seasonID;
    }

    /**
     * Legt den Wert der seasonID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonID(String value) {
        this.seasonID = value;
    }

}
