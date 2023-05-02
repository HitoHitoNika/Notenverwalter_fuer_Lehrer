package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import csv_reader_stuff.Datenleser;

public class KlassenAuswaehlen_Frame extends JFrame {

  private JPanel contentPane;
  // Variable zum speichern der gewünschten Klasse
  private String klasse;
  // ArrayList zum Abrufen der Schüler
  private ArrayList<String> schuelerList = new ArrayList<>();
  // Wird benötigt für das Auslesen und ausgeben benötigter Infos aus den CSV
  // Dateien
  private Datenleser csvReader;
  private String buffer;
  // Wird benötigt für die Schülerlisten Auslesung da wir nur die Namen wollen
  private String[] splitBuffer;
  // Frühes Deklarieren der Liste um Aktualiersierung zu ermöglichen
  private JList schuelerListeJList = new JList();
  // Liste um alle Klassen die angelegt sind zwischen zu speichern
  ArrayList<String> klassenNamen = new ArrayList<>();
  // Seperater Datenleser welcher nur die Ordnernamen abrufen soll
  private Datenleser folderReader = new Datenleser();

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {

      @Override
      public void run() {
        try {
          KlassenAuswaehlen_Frame frame = new KlassenAuswaehlen_Frame();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Main()=>Run() Aufruf");
        }
      }
    });
  }

  /**
   * Create the frame.
   *
   * @throws IOException
   */
  public KlassenAuswaehlen_Frame() throws IOException {
    // Erstellen des Fensters
    setTitle("Schülerauswahl");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 401, 446);
    contentPane = new JPanel();
    contentPane.setBackground(Color.GRAY);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    // Beschriftung des erstem Drop-down Menüs
    JLabel klassenAuswahlLabel = new JLabel("Klassenauswahl");
    klassenAuswahlLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
    klassenAuswahlLabel.setForeground(new Color(0, 0, 0));
    klassenAuswahlLabel.setBackground(new Color(0, 0, 0));
    klassenAuswahlLabel.setToolTipText("Hier können Sie die Klasse auswählen");
    klassenAuswahlLabel.setBounds(10, 32, 120, 44);
    contentPane.add(klassenAuswahlLabel);

    // Klassenauswahl Drop-down Menü
    JComboBox klassenAuswahlComboBox = new JComboBox();
    klassenAuswahlComboBox.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        klasse = (String) klassenAuswahlComboBox.getSelectedItem();
        getCSV();
      }

      private void getCSV() {
        schuelerList.clear();
        // Ab hier passiert die Generierung der Schülerliste
        // Vorbereiten des csvReaders
        try {
          csvReader = new Datenleser();
          csvReader.setFilePath(klasse);
          csvReader.initReader();
          csvReader.getLine();
          buffer = csvReader.getLine();
          // Auslesen der Liste
          while (buffer != null) {
            schuelerList.add(buffer);
            buffer = csvReader.getLine();
          }
        } catch (IOException e1) {
          e1.printStackTrace();
          System.out.println("Generierung der Schülerliste");
        }
        drawList();
        schuelerListeJList.revalidate();
        schuelerListeJList.repaint();
      }
    });
    klassenNamen = folderReader.getKlassenNamen();
    klassenAuswahlComboBox.setModel(new DefaultComboBoxModel(klassenNamen.toArray()));
    klassenAuswahlComboBox.setBounds(112, 43, 157, 22);
    contentPane.add(klassenAuswahlComboBox);

    schuelerListeJList.setBounds(10, 145, 284, 251);
    contentPane.add(schuelerListeJList);

    // Erstellung der Menübar am oberen Rand für Im- und Export
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBounds(0, -1, 633, 22);
    contentPane.add(menuBar);

    JMenuItem importButton = new JMenuItem("Import");
    importButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/misc/import.png"));
    importButton.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          Datenleser csvReader = new Datenleser();
          csvReader.importKlasse();
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
          System.out.println("Import");
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        importButton.setArmed(true);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        importButton.setArmed(false);
      }
    });
    menuBar.add(importButton);

    JMenuItem exportButton = new JMenuItem("Export");
    exportButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/misc/export.png"));
    exportButton.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent e) {
        System.out.println("Hello World");
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        exportButton.setArmed(true);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        exportButton.setArmed(false);
      }
    });
    menuBar.add(exportButton);

    JLabel schuelerListeLabel = new JLabel("Schülerliste");
    schuelerListeLabel.setBounds(10, 125, 120, 14);
    contentPane.add(schuelerListeLabel);

    schuelerListeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    JButton updateButton = new JButton("Update");
    updateButton.setToolTipText("Klassenliste nach einem Import updaten");
    updateButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        updateComboBox(klassenAuswahlComboBox);
      }
    });
    updateButton.setBounds(286, 43, 89, 23);
    contentPane.add(updateButton);

    schuelerListeJList.addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
        int selectedIndex = schuelerListeJList.getSelectedIndex();
        String selectedValue = (String) schuelerListeJList.getSelectedValue();
        System.out.println("Index: " + selectedIndex + ", Wert: " + selectedValue);

        setVisible(false);
        SchuelerDaten_Frame frame;
        try {
          frame = new SchuelerDaten_Frame(selectedIndex, klasse);
          frame.setVisible(true);
        } catch (IOException e1) {
          e1.printStackTrace();
          System.out.println("Aufruf zur Schuelerauswahl");
        }

      }
    });

  }

  private void drawList() {
    // Übertragen der Liste
    schuelerListeJList.setModel(new AbstractListModel() {

      @Override
      public int getSize() {
        return schuelerList.size();
      }

      @Override
      public Object getElementAt(int index) {
        buffer = schuelerList.get(index);
        splitBuffer = buffer.split(";");
        return splitBuffer[0];
      }
    });
  }

  private void updateComboBox(JComboBox comboBox) {
    comboBox.removeAllItems();
    klassenNamen.clear();
    klassenNamen = folderReader.getKlassenNamen();
    int i = 0;
    for (String item : klassenNamen) {
      comboBox.addItem(item);
    }
    while (klasse != klassenNamen.get(i)) {
      i++;
    }
    comboBox.setSelectedIndex(i);
    try {
      csvReader.closeFile();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Close File nach update der Combo Box");
    }

  }
}
