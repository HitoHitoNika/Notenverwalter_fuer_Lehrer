Präsentationsdokument: Notenverwaltungssystem für Lehrer in Java 

  

**1. Einleitung** 

Guten Tag, meine Damen und Herren. Ich freue mich, Ihnen heute unser Schulprojekt vorstellen zu dürfen. Unser Team hat ein Notenverwaltungssystem für Lehrer entwickelt, das ihnen bei der Organisation und Verwaltung der Schülerdaten und Noten unterstützt. 

  

**2. Verwendete Tools** 

Um das Projekt umzusetzen, haben wir folgende Tools verwendet: 

- Visual Studio Code (VSCode): Ein leistungsstarker Code-Editor mit Unterstützung für Java und verschiedene Erweiterungen. 

- Eclipse mit WindowBuilder: Eine integrierte Entwicklungsumgebung (IDE) für Java-Entwicklung, die uns bei der Erstellung des GUI unterstützt hat. 

- IntelliJ IDEA: Eine beliebte Java-IDE, die uns bei der Entwicklung und dem Testen des Projekts geholfen hat. 

- Git und GitHub: Versionskontrollsysteme, mit denen wir gemeinsam an unserem Code arbeiten und Änderungen verfolgen konnten. 

  

**3. Verwendete Plattformen** 

Unser Notenverwaltungssystem wurde auf der Windows-Plattform entwickelt und nutzt die Java-Version 17. 

  

**4. Struktur** 

Unser Projekt ist in verschiedene Packages strukturiert, um die Übersichtlichkeit und Wartbarkeit des Codes zu verbessern. Die Haupt-Packages sind: 

  

- csv_reader_stuff: Dieses Package enthält alle Klassen, die für das Lesen und Verwalten von CSV-Dateien zuständig sind. Hier werden Funktionen bereitgestellt, um Daten aus CSV-Dateien zu extrahieren und in das System zu importieren sowie um Daten in CSV-Dateien zu speichern. 

  

- OneInAll_GUI: Dieses Package beinhaltet alle Klassen, die sich mit dem GUI des Notenverwaltungssystems befassen. Hier haben wir die Benutzeroberfläche entwickelt, mit der Lehrer interagieren können, um Schüler hinzuzufügen, Noten zu vergeben usw. 

  

- school_attributes: In diesem Package werden die Schülerdaten gespeichert. Es beinhaltet eine Klasse um Daten der Schüler zu speichern. 

  

- start: Dieses Package enthält die Hauptklasse, die die Anwendung startet und initialisiert. 

  

**5. Teammitglieder** 

Unser Projektteam besteht aus folgenden Mitgliedern: 

- Maximilian Balthasar: Entwickler, verantwortlich für die Implementierung der Kernfunktionen des Notenverwaltungssystems. 

- Ceejay Jordan Vance: GUI-Designer, zuständig für die Gestaltung der Benutzeroberfläche und die Interaktion mit den Benutzern. 

- Tom Luca Schmitt: Mockup-Designer, hat sich um das Erstellen von Entwürfen und Prototypen gekümmert, um die visuelle Umsetzung des Systems zu unterstützen. 

  

**6. Aufgaben der Teammitglieder** 

Jedes Teammitglied hatte spezifische Aufgaben, um das Projekt erfolgreich abzuschließen: 

- Maximilian Balthasar: Entwicklung der Backend-Funktionalität, Implementierung der Kernfunktionen wie das Hinzufügen von Klassen, Schülern und Noten, sowie die Löschung aller Daten. 

- Ceejay Jordan Vance: Design und Umsetzung des GUIs, damit Lehrer einfach und intuitiv mit dem Notenverwaltungssystem interagieren können. 

- Tom Luca Schmitt: Erstellung von Mockups und Entwürfen für das GUI, um die visuelle Gestaltung und Benutzerfreundlichkeit des Systems zu verbessern. 

  

**7. Anforderungen** 

