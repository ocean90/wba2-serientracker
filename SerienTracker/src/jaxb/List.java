//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.05 um 07:38:48 PM CEST 
//


package jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;choice minOccurs="0">
 *         &lt;sequence>
 *           &lt;element ref="{}name"/>
 *           &lt;element ref="{}shows"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute ref="{}userID use="required""/>
 *       &lt;attribute ref="{}listID use="required""/>
 *       &lt;attribute ref="{}public use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "shows"
})
@XmlRootElement(name = "list")
public class List {

    protected String name;
    protected Shows shows;
    @XmlAttribute(name = "userID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger userID;
    @XmlAttribute(name = "listID", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger listID;
    @XmlAttribute(name = "public", required = true)
    protected boolean _public;

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
     * Ruft den Wert der shows-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Shows }
     *     
     */
    public Shows getShows() {
        return shows;
    }

    /**
     * Legt den Wert der shows-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Shows }
     *     
     */
    public void setShows(Shows value) {
        this.shows = value;
    }

    /**
     * Ruft den Wert der userID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUserID() {
        return userID;
    }

    /**
     * Legt den Wert der userID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUserID(BigInteger value) {
        this.userID = value;
    }

    /**
     * Ruft den Wert der listID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getListID() {
        return listID;
    }

    /**
     * Legt den Wert der listID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setListID(BigInteger value) {
        this.listID = value;
    }

    /**
     * Ruft den Wert der public-Eigenschaft ab.
     * 
     */
    public boolean isPublic() {
        return _public;
    }

    /**
     * Legt den Wert der public-Eigenschaft fest.
     * 
     */
    public void setPublic(boolean value) {
        this._public = value;
    }

}
