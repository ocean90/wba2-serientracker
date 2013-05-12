//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.12 um 03:25:22 PM CEST 
//


package jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;choice minOccurs="0">
 *         &lt;sequence>
 *           &lt;element ref="{}title"/>
 *           &lt;element ref="{}genre" maxOccurs="unbounded"/>
 *           &lt;element ref="{}year"/>
 *           &lt;element ref="{}firstaired"/>
 *           &lt;element ref="{}country"/>
 *           &lt;element ref="{}overview"/>
 *           &lt;element ref="{}episoderuntime"/>
 *           &lt;element ref="{}network"/>
 *           &lt;element ref="{}airday"/>
 *           &lt;element ref="{}airtime"/>
 *           &lt;element ref="{}images"/>
 *           &lt;element ref="{}seasons"/>
 *         &lt;/sequence>
 *       &lt;/choice>
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
    "airday",
    "airtime",
    "images",
    "seasons"
})
@XmlRootElement(name = "serie")
public class Serie {

    protected String title;
    protected List<String> genre;
    protected Integer year;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstaired;
    protected String country;
    protected String overview;
    protected BigInteger episoderuntime;
    protected String network;
    protected String airday;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar airtime;
    protected Images images;
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
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Legt den Wert der year-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setYear(Integer value) {
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
     * Ruft den Wert der airday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirday() {
        return airday;
    }

    /**
     * Legt den Wert der airday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirday(String value) {
        this.airday = value;
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
