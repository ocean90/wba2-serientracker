//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.12 um 04:04:23 PM CEST 
//


package jaxb;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Genre_QNAME = new QName("", "genre");
    private final static QName _Episoderuntime_QNAME = new QName("", "episoderuntime");
    private final static QName _Airtime_QNAME = new QName("", "airtime");
    private final static QName _Location_QNAME = new QName("", "location");
    private final static QName _About_QNAME = new QName("", "about");
    private final static QName _Lastname_QNAME = new QName("", "lastname");
    private final static QName _SeasonNumber_QNAME = new QName("", "seasonNumber");
    private final static QName _Airday_QNAME = new QName("", "airday");
    private final static QName _Firstname_QNAME = new QName("", "firstname");
    private final static QName _Avatar_QNAME = new QName("", "avatar");
    private final static QName _EpisodeNumber_QNAME = new QName("", "episodeNumber");
    private final static QName _Network_QNAME = new QName("", "network");
    private final static QName _Country_QNAME = new QName("", "country");
    private final static QName _Content_QNAME = new QName("", "content");
    private final static QName _Username_QNAME = new QName("", "username");
    private final static QName _Title_QNAME = new QName("", "title");
    private final static QName _Joined_QNAME = new QName("", "joined");
    private final static QName _Overview_QNAME = new QName("", "overview");
    private final static QName _Firstaired_QNAME = new QName("", "firstaired");
    private final static QName _Name_QNAME = new QName("", "name");
    private final static QName _Age_QNAME = new QName("", "age");
    private final static QName _Gender_QNAME = new QName("", "gender");
    private final static QName _Year_QNAME = new QName("", "year");
    private final static QName _Airdate_QNAME = new QName("", "airdate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Series }
     * 
     */
    public Series createSeries() {
        return new Series();
    }

    /**
     * Create an instance of {@link Serie }
     * 
     */
    public Serie createSerie() {
        return new Serie();
    }

    /**
     * Create an instance of {@link Genres }
     * 
     */
    public Genres createGenres() {
        return new Genres();
    }

    /**
     * Create an instance of {@link Images }
     * 
     */
    public Images createImages() {
        return new Images();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link Seasons }
     * 
     */
    public Seasons createSeasons() {
        return new Seasons();
    }

    /**
     * Create an instance of {@link Season }
     * 
     */
    public Season createSeason() {
        return new Season();
    }

    /**
     * Create an instance of {@link Episodes }
     * 
     */
    public Episodes createEpisodes() {
        return new Episodes();
    }

    /**
     * Create an instance of {@link Episode }
     * 
     */
    public Episode createEpisode() {
        return new Episode();
    }

    /**
     * Create an instance of {@link Users }
     * 
     */
    public Users createUsers() {
        return new Users();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link SubscribedUser }
     * 
     */
    public SubscribedUser createSubscribedUser() {
        return new SubscribedUser();
    }

    /**
     * Create an instance of {@link Lists }
     * 
     */
    public Lists createLists() {
        return new Lists();
    }

    /**
     * Create an instance of {@link List }
     * 
     */
    public List createList() {
        return new List();
    }

    /**
     * Create an instance of {@link Shows }
     * 
     */
    public Shows createShows() {
        return new Shows();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "genre")
    public JAXBElement<String> createGenre(String value) {
        return new JAXBElement<String>(_Genre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "episoderuntime")
    public JAXBElement<BigInteger> createEpisoderuntime(BigInteger value) {
        return new JAXBElement<BigInteger>(_Episoderuntime_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "airtime")
    public JAXBElement<XMLGregorianCalendar> createAirtime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Airtime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "location")
    public JAXBElement<String> createLocation(String value) {
        return new JAXBElement<String>(_Location_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "about")
    public JAXBElement<String> createAbout(String value) {
        return new JAXBElement<String>(_About_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lastname")
    public JAXBElement<String> createLastname(String value) {
        return new JAXBElement<String>(_Lastname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "seasonNumber")
    public JAXBElement<Integer> createSeasonNumber(Integer value) {
        return new JAXBElement<Integer>(_SeasonNumber_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "airday")
    public JAXBElement<String> createAirday(String value) {
        return new JAXBElement<String>(_Airday_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "firstname")
    public JAXBElement<String> createFirstname(String value) {
        return new JAXBElement<String>(_Firstname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "avatar")
    public JAXBElement<String> createAvatar(String value) {
        return new JAXBElement<String>(_Avatar_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "episodeNumber")
    public JAXBElement<Integer> createEpisodeNumber(Integer value) {
        return new JAXBElement<Integer>(_EpisodeNumber_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "network")
    public JAXBElement<String> createNetwork(String value) {
        return new JAXBElement<String>(_Network_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "country")
    public JAXBElement<String> createCountry(String value) {
        return new JAXBElement<String>(_Country_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "content")
    public JAXBElement<String> createContent(String value) {
        return new JAXBElement<String>(_Content_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "username")
    public JAXBElement<String> createUsername(String value) {
        return new JAXBElement<String>(_Username_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "title")
    public JAXBElement<String> createTitle(String value) {
        return new JAXBElement<String>(_Title_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "joined")
    public JAXBElement<XMLGregorianCalendar> createJoined(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Joined_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "overview")
    public JAXBElement<String> createOverview(String value) {
        return new JAXBElement<String>(_Overview_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "firstaired")
    public JAXBElement<XMLGregorianCalendar> createFirstaired(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Firstaired_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "age")
    public JAXBElement<Integer> createAge(Integer value) {
        return new JAXBElement<Integer>(_Age_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gender")
    public JAXBElement<String> createGender(String value) {
        return new JAXBElement<String>(_Gender_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "year")
    public JAXBElement<Integer> createYear(Integer value) {
        return new JAXBElement<Integer>(_Year_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "airdate")
    public JAXBElement<XMLGregorianCalendar> createAirdate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Airdate_QNAME, XMLGregorianCalendar.class, null, value);
    }

}
