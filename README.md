# Web-basierte Anwendungen 2: Verteilte Systeme
# Sommersemester 2013 - Phase 2

*Dennis Meyer und Dominik Schilling*

# Szenario: Serientracker

Die **Idee** ist, dass Serien-Interessierte über ihre zuvor favorisierten Serien benachrichtigt werden, sobald eine Epsiode dieser Serie im TV ausgestrahlt wird.

Der **Serien-Interessierte** soll Zugriff auf einen Pool von Serien bekommen, die auf einem Server gespeichert und verwaltet werden.  
Die einzelnen Serien soll der Nutzer dann favorisieren/abonnieren können um dann über die TV-Ausstrahlung informiert zu werden.
Außerdem soll die Möglichkeit bestehen eine Episode zu bewerten und als gesehen/ungesehen zu markieren.

Die **Server-Anwendung** soll die Nutzer über die TV-Austrahlung einer Episode einer zuvor favorisierten Serie in einem vom Benutzer definierten Zeitraum informiert werden.

Ein **Content-Admin** soll erweiterte Rechte bekommen, um die Content-Verwaltung zu übernehmen. Die Anwendung soll das Anlegen, Bearbeiten und Löschen von Serien bzw Episoden ermöglichen.

####Zusatz - Freunde:  
**Serien-Interessierte** sollen sich gegenseitig hinzufügen/abonnieren können um sich gegenseitig zu benachrichtigen, zum Beispiel in Form von „Freund X schaut gerade Y”, „Freund Z hat Serie/Episode mit 8,0 bewertet” oder „Freund Y empfiehlt Dir Serie W”.

###Umsetzung  

####Synchrone Datenübertragung

* **Serien-Interessierte**
   * Markieren von Episoden
     * Gesehen/Nicht gesehen
     * Bewertung einer Episode
         * Kommentar
         * Bewertung in Zahlen/„Boolean”
     * Fehlermeldung
         * geänderte Sendezeit, fehlerhaftes Datum, …   
   * Listen
     * Ausgabe (Un)Watched
     * Ausgabe vorhandene Serien
     * Ausgabe Follower/Following (?)
   * Favorisierung
     * Anlegen
     * Löschen
     * Bearbeiten
         * Zeitpunkt der Benachrichtigung
* **Content-Admin**
   * Verwaltung der Episoden
      * Anlegen
      * Löschen
      * Bearbeiten

####Asynchrone Datenübertragung

* **Serien-Interessierte**
   * Benachrichtung bei TV-Austrahlung
   * Freunde mit gleicher Favorisierung bei Serienstart mit Check-in benachrichtigen (?)
        * “Freund X schaut auch W”
   * Empfehlung einer Serie von Freund(e) anzeigen (?)
* **Content-Admin**
   * Benachrichtung bei Fehlermeldung durch User
