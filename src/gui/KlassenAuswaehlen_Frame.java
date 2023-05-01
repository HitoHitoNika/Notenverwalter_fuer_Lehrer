package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Label;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.border.LineBorder;

import csv_reader_stuff.Datenleser;

import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
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
	//Variable zum speichern der gewünschten Klasse
	private String klasse;
	//Variable zum speichern des gewünschten Kurses
	private String kurs;
	//ArrayList zum Abrufen der Schüler
	ArrayList schuelerList = new ArrayList<String>();
	//Wird benötigt für das Auslesen und ausgeben benötigter Infos aus den CSV Dateien
	Datenleser csvReader;	
	String buffer;
	//Wird benötigt für die Schülerlisten Auslesung da wir nur die Namen wollen
	String[] splitBuffer;
	//Frühes Deklarieren der Liste um Aktualiersierung zu ermöglichen
	JList list = new JList();

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
	 * @throws IOException
	 */
	public KlassenAuswaehlen_Frame() throws IOException {
		//Erstellen des Fensters
		setTitle("Schülerauswahl");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 446);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Beschriftung des erstem Drop-down Menüs
		JLabel lblNewLabel = new JLabel("Klassenauswahl");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setToolTipText("Hier können Sie die Klasse auswählen");
		lblNewLabel.setBounds(10, 32, 120, 44);
		contentPane.add(lblNewLabel);
		
	
		//Klassenauswahl Drop-down Menü
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selected = comboBox.getSelectedItem();
		        if (selected.toString().equals("BSIT22a")) {
		            klasse="BSIT22a";
		            getCSV();
		        } else if (selected.toString().equals("BSIT22b")) {
		        	klasse="BSIT22b";
		        	 schuelerList.clear();
			         getCSV();
			}
			}
			private void getCSV() {
				schuelerList.clear();
	            // Ab hier passiert die Generierung der Schülerliste
	    		//Vorbereiten des csvReaders
	    		try {
					csvReader= new Datenleser();
					csvReader.setFilePath(klasse);
		    		csvReader.initReader();
		    		csvReader.getLine();
		    		buffer = csvReader.getLine();
		    		//Auslesen der Liste
		    		while(buffer!=null){
		    			schuelerList.add(buffer);
		    			buffer=csvReader.getLine();
		    		}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		drawList();
	            list.revalidate();
	            list.repaint();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--bitte auswählen--", "BSIT22a", "BSIT22b"}));
		comboBox.setBounds(112, 43, 157, 22);
		contentPane.add(comboBox);
		
		


		//Beschriftung des zweiten Drop-down Menüs
		JLabel lblNewLabel_1 = new JLabel("Kurswahl");
		lblNewLabel_1.setBounds(326, 37, 105, 34);
		contentPane.add(lblNewLabel_1);
		
		//Kursauswahl Drop-down Menü
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selected = comboBox_1.getSelectedItem();
		        if (selected.toString().equals("Lernfeld 1")) {
		            kurs="Lernfeld 1";		       
		        } else if (selected.toString().equals("Lernfeld 2")) {
		        	kurs="Lernfeld 2";		        
		        } else if (selected.toString().equals("Lernfeld 3")) {
		        	kurs="Lernfeld 3";		        	
		        } else if (selected.toString().equals("Lernfeld 4")) {
		        	kurs="Lernfeld 4";		        
		        } else if (selected.toString().equals("Lernfeld 5")) {
		        	kurs="Lernfeld 5";		        	
		        }
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"--bitte auswählen--", "Lernfeld 1", "Lernfeld 2", "Lernfeld 3", "Lernfeld 4", "Lernfeld 5"}));
		comboBox_1.setBounds(403, 43, 157, 22);
		contentPane.add(comboBox_1);
		
		

		//Blanko Eintrag in Liste, da Liste erst nach Kurswahl verfügbar ist
		schuelerList.add("Bitte Klasse und Kurs wählen;s");	
		
		list.setBounds(10, 145, 284, 251);
		contentPane.add(list);
		

		//Erstellung der Menübar am oberen Rand für Im- und Export
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, -1, 633, 22);
		contentPane.add(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Import");
		mntmNewMenuItem.setIcon(new ImageIcon(System.getProperty("user.dir")+"/misc/import.png"));
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Hello World");
			}
			public void mouseEntered(MouseEvent e) {
				mntmNewMenuItem.setArmed(true);
			    }
			public void mouseExited(MouseEvent e) {
				mntmNewMenuItem.setArmed(false);
			    }
		});
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Export");
		mntmNewMenuItem_1.setIcon(new ImageIcon(System.getProperty("user.dir")+"/misc/export.png"));
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Hello World");
			}
			public void mouseEntered(MouseEvent e) {
				mntmNewMenuItem_1.setArmed(true);
			    }
			public void mouseExited(MouseEvent e) {
				mntmNewMenuItem_1.setArmed(false);
			    }
		});
		menuBar.add(mntmNewMenuItem_1);
		
		JLabel lblNewLabel_2 = new JLabel("Schülerliste");
		lblNewLabel_2.setBounds(10, 120, 120, 14);
		contentPane.add(lblNewLabel_2);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        int selectedIndex = list.getSelectedIndex();
		        String selectedValue = (String) list.getSelectedValue();
		        System.out.println("Index: " + selectedIndex + ", Wert: " + selectedValue);
		     
		        	setVisible(false);
					SchuelerDaten_Frame frame;
					try {
						frame = new SchuelerDaten_Frame(selectedIndex,klasse);
						frame.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
		    }
		});
		
		
	}
	
	private void drawList() {
		//Übertragen der Liste
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return schuelerList.size();
			}
			public Object getElementAt(int index) {
				buffer=(String) schuelerList.get(index);
				splitBuffer = buffer.split(";");
				return splitBuffer[0];
			}
		});
	}
}
