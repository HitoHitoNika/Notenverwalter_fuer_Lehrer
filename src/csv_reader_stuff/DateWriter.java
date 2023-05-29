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
     * Gibt eine Liste mit den Namen und E-Mails aller Schüler in der Schülerliste zurück.
     *
     * @param klasse die Klasse für die CSV-Datei
     * @return eine Liste mit den Namen und E-Mails der Schüler
     */
	/**
     * Gibt eine Liste von Student-Objekten zurück, die Namen und E-Mails aller Schüler in der Schülerliste enthält.
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
    
    public static void main(String[] args) {
		DateWriter test = new DateWriter();
		List<Student> Schueler = new ArrayList<Student>();
		Schueler = test.getStudentList("BSIT22b");
		
		for (Student student : Schueler) {
			student.getName();
			student.getEmail();
			student.getId();
			System.out.println(student.getName() + student.getEmail()+ 	student.getId());
		}
		

	}
    
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
    
    
}

