Web-basierte Anwendungen 2: Verteilte Systeme
==================  
##Phase 2 - Projekt "Serientracker"

###Projektidee  
Beliebige Benutzer sollen über ihre zuvor favorisierten Serien benachrichtigt werden, wenn diese im TV ausgestrahlt werden.

Der Benutzer soll über ein GUI eine Auswahlmöglichkeit über die aktuell auf eine entfernten Server gespeicherten Serien bekommen. Die einzelnen Serien kann er dann favorisieren/abonnieren um über die TV-Ausstrahlung informiert zu werden.
Außerdem soll die Möglichkeit bestehen eine Episode zu bewerten und als gesehen/ungesehen zu markieren. Diese Requests sollen synchron ablaufen.

Sobald eine Serie im TV ausgestrahlt wird, soll der abonnierte Benutzer mittels „Push Notification“ informiert werden. Die Benachrichtigung soll dabei in einem vom Benutzer definierten Zeitraum vor dem Start ausgeführt werden.

###Zusatz - Freunde  
Benutzer sollen sich gegenseitig hinzufügen/abonnieren können um sich gegenseitig zu benachrichtigen, zum Beispiel in Form von “Freund X schaut gerade Y”, “Freund Z hat Serie/Episode mit 8,0 bewertet” oder “Freund Y empfiehlt Dir Serie W”.

###Umsetzung  
Für die Serien sollte die Schnittstelle von dem Service http://trakt.tv/ genutzt werden. Diese gibt die Daten im JSON Format aus, welche dann von uns ins XML-Format einmalig transformiert werden.

**Synchron**  

* Ausgabe von auf dem Server gespeicherer Serien
* Markieren von Serien
  * Gesehen/Nicht gesehen
  * Bewertung einer Epsisode
* SerienListe
  * Unwatched
* Favorisieren
  * Anlegen
  * Löschen
  * Ändern
      * Zeitpunkt der Benachrichtigung

**Asynchron**

* Benachrichtung bei Serienstart
* Freunde mit gleicher Favorisierung bei Serienstart mit Check-in benachrichtigen (?)
  * “Freund X schaut auch W”
* Empfehlung einer Serie an Freund(e) schicken
