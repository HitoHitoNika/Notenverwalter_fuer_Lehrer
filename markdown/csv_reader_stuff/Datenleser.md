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


### Variante 1
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

### Variante 2
<details>
<summary>Code</summary>

```java
public List<String> getClassNames() {
		List<String> classNames = new ArrayList<>();
		String directoryPath = "CSV_Dateien";
		File directory = new File(directoryPath);

		if (directory.exists() && directory.isDirectory()) {
			File[] classFolders = directory.listFiles();

			if (classFolders != null) {
				for (File classFolder : classFolders) {
					if (classFolder.isDirectory()) {

						if (!classFolder.getName().equals("config")) {
							String className = classFolder.getName();
							classNames.add(className);
						}

					}
				}
			}
		}

		return classNames;
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

### Variante 1

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

### Variante 2

<details>
<summary>Code</summary>

```java
	public List<String> getSubjectsOfClass(String klasse) {
		List<String> subjects = new ArrayList<>();
		String folderPath = "CSV_Dateien/" + klasse;

		File folder = new File(folderPath);
		if (!folder.exists()) {
			System.out.println("Die Klasse existiert nicht.");
			return subjects;
		}

		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					String fileName = file.getName();
					if (fileName.endsWith(".csv")) {
						String subject = fileName.substring(0, fileName.length() - 4);
						// Überprüfen, ob das Fach "Schuelerliste" ist
						if (!subject.equalsIgnoreCase("Schuelerliste")) {
							subjects.add(subject);
						}
					}
				}
			}
		}

		return subjects;
	}
```
</details>


</details>

<details>
<summary> 10. getAverage </summary>

- Berechnet den Durschnitt und gibt diesen zurück

<details>
<summary>Code</summary>

```java
public double getAverage(int schuelerID) throws IOException {
		schuelerID++;
		double[] average = { 0, 0, 0, 0 };
		int[] countTestArten = { 0, 0, 0 };
		try {
			getLine();
		} catch (IOException e) {
			System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
			return 0;
		}
		while (hasMoreLines()) {
			String[] splitBuffer = getLine().split(";");
			if (Integer.parseInt(splitBuffer[0]) == schuelerID) {
				int index = Integer.parseInt(splitBuffer[2]) - 1;
				countTestArten[index]++;
				average[index] += Integer.parseInt(splitBuffer[1]);
				average[3] += Integer.parseInt(splitBuffer[1]) * ((index == 0) ? 0.5 : (index == 1) ? 0.3 : 0.2);
			}
		}
		for (int i = 0; i < 3; i++) {
			if (countTestArten[i] > 0) {
				average[i] /= countTestArten[i];
			}
		}
		return (countTestArten[0] == 0)
				? (countTestArten[2] == 0) ? average[1]
						: (countTestArten[1] == 0) ? average[2] : ((average[1] * 0.6) + (average[2] * 0.4))
				: (countTestArten[1] == 0)
						? (countTestArten[2] == 0) ? average[0] : ((average[0] * 0.71) + (average[2] * 0.285714))
						: (countTestArten[2] == 0) ? ((average[0] * 0.5) + (average[1] * 0.3)) : average[3];
	}
```
</details>
</details>

<details>
<summary> 11. getNoten </summary>

- Liest die Noten für einen bestimmten Schüler und eine bestimmte Testart aus der entsprechenden Datei.

<details>
<summary>Code</summary>

```java
public int[] getNoten(int schuelerID, int testArtID) throws IOException {
		ArrayList<Integer> notenList = new ArrayList<>();
		int[] noten;
		schuelerID++;
		boolean counter = false;
		try {
			getLine();
		} catch (IOException e) {
			System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
		}
		while (hasMoreLines()) {
			String[] splitBuffer = getLine().split(";");
			if (Integer.parseInt(splitBuffer[0]) == schuelerID && testArtID == Integer.parseInt(splitBuffer[2])) {
				notenList.add(Integer.parseInt(splitBuffer[1]));
				counter = true;
			}

		}
		if (counter) {
			noten = notenList.stream().mapToInt(i -> i).toArray();
			return noten;
		} else {
			notenList.add(0);
			noten = notenList.stream().mapToInt(i -> i).toArray();
			return noten;
		}
	}
``` 

</details>
</details>

<details>
<summary> 12. writeNote </summary>

- Schreibt eine Note für einen bestimmten Schüler und eine bestimmte Testart

<details>
<summary>Code</summary>

```java
/**
	 * Schreibt eine Note für einen Schüler in die CSV-Datei.
	 *
	 * @param note       Die Note, die hinzugefügt werden soll.
	 * @param schuelerID Die ID des Schülers.
	 * @param test       Die Testnummer.
	 */

	public void writeNote(int note, int schuelerID, int test) {
		schuelerID++;
		String[] newEntry = { String.valueOf(schuelerID), String.valueOf(note), String.valueOf(test) }; // Eintrag
																										// hinzufügen
		try {
			FileWriter csvWriter = new FileWriter(csvFile, true);
			csvWriter.append("\n");
			csvWriter.append(String.join(";", newEntry));
			csvWriter.append("\n");
			csvWriter.close();
			System.out.println("New entry added successfully.");
		} catch (IOException e) {
			System.out.println("Error while adding new entry to CSV file: " + e.getMessage());
		}

	}
```


</details>
</details>

<details>
<summary>13. exportKlasse</summary>

- Rekursives kopieren aller Klassen in gewünschtes Verzeichnis

<details>
<summary>Code</summary>

```java
public void exportKlasse() {
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
			File destDir = new File(System.getProperty("user.dir") + "/CSV_Dateien/");
			try {
				// Ruft Methode copyDirectory auf und gibt Ursprungs- und Zielverzeichnis mit
				copyDirectory(destDir, selectedDir);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
```


</details>
</details>