Die Anforderung waren wie folgt und wurden wie folgt erfüllt:

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
- [x] Optional können Sie auch weitere Funktionen hinzufügen, wie z.B. die Möglichkeit, Noten zu löschen oder zu bearbeiten, oder die Berechnung von statistischen Kennzahlen wie dem Median oder der Standardabweichung.
- [ ] Implementieren eine Umschaltung zwischen verschiedenen Notensystemen.
- [ ] Legen Sie eine Benutzerverwaltung an.
- [x] Entwickeln Sie kollaborativ das Projekt mit einem Tool, wie z.B. GitHub.
- [x] Entwickeln Sie Testfälle zur Überprüfung einzelner Programmabschnitte.
- [x] Entwickeln Sie eine Möglichkeit Noten in der Durchschnittsberechnung unterschiedlich zu gewichten.
- [x] Objektorientierter Programmieransatz.
- [x] Verwendung anderer Datentypen als Array (z.B. Nutzen von Java Collections).

  

**8. Funktionalität** 

Unser Notenverwaltungssystem bietet folgende Funktionalitäten: 

- Hinzufügen von Klassen: Lehrer können neue Klassen erstellen und ihnen Namen zuweisen. 

- Hinzufügen von Schülern: Zu den erstellten Klassen können Lehrer Schüler hinzufügen und ihre Informationen speichern. 

- Notenvergabe: Lehrer können den Schülern Noten zuweisen und sie im System speichern. 

- Zurücksetzen der Daten: Es besteht die Möglichkeit, alle Schülerdaten und Noten zu löschen und das System auf den Ausgangszustand zurückzusetzen. 

- Visuelle Darstellung der Daten: Die Benutzeroberfläche bietet Möglichkeiten sich die Noten der Schüler übersichtlich darzustellen.

  

**9. Anwendungsbeispiel** 

Man nehme an der Lehrer möchte eine komplett neue Klasse erstellen, Schüler hinzufügen und diesen direkt Noten geben. Dies würde wie folgt Ablaufen:

1. Klasse erstellen 

Der Lehrer kann in der ersten Maske eine Klasse erstellen, in dem er einen Klassennamen eingibt und auf "Hinzufügen" klickt


![title](misc/Screenshot%201.png)

In unserem Fall erstellen wir die Klasse "Test".

2. Schüler hinzufügen

Wenn wir nun in die Schülerverwaltungsmaske gehen, sehen wir nun das unsere neu angelegte Klasse keine Schüler hat.

![title](misc/Screenshot%202.png)

Daher fügen wir einen Schüler mit dem Namen Sascha Dietrich hinzu, indem wir Namen und Email hinterlegen.

![title](misc/Screenshot%203.png)

Nun klicken wir auf "Hinzufügen" und schon ist der Schüler da.

![title](misc/Screenshot%204.png)

3. Fach hinzufügen 

Da wir Fächer brauchen, in welche wir unsere Schüler benoten, fügen wir dieses hinzu in der Fachverwaltungs Maske.

Hier können wir nun unsere Klasse auswählen, einen Fachnamen eingeben und dieses dann hinzufügen.

![title](misc/Screenshot%205.png)

Nachdem wir auf Hinzufügen geklickt haben, sollte dieses nun in der Übersicht auftauchen.

![title](misc/Screenshot%206.png)

4. Noten hinzufügen

Nachdem wir nun Klasse, Schüler und Fach hinzugefügt haben, können wir nun eine Note eintragen.

Dazu gehen wir in die Notenvergaben Maske und wählen hier Klasse und Schüler aus. (ACHTUNG Hier muss man auf den Namen klicken, NICHT auf die Mail)

![title](misc/Screenshot%207.png)

Nachdem wir auf Note bearbeiten klicken befinden wir uns in der Maske in der wir final Noten hinzufügen bzw. löschen können.

![title](misc/Screenshot%208.png)

Wenn wir hier nun in die entsprechenden Felder Note, Testart und Fach eintragen können wir auf Hinzufügen klicken und die Note ist eingetragen !

![title](misc/Screenshot%209.png)

Fertig!




  

**10. Fazit** 

Unser Notenverwaltungssystem bietet Lehrern eine effiziente Möglichkeit, Schülerdaten und Noten zu verwalten. Mit den Funktionen zum Hinzufügen von Klassen und Schülern sowie der Notenvergabe und der Möglichkeit, alle Daten zurückzusetzen, unterstützt es Lehrer bei ihrer täglichen Arbeit. Wir sind stolz auf das Ergebnis unseres Projekts und stehen Ihnen für Fragen zur Verfügung. 

  
