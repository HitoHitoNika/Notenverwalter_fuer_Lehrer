package csv_reader_stuff;

import java.io.*;

//Alle Variablen und Funktionen werden in /dokumentation/Datenleser.md 

public class Datenleser {
    private String projectPath = System.getProperty("user.dir");
    private File csvFile;
    private BufferedReader csvReader ;
    

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



}