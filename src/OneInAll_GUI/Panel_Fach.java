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

public class Panel_Fach extends JPanel {
	private JTextField textFieldName;
	private DateWriter dateWriter = new DateWriter();
	private Datenleser dateReader = new Datenleser();;
	List<String> classNames = dateReader.getClassNames();
	String[] classNamesArray = classNames.toArray(new String[classNames.size()]);
	private DefaultTableModel tableModel;
	private String[] columnNames = { "Schüler", "Email" };
	List<Student> students = new ArrayList<Student>();
	JComboBox<String> comboBox = new JComboBox();
	JButton btnSchuelerLschen = new JButton("Fach löschen");
	private MouseAdapter deleteButtonMouseAdapter;
	

	/**
	 * Create the panel.
	 * 
	 * @throws FileNotFoundException
	 */
	public Panel_Fach() throws FileNotFoundException {

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
		JLabel lblNewLabel = new JLabel("Neues Fach hinzufügen:");
		lblNewLabel.setBounds(10, 11, 163, 14);
		panel.add(lblNewLabel);
		JLabel lblKlassenname = new JLabel("Fächername");
		lblKlassenname.setBounds(10, 100, 136, 14);
		panel.add(lblKlassenname);
		JList list = new JList();
		String selectedClassName = (String) comboBox.getSelectedItem();
		List<String> subjectName = dateReader.getSubjectsOfClass(selectedClassName);
		String[] subjectNameArrays = subjectName.toArray(new String[classNames.size()]);
		refreshRow(classNamesArray[0], list);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(281, 0, 595, 331);
		add(panel_1);
		
		
		
		list.setModel(new AbstractListModel() {
			String[] values = subjectNameArrays;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 49, 575, 223);
		panel_1.add(list);
		
		    classNames = dateReader.getClassNames();
	        classNamesArray = classNames.toArray(new String[classNames.size()]);
	        comboBox = new JComboBox<>();
	        comboBox.setModel(new DefaultComboBoxModel(classNamesArray));
	        comboBox.setBounds(10, 67, 122, 22);
	        comboBox.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent event) {
	                if (event.getStateChange() == ItemEvent.SELECTED) {
	                    String selectedClassName = (String) comboBox.getSelectedItem();
	                    refreshRow(selectedClassName, list);
	                }
	            }
	        });

	        
	        
	        
		JButton btnNewButton = new JButton("Hinzufügen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedClassName = (String) comboBox.getSelectedItem();
				String eingabe = textFieldName.getText();
				System.out.println(eingabe);
				if (dateWriter.createSubjectCSVFile(eingabe, selectedClassName) == true) {
					JOptionPane.showMessageDialog(null, eingabe + " wurde Erfolgreich hinzugefügt", "Erfolg",
							JOptionPane.INFORMATION_MESSAGE);
					refreshRow(selectedClassName, list);
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Das Fach " + eingabe + " ist bereits vorhanden!", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
			

		btnNewButton.setBounds(10, 281, 105, 23);
		panel.add(btnNewButton);

		JLabel lblKlassenname_1 = new JLabel("Klasse");
		lblKlassenname_1.setBounds(10, 42, 136, 14);
		panel.add(lblKlassenname_1);

		panel.add(comboBox);
		
		panel_1.setLayout(null);
		JLabel lblKlassenliste = new JLabel("Fachliste");
		lblKlassenliste.setBounds(10, 11, 70, 14);
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
		btnSchuelerLschen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				   String selectedClassName = (String) comboBox.getSelectedItem();
			        List<String> subjectName = dateReader.getSubjectsOfClass(selectedClassName);
			        String[] subjectNameArray = subjectName.toArray(new String[subjectName.size()]);
			        
			        // Bestätigungsdialog anzeigen
			        int option = JOptionPane.showConfirmDialog(null, "Bist du sicher, dass du das Fach löschen möchtest?", "Fach löschen", JOptionPane.YES_NO_OPTION);
			        
			        if (option == JOptionPane.YES_OPTION) {
			            dateWriter.deleteSubject(subjectNameArray[list.getSelectedIndex()], selectedClassName);
			            refreshRow(selectedClassName, list);
			        }
			    }
			});
		
		btnSchuelerLschen.setBounds(10, 280, 164, 23);
		panel_1.add(btnSchuelerLschen);
		
		
	

	}

	private void addRowArray(String name, String eMail) {
		Object[] row = { name, eMail };
		tableModel.addRow(row);
	}

	public void refreshRow(String selectedClassName, JList list) {
        List<String> subjectName = dateReader.getSubjectsOfClass(selectedClassName);
        String[] subjectNameArray = subjectName.toArray(new String[subjectName.size()]);
        list.setListData(subjectNameArray);
        revalidate();
        repaint();
    }
	
	public void refreshCombo() {
		   classNames = dateReader.getClassNames();
	        classNamesArray = classNames.toArray(new String[classNames.size()]);
	        comboBox.setModel(new DefaultComboBoxModel(classNamesArray));
	      
    }
	
	
	

	
	
}