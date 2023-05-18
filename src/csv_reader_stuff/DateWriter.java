package csv_reader_stuff;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class DateWriter {

	public boolean createSubjectCSVFile(String fach, String klasse) {
		String path = "CSV_Dateien/" + klasse + "/" + fach + ".csv";
		File file = new File(path);

		try {
			if (file.exists()) {
				System.out.println("Die CSV-Datei existiert bereits.");
				return false;
			}
			FileWriter csvWriter = new FileWriter(file);
			csvWriter.append("Schuelerinfo_ID");
			csvWriter.append(";");
			csvWriter.append("Note(MSS)");
			csvWriter.append(";");
			csvWriter.append("Notenart_ID");
			csvWriter.append("\n");
			csvWriter.close();
			System.out.println("Neue CSV-Datei wurde erfolgreich erstellt.");
			return true;
		} catch (IOException e) {
			System.out.println("Fehler beim Erstellen der CSV-Datei: " + e.getMessage());
			return false;
		}
	}

	public boolean createStudentsCSVFile(String klasse) {
		String path = "CSV_Dateien/" + klasse + "/" + "Schülerliste.csv";
		try {
			FileWriter csvWriter = new FileWriter(path);
			csvWriter.append("Name");
			csvWriter.append(";");
			csvWriter.append("E-Mail");
			csvWriter.append(";");
			csvWriter.append("ID");
			csvWriter.append("\n");
			csvWriter.close();
			System.out.println("Neue CSV-Datei wurde erfolgreich erstellt.");
			return true;
		} catch (IOException e) {
			System.out.println("Fehler beim Erstellen der CSV-Datei: " + e.getMessage());
			return false;
		}
	}

	public boolean addEntryToCSV(String klasse, String name, String email) {
		try {
			String fileName = "CSV_Dateien/" + klasse + "/" + "Schülerliste.csv";

			// Überprüfen, ob die Datei bereits vorhanden ist und eine Schülerinfo-ID
			// festlegen
			int schuelerinfoId = 1; // Standardwert, falls es keinen vorherigen Eintrag gibt
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String lastLine = null;
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				lastLine = currentLine;
			}
			reader.close();
			if (lastLine != null) {
				// Extrahieren der Notenart-ID aus dem vorherigen Eintrag und erhöhen um 1
				String[] parts = lastLine.split(";");
				String previousNotenartId = parts[2];
				try {
					if (previousNotenartId.equals("ID")) {
					} else {
						schuelerinfoId = Integer.parseInt(previousNotenartId) + 1;
					}

				} catch (NumberFormatException e) {
					System.out.println("Ungültige Notenart-ID im vorherigen Eintrag: " + previousNotenartId);
					return false;
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

			String id = Integer.toString(schuelerinfoId);

			// Schreiben des neuen Eintrags
			writer.write(name + ";" + email + ";" + id);
			writer.newLine();

			writer.close();
			System.out.println("Eintrag wurde erfolgreich zur CSV-Datei hinzugefügt.");
			return true;
		} catch (IOException e) {
			System.out.println("Fehler beim Hinzufügen des Eintrags zur CSV-Datei: " + e.getMessage());
			return false;
		}
	}

	public boolean createClassFolder(String klasse) {
		String path = "CSV_Dateien/" + klasse;
		File folder = new File(path);
		if (!folder.exists()) {
			if (folder.mkdir()) {
				createStudentsCSVFile(klasse);
				System.out.println("Neuer Ordner wurde erfolgreich erstellt.");
				return true;
			} else {
				System.out.println("Fehler beim Erstellen des Ordners.");
				return false;
			}
		} else {
			System.out.println("Der Ordner existiert bereits.");
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		DateWriter test = new DateWriter();

	}

}
