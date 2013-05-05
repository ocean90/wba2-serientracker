//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.05 um 07:01:51 PM CEST 
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
 *         &lt;element ref="{}subscribedUser"/>
 *         &lt;element ref="{}airdate"/>
 *         &lt;element ref="{}episodeID"/>
 *         &lt;element ref="{}content"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{}messageID use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "subscribedUser",
    "airdate",
    "episodeID",
    "content"
})
@XmlRootElement(name = "message")
public class Message {

    @XmlElement(required = true)
    protected SubscribedUser subscribedUser;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar airdate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger episodeID;
    @XmlElement(required = true)
    protected String content;
    @XmlAttribute(name = "messageID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger messageID;

    /**
     * Ruft den Wert der subscribedUser-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SubscribedUser }
     *     
     */
    public SubscribedUser getSubscribedUser() {
        return subscribedUser;
    }

    /**
     * Legt den Wert der subscribedUser-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscribedUser }
     *     
     */
    public void setSubscribedUser(SubscribedUser value) {
        this.subscribedUser = value;
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

    /**
     * Ruft den Wert der content-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Legt den Wert der content-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Ruft den Wert der messageID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMessageID() {
        return messageID;
    }

    /**
     * Legt den Wert der messageID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMessageID(BigInteger value) {
        this.messageID = value;
    }

}
