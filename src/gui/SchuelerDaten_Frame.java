package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class SchuelerDaten_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField txtTest;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtFach;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchuelerDaten_Frame frame = new SchuelerDaten_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SchuelerDaten_Frame() {
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
		txtTest.setText("<Hier Schülername einfügen>");
		txtTest.setBounds(0, 11, 192, 20);
		contentPane.add(txtTest);
		txtTest.setColumns(10);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("<Hier Klasse einfg>");
		textField.setBounds(202, 11, 182, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("<Hier E-Mail einfügen>");
		textField_1.setBounds(394, 11, 163, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		txtFach = new JTextField();
		txtFach.setText("Fach:");
		txtFach.setBounds(0, 42, 86, 20);
		contentPane.add(txtFach);
		txtFach.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--bitte auswählen--", "Deutsch", "Englisch", "Mathe", "Physik", "Chemie", "Biologie", "Sozialkunde", "Erdkunde", "Religion", "Informatik", "Sport", "Kunst", "Musik"}));
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
}
