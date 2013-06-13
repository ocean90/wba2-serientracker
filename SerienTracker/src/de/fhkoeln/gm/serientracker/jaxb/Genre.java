//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.13 um 11:30:09 AM CEST 
//


package de.fhkoeln.gm.serientracker.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für null.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType>
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Action"/>
 *     &lt;enumeration value="Adventure"/>
 *     &lt;enumeration value="Animation"/>
 *     &lt;enumeration value="Children"/>
 *     &lt;enumeration value="Comedy"/>
 *     &lt;enumeration value="Crime"/>
 *     &lt;enumeration value="Drama"/>
 *     &lt;enumeration value="Documentary"/>
 *     &lt;enumeration value="Fantasy"/>
 *     &lt;enumeration value="Game Show"/>
 *     &lt;enumeration value="Historical"/>
 *     &lt;enumeration value="Horror"/>
 *     &lt;enumeration value="Mystery"/>
 *     &lt;enumeration value="News"/>
 *     &lt;enumeration value="Romance"/>
 *     &lt;enumeration value="Science Fiction"/>
 *     &lt;enumeration value="Sport"/>
 *     &lt;enumeration value="Suspence"/>
 *     &lt;enumeration value="Thriller"/>
 *     &lt;enumeration value="Western"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "")
@XmlEnum
public enum Genre {

    @XmlEnumValue("Action")
    ACTION("Action"),
    @XmlEnumValue("Adventure")
    ADVENTURE("Adventure"),
    @XmlEnumValue("Animation")
    ANIMATION("Animation"),
    @XmlEnumValue("Children")
    CHILDREN("Children"),
    @XmlEnumValue("Comedy")
    COMEDY("Comedy"),
    @XmlEnumValue("Crime")
    CRIME("Crime"),
    @XmlEnumValue("Drama")
    DRAMA("Drama"),
    @XmlEnumValue("Documentary")
    DOCUMENTARY("Documentary"),
    @XmlEnumValue("Fantasy")
    FANTASY("Fantasy"),
    @XmlEnumValue("Game Show")
    GAME_SHOW("Game Show"),
    @XmlEnumValue("Historical")
    HISTORICAL("Historical"),
    @XmlEnumValue("Horror")
    HORROR("Horror"),
    @XmlEnumValue("Mystery")
    MYSTERY("Mystery"),
    @XmlEnumValue("News")
    NEWS("News"),
    @XmlEnumValue("Romance")
    ROMANCE("Romance"),
    @XmlEnumValue("Science Fiction")
    SCIENCE_FICTION("Science Fiction"),
    @XmlEnumValue("Sport")
    SPORT("Sport"),
    @XmlEnumValue("Suspence")
    SUSPENCE("Suspence"),
    @XmlEnumValue("Thriller")
    THRILLER("Thriller"),
    @XmlEnumValue("Western")
    WESTERN("Western");
    private final String value;

    Genre(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Genre fromValue(String v) {
        for (Genre c: Genre.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
