package csv_reader_stuff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Datenleser {
    // Dient des Aufrufes des Verzeichnisses in dem sich das Projekt befindet
    private String projectPath = System.getProperty("user.dir");
    // Speichert den Pfad der gewünschten Datei ab
    private File csvFile;
    // Liest die Datei aus
    private BufferedReader csvReader ;
    // Speichert dynamisch die klassenNamen
    private ArrayList<String> klassenNamen = new ArrayList<>();
    
//Konstruktor
public Datenleser() throws FileNotFoundException{
    
}

//Setzt den Dateipfad so, dass das gewünschte Fach aufgerufen werden kann
public void setFilePath(String fach,String klasse) throws IOException{               
    String path = projectPath+"/CSV_Dateien/"+klasse;
    switch(fach){
    case "Deutsch":                            
        System.out.println(path+"/Deutsch.csv");                     
        csvFile = new File(path+"/Deutsch.csv");      
        break;
    }
}
//Setzt den FilePath so, dass die gewünschte Schuelerliste aufgerufen werden kann
public void setFilePath(String klasse) {
	csvFile = new File(projectPath+"/CSV_Dateien/"+klasse+"/Schuelerliste.csv");
	
}
//Initialisiert den BufferedReader
public void initReader() throws FileNotFoundException{     
    csvReader = new BufferedReader(new FileReader(csvFile));
}
//Gibt eine Zeile der CSV zurück
public String getLine() throws IOException{
    return csvReader.readLine();
}
//Schließt den Reader und die Datei
public void closeFile() throws IOException {
		csvReader.close();
}
//Erlaubt Import von außerhalb angelegten Klassendateien
public void importKlasse() {
    // Erstellt einen FileChooser, welcher dafür dient ein Auswahl Fenster zu öffnen
	JFileChooser fileChooser = new JFileChooser();
    // Erlaubt nur die Auswahl von Ordnern
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
    //Öffnet besagtes Fenster
    int result = fileChooser.showOpenDialog(null);
    //Prüft ob die Auswahl bestätigt wurde
    if (result == JFileChooser.APPROVE_OPTION) {
        //Speichert den Pfad zum ausgewählten Verzeichnis
        File selectedDir = fileChooser.getSelectedFile();
        //Speichert den Pfad zum vorgegebenen Verzeichnis
        File destDir = new File(System.getProperty("user.dir")+"/CSV_Dateien/" + selectedDir.getName());
        try {
            //Ruft Methode copyDirectory auf und gibt Ursprungs- und Zielverzeichnis mit
            copyDirectory(selectedDir, destDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
//Rekursives kopieren der einzelnen Dateien
public static void copyDirectory(File sourceDir, File destDir) throws IOException {
    //Falls das Zielverzeichnis nicht existiert, wird es erstellt
    if (!destDir.exists()) {
     destDir.mkdir();
    } 
 //Speichert die Pfade zu den Dateien, die im Ursprungsordner hinterlegt sind, ab
 File[] children = sourceDir.listFiles();
 //Für jedes Element des oben festgelegten Arrays, wird nun eine Schleife abgegegangen 
 for (File sourceChild : children) {
    // Der Name der Ursprungsdatei wird gespeichert
     String name = sourceChild.getName();
    // Die Datei wird mit neuem Pfad und dem Ursprungsnamen erstellt
     File destChild = new File(destDir, name);
    // Falls es sich um ein Ordner handelt wird dieser erstellt
     if (sourceChild.isDirectory()) {
         copyDirectory(sourceChild, destChild);
     } else { // Ansonsten wird die Datei gespeichert, falls bereits existiert wird sie überschrieben
         Files.copy(sourceChild.toPath(), destChild.toPath(), StandardCopyOption.REPLACE_EXISTING);
     }
 }
}
//Gibt Klassennamen zurück als ArrayList zurück
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
//Prüft ob die CSV Datei noch verfügbare Zeilen hat
public boolean hasMoreLines() {
    try {
        return csvReader.ready();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}



}