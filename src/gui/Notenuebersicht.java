package gui;

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

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Notenuebersicht extends JFrame {

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
	private JTable table_1;
	private JTable table_2;

	public Notenuebersicht(int selectedIndex, String klasse) throws IOException {
		setSchuelerInfo(selectedIndex, klasse);
		setBackground(new Color(255, 255, 255));
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
		tabbedPane.setBounds(10, 0, 808, 484);
		contentPane.add(tabbedPane);
		
				JPanel notenfenster = new JPanel();
				notenfenster.setBackground(Color.WHITE);
				tabbedPane.addTab("Notenfenster", null, notenfenster, null);
				notenfenster.setLayout(null);
				
						nameField = new JTextField();
						nameField.setHorizontalAlignment(SwingConstants.CENTER);
						nameField.setBounds(0, 11, 236, 20);
						notenfenster.add(nameField);
						nameField.setEditable(false);
						nameField.setText(schuelername);
						nameField.setColumns(10);
						
								klasseField = new JTextField();
								klasseField.setHorizontalAlignment(SwingConstants.CENTER);
								klasseField.setBounds(231, 11, 203, 20);
								notenfenster.add(klasseField);
								klasseField.setEditable(false);
								klasseField.setText(klasse);
								klasseField.setColumns(10);
								
										eMailField = new JTextField();
										eMailField.setHorizontalAlignment(SwingConstants.CENTER);
										eMailField.setBounds(432, 11, 346, 20);
										notenfenster.add(eMailField);
										eMailField.setEditable(false);
										eMailField.setText(email);
										eMailField.setColumns(10);
										
												JComboBox fachDropdown = new JComboBox();
												fachDropdown.setBackground(Color.LIGHT_GRAY);
												fachDropdown.setBounds(109, 76, 127, 30);
												notenfenster.add(fachDropdown);
												fachDropdown.setModel(new DefaultComboBoxModel(
														new String[] { "--bitte auswählen--", "Deutsch", "Englisch", "Mathe", "Physik", "Chemie", "Biologie",
																"Sozialkunde", "Erdkunde", "Religion", "Informatik", "Sport", "Kunst", "Musik" }));
												
												JLabel notenLabel = new JLabel("Note:");
												notenLabel.setHorizontalAlignment(SwingConstants.CENTER);
												notenLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
												notenLabel.setBounds(293, 75, 59, 28);
												notenfenster.add(notenLabel);
												
												JLabel testformLabel = new JLabel("Testform:");
												testformLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
												testformLabel.setHorizontalAlignment(SwingConstants.CENTER);
												testformLabel.setBounds(536, 69, 104, 40);
												notenfenster.add(testformLabel);
												
												JComboBox testformBox = new JComboBox();
												testformBox.setBackground(Color.LIGHT_GRAY);
												testformBox.setModel(new DefaultComboBoxModel(new String[] {"--Bitte auswählen--", "Klausur (50%)", "Epo(30%)", "HÜ(20%)"}));
												testformBox.setBounds(640, 76, 138, 30);
												notenfenster.add(testformBox);
												
												JLabel lblNewLabel_1 = new JLabel("Fach:");
												lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
												lblNewLabel_1.setBounds(25, 77, 59, 25);
												notenfenster.add(lblNewLabel_1);
												
												JButton hinzufButton = new JButton("Hinzufügen");
												hinzufButton.setBackground(Color.LIGHT_GRAY);
												hinzufButton.setBounds(672, 199, 89, 23);
												notenfenster.add(hinzufButton);
												
												JComboBox comboBox = new JComboBox();
												comboBox.setBackground(Color.LIGHT_GRAY);
												comboBox.setModel(new DefaultComboBoxModel(new String[] {"--Bitte auswählen--", "1+\t", "1\t", "1-\t", "2+\t", "2\t", "2-\t", "3+\t", "3\t", "3-\t", "4+\t", "4\t", "4-\t", "5+\t", "5\t", "5-\t", "6\t"}));
												comboBox.setBounds(362, 76, 127, 30);
												notenfenster.add(comboBox);
												
												table_2 = new JTable();
												table_2.setShowHorizontalLines(false);
												table_2.setBackground(Color.LIGHT_GRAY);
												table_2.setFont(new Font("Tahoma", Font.BOLD, 14));
												table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
												table_2.setModel(new DefaultTableModel(
													new Object[][] {
														{"Klausuren", "Epos", "H\u00DCs"},
														{null, null, null},
													},
													new String[] {
														"New column", "New column", "New column"
													}
												) {
													boolean[] columnEditables = new boolean[] {
														false, false, false
													};
													public boolean isCellEditable(int row, int column) {
														return columnEditables[column];
													}
												});
												table_2.setBounds(362, 287, 416, 30);
												notenfenster.add(table_2);

		JPanel notenhistorie = new JPanel();
		notenhistorie.setBackground(Color.WHITE);
		tabbedPane.addTab("Notenhistorie", null, notenhistorie, null);
		notenhistorie.setLayout(null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Fach1"},
				{""},
				{""},
				{""},
				{""},
				{""},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBounds(10, 11, 139, 434);
		notenhistorie.add(table_1);

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