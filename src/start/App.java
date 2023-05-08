package start;

import java.awt.EventQueue;

import gui.KlassenAuswaehlen_Frame;
import gui.darkmode.NotenuebersichtDark;
import gui.darkmode.*;
import gui.*;

public class App {
	// Starten des GUIs
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			// Erstellt einen Thread für das GUI
			@Override
			public void run() {
				try {
					// Objekt des GUIs wird instanziiert
					//KlassenAuswaehlen_Frame frame = new KlassenAuswaehlen_Frame();
					//KlassenAuswaehlen_Frame_Dark frame = new KlassenAuswaehlen_Frame_Dark();
					Notenuebersicht_Ceejay frame = new Notenuebersicht_Ceejay(0, "BSIT22b");
					// GUI wird sichtbar gemacht
					frame.setVisible(true);
					// Da im Programm mehrere Exceptions passieren könnten, werden diese hier
					// abgefangen
				} catch (Exception e) {
					e.printStackTrace();
					// Zur einfacheren Nachverfolgung falls Fehler auftreten
					System.out.println("Main()=>Run() Aufruf");
				}
			}
		});
	}
}
