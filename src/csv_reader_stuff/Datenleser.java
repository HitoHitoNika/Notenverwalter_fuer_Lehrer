package csv_reader_stuff;

import java.io.*;

public class Datenleser {
    private String projectPath = System.getProperty("user.dir");
    private File file;
    private FileReader fr;
    private BufferedReader csvReader ;
    

public Datenleser() throws FileNotFoundException{
    
}

public void setFilePath(String Fach) throws IOException{               //Bei Auswahl auf ein Fach soll dieses hier hin übergeben werden
    switch(Fach){
    case "Deutsch":                                                 
        file = new File(projectPath+"/CSV_Dateien/Deutsch.csv");      
        break;
    }
}
public void initReader() throws FileNotFoundException{     
    fr = new FileReader(file);
    csvReader = new BufferedReader(fr);
}

public String getLine() throws IOException{
    return csvReader.readLine();
}

}