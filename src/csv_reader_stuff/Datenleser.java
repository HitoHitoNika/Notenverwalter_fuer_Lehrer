package csv_reader_stuff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

import javax.swing.JFileChooser;

public class Datenleser {

	/**
	 * Speichert den Pfad der gewünschten Datei ab
	 **/
	private File csvFile;
	/**
	 * Liest die Datei aus
	 **/
	private BufferedReader csvReader;
	/**
	 * Speichert dynamisch die Klassennamen
	 **/
	private ArrayList<String> klassenNamen = new ArrayList<>();
	/**
	 * Speichert dynamisch die Faechernamen
	 **/
	private ArrayList<String> faecherNamen = new ArrayList<>();

	/**
	 * Konstruktor
	 * 
	 * @throws FileNotFoundException Aufgrund des BufferedReaders
	 */
	public Datenleser() throws FileNotFoundException {

	}

	/**
	 * Setzt den Dateipfad so, dass das gewünschte Fach aufgerufen werden kann
	 * 
	 * @param String fach, um die richtige Datei zu finden
	 * @param String klasse, um den richtigen Ordner zu finden
	 * @throws IOException Da Datei theoretisch nicht existieren könnte
	 */
	public void setFilePath(String fach, String klasse) throws IOException {
		String path = "CSV_Dateien/" + klasse + "/" + fach + ".csv";
		csvFile = new File(path);
	}

	/**
	 * Setzt den FilePath so, dass die gewünschte Schuelerliste aufgerufen werden
	 * kann
	 * 
	 * @param klasse String klasse, um den richtigen Ordner zu finden
	 */
	public void setFilePath(String klasse) {
		csvFile = new File("CSV_Dateien/" + klasse + "/Schuelerliste.csv");

	}

	/**
	 * Initialisiert den BufferedReader
	 * 
	 * @throws FileNotFoundException Falls die Datei nicht existiert
	 */
	public void initReader() throws FileNotFoundException {
		csvReader = new BufferedReader(new FileReader(csvFile));
	}

	/**
	 *
	 * @return Gibt eine Zeile der CSV zurück
	 * @throws IOException Datei könnte leer sein, oder nicht vorhanden
	 */
	public String getLine() throws IOException {
		return csvReader.readLine();
	}

	/**
	 * Schließt den Reader und die Datei
	 * 
	 * @throws IOException Es könnte garkeine Datei geöffnet sein
	 */
	public void closeFile() throws IOException {
		csvReader.close();
	}

