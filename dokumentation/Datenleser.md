# Datenleser Klasse

Datenleser soll dafür da sein, alles was sich um die CSV-Dateien dreht kompakt an einem Ort zu halten um Code leserlicher zu gestalten.

## Variablen

<details>
<summary> 1. csvFile </summary>

- csvFile wird verwendet um die gewünschte CSV-Datei zu öffnen
- In der CSV-Datei wird die Fachspezifische Notentabelle hinterlegt

</details>
<details>
<summary> 2. csvReader </summary>

- Dient dafür, die Ausgabe des FileReaders Zeilenweise abzurufen und diese nach und nach zu verarbeiten

</details>
<details>
<summary> 3. klassenNamen </summary>

- Eine ArrayList die alle Klassennamen abspeichern soll
- ArrayList um dynamik zu garantieren

</details>
<details>
<summary> 4. faecherNamen </summary>

- Eine ArrayList die alle Faechernamen abspeichern soll
- ArrayList um dynamik zu garantieren

</details>

## Funktionen

<details>
<summary> 1. setFilePath </summary>

- Soll nach der Auswahl des Faches und der Klasse den Pfad zur entsprechenden CSV-Datei festlegen

- Es gibt 2 Varianten:

### Variante 1

<details>
<summary>Code</summary>

```java
public void setFilePath(String klasse) {
	csvFile = new File("CSV_Dateien/"+klasse+"/Schuelerliste.csv");
	
}
```
</details>

- Diese dient nur für den Aufruf der Schuelerliste
- Hier wird nur die Klasse benötigt

### Variante 2

<details>
<summary>Code</summary>

```java
public void setFilePath(String fach,String klasse) throws IOException{               
    String path = "CSV_Dateien/"+klasse+"/"+fach;                     
        csvFile = new File(path);      
    }
```

</details>

- Diese dient nur für den Aufruf der Fächer
- Hier wird daher fach und klasse als Parameter benötigt

</details>

<details>
<summary> 2. initReader </summary>

- Soll nach setFilePath aufgerufen werden
- Erstellt die Reader und ermöglicht spätere Auslesung
<details>
<summary>Code</summary>

```java
	public void initReader() throws FileNotFoundException {
		csvReader = new BufferedReader(new FileReader(csvFile));
	}
```

</details>

</details>
<details>
<summary> 3. getLine </summary>

- Gibt die aktuelle Zeile aus

<details>
<summary>Code</summary>

```java
	public String getLine() throws IOException {
		return csvReader.readLine();
	}
```

</details>

</details>

<details>
<summary> 4. closeFile </summary>

- Soll den Reader und die die geöffnete Datei schließen

<details>
<summary>Code</summary>

```java
	public void closeFile() throws IOException {
		csvReader.close();
	}
```
</details>

</details>
<details>
<summary> 5. importKlasse </summary>

 - Implementiert den JFileChooser
        - Dieser öffnet ein Fenster, welches mit dem Dateiexplorer gleichzustellen ist
        - Mit diesem soll der Nutzer sein gewünschten Ordner auswählen können => Import/Export
    - Es wird gewartet bis der Nutzer eine auswahl getroffen hat und diese bestätigt
        - Dann wird das ausgewählte Verzeichnis übertragen in die von uns vorgegebene Datenstruktur

<details>
<summary>Code</summary>

```java
	public void importKlasse() {
		// Erstellt einen FileChooser, welcher dafür dient ein Auswahl Fenster zu öffnen
		JFileChooser fileChooser = new JFileChooser();
		// Erlaubt nur die Auswahl von Ordnern
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// Öffnet besagtes Fenster
		int result = fileChooser.showOpenDialog(null);
		// Prüft ob die Auswahl bestätigt wurde
		if (result == JFileChooser.APPROVE_OPTION) {
			// Speichert den Pfad zum ausgewählten Verzeichnis
			File selectedDir = fileChooser.getSelectedFile();
			// Speichert den Pfad zum vorgegebenen Verzeichnis
			File destDir = new File(System.getProperty("user.dir") + "/CSV_Dateien/" + selectedDir.getName());
			try {
				// Ruft Methode copyDirectory auf und gibt Ursprungs- und Zielverzeichnis mit
				copyDirectory(selectedDir, destDir);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

```

