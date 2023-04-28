# Datenleser Klasse

Datenleser soll dafür da sein, alles was sich um die CSV-Dateien dreht kompakt an einem Ort zu halten um Code leserlicher zu gestalten.

## Variablen

1. projectPath
    - projectPath ermittelt mit Hilfe der System libary das aktuelle Verzeichnis des Projektes.
    - Hier mit soll realisiert werden das das Programm auf jedem PC funktioniert ohne eine genaue Ordner struktur zu kopieren.
2. csvFile
    - csvFile wird verwendet um die gewünschte CSV-Datei zu öffnen
    - In der CSV-Datei wird die Fachspezifische Notentabelle hinterlegt
3. fr
    - Dient als InputStream um den Inhalt der Datei aufzurufen
4. csvReader
    - Dient dafür, die Ausgabe des FileReaders Zeilenweise abzurufen und diese nach und nach zu verarbeiten

## Funktionen

1. setFilePath
    - Soll nach der Auswahl des Faches und der Klasse den Pfad zur entsprechenden CSV-Datei festlegen
2. initReader
    - Soll nach setFilePath aufgerufen werden
    - Erstellt die Reader und ermöglicht spätere Auslesung
3. getLine
    - Gibt die aktuelle Zeile aus