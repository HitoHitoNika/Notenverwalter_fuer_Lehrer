package editGUI;

import java.awt.EventQueue;

import csv_reader_stuff.DateWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AddNewStudentWithEmail_Frame extends JFrame {
	
	DateWriter writer = new DateWriter();
	private JPanel contentPane;
	private JTextField txtvornahme;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewStudentWithEmail_Frame frame = new AddNewStudentWithEmail_Frame("Ceejay");
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
	public AddNewStudentWithEmail_Frame(String klasse) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 88, 128);
		contentPane.add(panel);
		
		txtvornahme = new JTextField();
		txtvornahme.setText("--Vorname--");
		txtvornahme.setBounds(98, 39, 133, 20);
		contentPane.add(txtvornahme);
		txtvornahme.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Neuen Schüler hinzufügen");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String eingabe = txtvornahme.getText();
				System.out.println(eingabe);
				if (writer.addEntryToCSV(klasse, txtvornahme.getText(), txtemail.getText())) {
					JOptionPane.showMessageDialog(null, txtvornahme.getText() + " wurde Erfolgreich hinzugefügt", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Ungültige Notenart-ID im vorherigen Eintrag", "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(242, 38, 182, 23);
		contentPane.add(btnNewButton);
		
		txtemail = new JTextField();
		txtemail.setText("--Email--");
		txtemail.setColumns(10);
		txtemail.setBounds(98, 68, 133, 20);
		contentPane.add(txtemail);
	}
}
