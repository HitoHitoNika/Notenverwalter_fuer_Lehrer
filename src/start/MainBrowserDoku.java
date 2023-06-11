package start;

import java.io.File;
import java.io.IOException;
import java.awt.Desktop;

public class MainBrowserDoku {
    public static void main(String[] args) {
        String htmlFilePath = "doc/index.html";
        
        openWithDefaultBrowser(htmlFilePath);
        
    }
    /**
     * Öffnet die Webdoku im Standard Browser
     * @param htmlFilePath
     */
    public static void openWithDefaultBrowser(String htmlFilePath) {
        File htmlFile = new File(htmlFilePath);
        
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
