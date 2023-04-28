package csv_reader_stuff;

import java.io.*;

//Alle Variablen und Funktionen werden in /dokumentation/Datenleser.md 

public class Datenleser {
    private String projectPath = System.getProperty("user.dir");
    private File csvFile;
    private FileReader fr;
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
public void initReader() throws FileNotFoundException{     
    fr = new FileReader(csvFile);
    csvReader = new BufferedReader(fr);
}

public String getLine() throws IOException{
    return csvReader.readLine();
}

}