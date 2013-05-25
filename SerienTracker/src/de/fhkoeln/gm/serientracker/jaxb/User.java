//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.25 um 01:59:13 PM CEST 
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
 *           &lt;element ref="{}username"/>
 *           &lt;element ref="{}lastname"/>
 *           &lt;element ref="{}firstname"/>
 *           &lt;element ref="{}gender"/>
 *           &lt;element ref="{}age"/>
 *           &lt;element ref="{}location"/>
 *           &lt;element ref="{}about"/>
 *           &lt;element ref="{}joined"/>
 *           &lt;element ref="{}avatar"/>
 *           &lt;element ref="{}subscriptions" minOccurs="0"/>
 *           &lt;element ref="{}lists" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute ref="{}admin use="required""/>
 *       &lt;attribute ref="{}userID use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "username",
    "lastname",
    "firstname",
    "gender",
    "age",
    "location",
    "about",
    "joined",
    "avatar",
    "subscriptions",
    "lists"
})
@XmlRootElement(name = "user")
public class User {

    protected String username;
    protected String lastname;
    protected String firstname;
    protected String gender;
    protected Integer age;
    protected String location;
    protected String about;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar joined;
    @XmlSchemaType(name = "anyURI")
    protected String avatar;
    protected Subscriptions subscriptions;
    protected Lists lists;
    @XmlAttribute(name = "admin", required = true)
    protected boolean admin;
    @XmlAttribute(name = "userID", required = true)
    protected String userID;

    /**
     * Ruft den Wert der username-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Legt den Wert der username-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Ruft den Wert der lastname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Legt den Wert der lastname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastname(String value) {
        this.lastname = value;
    }

    /**
     * Ruft den Wert der firstname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Legt den Wert der firstname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Ruft den Wert der gender-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Legt den Wert der gender-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Ruft den Wert der age-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Legt den Wert der age-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAge(Integer value) {
        this.age = value;
    }

    /**
     * Ruft den Wert der location-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Legt den Wert der location-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Ruft den Wert der about-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbout() {
        return about;
    }

    /**
     * Legt den Wert der about-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbout(String value) {
        this.about = value;
    }

    /**
     * Ruft den Wert der joined-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getJoined() {
        return joined;
    }

    /**
     * Legt den Wert der joined-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setJoined(XMLGregorianCalendar value) {
        this.joined = value;
    }

    /**
     * Ruft den Wert der avatar-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Legt den Wert der avatar-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvatar(String value) {
        this.avatar = value;
    }

    /**
     * Ruft den Wert der subscriptions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Subscriptions }
     *     
     */
    public Subscriptions getSubscriptions() {
        return subscriptions;
    }

    /**
     * Legt den Wert der subscriptions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Subscriptions }
     *     
     */
    public void setSubscriptions(Subscriptions value) {
        this.subscriptions = value;
    }

    /**
     * Ruft den Wert der lists-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Lists }
     *     
     */
    public Lists getLists() {
        return lists;
    }

    /**
     * Legt den Wert der lists-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Lists }
     *     
     */
    public void setLists(Lists value) {
        this.lists = value;
    }

    /**
     * Ruft den Wert der admin-Eigenschaft ab.
     * 
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Legt den Wert der admin-Eigenschaft fest.
     * 
     */
    public void setAdmin(boolean value) {
        this.admin = value;
    }

    /**
     * Ruft den Wert der userID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Legt den Wert der userID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

}
