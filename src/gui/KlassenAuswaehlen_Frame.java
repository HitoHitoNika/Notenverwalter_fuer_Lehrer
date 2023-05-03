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

<<<<<<< HEAD
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
	// Klassenauswahl Drop-down Menü
	JComboBox klassenAuswahlComboBox = new JComboBox();

	/**
	 * Konstruktor des GUIs
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

		// Soll ausgewählte Klasse des Nutzers abspeichern zur weiteren Verarbeitung
		klassenAuswahlComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				klasse = (String) klassenAuswahlComboBox.getSelectedItem();
				if (klasse != null) {
					updateSchuelerListe();
				}
			}

			private void updateSchuelerListe() {
				// Die Schueler Liste sollte leer sein, damit auch nur die richtigen Schüler
				// angezeigt werden
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
		generateKlassenDropdown();
		klassenAuswahlComboBox.setBounds(112, 43, 157, 22);
		contentPane.add(klassenAuswahlComboBox);

		schuelerListeJList.setBounds(10, 145, 284, 251);
		contentPane.add(schuelerListeJList);

		// Erstellung der Menübar am oberen Rand für Im- und Export
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, -1, 633, 22);
		contentPane.add(menuBar);

		// Erstellung des Import Buttons
		JMenuItem importButton = new JMenuItem("Import");
		importButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/misc/import.png"));
		// Funktionen des Importbuttons werden hier definiert
		importButton.addMouseListener(new MouseAdapter() {
			// Wenn ein Maus klick auf den Button getätigt wird, sollen die Klassen neu
			// eingelesen werden
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

			// Farbwechsel wenn der Mausbutton hovert
			@Override
			public void mouseEntered(MouseEvent e) {
				importButton.setArmed(true);
			}

			// Farbwechsel wenn der Mausbutton nicht mehr hovert
			@Override
			public void mouseExited(MouseEvent e) {
				importButton.setArmed(false);
			}
		});
		// Hinzufügen des Buttons zur MenuBar
		menuBar.add(importButton);

		// Erstellung des Export Buttons
		JMenuItem exportButton = new JMenuItem("Export");
		exportButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/misc/export.png"));
		// Funktionen des Exportbuttons werden hier definiert
		exportButton.addMouseListener(new MouseAdapter() {
			// Wenn ein Maus klick auf den Button getätigt wird, sollen die Klassen in ein
			// Verzeichnis exportiert werden
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Hello World");
			}

			// Farbwechsel wenn der Mausbutton hovert
			@Override
			public void mouseEntered(MouseEvent e) {
				exportButton.setArmed(true);
			}

			// Farbwechsel wenn der Mausbutton nicht mehr hovert
			@Override
			public void mouseExited(MouseEvent e) {
				exportButton.setArmed(false);
			}
		});
		// Hinzufügen des Buttons zur MenuBar
		menuBar.add(exportButton);

		// Label für die Schülerliste
		JLabel schuelerListeLabel = new JLabel("Schülerliste");
		schuelerListeLabel.setBounds(10, 125, 120, 14);
		contentPane.add(schuelerListeLabel);

		// Stellt sicher das nur ein Schüler ausgewählt werden kann
		schuelerListeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Update Button wird erstellt
		JButton updateButton = new JButton("Update");
		updateButton.setToolTipText("Klassenliste nach einem Import updaten");
		// Beim betätigen des Buttons, soll die Klassenliste aktualisiert werden
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateComboBox(klassenAuswahlComboBox);
			}
		});
		updateButton.setBounds(286, 43, 89, 23);
		contentPane.add(updateButton);

		// Funktionen der Schuelerliste werden hier definiert
		schuelerListeJList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				// Speichert den ausgewählten Index und den Wert ab. Index entspricht der
				// SchuelerID, Value dem Namen des Schuelers
				int selectedIndex = schuelerListeJList.getSelectedIndex();
				String selectedValue = (String) schuelerListeJList.getSelectedValue();
				// Ausgabe zu Debugging zwecken
				System.out.println("Index: " + selectedIndex + ", Wert: " + selectedValue);
				// Das neue Fenster wird erstellt
				SchuelerDaten_Frame frame;
				try {
					// Das neue Fenster wird erstellt, die oben gespeicherten Werte werden
					// mitgegeben
					frame = new SchuelerDaten_Frame(selectedIndex, klasse);
					// Das neue Fenster wird sichtbar gemacht
					frame.setVisible(true);
					// Das Ursprungsfenster wird geschlossen
					setVisible(false);
				} catch (IOException e1) {
					// Exception wird ausgeben mit print line für debugging Zwecke
					e1.printStackTrace();
					System.out.println("Aufruf zur Schuelerauswahl");
				}

			}
		});

	}

	// Die Funktion drawList soll die Schülerliste aktualisieren
	private void drawList() {
		// Hier werden die Kern Attribute der Liste festgelegt:
		schuelerListeJList.setModel(new AbstractListModel() {

			// Die Länge der Liste
			@Override
			public int getSize() {
				return schuelerList.size();
			}

			// Der Inhalt der Liste
			@Override
			public Object getElementAt(int index) {
				buffer = schuelerList.get(index);
				splitBuffer = buffer.split(";");
				return splitBuffer[0];
			}
		});
	}

	// Hier wird die Klassenliste aktualisiert
	private void updateComboBox(JComboBox comboBox) {
		// Zunächst werden alle Items aus der ComboBox entfernt
		comboBox.removeAllItems();
		// Dann werden die Klassennamen ebenfalls gelöscht
		klassenNamen.clear();
		// Hier wird mithilfe der Datenleserklasse die Klassennamen abgerufen
		klassenNamen = folderReader.getKlassenNamen();
		// Hier werden die Namen einzeln wieder in die ComboBox übertragen
		for (String item : klassenNamen) {
			comboBox.addItem(item);
		}
		// Zählervariable
		int i = 0;
		// Die vorherige Auswahl soll wiederhergestellt werden. Das erreichen wir in dem
		// wir die Liste solange absuchen bis wir diese wieder gefunden haben.
		while (klasse != klassenNamen.get(i) && i < klassenNamen.size()) {
			// Zähler hoch zählen um weiter durch die Liste zu gehen
			i++;
		}
		// Falls der Ursprungseintrag nicht gefunden werden kann, soll einfach der erste
		// Eintrag genommen werden
		if (klasse != klassenNamen.get(i) && i == klassenNamen.size()) {
			i = 0;
		}
		// ComboBox auf den oben bestimmten Index setzen
		comboBox.setSelectedIndex(i);
	}

	// Die ComboBox für die Klassenauswahl generieren
	private void generateKlassenDropdown() {
		klassenNamen = folderReader.getKlassenNamen();
		klassenAuswahlComboBox.setModel(new DefaultComboBoxModel(klassenNamen.toArray()));
	}

=======
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
  // Klassenauswahl Drop-down Menü
  JComboBox klassenAuswahlComboBox = new JComboBox();
  // Beschriftung des Drop-down
  JLabel klassenAuswahlLabel = new JLabel();
  // Menübar soll Import und Export beinhalten
  JMenuBar menuBar = new JMenuBar();
  // Erstellung des Import Buttons
  JMenuItem importButton = new JMenuItem("Import");
  // Erstellung des Export Buttons
  JMenuItem exportButton = new JMenuItem("Export");
  // Label für die Schülerliste
  JLabel schuelerListeLabel = new JLabel("Schülerliste");
  // Update Button wird erstellt, soll Klassenliste updaten
  JButton updateButton = new JButton("Update");

  /**
   * Konstruktor des GUIs
   *
   * @throws IOException
   */
  public KlassenAuswaehlen_Frame() throws IOException {
    // Erstellen des Fensters
    createWindow();

    //Beschriftung des erstem Drop-down Menüs
    klassenAuswahlLabelInit();

    //Generierung der Klassen Dropdown Menüs
    generateKlassenDropdown();

    //Generierung der SchuelerListe im GUI
    generateSchuelerJList();

    // Erstellung der Menübar am oberen Rand für Im- und Export
    generateMenuBar();

    // Generierung des Import Buttons
    generateImportButton();

    // Generierung des Export Buttons
    generateExportButton();

    //Generierung des SchuelerListeLabel
    generateSchuelerListeLabel();

    //Generierung des Update Buttons
    generateUpdateButton();

  }
  private void generateUpdateButton() {
    updateButton.setToolTipText("Klassenliste nach einem Import updaten");
    // Beim betätigen des Buttons, soll die Klassenliste aktualisiert werden
    updateButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        updateComboBox(klassenAuswahlComboBox);
      }
    });
    updateButton.setBounds(286, 43, 89, 23);
    contentPane.add(updateButton);
  }
  
  private void generateSchuelerListeLabel() {
    schuelerListeLabel.setBounds(10, 125, 120, 14);
    contentPane.add(schuelerListeLabel);
  }
  
  private void generateExportButton(){
    exportButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/misc/export.png"));
    // Funktionen des Exportbuttons werden hier definiert
    exportButton.addMouseListener(new MouseAdapter() {
      // Wenn ein Maus klick auf den Button getätigt wird, sollen die Klassen in ein
      // Verzeichnis exportiert werden
      @Override
      public void mouseClicked(MouseEvent e) {
        System.out.println("Hello World");
      }

      // Farbwechsel wenn der Mausbutton hovert
      @Override
      public void mouseEntered(MouseEvent e) {
        exportButton.setArmed(true);
      }

      // Farbwechsel wenn der Mausbutton nicht mehr hovert
      @Override
      public void mouseExited(MouseEvent e) {
        exportButton.setArmed(false);
      }
    });
    // Hinzufügen des Buttons zur MenuBar
    menuBar.add(exportButton);


  }

  private void generateImportButton() {
    importButton.setIcon(new ImageIcon(System.getProperty("user.dir") + "/misc/import.png"));
    // Funktionen des Importbuttons werden hier definiert
    importButton.addMouseListener(new MouseAdapter() {
      // Wenn ein Maus klick auf den Button getätigt wird, sollen die Klassen neu
      // eingelesen werden
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

      // Farbwechsel wenn der Mausbutton hovert
      @Override
      public void mouseEntered(MouseEvent e) {
        importButton.setArmed(true);
      }

      // Farbwechsel wenn der Mausbutton nicht mehr hovert
      @Override
      public void mouseExited(MouseEvent e) {
        importButton.setArmed(false);
      }
    });
    // Hinzufügen des Buttons zur MenuBar
    menuBar.add(importButton);
  }

  private void generateMenuBar() {
    menuBar.setBounds(0, -1, 633, 22);
    contentPane.add(menuBar);
  }

  private void generateSchuelerJList() {
    schuelerListeJList.setBounds(10, 145, 284, 251);
    contentPane.add(schuelerListeJList);
    // Stellt sicher das nur ein Schüler ausgewählt werden kann
    schuelerListeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    // Funktionen der Schuelerliste werden hier definiert
    schuelerListeJList.addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
        // Speichert den ausgewählten Index und den Wert ab. Index entspricht der
        // SchuelerID, Value dem Namen des Schuelers
        int selectedIndex = schuelerListeJList.getSelectedIndex();
        String selectedValue = (String) schuelerListeJList.getSelectedValue();
        // Ausgabe zu Debugging zwecken
        System.out.println("Index: " + selectedIndex + ", Wert: " + selectedValue);
        // Das neue Fenster wird erstellt
        SchuelerDaten_Frame frame;
        try {
          // Das neue Fenster wird erstellt, die oben gespeicherten Werte werden
          // mitgegeben
          frame = new SchuelerDaten_Frame(selectedIndex, klasse);
          // Das neue Fenster wird sichtbar gemacht
          frame.setVisible(true);
          // Das Ursprungsfenster wird geschlossen
          setVisible(false);
        } catch (IOException e1) {
          // Exception wird ausgeben mit print line für debugging Zwecke
          e1.printStackTrace();
          System.out.println("Aufruf zur Schuelerauswahl");
        }

      }
    });
  }

  private void createWindow() {
    setTitle("Schülerauswahl");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 401, 446);
    contentPane = new JPanel();
    contentPane.setBackground(Color.GRAY);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
  }

  private void klassenAuswahlLabelInit() {
    klassenAuswahlLabel.setText("Klassenauswahl");
    klassenAuswahlLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
    klassenAuswahlLabel.setForeground(new Color(0, 0, 0));
    klassenAuswahlLabel.setBackground(new Color(0, 0, 0));
    klassenAuswahlLabel.setToolTipText("Hier können Sie die Klasse auswählen");
    klassenAuswahlLabel.setBounds(10, 32, 120, 44);
    contentPane.add(klassenAuswahlLabel);
  }

  // Die Funktion drawList soll die Schülerliste aktualisieren
  private void drawList() {
    // Hier werden die Kern Attribute der Liste festgelegt:
    schuelerListeJList.setModel(new AbstractListModel() {

      // Die Länge der Liste
      @Override
      public int getSize() {
        return schuelerList.size();
      }

      // Der Inhalt der Liste
      @Override
      public Object getElementAt(int index) {
        buffer = schuelerList.get(index);
        splitBuffer = buffer.split(";");
        return splitBuffer[0];
      }
    });
  }

  // Hier wird die Klassenliste aktualisiert
  private void updateComboBox(JComboBox comboBox) {
    // Zunächst werden alle Items aus der ComboBox entfernt
    comboBox.removeAllItems();
    // Dann werden die Klassennamen ebenfalls gelöscht
    klassenNamen.clear();
    // Hier wird mithilfe der Datenleserklasse die Klassennamen abgerufen
    klassenNamen = folderReader.getKlassenNamen();
    // Hier werden die Namen einzeln wieder in die ComboBox übertragen
    for (String item : klassenNamen) {
      comboBox.addItem(item);
    }
    // Zählervariable
    int i = 0;
    // Die vorherige Auswahl soll wiederhergestellt werden. Das erreichen wir in dem
    // wir die Liste solange absuchen bis wir diese wieder gefunden haben.
    while (klasse != klassenNamen.get(i) && i < klassenNamen.size()) {
      // Zähler hoch zählen um weiter durch die Liste zu gehen
      i++;
    }
    // Falls der Ursprungseintrag nicht gefunden werden kann, soll einfach der erste
    // Eintrag genommen werden
    if (klasse != klassenNamen.get(i) && i == klassenNamen.size()) {
      i = 0;
    }
    // ComboBox auf den oben bestimmten Index setzen
    comboBox.setSelectedIndex(i);
  }

  // Die ComboBox für die Klassenauswahl generieren
  private void generateKlassenDropdown() {
    klassenNamen = folderReader.getKlassenNamen();
    klassenAuswahlComboBox.setModel(new DefaultComboBoxModel(klassenNamen.toArray()));
    // Soll ausgewählte Klasse des Nutzers abspeichern zur weiteren Verarbeitung
    klassenAuswahlComboBox.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        klasse = (String) klassenAuswahlComboBox.getSelectedItem();
        if (klasse != null) {
          updateSchuelerListe();
        }
      }

      private void updateSchuelerListe() {
        // Die Schueler Liste sollte leer sein, damit auch nur die richtigen Schüler
        // angezeigt werden
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
    klassenAuswahlComboBox.setBounds(112, 43, 157, 22);
    contentPane.add(klassenAuswahlComboBox);
  }

>>>>>>> d9daca4723aef33efb845bd6ffd2a28aab04eb0b
}
