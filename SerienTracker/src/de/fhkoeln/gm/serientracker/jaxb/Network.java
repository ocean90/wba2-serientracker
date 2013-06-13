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
 *     &lt;enumeration value="ABC"/>
 *     &lt;enumeration value="AMC"/>
 *     &lt;enumeration value="BBC America"/>
 *     &lt;enumeration value="BBC One"/>
 *     &lt;enumeration value="CBC"/>
 *     &lt;enumeration value="Comedy Central"/>
 *     &lt;enumeration value="The CW"/>
 *     &lt;enumeration value="Fox"/>
 *     &lt;enumeration value="FX"/>
 *     &lt;enumeration value="HBO"/>
 *     &lt;enumeration value="History Channel"/>
 *     &lt;enumeration value="NBC"/>
 *     &lt;enumeration value="PBS"/>
 *     &lt;enumeration value="Showtime"/>
 *     &lt;enumeration value="Starz"/>
 *     &lt;enumeration value="TNT"/>
 *     &lt;enumeration value="ARD"/>
 *     &lt;enumeration value="ZDF"/>
 *     &lt;enumeration value="RTL"/>
 *     &lt;enumeration value="Sat1"/>
 *     &lt;enumeration value="Prosieben"/>
 *     &lt;enumeration value="Kabel1"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "")
@XmlEnum
public enum Network {

    ABC("ABC"),
    AMC("AMC"),
    @XmlEnumValue("BBC America")
    BBC_AMERICA("BBC America"),
    @XmlEnumValue("BBC One")
    BBC_ONE("BBC One"),
    CBC("CBC"),
    @XmlEnumValue("Comedy Central")
    COMEDY_CENTRAL("Comedy Central"),
    @XmlEnumValue("The CW")
    THE_CW("The CW"),
    @XmlEnumValue("Fox")
    FOX("Fox"),
    FX("FX"),
    HBO("HBO"),
    @XmlEnumValue("History Channel")
    HISTORY_CHANNEL("History Channel"),
    NBC("NBC"),
    PBS("PBS"),
    @XmlEnumValue("Showtime")
    SHOWTIME("Showtime"),
    @XmlEnumValue("Starz")
    STARZ("Starz"),
    TNT("TNT"),
    ARD("ARD"),
    ZDF("ZDF"),
    RTL("RTL"),
    @XmlEnumValue("Sat1")
    SAT_1("Sat1"),
    @XmlEnumValue("Prosieben")
    PROSIEBEN("Prosieben"),
    @XmlEnumValue("Kabel1")
    KABEL_1("Kabel1");
    private final String value;

    Network(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Network fromValue(String v) {
        for (Network c: Network.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
