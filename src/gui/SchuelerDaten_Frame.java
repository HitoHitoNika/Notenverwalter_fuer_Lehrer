package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import csv_reader_stuff.Datenleser;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class SchuelerDaten_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTest;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtFach;
	// Wird benötigt für das Auslesen und ausgeben benötigter Infos aus den CSV
	// Dateien
	Datenleser csvReader;
	String buffer;
	String schuelername;
	String email;
	String klasse;

	/**
	 * Create the frame.
	 * 
	 * @param klasse
	 * @param selectedIndex
	 * @throws IOException
	 */
	public SchuelerDaten_Frame(int selectedIndex, String klasse) throws IOException {
		setSchuelerInfo(selectedIndex, klasse);
		setBackground(new Color(255, 255, 255));
		setTitle("Schüler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtTest = new JTextField();
		txtTest.setEditable(false);
		txtTest.setText(schuelername);
		txtTest.setBounds(0, 11, 192, 20);
		contentPane.add(txtTest);
		txtTest.setColumns(10);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setText(klasse);
		textField.setBounds(202, 11, 182, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText(email);
		textField_1.setBounds(394, 11, 163, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		txtFach = new JTextField();
		txtFach.setText("Fach:");
		txtFach.setBounds(0, 42, 86, 20);
		contentPane.add(txtFach);
		txtFach.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "--bitte auswählen--", "Deutsch", "Englisch", "Mathe", "Physik", "Chemie", "Biologie",
						"Sozialkunde", "Erdkunde", "Religion", "Informatik", "Sport", "Kunst", "Musik" }));
		comboBox.setBounds(96, 42, 127, 22);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Noten anzeigen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(233, 42, 151, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Alle Noten anzeigen");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(30, 86, 490, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Note hinzufügen");
		btnNewButton_2.setBounds(394, 42, 163, 23);
		contentPane.add(btnNewButton_2);
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