package OneInAll_GUI;

import java.awt.Font;
import school_attributes.Student;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import csv_reader_stuff.DateWriter;
import csv_reader_stuff.Datenleser;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Panel_Schueler extends JPanel {
	private JTextField textFieldName;
	private DateWriter dateWriter = new DateWriter();
	private Datenleser dateReader = new Datenleser();;
	List<String> classNames = dateReader.getClassNames();
	String[] classNamesArray = classNames.toArray(new String[classNames.size()]);
	private JTextField textFieldEmail;
	private DefaultTableModel tableModel;
	private JTable table;
	private String[] columnNames = { "Schüler", "Email" };
	List<Student> students = new ArrayList<Student>();
	JComboBox comboBox = new JComboBox();
	JButton btnSchuelerLschen = new JButton("Schueler Löschen");
	private MouseAdapter deleteButtonMouseAdapter;

	/**
	 * Create the panel.
	 * 
	 * @throws FileNotFoundException
	 */
	public Panel_Schueler() throws FileNotFoundException {

		setBounds(0, 0, 886, 331);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 280, 331);
		add(panel);
		panel.setLayout(null);
		textFieldName = new JTextField();
		textFieldName.setBounds(10, 125, 122, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		JLabel lblNewLabel = new JLabel("Neuen Schüler hinzufügen:");
		lblNewLabel.setBounds(10, 11, 163, 14);
		panel.add(lblNewLabel);
		JLabel lblKlassenname = new JLabel("Vor - und Nachname");
		lblKlassenname.setBounds(10, 100, 136, 14);
		panel.add(lblKlassenname);
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(10, 181, 122, 20);
		panel.add(textFieldEmail);

		comboBox.setModel(new DefaultComboBoxModel(classNamesArray));
		comboBox.setBounds(10, 67, 122, 22);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				classNames = dateReader.getClassNames();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					// Code hier, um die Methode aufzurufen, wenn ein Item ausgewählt wurde
					String selectedClassName = (String) comboBox.getSelectedItem();
					// Methode aufrufen mit dem ausgewählten Klassennamen
					refreshRow(selectedClassName);
				}
			}
		});

		JButton btnNewButton = new JButton("Hinzufügen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedClassName = (String) comboBox.getSelectedItem();
				System.out.println(selectedClassName);
				if (dateWriter.addEntryToCSV(selectedClassName, textFieldName.getText(), textFieldEmail.getText())) {
					refreshRow(selectedClassName);
					JOptionPane.showMessageDialog(null, textFieldName.getText() + " wurde Erfolgreich hinzugefügt",
							"Erfolg", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Ungültige Notenart-ID im vorherigen Eintrag", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnNewButton.setBounds(10, 281, 105, 23);
		panel.add(btnNewButton);

		JLabel lblEmailadresse = new JLabel("E-Mail-Adresse");
		lblEmailadresse.setBounds(10, 156, 136, 14);
		panel.add(lblEmailadresse);

		JLabel lblKlassenname_1 = new JLabel("Klasse");
		lblKlassenname_1.setBounds(10, 42, 136, 14);
		panel.add(lblKlassenname_1);

		panel.add(comboBox);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(281, 0, 595, 331);
		add(panel_1);
		panel_1.setLayout(null);
		JLabel lblKlassenliste = new JLabel("Schülerliste:");
		lblKlassenliste.setBounds(10, 11, 143, 14);
		panel_1.add(lblKlassenliste);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String eingabe = textFieldName.getText();
				if (dateWriter.createClassFolder(eingabe)) {
					List<String> classNames = dateReader.getClassNames();
					classNamesArray = classNames.toArray(new String[classNames.size()]);

					JOptionPane.showMessageDialog(null, "Erfolgreich", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Fehler beim Hinzufügen der Klasse, die Klasse existiert bereits!", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		JButton btnNewButton_1 = new JButton("Schüler entfernen");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 575, 222);
		panel_1.add(scrollPane);
		tableModel = new DefaultTableModel(columnNames, 0);
		{
		}
		table = new JTable(tableModel);
		disableTable(table);
		

		for (Student student : students) {
			addRowArray(student.getName(), student.getEmail());

		}
	

		btnSchuelerLschen.setBounds(10, 280, 164, 23);
		panel_1.add(btnSchuelerLschen);

		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.rowAtPoint(e.getPoint());
		        int column = table.columnAtPoint(e.getPoint());
		        if (row >= 0 && column >= 0) {
		            Object value = table.getValueAt(row, column);
		            String test = value.toString();
		            System.out.println("Angeklickter Wert: " + test);

		            initializeDeleteButton(test);
		        }
		    }
		});

		scrollPane.setViewportView(table);

	}

	private void addRowArray(String name, String eMail) {
		Object[] row = { name, eMail };
		tableModel.addRow(row);
	}

	public void refreshRow(String selectedClassName) {

		students = dateWriter.getStudentList(classNamesArray[comboBox.getSelectedIndex()]);
		resetTable();
		for (Student student : students) {
			addRowArray(student.getName(), student.getEmail());

		}
	}
	public void resetTable() {

		tableModel.setRowCount(0);
	}
	
	
	public void refreshCombo() {
		   classNames = dateReader.getClassNames();
	        classNamesArray = classNames.toArray(new String[classNames.size()]);
	        comboBox.setModel(new DefaultComboBoxModel(classNamesArray));
	      
 }
	
	private void initializeDeleteButton(String test) {
	    if (deleteButtonMouseAdapter != null) {
	        btnSchuelerLschen.removeMouseListener(deleteButtonMouseAdapter);
	    }
	    
	    

	    deleteButtonMouseAdapter = new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            String selectedClassName = (String) comboBox.getSelectedItem();
	            int option = JOptionPane.showConfirmDialog(null, "Möchten Sie den Studenten wirklich löschen?", "Bestätigung", JOptionPane.YES_NO_OPTION);

	            if (option == JOptionPane.YES_OPTION) {
	                // Der Benutzer hat "Ja" ausgewählt, Studenten löschen
	                dateWriter.deleteStudent(selectedClassName, test);
	                refreshRow(selectedClassName);
	            }
	        }
	    };

	    btnSchuelerLschen.addMouseListener(deleteButtonMouseAdapter);
	}
	
	private void disableTable(JTable table) {
		for (int c = 0; c < table.getColumnCount(); c++) {
			Class<?> colClass = table.getColumnClass(c);
			table.setDefaultEditor(colClass, null); // disable editing for all columns
		}

	}

}
