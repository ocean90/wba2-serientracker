//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.17 um 03:19:42 PM CEST 
//


package de.fhkoeln.gm.serientracker.jaxb;

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
 *           &lt;element ref="{}genres"/>
 *           &lt;element ref="{}year"/>
 *           &lt;element ref="{}firstaired"/>
 *           &lt;element ref="{}country"/>
 *           &lt;element ref="{}overview"/>
 *           &lt;element ref="{}episoderuntime"/>
 *           &lt;element ref="{}network"/>
 *           &lt;element ref="{}airday"/>
 *           &lt;element ref="{}airtime"/>
 *           &lt;element ref="{}images" minOccurs="0"/>
 *           &lt;element ref="{}seasons" minOccurs="0"/>
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
    "genres",
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
    protected Genres genres;
    protected Integer year;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar firstaired;
    protected Country country;
    protected String overview;
    protected Runtime episoderuntime;
    protected Network network;
    protected Weekday airday;
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar airtime;
    protected Images images;
    protected Seasons seasons;
    @XmlAttribute(name = "serieID", required = true)
    protected String serieID;

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
     *     {@link Country }
     *     
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Legt den Wert der country-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setCountry(Country value) {
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
     *     {@link Runtime }
     *     
     */
    public Runtime getEpisoderuntime() {
        return episoderuntime;
    }

    /**
     * Legt den Wert der episoderuntime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Runtime }
     *     
     */
    public void setEpisoderuntime(Runtime value) {
        this.episoderuntime = value;
    }

    /**
     * Ruft den Wert der network-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Network }
     *     
     */
    public Network getNetwork() {
        return network;
    }

    /**
     * Legt den Wert der network-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Network }
     *     
     */
    public void setNetwork(Network value) {
        this.network = value;
    }

    /**
     * Ruft den Wert der airday-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Weekday }
     *     
     */
    public Weekday getAirday() {
        return airday;
    }

    /**
     * Legt den Wert der airday-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Weekday }
     *     
     */
    public void setAirday(Weekday value) {
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

}
