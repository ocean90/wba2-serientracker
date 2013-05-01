//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.01 um 01:52:46 PM CEST 
//


package jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}title"/>
 *         &lt;element ref="{}genre" maxOccurs="unbounded"/>
 *         &lt;element ref="{}year"/>
 *         &lt;element ref="{}firstaired"/>
 *         &lt;element ref="{}country"/>
 *         &lt;element ref="{}overview"/>
 *         &lt;element ref="{}episoderuntime"/>
 *         &lt;element ref="{}network"/>
 *         &lt;element ref="{}airdate"/>
 *         &lt;element ref="{}airtime"/>
 *         &lt;element ref="{}image"/>
 *         &lt;element ref="{}seasons"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{}serieID use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "title",
    "genre",
    "year",
    "firstaired",
    "country",
    "overview",
    "episoderuntime",
    "network",
    "airdate",
    "airtime",
    "image",
    "seasons"
})
@XmlRootElement(name = "serie")
public class Serie {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected List<String> genre;
    protected int year;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstaired;
    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true)
    protected String overview;
    @XmlElement(required = true)
    protected BigInteger episoderuntime;
    @XmlElement(required = true)
    protected String network;
    @XmlElement(required = true)
    protected String airdate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar airtime;
    @XmlElement(required = true)
    protected Image image;
    @XmlElement(required = true)
    protected Seasons seasons;
    @XmlAttribute(name = "serieID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger serieID;

    /**
     * Ruft den Wert der title-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Legt den Wert der title-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the genre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGenre() {
        if (genre == null) {
            genre = new ArrayList<String>();
        }
        return this.genre;
    }

    /**
     * Ruft den Wert der year-Eigenschaft ab.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Legt den Wert der year-Eigenschaft fest.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Ruft den Wert der firstaired-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstaired() {
        return firstaired;
    }

    /**
     * Legt den Wert der firstaired-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstaired(XMLGregorianCalendar value) {
        this.firstaired = value;
    }

    /**
     * Ruft den Wert der country-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Legt den Wert der country-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
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
     * Ruft den Wert der episoderuntime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEpisoderuntime() {
        return episoderuntime;
    }

    /**
     * Legt den Wert der episoderuntime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEpisoderuntime(BigInteger value) {
        this.episoderuntime = value;
    }

    /**
     * Ruft den Wert der network-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetwork() {
        return network;
    }

    /**
     * Legt den Wert der network-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetwork(String value) {
        this.network = value;
    }

    /**
     * Ruft den Wert der airdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirdate() {
        return airdate;
    }

    /**
     * Legt den Wert der airdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirdate(String value) {
        this.airdate = value;
    }

    /**
     * Ruft den Wert der airtime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAirtime() {
        return airtime;
    }

    /**
     * Legt den Wert der airtime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAirtime(XMLGregorianCalendar value) {
        this.airtime = value;
    }

    /**
     * Ruft den Wert der image-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Image }
     *     
     */
    public Image getImage() {
        return image;
    }

    /**
     * Legt den Wert der image-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Image }
     *     
     */
    public void setImage(Image value) {
        this.image = value;
    }

    /**
     * Ruft den Wert der seasons-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Seasons }
     *     
     */
    public Seasons getSeasons() {
        return seasons;
    }

    /**
     * Legt den Wert der seasons-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Seasons }
     *     
     */
    public void setSeasons(Seasons value) {
        this.seasons = value;
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

}
