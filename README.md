# Web-basierte Anwendungen 2: Verteilte Systeme
# Sommersemester 2013 - Phase 2


*Dennis Meyer und Dominik Schilling*

# Szenario: Serientracker

##Konzept
===
Die **Idee** ist, dass Serien-Interessierte über ihre zuvor favorisierten Serien und abonnierten Interessen benachrichtigt werden, sobald eine Episode dieser Serie im TV ausgestrahlt wird.

Der **Serien-Interessierte** soll Zugriff auf einen Pool von Serien bekommen, die auf einem Server gespeichert und verwaltet werden.  
Die einzelnen Serien soll der Nutzer dann favorisieren/abonnieren können um dann über die TV-Ausstrahlung informiert zu werden.
Außerdem soll die Möglichkeit bestehen eine Episode zu bewerten und als gesehen/ungesehen zu markieren. Es besteht zudem die Möglichkeiten Serien in Listen einzuordnen und zu verwalten. (definitiv automatische Watchlist/Seenlist)

Die **Server-Anwendung** soll die Nutzer über die TV-Austrahlung einer Episode einer zuvor favorisierten Serie in einem vom Benutzer definierten Zeitraum informiert werden. Jeder Eintrag einer Serie enthalt zudem Informationen wie "Genre", anhand derer Abonnentenl bezüglich eines bestimmten Themas, zusätzliche Benachrichtigungen empfangen.

Ein **Content-Admin** soll erweiterte Rechte bekommen, um die Content-Verwaltung zu übernehmen. Die Anwendung soll das Anlegen, Bearbeiten und Löschen von Serien bzw Episoden ermöglichen. Zudem ist somit das Korrigieren von Fehlern möglich, die von Usern eingeschickt werden.

####Zusatz - Freunde:  
**Serien-Interessierte** sollen sich gegenseitig hinzufügen/abonnieren können um sich gegenseitig zu benachrichtigen, zum Beispiel in Form von „Freund X schaut gerade Y”, „Freund Z hat Serie/Episode mit 8,0 bewertet” oder „Freund Y empfiehlt Dir Serie W”.

<br>    

###Umsetzung  
Die Anwendung ermöglicht den Austausch von Informationen zwischen Server und Anwender entsprechend den jeweiligen Funktionen. 

####Synchrone Datenübertragung

Zum einen hat der Anwender direkt die Möglichkeit auf Informationen in Form von Daten zuzugreifen und diese zu Manipulieren.

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
Ein weiterer Aspekt ist das Anfordern von Informationen, wobei die entsprechenden Informationen von Seiten des Servers von Bedingungen abhängig gesendet werden, was auch mehrfach geschehen kann.

* **Serien-Interessierte**
   * Benachrichtung bei TV-Austrahlung
   * Freunde mit gleicher Favorisierung bei Serienstart mit Check-in benachrichtigen (?)
        * “Freund X schaut auch W”
   * Empfehlung einer Serie von Freund(e) anzeigen (?)
* **Content-Admin**
   * Benachrichtung bei Fehlermeldung durch User
   
<br>    

## Meilensteine
===
Die Umsetzung des Projektes erfolgt anhand festgelegter Meilensteine zu festgelegten Zeitpunkten.

* 06.05.2013 Meilenstein 1 + 2 (Projektbezogenes XML Schema + Ressourcen und die Semantik der HTTP-Operationen)  		
* 13.05.2013 Meilenstein 3 (RESTful Webservice)			
* 03.06.2013 Meilenstein 4 + 5 (Konzeption asynchrone Kommunikation + XMPP - Client)			
* 17.06.2013 Meilenstein 6 (Client - Entwicklung)  


   

