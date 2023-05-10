# Klassen Auswahl Frame

Das KlassenAuswaehlen_Frame dient dazu hauptsächlich zur Verwaltung der Verfügbaren Klassen und einen Schüler zu wählen.

Sie bietet ausserdem Im- und Export Möglichkeiten für die Schulklassen.


## Variablen

<details>
<summary> 1. contentPane</summary>

- Eine Instanz der JPanel-Klasse, die das Hauptpanel des Fensters darstellt

</details>

<details>
<summary> 2. klasse</summary>

- Dient dazu die aktuelle Klassen auswahl zu speichern

</details>

<details>
<summary> 3. schuelerList</summary>

- Eine ArrayList vom Typ String, die die Namen der Schüler speichert.

</details>

<details>
<summary> 4. csvReader</summary>

- Eine Instanz der Datenleser Klasse, die zum Lesen der CSV-Datei

</details>

<details>
<summary> 5. buffer </summary>

- Dient zum temporären Speichern der CSV-Daten

</details>

<details>
<summary> 6. splitBuffer</summary>

- Ein Array, welches dazu dient nur die benötigten Infos aus der CSV-Datei zu holen
    - buffer wird an jedem ";" gespalten und in dieses Array befüllt

</details>

<details>
<summary> 7. schuelerListeJList </summary>

- Eine Instanz der JList-Klasse, die die Liste der Schüler anzeigt

</details>

<details>
<summary> 8. klassenNamen </summary>

- Eine ArrayList vom Typ String, die alle Klassennamen speichert

</details>

<details>
<summary> 9. folderReader</summary> 

- Entspricht im Grunde genommen dem csvReader
- Die Unterscheidung dient rein der Übersicht im schneller zu erkennen was wo gemacht wird
    - csvReader für CSV-Dateien
    - folderReader für Ordner

</details>

<details>
<summary> 10. klassenAuswahlComboBox</summary>

- Eine Instanz der JComboBox-Klasse, die das Dropdown-Menü der Klassenauswahl darstellt

</details>

<details>
<summary> 11. klassenAuswahlLabel</summary>

- Eine Instanz der JLabel-Klasse, die das Label für das Dropdown-Menü der Klassenauswahl darstellt

</details>

<details>
<summary> 12. menuBar</summary>

- Eine Instanz der JMenuBar-Klasse, die die Menüleiste am oberen Rand des Fensters darstellt

</details>

<details>
<summary> 13. importButton</summary>

- Eine Instanz der JMenuItem-Klasse, die den Import-Button darstellt

</details>

<details>
<summary> 14. exportButton </summary>

- Eine Instanz der JMenuItem-Klasse, die den Export-Button darstellt

</details>

<details>
<summary> 15. schuelerListeLabel</summary>

- Eine Instanz der JLabel-Klasse , die das Label für die Schülerliste darstellt

</details>

<details>
<summary> 16. updateButton </summary>

- Eine Instanz der JButton-Klasse, die den Update-Button darstellt

</details>


## Funktionen

<details>
<summary> 1. createWindow</summary>

- Erstellt das Hauptfenster des GUI

</details>

<details>
<summary> 2. klassenAuswahlLabelInit</summary>

- Initialisiert das Label für das Klassen-Dropdown-Menü

</details>

<details>
<summary> 3. generateKlassenDropdown</summary>

- Generiert das Dropdown-Menü mit allen vorhandenen Klassen

</details>

<details>
<summary> 4. generateSchuelerJList </summary>

- Generiert die Liste der Schüler in der ausgewählten Klasse.

</details>

<details>
<summary> 5. generateMenuBar </summary>

- Generiert die Menüleiste des GUI.

</details>

<details>
<summary> 6. generateImportButton</summary>

- Generiert den Import-Button. 

</details>

<details>
<summary> 7. generateExportButton</summary>

- Generiert den Export-Button.

</details>

<details>
<summary> 8. generateSchuelerListeLabel</summary>

- Generiert das Label für die Schülerliste

</details>

<details>
<summary> 9. generateUpdateButton </summary>

- Generiert den Button zum Aktualisieren der Klassenliste nach einem Import

</details>
