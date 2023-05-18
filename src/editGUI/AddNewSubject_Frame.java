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

public class AddNewSubject_Frame extends JFrame {

	DateWriter writer = new DateWriter();
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewSubject_Frame frame = new AddNewSubject_Frame("Ceejay");
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
	public AddNewSubject_Frame(String klasse) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 88, 128);
		contentPane.add(panel);

		textField = new JTextField();
		textField.setBounds(98, 39, 133, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Neues Fach Hinzufügen");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String eingabe = textField.getText();
				System.out.println(eingabe);
				if (writer.createSubjectCSVFile(eingabe, klasse) == true) {
					JOptionPane.showMessageDialog(null, eingabe + " wurde Erfolgreich hinzugefügt", "Erfolg",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Das Fach " + eingabe + " ist bereits vorhanden!", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(242, 38, 182, 23);
		contentPane.add(btnNewButton);
	}
}
