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
import java.awt.Label;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DropMode;

public class KlassenAuswaehlen_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSchlerliste;
	private JTextField txtDurchschnittsnote;
	private JTextField textField;
	private JTextField textField_1;

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
	 */
	public KlassenAuswaehlen_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 446);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Klassenauswahl");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setToolTipText("Hier können Sie die Klasse auswählen");
		lblNewLabel.setBounds(10, 32, 120, 44);
		contentPane.add(lblNewLabel);
		
	
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "5a", "5b", "5c", "6a", "6b", "6c", "7a", "7b", "7c", "8a", "8b", "8c", "9a", "9b", "9c", "10a", "10b", "10c", "11", "12", "13"}));
		comboBox.setBounds(112, 43, 157, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Kurswahl");
		lblNewLabel_1.setBounds(326, 37, 105, 34);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "\tDeutsch", "\tEnglisch", "\tMathe", "\tPhysik", "\tChemie", "\tBiologie", "\tSozialkunde", "\tErdkunde", "\tReligion", "\tInformatik", "\tSport", "\tKunst", "\tMusik"}));
		comboBox_1.setBounds(403, 43, 157, 22);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_2 = new JLabel("Schülerauswahl");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 11, 613, 21);
		contentPane.add(lblNewLabel_2);
		
		txtSchlerliste = new JTextField();
		txtSchlerliste.setForeground(Color.WHITE);
		txtSchlerliste.setBackground(Color.BLACK);
		txtSchlerliste.setText("<Schülerliste>");
		txtSchlerliste.setEditable(false);
		txtSchlerliste.setBounds(10, 135, 266, 261);
		contentPane.add(txtSchlerliste);
		txtSchlerliste.setColumns(10);
		
		txtDurchschnittsnote = new JTextField();
		txtDurchschnittsnote.setBackground(Color.BLACK);
		txtDurchschnittsnote.setForeground(Color.WHITE);
		txtDurchschnittsnote.setEditable(false);
		txtDurchschnittsnote.setText("<Durchschnittsnote>");
		txtDurchschnittsnote.setBounds(326, 135, 86, 261);
		contentPane.add(txtDurchschnittsnote);
		txtDurchschnittsnote.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Durchschnittsnote");
		lblNewLabel_3.setBackground(Color.BLACK);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(326, 102, 105, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("1. Halbjahr");
		lblNewLabel_4.setBounds(451, 106, 60, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("2. Halbjahr");
		lblNewLabel_5.setBounds(570, 106, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBackground(Color.BLACK);
		textField.setForeground(Color.WHITE);
		textField.setEditable(false);
		textField.setText("<note 1.Halbjahr>");
		textField.setBounds(451, 135, 86, 261);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.BLACK);
		textField_1.setForeground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setText("<Note 2.Halbjahr>");
		textField_1.setBounds(537, 135, 86, 261);
		contentPane.add(textField_1);
		textField_1.setColumns(1);
	}
}
