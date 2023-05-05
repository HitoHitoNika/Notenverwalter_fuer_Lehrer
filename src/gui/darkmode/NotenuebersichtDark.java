package gui.darkmode;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import csv_reader_stuff.Datenleser;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotenuebersichtDark extends JFrame {

	private JPanel contentPane;
	private JTextField eMailField;
	// Wird benötigt für das Auslesen und ausgeben benötigter Infos aus den CSV
	// Dateien
	Datenleser csvReader;
	String buffer;
	String schuelername;
	String email;
	String klasse;
	private JTextField nameField;
	private JTextField klasseField;
	private JTable faecherTable;
	private JTable tableNotenübersicht;
	private JTable mssTable;
	private String fach;
	private String klausurnoten = "";
	private String eponoten = "";
	private String huenoten = "";

	public NotenuebersichtDark(int selectedIndex, String klasse) throws IOException {
		setSchuelerInfo(selectedIndex, klasse);
		setBackground(Color.DARK_GRAY);
		setTitle("Schüler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setBounds(10, 0, 808, 484);
		contentPane.add(tabbedPane);

		JPanel notenfenster = new JPanel();
		notenfenster.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Notenfenster", null, notenfenster, null);
		notenfenster.setLayout(null);

		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameField.setForeground(Color.WHITE);
		nameField.setBackground(Color.GRAY);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(0, 11, 236, 20);
		notenfenster.add(nameField);
		nameField.setEditable(false);
		nameField.setText(schuelername);
		nameField.setColumns(10);

		klasseField = new JTextField();
		klasseField.setFont(new Font("Tahoma", Font.BOLD, 11));
		klasseField.setForeground(Color.WHITE);
		klasseField.setBackground(Color.GRAY);
		klasseField.setHorizontalAlignment(SwingConstants.CENTER);
		klasseField.setBounds(231, 11, 203, 20);
		notenfenster.add(klasseField);
		klasseField.setEditable(false);
		klasseField.setText(klasse);
		klasseField.setColumns(10);

		eMailField = new JTextField();
		eMailField.setForeground(Color.WHITE);
		eMailField.setFont(new Font("Tahoma", Font.BOLD, 11));
		eMailField.setBackground(Color.GRAY);
		eMailField.setHorizontalAlignment(SwingConstants.CENTER);
		eMailField.setBounds(432, 11, 371, 20);
		notenfenster.add(eMailField);
		eMailField.setEditable(false);
		eMailField.setText(email);
		eMailField.setColumns(10);

		JComboBox fachDropdown = new JComboBox();
		fachDropdown.setFont(new Font("Tahoma", Font.BOLD, 11));
		fachDropdown.setForeground(Color.WHITE);
		fachDropdown.setBackground(Color.GRAY);
		fachDropdown.setBounds(109, 76, 127, 30);
		notenfenster.add(fachDropdown);
		fachDropdown.setModel(new DefaultComboBoxModel(
				new String[] { "--bitte auswählen--", "Deutsch", "Englisch", "Mathe", "Physik", "Chemie", "Biologie",
						"Sozialkunde", "Erdkunde", "Religion", "Informatik", "Sport", "Kunst", "Musik" }));
		fachDropdown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fach = (String) fachDropdown.getSelectedItem();
				if (fach != null) {
					try {
						csvReader.setFilePath(fach, klasse);
						csvReader.initReader();
						updateNotentabelle();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					
				}

			}
			private void updateNotentabelle() throws IOException {
				//Klausur
				int[]notenBuffer=csvReader.getNoten(selectedIndex, 1);
				System.out.println("Klausur "+notenBuffer[0]);
				int note=notenBuffer[0];
				tableNotenübersicht.setValueAt(note,1,0);
				//HÜ
				notenBuffer=csvReader.getNoten(selectedIndex, 2);
				note=notenBuffer[0];
				System.out.println("HÜ "+notenBuffer[0]);
				tableNotenübersicht.setValueAt(note,1,2);
				//EPO
				notenBuffer=csvReader.getNoten(selectedIndex, 3);
				note=notenBuffer[0];
				System.out.println("EPO "+notenBuffer[0]);
				tableNotenübersicht.setValueAt(note,1,1);
				
			}
		});

		JLabel notenLabel = new JLabel("Note:");
		notenLabel.setForeground(Color.LIGHT_GRAY);
		notenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notenLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		notenLabel.setBounds(293, 75, 59, 28);
		notenfenster.add(notenLabel);

		JLabel testformLabel = new JLabel("Testform:");
		testformLabel.setForeground(Color.LIGHT_GRAY);
		testformLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		testformLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testformLabel.setBounds(536, 69, 104, 40);
		notenfenster.add(testformLabel);

		JComboBox testformBox = new JComboBox();
		testformBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		testformBox.setForeground(Color.WHITE);
		testformBox.setBackground(Color.GRAY);
		testformBox.setModel(new DefaultComboBoxModel(
				new String[] { "--Bitte auswählen--", "Klausur (50%)", "Epo(30%)", "HÜ(20%)" }));
		testformBox.setBounds(640, 76, 138, 30);
		notenfenster.add(testformBox);

		JLabel lblNewLabel_1 = new JLabel("Fach:");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(25, 77, 59, 25);
		notenfenster.add(lblNewLabel_1);

		JButton hinzufButton = new JButton("Hinzufügen");
		hinzufButton.setForeground(Color.WHITE);
		hinzufButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		hinzufButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		hinzufButton.setBackground(Color.GRAY);
		hinzufButton.setBounds(672, 199, 89, 23);
		notenfenster.add(hinzufButton);

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setBackground(Color.GRAY);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "--Bitte auswählen--", "1+\t", "1\t", "1-\t", "2+\t",
				"2\t", "2-\t", "3+\t", "3\t", "3-\t", "4+\t", "4\t", "4-\t", "5+\t", "5\t", "5-\t", "6\t" }));
		comboBox.setBounds(362, 76, 127, 30);
		notenfenster.add(comboBox);

		tableNotenübersicht = new JTable();

		tableNotenübersicht.setShowHorizontalLines(false);
		tableNotenübersicht.setBackground(Color.GRAY);
		tableNotenübersicht.setFont(new Font("Tahoma", Font.BOLD, 14));
		tableNotenübersicht.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tableNotenübersicht.setModel(
				new DefaultTableModel(new Object[][] { { "Klausuren", "Epos", "H\u00DCs" }, { klausurnoten, eponoten, huenoten }, },
						new String[] { "New column", "New column", "New column" }) {
					boolean[] columnEditables = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		tableNotenübersicht.setBounds(362, 287, 416, 30);
		notenfenster.add(tableNotenübersicht);

		mssTable = new JTable();
		mssTable.setBackground(Color.GRAY);
		mssTable.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		mssTable.setShowVerticalLines(false);
		mssTable.setFont(new Font("Tahoma", Font.BOLD, 15));
		mssTable.setModel(new DefaultTableModel(
				new Object[][] { { "1+", "=", "15" }, { "1", "=", "14  " }, { "1-", "=", "13" }, { "2+", "=", "12" },
						{ "2", "=", "11" }, { "2-", "=", "10" }, { "3+", "=", "9" }, { "3", "=", "8" },
						{ "3-", "=", "7" }, { "4+", "=", "6" }, { "4", "=", "5" }, { "4-", "=", "4" },
						{ "5+", "=", "3" }, { "5", "=", "2" }, { "5-", "=", "1" }, { "6", "=", "0" }, },
				new String[] { "New column", "New column", "New column" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		mssTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		mssTable.getColumnModel().getColumn(0).setMinWidth(25);
		mssTable.getColumnModel().getColumn(0).setMaxWidth(25);
		mssTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		mssTable.getColumnModel().getColumn(1).setMaxWidth(25);
		mssTable.getColumnModel().getColumn(2).setPreferredWidth(25);
		mssTable.getColumnModel().getColumn(2).setMinWidth(25);
		mssTable.getColumnModel().getColumn(2).setMaxWidth(32);
		mssTable.setBounds(153, 143, 83, 256);
		notenfenster.add(mssTable);

		JPanel notenhistorie = new JPanel();
		notenhistorie.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Notenhistorie", null, notenhistorie, null);
		notenhistorie.setLayout(null);

		faecherTable = new JTable();
		faecherTable.setToolTipText("Fäscher");
		faecherTable.setFillsViewportHeight(true);
		faecherTable.setColumnSelectionAllowed(true);
		faecherTable.setCellSelectionEnabled(true);
		faecherTable.setForeground(Color.WHITE);
		faecherTable.setFont(new Font("Tahoma", Font.BOLD, 16));
		faecherTable.setBackground(Color.LIGHT_GRAY);
		faecherTable.setRowHeight(20);
		faecherTable
				.setModel(new DefaultTableModel(
						new Object[][] { { "Fach1" }, { "" }, { "" }, { "" }, { "" }, { "" }, { null }, { null },
								{ null }, { null }, { null }, { null }, { null }, { null }, { null }, },
						new String[] { "New column" }) {
					boolean[] columnEditables = new boolean[] { false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		faecherTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		faecherTable.setBounds(10, 11, 139, 434);
		notenhistorie.add(faecherTable);

	}

	public void setSchuelerInfo(int selectedIndex, String klasse) throws IOException {
		String[] splitBuffer = { " ", " ", "10" };
		csvReader = new Datenleser();
		csvReader.setFilePath(klasse);
		csvReader.initReader();
		csvReader.getLine();
		this.klasse = klasse;
		while (Integer.parseInt(splitBuffer[2]) != selectedIndex + 1) {
			buffer = csvReader.getLine();
			splitBuffer = buffer.split(";");
		}
		this.schuelername = splitBuffer[0];
		this.email = splitBuffer[1];
		System.out.println("Wert gefunden : " + splitBuffer[2] + " " + splitBuffer[1] + " " + splitBuffer[0] + " ");
		revalidate();
		repaint();

	}
}