# Leitfaden für Klassenmodelierung

## Welche Objekte sind benötigt ?

- BufferedReader für die CSV Datei die ausgelesen werden soll
    - CSV Dateien sorgen dafür das Daten nach Programmstart und Schließung erhalten bleiben
    
- Arraylist die Schüler Objekt speichert
    - Frage die hier wichtig wäre: Wie soll der Aufruf der Daten passieren ?
    - Arraylist (oder generell eine Liste) da wir die dynamik brauchen 
        - Schüler müssen geaddet und deleted werden können
            - Feste Anzahl hier also unsinnig

- Schüler Objekt
    - Soll Daten aus der CSV speichern
    - Name (Vor und Nachname trennen ?)
    - ID (Vielleicht einfach hier die Zeile aus der CSV übernehmen ?)
        - ID könnte helfen das Daten nicht gespeichert werden muss in einem Objekt, sondern das die Liste zur Runtime ausgelesen wird

- Java Swing GUI Zeugs
    - GUI halt lmao

- Interne Logik für Fächer muss festgelegt werden 
    1. Pro Fach eine CSV Datei
        - Verringert Möglichkeiten für unübersichtlichen Code
        - Könnte jedoch undurchsichtiger sein (?)
        - Feste Logik für jede CSV vorraus gesetzt
        - Das große Plus in der Situation ist das der Aufruf der Daten dadurch einfacher wird
            - Keine Gedanken um Mappung der Noten machen 
            - Der Aufruf der Fächer sollte somit vereinfacht werden
            - Code sollte einfacher werden und CSV-Dateien sollten nicht überfüllt und simple sein
        - Das dicke Minus wäre jedoch der Aufruf der CSV-Dateien
            - Aufruf muss dynamisch in Relation zum Projektverzeichnis gemacht werden
                - Wie ?
        
  