	/**
	 * Erlaubt Import von außerhalb angelegten Klassendateien
	 */
	public void importKlasse() {
		// Erstellt einen FileChooser, welcher dafür dient ein Auswahl Fenster zu öffnen
		JFileChooser fileChooser = new JFileChooser();
		// Erlaubt nur die Auswahl von Ordnern
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//Bennent Fenster entsprechend
		fileChooser.setDialogTitle("Welche Klasse möchten sie Importieren?");
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

	/**
	 * Rekursives kopieren der einzelnen Dateien
	 * 
	 * @param sourceDir
	 * @param destDir
	 * @throws IOException
	 */
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

	/**
	 * Gibt Klassennamen zurück als ArrayList zurück
	 * 
	 * @return ArrayList als String
	 */
	public ArrayList<String> getKlassenNamen() {
		// Ordner in dem die Klassenverzeichnisse liegen, wird übergeben
		File directory = new File("CSV_Dateien");
		// Hier wird festgelegt das wir nur Ordner gelistet haben möchten
		File[] files = directory.listFiles(File::isDirectory);
		// Hier wird nun über das obige Array iteriert
		for (File file : files) {
			// Der ArrayList werden hier die Namen der Ordner mitgegeben
			klassenNamen.add(file.getName());
		}
		return klassenNamen;
	}

	/**
	 * Gibt Faechernamen zurück als ArrayList zurück
	 * 
	 * @param klasse damit die richtige Liste gefunden wird
	 * @return ArrayList als String
	 */
	public ArrayList<String> getFaecherNamen(String klasse) {
		// Ordner in dem die Klassenverzeichnisse liegen, wird übergeben
		File directory = new File("CSV_Dateien/" + klasse + "/");
		// Hier wird festgelegt das wir nur Dateien gelistet haben möchten
		File[] files = directory.listFiles(File::isFile);
		// Hier wird nun über das obige Array iteriert
		for (File file : files) {
			if (!"Schuelerliste.csv".equals(file.getName())) {
				// Der ArrayList werden hier die Namen der Faecher mitgegeben
				faecherNamen.add(file.getName());
			}
		}
		return faecherNamen;
	}

	/**
	 * Prüft ob die CSV Datei noch verfügbare Zeilen hat
	 * 
	 * @return boolean
	 */
	public boolean hasMoreLines() {
		try {
			return csvReader.ready();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Berechnet den Durchschnit der Noten eines Schülers
	 * @param schuelerID
	 * @param testArtID
	 * @return
	 * @throws IOException
	 */
	public double getAverage(int schuelerID) throws IOException {
		schuelerID++;
		int[] counter = new int[3];
		double[] sum = new double[3];
		double total = 0.0;
		double totalWeight = 0.0;
		try {
			getLine();
		} catch (IOException e) {
			System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
			return 0;
		}
		while (hasMoreLines()) {
			String[] splitBuffer = getLine().split(";");
			if (Integer.parseInt(splitBuffer[0]) == schuelerID) {
				sum[Integer.parseInt(splitBuffer[2])-1] += Integer.parseInt(splitBuffer[1]);
				counter[Integer.parseInt(splitBuffer[2])-1]++;
			}
		}
		if (counter[0] > 0) {
			total += (sum[0] / counter[0]) * 0.5;
			totalWeight += 0.5; 
		} 
		if (counter[1] > 0) {
			total += (sum[1] / counter[1]) * 0.3;
			totalWeight += 0.3;
		}
		if (counter[2] > 0) {
			total += (sum[2] / counter[2]) * 0.2;
			totalWeight += 0.2;
		}
		System.out.println(totalWeight);
		System.out.println(total);
		return total / totalWeight;
	}
	

	/**
	 * Liest die Noten für einen bestimmten Schüler und eine bestimmte Testart aus
	 * der Datei.
	 *
	 * @param schuelerID Die ID des Schülers.
	 * @param testArtID  Die ID der Testart.
	 * @return Ein Array mit den gelesenen Noten.
	 * @throws IOException Wenn ein Fehler beim Lesen der Datei auftritt.
	 */
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

	// DIE SIND VON MIR MAX, TUT MIR LEID, DEINE HABEN BEI MIR DINGE GEFICKT BRUDA

	/**
	 * Gibt eine Liste mit den Namen der Klassen im Verzeichnis "CSV_Dateien"
	 * zurück.
	 *
	 * @return Eine Liste mit den Namen der Klassen.
	 */

	public List<String> getClassNames() {
		List<String> classNames = new ArrayList<>();
		String directoryPath = "CSV_Dateien";
		File directory = new File(directoryPath);

		if (directory.exists() && directory.isDirectory()) {
			File[] classFolders = directory.listFiles();

			if (classFolders != null) {
				for (File classFolder : classFolders) {
					if (classFolder.isDirectory()) {
							String className = classFolder.getName();
							classNames.add(className);
					}
				}
			}
		}

		return classNames;
	}

	/**
	 * Gibt eine Liste mit den Fächern einer bestimmten Klasse zurück.
	 *
	 * @param klasse Der Name der Klasse.
	 * @return Eine Liste mit den Fächern der Klasse.
	 */

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

	/**
	 * Rekursives kopieren aller Klassen in gewünschtes Verzeichnis
	 */
	public void exportKlasse() {
		// Erstellt einen FileChooser, welcher dafür dient ein Auswahl Fenster zu öffnen
		JFileChooser fileChooser = new JFileChooser();
		// Erlaubt nur die Auswahl von Ordnern
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//Bennent das Fenster entsprechend
		fileChooser.setDialogTitle("Wählen sie ihren Ablageort");
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

}