//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.17 um 03:19:42 PM CEST 
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
 *     &lt;enumeration value="Australia"/>
 *     &lt;enumeration value="Austria"/>
 *     &lt;enumeration value="Canada"/>
 *     &lt;enumeration value="China"/>
 *     &lt;enumeration value="Denmark"/>
 *     &lt;enumeration value="England"/>
 *     &lt;enumeration value="France"/>
 *     &lt;enumeration value="Germany"/>
 *     &lt;enumeration value="Ireland"/>
 *     &lt;enumeration value="Italy"/>
 *     &lt;enumeration value="Japan"/>
 *     &lt;enumeration value="Netherlands"/>
 *     &lt;enumeration value="Norway"/>
 *     &lt;enumeration value="Sweden"/>
 *     &lt;enumeration value="USA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "")
@XmlEnum
public enum Country {

    @XmlEnumValue("Australia")
    AUSTRALIA("Australia"),
    @XmlEnumValue("Austria")
    AUSTRIA("Austria"),
    @XmlEnumValue("Canada")
    CANADA("Canada"),
    @XmlEnumValue("China")
    CHINA("China"),
    @XmlEnumValue("Denmark")
    DENMARK("Denmark"),
    @XmlEnumValue("England")
    ENGLAND("England"),
    @XmlEnumValue("France")
    FRANCE("France"),
    @XmlEnumValue("Germany")
    GERMANY("Germany"),
    @XmlEnumValue("Ireland")
    IRELAND("Ireland"),
    @XmlEnumValue("Italy")
    ITALY("Italy"),
    @XmlEnumValue("Japan")
    JAPAN("Japan"),
    @XmlEnumValue("Netherlands")
    NETHERLANDS("Netherlands"),
    @XmlEnumValue("Norway")
    NORWAY("Norway"),
    @XmlEnumValue("Sweden")
    SWEDEN("Sweden"),
    USA("USA");
    private final String value;

    Country(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Country fromValue(String v) {
        for (Country c: Country.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