</details>

</details>
<details>
<summary> 6. copyDirectory </summary>

- Rekursive Methode um die Dateien innerhalb des Ordners zu kopieren(siehe 5.)
    - Falls das Verzeichnis des Nutzers nicht existiert wird es erstellt
    - Ein File Array für die Dateien wird angelegt 
        - Die Dateien des Ursprung Ordners werden hier reingeladen
    - Jetzt wird für jedes Element, wenn es noch nicht existiert , eine Kopie im neuen Verzeichnis angelegt
        - Falls die Datei schon existiert wird sie ersetzt

<details>
<summary>Code</summary>

```java
	public static void copyDirectory(File sourceDir, File destDir) throws IOException {
		// Falls das Zielverzeichnis nicht existiert, wird es erstellt
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		// Speichert die Pfade zu den Dateien, die im Ursprungsordner hinterlegt sind,
		// ab
		File[] children = sourceDir.listFiles();
		// Für jedes Element des oben festgelegten Arrays, wird nun eine Schleife
		// abgegegangen
		for (File sourceChild : children) {
			// Der Name der Ursprungsdatei wird gespeichert
			String name = sourceChild.getName();
			// Die Datei wird mit neuem Pfad und dem Ursprungsnamen erstellt
			File destChild = new File(destDir, name);
			// Falls es sich um ein Ordner handelt wird dieser erstellt
			if (sourceChild.isDirectory()) {
				copyDirectory(sourceChild, destChild);
			} else { // Ansonsten wird die Datei gespeichert, falls bereits existiert wird sie
						// überschrieben
				Files.copy(sourceChild.toPath(), destChild.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}
```

</details>

</details>
<details>
<summary> 7. getKlassenNamen </summary>

- Ruft die Klassennamen ab und gibt diese als ArrayList zurück

<details>
<summary>Code</summary>

```java
public ArrayList<String> getKlassenNamen() {
    //Ordner in dem die Klassenverzeichnisse liegen, wird übergeben
	File directory = new File("CSV_Dateien");
    //Hier wird festgelegt das wir nur Ordner gelistet haben möchten
    File[] files = directory.listFiles(File::isDirectory);
    //Hier wird nun über das obige Array iteriert
    for (File file : files) {
        //Der ArrayList werden hier die Namen der Ordner mitgegeben
        klassenNamen.add(file.getName());
    }
	return klassenNamen;
}
```
</details>

</details>
<details>
<summary> 8. hasMoreLines </summary>

- Prüft ob csvReader noch Zeilen zur Verfügung hat

```java
	public boolean hasMoreLines() {
		try {
			return csvReader.ready();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
```

</details>    
<details>
<summary> 9. getFaecherNamen </summary>

- Ruft die Faechernamen ab und gibt diese als ArrayList zurück

<details>
<summary>Code</summary>

```java
public ArrayList<String> getFaecherNamen(String klasse) {
    //Ordner in dem die Klassenverzeichnisse liegen, wird übergeben
	File directory = new File("CSV_Dateien/"+klasse+"/");
    //Hier wird festgelegt das wir nur Dateien gelistet haben möchten
    File[] files = directory.listFiles(File::isFile);
    //Hier wird nun über das obige Array iteriert
    for (File file : files) {
        if(!"Schuelerliste.csv".equals(file.getName())){
        //Der ArrayList werden hier die Namen der Faecher mitgegeben
        faecherNamen.add(file.getName());
        }
    }
	return faecherNamen;
}
```
</details>    

