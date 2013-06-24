# Web-basierte Anwendungen 2: Verteilte Systeme
# Sommersemester 2013 - Phase 2


*Dennis Meyer und Dominik Schilling*

# Szenario: Serientracker

##Konzeption

Die **Idee** ist, dass Serien-Interessierte über ihre zuvor favorisierten Serien und abonnierten Interessen benachrichtigt werden, sobald eine Episode dieser Serie im TV ausgestrahlt wird.

Der **Serien-Interessierte** soll Zugriff auf einen Pool von Serien bekommen, die auf einem Server gespeichert und verwaltet werden.  
Die einzelnen Serien soll der Nutzer dann favorisieren/abonnieren können um dann über die TV-Ausstrahlung informiert zu werden.
Außerdem soll die Möglichkeit bestehen eine Episode zu bewerten und als gesehen/ungesehen zu markieren. Es besteht zudem die Möglichkeiten Serien in Listen einzuordnen und zu verwalten. (definitiv automatische Watchlist/Seenlist)

Die **Server-Anwendung** soll die Nutzer über die TV-Austrahlung einer Episode einer zuvor favorisierten Serie in einem vom Benutzer definierten Zeitraum informiert werden. Jeder Eintrag einer Serie enthalt zudem Informationen wie "Genre", anhand derer Abonnentenl bezüglich eines bestimmten Themas, zusätzliche Benachrichtigungen empfangen.

Ein **Content-Admin** soll erweiterte Rechte bekommen, um die Content-Verwaltung zu übernehmen. Die Anwendung soll das Anlegen, Bearbeiten und Löschen von Serien bzw Episoden ermöglichen. Zudem ist somit das Korrigieren von Fehlern möglich, die von Usern eingeschickt werden.

####Zusatz - Freunde:  
**Serien-Interessierte** sollen sich gegenseitig hinzufügen/abonnieren können um sich gegenseitig zu benachrichtigen, zum Beispiel in Form von „Freund X schaut gerade Y”, „Freund Z hat Serie/Episode mit 8,0 bewertet” oder „Freund Y empfiehlt Dir Serie W”.


## Nutzung des User-Client


### Vorrausetzungen

* **Openfire** Installation
  * Standard-Server-Einstellungen, ansonsten `de.fhkoeln.gm.serientracker.xmpp.XMPPConfig.java` anpassen
* **Benutzeraccounts**
  * In Opefire existiert ein Account mit dem Usernamen `bot` und dem Passwort `bot`.
     * Alternativ `de.fhkoeln.gm.serientracker.bot.BotClient.java` anpassen
  * Die Usernamen `admin` und `test` (empfohlendes Passwort: `test`) sind reserviert und sollten ebenfalls vorher in Openfire angelegt worden sein
* **REST Server** läuft auf `localhost:1337`, sollten die Werte bereits belegt sein, muss `de.fhkoeln.gm.serientracker.webservice.RESTServerConfig.java` angepasst werden

### Start

1. Der BotClient muss gestartet werden, welcher die Nodes anlegt.
   * `de.fhkoeln.gm.serientracker.xmpp.XMPPConfig.java`
2. Der Rest Server muss gestartet werden.
   * `de.fhkoeln.gm.serientracker.webservice.RESTServer.java`
3. Der User-Client kann gestartet werden.
   * `de.fhkoeln.gm.serientracker.client.TrackerClient.java`
4. Optional: XMPP Client für Notifcation-Debugging starten.
   * `de.fhkoeln.gm.serientracker.xmpp.XMPPClient.java`

