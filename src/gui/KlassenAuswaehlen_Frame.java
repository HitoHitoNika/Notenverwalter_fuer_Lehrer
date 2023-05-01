package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JTextField;

import csv_reader_stuff.Datenleser;

import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KlassenAuswaehlen_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField txtDurchschnittsnote;
	private JTextField textField;
	private JTextField textField_1;
	// Variable zum speichern der gewünschten Klasse
	private String klasse;
	// Variable zum speichern des gewünschten Kurses
	private String kurs;
	// ArrayList zum Abrufen der Schüler
	private ArrayList schuelerList = new ArrayList<String>();
	// Wird benötigt für das Auslesen und ausgeben benötigter Infos aus den CSV
	// Dateien
	private Datenleser csvReader;
	private String buffer;
	// Wird benötigt für die Schülerlisten Auslesung da wir nur die Namen wollen
	private String[] splitBuffer;
	// Frühes Deklarieren der Liste um Aktualiersierung zu ermöglichen
	private JList schuelerListeJList = new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KlassenAuswaehlen_Frame frame = new KlassenAuswaehlen_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
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
			public void actionPerformed(ActionEvent e) {
				Object selected = klassenAuswahlComboBox.getSelectedItem();
				if (selected.toString().equals("BSIT22a")) {
					klasse = "BSIT22a";
					getCSV();
				} else if (selected.toString().equals("BSIT22b")) {
					klasse = "BSIT22b";
					schuelerList.clear();
					getCSV();
				}
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
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				drawList();
				schuelerListeJList.revalidate();
				schuelerListeJList.repaint();
			}
		});
		klassenAuswahlComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "--bitte auswählen--", "BSIT22a", "BSIT22b" }));
		klassenAuswahlComboBox.setBounds(112, 43, 157, 22);
		contentPane.add(klassenAuswahlComboBox);

		// Blanko Eintrag in Liste, da Liste erst nach Kurswahl verfügbar ist
		schuelerList.add("Bitte Klasse und Kurs wählen;s");

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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			public void mouseEntered(MouseEvent e) {
				importButton.setArmed(true);
			}

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

			public void mouseEntered(MouseEvent e) {
				exportButton.setArmed(true);
			}

			public void mouseExited(MouseEvent e) {
				exportButton.setArmed(false);
			}
		});
		menuBar.add(exportButton);

		JLabel schuelerListeLabel = new JLabel("Schülerliste");
		schuelerListeLabel.setBounds(10, 125, 120, 14);
		contentPane.add(schuelerListeLabel);

		schuelerListeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	private void drawList() {
		// Übertragen der Liste
		schuelerListeJList.setModel(new AbstractListModel() {
			public int getSize() {
				return schuelerList.size();
			}

			public Object getElementAt(int index) {
				buffer = (String) schuelerList.get(index);
				splitBuffer = buffer.split(";");
				return splitBuffer[0];
			}
		});
	}
}
