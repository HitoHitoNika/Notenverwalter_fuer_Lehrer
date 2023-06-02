package OneInAll_GUI;

import school_attributes.Student;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import csv_reader_stuff.DateWriter;
import csv_reader_stuff.Datenleser;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

public class Panel_Notenvergabe_Screen extends JPanel {
	private DateWriter dateWriter = new DateWriter();
	private Datenleser dateReader = new Datenleser();;
	List<String> classNames = dateReader.getClassNames();
	String[] classNamesArray = classNames.toArray(new String[classNames.size()]);
	private DefaultTableModel tableModel;
	private String[] columnNames = { "Schüler", "Email" };
	List<Student> students = new ArrayList<Student>();
	JComboBox<String> comboBox = new JComboBox();
	private MouseAdapter deleteButtonMouseAdapter;
	JPanel panel = new JPanel();
	private JTable table;
	private JTextField searchTextField;
	  private TableRowSorter<DefaultTableModel> tableRowSorter;
	  Student selectedStudent;
	  
	 
	  
	

	/**
	 * Create the panel.
	 * 
	 * @throws FileNotFoundException
	 */
	public Panel_Notenvergabe_Screen() throws FileNotFoundException {

		setBounds(0, 0, 886, 331);
		setLayout(null);
		 
		
		JButton btnNewButton = new JButton("Noten bearbeiten");
		
		btnNewButton.setBounds(246, 241, 483, 23);
		btnNewButton.setVisible(false);
		panel.add(btnNewButton);
		
		panel.setBounds(0, 0, 886, 331);
		add(panel);
		panel.setLayout(null);
		String selectedClassName = (String) comboBox.getSelectedItem();
		List<String> subjectName = dateReader.getSubjectsOfClass(selectedClassName);
		String[] subjectNameArrays = subjectName.toArray(new String[classNames.size()]);
		JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 886, 331);
        add(panel_1);
        panel_1.setVisible(false);
		    classNames = dateReader.getClassNames();
	        classNamesArray = classNames.toArray(new String[classNames.size()]);
	        comboBox = new JComboBox<>();
	        comboBox.setModel(new DefaultComboBoxModel(classNamesArray));
	        comboBox.setBounds(246, 117, 122, 22);
	        comboBox.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent event) {
	                if (event.getStateChange() == ItemEvent.SELECTED) {
	                	String selectedClassName = (String) comboBox.getSelectedItem();
						// Methode aufrufen mit dem ausgewählten Klassennamen
						refreshRow(selectedClassName);
						
	                }
	            }
	        });

		JLabel lblKlassenname_1 = new JLabel("Klasse");
		lblKlassenname_1.setBounds(246, 92, 136, 14);
		panel.add(lblKlassenname_1);

		panel.add(comboBox);
		
	
		
		
		
		
		
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(392, 91, 337, 136);
		panel.add(scrollPane);
		tableModel = new DefaultTableModel(columnNames, 0);
		{
		}
		table = new JTable(tableModel);
		disableTable(table);
		if (students.isEmpty()) {
			
		}
		else {
			students = dateWriter.getStudentList(classNamesArray[comboBox.getSelectedIndex()]);
		}
		

		for (Student student : students) {
			addRowArray(student.getName(), student.getEmail());

		}

		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.rowAtPoint(e.getPoint());
		        int column = table.columnAtPoint(e.getPoint());
		        if (row >= 0 && column >= 0) {
		            Object value = table.getValueAt(row, column);
		            String test = value.toString();
		            System.out.println("Angeklickter Wert: " + test);
		            btnNewButton.setVisible(true);

		            for (Student student : students) {
		                if (student.getName().equals(test)) {
		                    // Speichere den ausgewählten Studenten
		                    selectedStudent = student;
		                    
		                    break; // Exit the loop since the student has been found
		                }
		            }
		        }
		    }
		});
		
		// Registriere den MouseListener nur einmal für btnNewButton außerhalb der Tabelle
		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String selectedClassName = (String) comboBox.getSelectedItem();
		        try {
		            Panel_Notenvergabe_MainScreen editScreen = new Panel_Notenvergabe_MainScreen(selectedStudent , selectedClassName);
		            panel.setVisible(false);
		            panel_1.add(editScreen);
		            panel_1.setVisible(true);    
		            editScreen.setVisible(true);
		            
		        } catch (NumberFormatException e1) {
		            e1.printStackTrace();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
//EXTRA
		tableRowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(tableRowSorter);
		
		scrollPane.setViewportView(table);
		 // Suchleiste hinzufügen
        searchTextField = new JTextField();
        searchTextField.setBounds(392, 51, 200, 30);
        panel.add(searchTextField);
        
       

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchTable();
            }
        });
    }
	//EXTRA
	
	
	
    private void searchTable() {
        String searchText = searchTextField.getText();
        if (searchText.trim().length() == 0) {
            tableRowSorter.setRowFilter(null);
        } else {
            tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

	
	public void createCombo(String selectedClassName) {
		JLabel lblKlassenname_1_1 = new JLabel("Fach");
		lblKlassenname_1_1.setBounds(480, 114, 136, 14);
		lblKlassenname_1_1.revalidate();
		lblKlassenname_1_1.repaint();
		panel.add(lblKlassenname_1_1);
		
		students = dateWriter.getStudentList(selectedClassName);
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		String [] studentsArray = new String[students.size()];
		
		for (int i = 0; i < studentsArray.length; i++) {
			studentsArray[i] = students.get(i).getName();
		}
		
		comboBox_1.setModel(new DefaultComboBoxModel(studentsArray));
		comboBox_1.setBounds(480, 139, 122, 22);
		comboBox_1.revalidate();
		comboBox_1.repaint();
		panel.add(comboBox_1);
	      
 }
	

	
	public void refreshCombo() {
		    classNames = dateReader.getClassNames();
	        classNamesArray = classNames.toArray(new String[classNames.size()]);
	        comboBox.setModel(new DefaultComboBoxModel(classNamesArray));
	      
    }
	
	private void addRowArray(String name, String eMail) {
		Object[] row = { name, eMail };
		tableModel.addRow(row);
	}
	
	private void disableTable(JTable table) {
		for (int c = 0; c < table.getColumnCount(); c++) {
			Class<?> colClass = table.getColumnClass(c);
			table.setDefaultEditor(colClass, null); // disable editing for all columns
		}
}
	public void refreshRow(String selectedClassName) {

		students = dateWriter.getStudentList(classNamesArray[comboBox.getSelectedIndex()]);
		tableModel.setRowCount(0);
		for (Student student : students) {
			addRowArray(student.getName(), student.getEmail());

		}
	}
	
	public void setSelectedItem(String klasse) {

		
		comboBox.setSelectedItem(klasse);

		}
	}
	
	
