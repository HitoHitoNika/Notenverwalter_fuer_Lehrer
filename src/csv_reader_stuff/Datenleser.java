package csv_reader_stuff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JFileChooser;

//Alle Variablen und Funktionen werden in /dokumentation/Datenleser.md 

public class Datenleser {
    private String projectPath = System.getProperty("user.dir");
    private File csvFile;
    private BufferedReader csvReader ;
    private ArrayList<String> klassenNamen = new ArrayList<>();
    

public Datenleser() throws FileNotFoundException{
    
}

public void setFilePath(String fach,String klasse) throws IOException{               
    String path = projectPath+"/CSV_Dateien/"+klasse;
    switch(fach){
    case "Deutsch":                            
        System.out.println(path+"/Deutsch.csv");                     
        csvFile = new File(path+"/Deutsch.csv");      
        break;
    }
}
public void setFilePath(String klasse) {
	csvFile = new File(projectPath+"/CSV_Dateien/"+klasse+"/Schuelerliste.csv");
	
}
public void initReader() throws FileNotFoundException{     
    csvReader = new BufferedReader(new FileReader(csvFile));
}

public String getLine() throws IOException{
    return csvReader.readLine();
}
public void closeFile() throws IOException {
		csvReader.close();
}

public void importKlasse() {
	JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Erlaubt nur die Auswahl von Ordner
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedDir = fileChooser.getSelectedFile();
        File destDir = new File(System.getProperty("user.dir")+"/CSV_Dateien/" + selectedDir.getName());
        try {
            copyDirectory(selectedDir, destDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
//Rekursives kopieren der einzelnen Dateien
public static void copyDirectory(File sourceDir, File destDir) throws IOException {
 if (!destDir.exists()) {
     destDir.mkdir();
 }
 File[] children = sourceDir.listFiles();
 for (File sourceChild : children) {
     String name = sourceChild.getName();
     File destChild = new File(destDir, name);
     if (sourceChild.isDirectory()) {
         copyDirectory(sourceChild, destChild);
     } else {
         Files.copy(sourceChild.toPath(), destChild.toPath(), StandardCopyOption.REPLACE_EXISTING);
     }
 }
}

public ArrayList<String> getKlassenNamen() {
	File directory = new File("CSV_Dateien");
    File[] files = directory.listFiles(File::isDirectory);
    for (File file : files) {
        klassenNamen.add(file.getName());
    }
	return klassenNamen;
}



}