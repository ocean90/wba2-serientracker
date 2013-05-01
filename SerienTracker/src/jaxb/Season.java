//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.01 um 01:52:34 PM CEST 
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
 *         &lt;element ref="{}picture"/>
 *         &lt;element ref="{}episodes"/>
 *       &lt;/sequence>
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
    "number",
    "picture",
    "episodes"
})
@XmlRootElement(name = "season")
public class Season {

    protected int number;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String picture;
    @XmlElement(required = true)
    protected Episodes episodes;
    @XmlAttribute(name = "serieID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger serieID;
    @XmlAttribute(name = "seasonID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger seasonID;

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

}
