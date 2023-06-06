# Projekt Notenbuch LF5

[Moodle Link für das Projekt](https://moodle.bildung.koblenz.de/mod/assign/view.php?id=187185)

Um auf einen bestimmten Unterpunkt zu kommmen , links neben README.md auf das Burgermenü klicken.

![image](https://user-images.githubusercontent.com/103290810/234218111-9a9d3344-a266-4d8e-8752-db543a3ab6d1.png)

&nbsp;

## Struktur 
Das Projekt beinhaltet folgende Dinge:

- `src`: Source Ordner für die Java Dateien
- `lib`: Bibliotheken die eingebunden werden
- `.gitignore` : Um Dateien aus dem Git Projekt auszuschließen (Binär Dateien,IDE Spezifische Dateien,...)
- `README.md` : Markdown Datei, die alles bezüglich des Projektes festhalten soll (Aufbau,Fortschritt,Branches,...)
- `CSV_Dateien` : Hier werden die Notenlisten gespeichert, jeweils nach Klasse und Fach unterteilt

## Vorgaben von Herr Dietrich

Die Aufgabenstellung gibt folgendes vor:

- Sicht aus Schüler- __oder__ Lehrersicht (Denkbar das man beides macht)

### Vorgegebene Funktionen

- [x] Eingabe von Noten für verschiedene Fächer (z.B. LF1, LF5, Deutsch)
- [x] Berechnung des Durchschnitts für jedes Fach und insgesamt
- [x] Ausgabe der eingegebenen Noten sowie des Durchschnitts für jedes Fach und insgesamt
- [x] Speichern und Laden der eingegebenen Noten in/aus einer Textdatei

### Technische Anforderungen

- [x] Schleifen (z.B. for, while) für die Eingabe von Noten und die Berechnung des Durchschnitts
- [x] Bedingungen (z.B. if-else) für die Überprüfung von Eingaben
- [x] Einfache Datentypen (z.B. int, double) für die Speicherung der Noten
- [x] Arrays für die Speicherung der eingegebenen Noten
- [x] Methoden (z.B. Berechnung Durchschnittsnote)

### Zusätzliche Anforderungen

- [x] Zusätzlich sollen Sie eine Benutzeroberfläche für das Programm entwickeln, die dem Benutzer die Eingabe von Noten und die Anzeige der Ergebnisse erleichtert. Sie können dazu z.B. die Java-Bibliothek Swing oder FX verwenden.
- [ ] Optional können Sie auch weitere Funktionen hinzufügen, wie z.B. die Möglichkeit, Noten zu löschen oder zu bearbeiten, oder die Berechnung von statistischen Kennzahlen wie dem Median oder der Standardabweichung.
- [ ] Implementieren eine Umschaltung zwischen verschiedenen Notensystemen.
- [ ] Legen Sie eine Benutzerverwaltung an.
- [x] Entwickeln Sie kollaborativ das Projekt mit einem Tool, wie z.B. GitHub.
- [ ] Entwickeln Sie Testfälle zur Überprüfung einzelner Programmabschnitte.
- [x] Entwickeln Sie eine Möglichkeit Noten in der Durchschnittsberechnung unterschiedlich zu gewichten.
- [x] Objektorientierter Programmieransatz.
- [x] Verwendung anderer Datentypen als Array (z.B. Nutzen von Java Collections).

### Bewertungskriterien

- [x] Erfüllung der technischen Anforderungen
- [ ] Erfüllung der zusätzlichen Anforderungen
- [x] Benutzerfreundlichkeit der Anwendung
- [x] Code-Qualität und Lesbarkeit
- [x] Dokumentation und Erklärung des Codes
- [x] Fristgerechte Abgabe und Präsentation

### Organisatorisches

- Zeitrahmen: 8 Wochen (11.5 in Eigenarbeit (Fortbildung Dietrich), 18.11 Feiertag (Chr. Himmelfahrt), 1.6 und 8.6 Pfingstferien)
- Projektstart: Do. 20.04.2023
- Projektabgabe bis: Mo. 12.06.2023, 23:59 Uhr
- Projektpräsentation am: Do. 15.6.2023 und 22.06.2023
- Gruppengröße: Maximal 3 Personen

### Hinweise zur Präsentation

- Code-Überblick geben
- Funktionalität demonstrieren
- behandelte Probleme / Besonderheiten vorstellen
- Powerpoint optional

## Branch Übersicht

- master: Getesteter Code in dem Funktionalität sichergestellt ist
- develop: Code der noch in Entwicklung ist aber vielleicht nicht 100% funktioniert, features werden hier getestet
- feature/... : Branches in denen bestimmte features ausprogrammiert werden, bevor sie für alle freigegeben werden
