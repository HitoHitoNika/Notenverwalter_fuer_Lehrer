package start;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import csv_reader_stuff.Datenleser;
import gui.KlassenAuswaehlen_Frame;
import gui.darkmode.KlassenAuswaehlen_Frame_Dark;
import gui.selecter.StartWindow;

public class App {

  // Starten des GUIs
  public static void main(String[] args) throws IOException {
    Datenleser configReader = new Datenleser();
    ArrayList<String> config = configReader.getConfig();
    configReader.closeFile();
    EventQueue.invokeLater(new Runnable() {

      // Erstellt einen Thread für das GUI
      @Override
      public void run() {
          try {
            // Objekt des GUIs wird instanziiert
            StartWindow frame = new StartWindow();
            // GUI wird sichtbar gemacht
            frame.setVisible(true);
          } catch (Exception e) {
            e.printStackTrace();
            // Zur einfacheren Nachverfolgung falls Fehler auftreten
            System.out.println("Main()=>Run() Aufruf");
          }
          
        }
      
    });
  }
}
