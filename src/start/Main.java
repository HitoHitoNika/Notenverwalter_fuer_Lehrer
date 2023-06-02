package start;

import java.awt.EventQueue;

import OneInAll_GUI.Dashboard_Gui;


/**
 * Der Sinn der Main-Klasse ist das auslagern von dem intialen Aufruf des Programms
 * Hier wird ein Thread für das GUI erstellt und dieses erstellt und sichtbar gemacht
 */
public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard_Gui frame = new Dashboard_Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
}
