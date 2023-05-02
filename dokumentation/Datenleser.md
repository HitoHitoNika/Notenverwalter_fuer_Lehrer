# Datenleser Klasse

Datenleser soll dafür da sein, alles was sich um die CSV-Dateien dreht kompakt an einem Ort zu halten um Code leserlicher zu gestalten.

## Variablen

1. projectPath
    - projectPath ermittelt mit Hilfe der System libary das aktuelle Verzeichnis des Projektes.
    - Hier mit soll realisiert werden das das Programm auf jedem PC funktioniert ohne eine genaue Ordner struktur zu kopieren.
2. csvFile
    - csvFile wird verwendet um die gewünschte CSV-Datei zu öffnen
    - In der CSV-Datei wird die Fachspezifische Notentabelle hinterlegt
3. csvReader
    - Dient dafür, die Ausgabe des FileReaders Zeilenweise abzurufen und diese nach und nach zu verarbeiten
4. klassenNamen
    - Eine ArrayList die alle Klassennamen abspeichern soll
    - ArrayList um dynamik zu garantieren

## Funktionen

1. setFilePath
    - Soll nach der Auswahl des Faches und der Klasse den Pfad zur entsprechenden CSV-Datei festlegen
2. initReader
    - Soll nach setFilePath aufgerufen werden
    - Erstellt die Reader und ermöglicht spätere Auslesung
3. getLine
    - Gibt die aktuelle Zeile aus
4. closeFile
    - Soll den Reader und die die geöffnete Datei schließen
5. importKlasse
    - Implementiert den JFileChooser
        - Dieser öffnet ein Fenster, welches mit dem Dateiexplorer gleichzustellen ist
        - Mit diesem soll der Nutzer sein gewünschten Ordner auswählen können => Import/Export
    - Es wird gewartet bis der Nutzer eine auswahl getroffen hat und diese bestätigt
        - Dann wird das ausgewählte Verzeichnis übertragen in die von uns vorgegebene Datenstruktur
6. copyDirectory
    - Rekursive Methode um die Dateien innerhalb des Ordners zu kopieren(siehe 5.)
    - Falls das Verzeichnis des Nutzers nicht existiert wird es erstellt
    - Ein File Array für die Dateien wird angelegt 
        - Die Dateien des Ursprung Ordners werden hier reingeladen
    - Jetzt wird für jedes Element, wenn es noch nicht existiert , eine Kopie im neuen Verzeichnis angelegt
        - Falls die Datei schon existiert wird sie ersetzt
7. getKlassenNamen
    - Ruft die Klassennamen ab und gibt diese als ArrayList zurück
8. hasMoreLines
    - Prüft ob csvReader noch Zeilen zur Verfügung hat