//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.13 um 11:30:09 AM CEST 
//


package de.fhkoeln.gm.serientracker.jaxb;

import java.math.BigInteger;
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
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *     &lt;enumeration value="10"/>
 *     &lt;enumeration value="20"/>
 *     &lt;enumeration value="30"/>
 *     &lt;enumeration value="50"/>
 *     &lt;enumeration value="60"/>
 *     &lt;enumeration value="90"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "")
@XmlEnum(BigInteger.class)
public enum Runtime {

    @XmlEnumValue("10")
    TEN(new BigInteger("10")),
    @XmlEnumValue("20")
    TWENTY(new BigInteger("20")),
    @XmlEnumValue("30")
    THIRTY(new BigInteger("30")),
    @XmlEnumValue("50")
    FIFTY(new BigInteger("50")),
    @XmlEnumValue("60")
    SIXTY(new BigInteger("60")),
    @XmlEnumValue("90")
    NINETY(new BigInteger("90"));
    private final BigInteger value;

    Runtime(BigInteger v) {
        value = v;
    }

    public BigInteger value() {
        return value;
    }

    public static Runtime fromValue(BigInteger v) {
        for (Runtime c: Runtime.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
