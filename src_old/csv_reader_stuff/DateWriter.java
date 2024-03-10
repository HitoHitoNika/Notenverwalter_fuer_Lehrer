package csv_reader_stuff;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import school_attributes.Student;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class DateWriter {

	/**
	 * Erstellt eine neue CSV-Datei für ein bestimmtes Fach und eine bestimmte
	 * Klasse, falls die Datei noch nicht existiert.
	 *
	 * @param fach   das Fach für die CSV-Datei
	 * @param klasse die Klasse für die CSV-Datei
	 * @return true, wenn die Datei erfolgreich erstellt wurde, false, wenn die
	 *         Datei bereits existiert oder ein Fehler aufgetreten ist
	 */

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

	/**
	 * Erstellt eine neue CSV-Datei für die Schülerliste einer bestimmten Klasse,
	 * falls die Datei noch nicht existiert.
	 *
	 * @param klasse die Klasse für die CSV-Datei
	 * @return true, wenn die Datei erfolgreich erstellt wurde, false, wenn die
	 *         Datei bereits existiert oder ein Fehler aufgetreten ist
	 */
	public boolean createStudentsCSVFile(String klasse) {
		String path = "CSV_Dateien/" + klasse + "/" + "Schuelerliste.csv";
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

	/**
	 * Fügt einen neuen Eintrag zur CSV-Datei der Schülerliste einer bestimmten
	 * Klasse hinzu.
	 *
	 * @param klasse die Klasse für die CSV-Datei
	 * @param name   der Name des Schülers
	 * @param email  die E-Mail-Adresse des Schülers
	 * @return true, wenn der Eintrag erfolgreich hinzugefügt wurde, false, wenn ein
	 *         Fehler aufgetreten ist
	 */

	public boolean addEntryToCSV(String klasse, String name, String email) {
		try {
			String fileName = "CSV_Dateien/" + klasse + "/" + "Schuelerliste.csv";

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

	/**
	 * Erstellt einen neuen Ordner für eine bestimmte Klasse, falls der Ordner noch
	 * nicht existiert.
	 *
	 * @param klasse die Klasse für den Ordner
	 * @return true, wenn der Ordner erfolgreich erstellt wurde, false, wenn der
	 *         Ordner bereits existiert oder ein Fehler aufgetreten ist
	 */
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

	/**
	 * Gibt eine Liste von Student-Objekten zurück, die Namen und E-Mails aller
	 * Schüler in der Schülerliste enthält.
	 *
	 * @param klasse die Klasse für die CSV-Datei
	 * @return eine Liste von Student-Objekten
	 */
	public List<Student> getStudentList(String klasse) {
		List<Student> studentList = new ArrayList<>();
		String fileName = "CSV_Dateien/" + klasse + "/Schuelerliste.csv";

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(";");
				String name = parts[0];
				String email = parts[1];
				String id = parts[2];
				Student student = new Student(name, email, id);
				studentList.add(student);
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Schülerliste: " + e.getMessage());
		}

		return studentList;
	}

	/**
	 * 
	 * Zählt die Anzahl der Schüler in einer bestimmten Klasse.
	 * 
	 * @param klasse die Klasse, für die die Anzahl der Schüler gezählt werden soll
	 * @return die Anzahl der Schüler in der Klasse
	 */

	public int countStudents(String klasse) {
		int count = 0;
		String fileName = "CSV_Dateien/" + klasse + "/Schuelerliste.csv";
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			// Die Schülerliste wird Zeile für Zeile durchgegangen
			reader.readLine();
			while (reader.readLine() != null) {
				count++;
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Schülerliste: " + e.getMessage());
		}

		return count;
	}

	/**
	 * Löscht eine Klasse und alle zugehörigen Dateien.
	 *
	 * @param klasse die zu löschende Klasse
	 */
	public void deleteClass(String klasse) {
		String folderPath = "CSV_Dateien/" + klasse;
		File folder = new File(folderPath);

		if (!folder.exists()) {
			System.out.println("Die Klasse existiert nicht.");
			return;
		}

		// Lösche alle Dateien im Ordner
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				file.delete();
			}
		}

		// Lösche den Ordner
		if (folder.delete()) {
			System.out.println("Die Klasse wurde erfolgreich gelöscht.");
		} else {
			System.out.println("Fehler beim Löschen der Klasse.");
		}
	}

	/**
	 * 
	 * Löscht einen Schüler aus einer bestimmten Klasse.
	 * 
	 * @param klasse      die Klasse, aus der der Schüler gelöscht werden soll
	 * @param studentName der Name des zu löschenden Schülers
	 */

	public void deleteStudent(String klasse, String studentName) {
		String fileName = "CSV_Dateien/" + klasse + "/Schuelerliste.csv";
		List<Student> studentList = getStudentList(klasse);

		for (int i = 0; i < studentList.size(); i++) {
			Student student = studentList.get(i);
			if (student.getName().equals(studentName)) {
				studentList.remove(i);
				break;
			}
		}

		// Aktualisierte Schülerliste speichern
		saveStudentList(fileName, studentList);
	}

	/**
	 * 
	 * Speichert eine Schülerliste in einer CSV-Datei.
	 * 
	 * @param fileName    der Name der CSV-Datei, in die die Schülerliste
	 *                    gespeichert werden soll
	 * @param studentList die Liste der Schülerdaten, die gespeichert werden sollen
	 */
	public void saveStudentList(String fileName, List<Student> studentList) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			// Schreiben der Header-Zeile
			writer.write("Name;E-Mail;ID");
			writer.newLine();

			// Schreiben der Schülerdaten
			for (Student student : studentList) {
				writer.write(student.getName() + ";" + student.getEmail() + ";" + student.getId());
				writer.newLine();
			}

			System.out.println("Schülerliste wurde erfolgreich gespeichert.");
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern der Schülerliste: " + e.getMessage());
		}
	}

	/**
	 * 
	 * Löscht ein Fach und die zugehörige CSV-Datei.
	 * 
	 * @param fach   das zu löschende Fach
	 * @param klasse die Klasse, zu der das Fach gehört
	 * @return true, wenn das Fach erfolgreich gelöscht wurde, andernfalls false
	 */

	public boolean deleteSubject(String fach, String klasse) {
		String fileName = "CSV_Dateien/" + klasse + "/" + fach + ".csv";
		File file = new File(fileName);

		if (file.exists()) {
			if (file.delete()) {
				System.out.println("Das Fach wurde erfolgreich gelöscht.");
				return true;
			} else {
				System.out.println("Fehler beim Löschen des Fachs.");
				return false;
			}
		} else {
			System.out.println("Das Fach existiert nicht.");
			return false;
		}
	}

	/**
	 * 
	 * Fügt eine Note zu einem Fach hinzu.
	 * 
	 * @param klasse     die Klasse, zu der das Fach gehört
	 * @param fach       das Fach, zu dem die Note hinzugefügt werden soll
	 * @param schuelerID die ID des Schülers
	 * @param note       die Note
	 * @param test       der Test/Prüfung, zu dem die Note gehört
	 * @return true, wenn die Note erfolgreich zum Fach hinzugefügt wurde,
	 *         andernfalls false
	 */

	public boolean addGradeToSubject(String fach, String klasse, int schuelerID, int note, int test) {
		String fileName = "CSV_Dateien/" + klasse + "/" + fach + ".csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
			// Schreiben des neuen Eintrags
			writer.write(schuelerID + ";" + note + ";" + test);
			writer.newLine();

			System.out.println("Note wurde erfolgreich zum Fach hinzugefügt.");
			return true;
		} catch (IOException e) {
			System.out.println("Fehler beim Hinzufügen der Note zum Fach: " + e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * Löscht eine Note aus einem Fach.
	 * 
	 * @param fach       das Fach, aus dem die Note gelöscht werden soll
	 * @param klasse     die Klasse, zu der das Fach gehört
	 * @param schuelerID die ID des Schülers
	 * @param note       die zu löschende Note
	 * @param notenartID die ID der Notenart
	 * @return true, wenn die Note erfolgreich aus dem Fach gelöscht wurde,
	 *         andernfalls false
	 */

	public boolean deleteGradeFromSubject(String fach, String klasse, int schuelerID, int note, int notenartID) {
		String fileName = "CSV_Dateien/" + klasse + "/" + fach + ".csv";

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			List<String> lines = new ArrayList<>();

			// Die CSV-Datei Zeile für Zeile durchgehen und die gewünschte Note entfernen
			String line;
			boolean isFirstLine = true; // Variable, um die erste Zeile zu überspringen
			boolean gradeDeleted = false;
			while ((line = reader.readLine()) != null) {
				if (isFirstLine) {
					lines.add(line);
					isFirstLine = false;
					continue; // Überspringe die erste Zeile
				}

				String[] parts = line.split(";");
				int currentSchuelerID = Integer.parseInt(parts[0]);
				int currentNote = Integer.parseInt(parts[1]);
				int currentNotenartID = Integer.parseInt(parts[2]);
				if (currentSchuelerID == schuelerID && currentNote == note && currentNotenartID == notenartID
						&& !gradeDeleted) {
					gradeDeleted = true;
					continue; // Überspringe den zu löschenden Eintrag
				}

				lines.add(line);
			}

			if (!gradeDeleted) {
				System.out.println("Die Note konnte nicht gefunden werden.");
				return false;
			}

			// Aktualisierte Daten in die CSV-Datei schreiben
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
				for (String updatedLine : lines) {
					writer.write(updatedLine);
					writer.newLine();
				}
				System.out.println("Note wurde erfolgreich aus dem Fach gelöscht.");
				return true;
			} catch (IOException e) {
				System.out.println("Fehler beim Schreiben der aktualisierten CSV-Datei: " + e.getMessage());
				return false;
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der CSV-Datei: " + e.getMessage());
			return false;
		}
	}

}
