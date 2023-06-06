package OneInAll_GUI;

import java.awt.Color;
import csv_reader_stuff.DateWriter;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import csv_reader_stuff.Datenleser;
import school_attributes.Student;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Panel_Notenvergabe_MainScreen extends JPanel {
	private JPanel contentPane_1;
	private JPanel contentPane_2;
	private JTextField eMailField;
	// Wird benötigt für das Auslesen und ausgeben benötigter Infos aus den CSV
	// Dateien
	Datenleser csvReader;
	String buffer;
	String schuelername;
	String email;
	String klasse;
	// noteneintragung
	int note;
	int test;
	String fach;

	private JTextField nameField;
	private JTextField klasseField;
	private JTable table_2;
	private JTable table;
	private final JPanel panel = new JPanel();
	private ArrayList<String> faecher = new ArrayList<>();
	private JComboBox fachDropdown = new JComboBox();
	private int schuelerID;
	private Student student;
	JComboBox testformBox = new JComboBox();
	Datenleser dateReader = new Datenleser();
	DateWriter dateWriter = new DateWriter();
	private static final int klausur = 1;
	private static final int epo = 2;
	private static final int hue = 3;
	private DefaultTableModel tableModel;
	private String[] columnNames = { "Klausur", "HÜ", "Epochalnote" };
	String selectedClassName = (String) fachDropdown.getSelectedItem();

	/**
	 * Erstellt das Panel
	 * @param student
	 * @param klasse
	 * @throws IOException
	 */
	public Panel_Notenvergabe_MainScreen(Student student, String klasse) throws IOException {

		this.schuelerID = Integer.parseInt(student.getId()) - 1;
		this.klasse = klasse;
		this.student = student;

		createWindow();

		initFachDropdown();

		initNotenLabel();

		initTestformLabel();

		initTestComboBox();

		initFachLabel();

		initHinzufButton();

		initNotenDropdown();

		initNotenTable();

		initMSSTable();

		initStudentPic();

		initNameField();

		initKlassenField();

		initEmailField();
	}

	/**
	 * Generierung des Emailfelds
	 */
	private void initEmailField() {
		eMailField = new JTextField();
		eMailField.setBackground(Color.LIGHT_GRAY);
		eMailField.setBounds(10, 293, 200, 20);
		panel.add(eMailField);
		eMailField.setHorizontalAlignment(SwingConstants.CENTER);
		eMailField.setEditable(false);
		eMailField.setText(email);
		eMailField.setColumns(10);

	}

	/**
	 * Generierung des Klassenfelds
	 */
	private void initKlassenField() {
		klasseField = new JTextField();
		klasseField.setForeground(Color.BLACK);
		klasseField.setBackground(Color.LIGHT_GRAY);
		klasseField.setBounds(10, 261, 200, 20);
		panel.add(klasseField);
		klasseField.setHorizontalAlignment(SwingConstants.CENTER);
		klasseField.setEditable(false);
		klasseField.setText(klasse);
		klasseField.setColumns(10);
	}

	/**
	 * Generierung des Namefelds
	 */
	private void initNameField() {
		nameField = new JTextField();
		nameField.setForeground(Color.BLACK);
		nameField.setBackground(Color.LIGHT_GRAY);
		nameField.setBounds(10, 230, 200, 20);
		panel.add(nameField);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setEditable(false);
		nameField.setText(schuelername);
		nameField.setColumns(10);
	}

	/**
	 * Generierung des Studentenbilds
	 */
	private void initStudentPic() {
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(49, 69, 130, 132);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("misc/student_icon.png"));
	}

	/**
	 * Generierung der Notentabelle
	 */
	private void initMSSTable() {
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		table.setShowVerticalLines(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		table.setModel(new DefaultTableModel(
				new Object[][] { { "1+", "=", "15" }, { "1", "=", "14  " }, { "1-", "=", "13" }, { "2+", "=", "12" },
						{ "2", "=", "11" }, { "2-", "=", "10" }, { "3+", "=", "9" }, { "3", "=", "8" },
						{ "3-", "=", "7" }, { "4+", "=", "6" }, { "4", "=", "5" }, { "4-", "=", "4" },
						{ "5+", "=", "3" }, { "5", "=", "2" }, { "5-", "=", "1" }, { "6", "=", "0" }, },
				new String[] { "New column", "New column", "New column" }) {

			boolean[] columnEditables = new boolean[] { false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(0).setMinWidth(25);
		table.getColumnModel().getColumn(0).setMaxWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setMaxWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(2).setMinWidth(25);
		table.getColumnModel().getColumn(2).setMaxWidth(32);
		table.setBounds(829, 18, 83, 256);
		contentPane_1.add(table);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(54, -11, 227, 410);
		contentPane_1.add(panel);
		panel.setLayout(null);

		JButton btnZurck = new JButton("Zurück");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnZurck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				contentPane_1.setVisible(false);
				Panel_Notenvergabe_Screen mainScreen;
				try {
					mainScreen = new Panel_Notenvergabe_Screen();
					mainScreen.setVisible(true);
					contentPane_2.add(mainScreen);
					contentPane_2.setVisible(true);
					mainScreen.setSelectedItem(klasse.replace(".csv", ""));
					mainScreen.refreshRow(klasse.replace(".csv", ""));

				} catch (FileNotFoundException e1) {
					System.err.println("Fehler in NotenvergabePanel ");
					e1.printStackTrace();
				}

			}
		});
		btnZurck.setBackground(Color.LIGHT_GRAY);
		btnZurck.setBounds(623, 260, 89, 23);
		contentPane_1.add(btnZurck);
		{
			JButton hinzufButton = new JButton("Löschen");
			hinzufButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dateWriter.deleteGradeFromSubject(fach.replace(".csv", ""), klasse, schuelerID + 1, note, test);
					addRowArrayForTabl();
				}
			});
			hinzufButton.setBackground(Color.LIGHT_GRAY);
			hinzufButton.setBounds(434, 260, 127, 23);
			contentPane_1.add(hinzufButton);
		}
		{
			JButton btnNewButton = new JButton("ZeugnisAnzeigen");
			btnNewButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        try {
			            HistorieGUI_chat historie = new HistorieGUI_chat(schuelerID, klasse);
			            historie.setWindowVisible(true);
			            historie.addWindowListener(new WindowAdapter() {
			                @Override
			                public void windowClosing(WindowEvent e) {
			                    historie.setWindowVisible(false);
			                    historie.dispose();
			                }
			            });
			            
			            historie.setWindowVisible(true);
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
			    }
			});
			
			
			btnNewButton.setBounds(722, 78, 97, 170);
			contentPane_1.add(btnNewButton);
		}
	}

	/**
	 * Generierung der Notentabelle des Schuelers
	 */
	private void initNotenTable() {
		tableModel = new DefaultTableModel(columnNames, 0);
		table_2 = new JTable(tableModel);
		table_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(table_2);
        scrollPane.setBounds(287, 79, 425, 170);
        fachDropdown.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    selectedClassName = (String) fachDropdown.getSelectedItem();
                    addRowArrayForTabl();
                }
            }
        });
        
		
		table_2.setBounds(287, 116, 416, 30);
		contentPane_1.add(scrollPane);
		table_2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()) {
		            int selectedRow = table_2.getSelectedRow();
		            int selectedColumn = table_2.getSelectedColumn();

		            if (selectedRow != -1 && selectedColumn != -1) {
		                Object value = table_2.getValueAt(selectedRow, selectedColumn);
		                String columnName = table_2.getColumnName(selectedColumn);

		                System.out.println("Ausgewählter Wert: " + value);
		                System.out.println("Name der Tabellenspalte: " + columnName);
		            }
		        }
		    }
		});
	}

	/**
	 * Generierung des Notendropdownmenüs
	 */
	private void initNotenDropdown() {
		JComboBox notenDropDown = new JComboBox();
		notenDropDown.setBackground(Color.LIGHT_GRAY);
		notenDropDown.setModel(new DefaultComboBoxModel(new String[] { "--Bitte auswählen--", "0", "1", "2", "3", "4",
				"5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));
		notenDropDown.setBounds(287, 38, 127, 30);
		contentPane_1.add(notenDropDown);

		notenDropDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				note = notenDropDown.getSelectedIndex() - 1;
				System.out.println("Note " + note);
			}
		});
	}

	/**
	 * Generierung des Hinzufügen Buttons
	 */
	private void initHinzufButton() {
		JButton hinzufButton = new JButton("Hinzufügen");
		hinzufButton.setBackground(Color.LIGHT_GRAY);
		hinzufButton.setBounds(287, 260, 127, 23);
		contentPane_1.add(hinzufButton);
		hinzufButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dateWriter.addGradeToSubject(fach.replace(".csv", ""), klasse, schuelerID + 1, note, test);
				addRowArrayForTabl();
			}
		});

	}

	/**
	 * Generierung des Fachlabels
	 */
	private void initFachLabel() {
		JLabel lblNewLabel_1 = new JLabel("Fach:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(585, 13, 59, 25);
		contentPane_1.add(lblNewLabel_1);
	}

	/**
	 * Generierung der Testartcombobox
	 */
	private void initTestComboBox() {
		testformBox.setBackground(Color.LIGHT_GRAY);
		testformBox.setModel(new DefaultComboBoxModel(
				new String[] { "--Bitte auswählen--", "Klausur (50%)", "Epo(30%)", "hue(20%)" }));
		testformBox.setBounds(434, 38, 138, 30);
		contentPane_1.add(testformBox);
		testformBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				test = testformBox.getSelectedIndex();
				System.out.println("Test " + test);
			}
		});
	}

	/**
	 * Generierung des Testartlabels
	 */
	private void initTestformLabel() {
		JLabel testformLabel = new JLabel("Testform:");
		testformLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		testformLabel.setHorizontalAlignment(SwingConstants.CENTER);
		testformLabel.setBounds(434, 5, 104, 40);
		contentPane_1.add(testformLabel);
	}

	/**
	 * Initialisiert das Notenlabel
	 */
	private void initNotenLabel() {
		JLabel notenLabel = new JLabel("Note:");
		notenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notenLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		notenLabel.setBounds(287, 11, 59, 28);
		contentPane_1.add(notenLabel);
	}

	/**
	 * Initialisiert das Fach-Dropdown Menü
	 */
	private void initFachDropdown() {
		fachDropdown.setBackground(Color.LIGHT_GRAY);
		fachDropdown.setBounds(585, 38, 127, 30);
		contentPane_1.add(fachDropdown);
		updateFaechernamen(fachDropdown);

		fachDropdown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fach = (String) fachDropdown.getSelectedItem();
				System.out.println("Fach " + fach);
			}
		});
	}

	/**
	 * Erstellt das Fenster
	 * @throws IOException
	 */
	private void createWindow() throws IOException {
		setPreferredSize(new Dimension(1000, 800));

		setSchuelerInfo(schuelerID, klasse);
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		contentPane_1 = new JPanel();
		contentPane_1.setBounds(0, 0, 1000, 800);
		contentPane_1.setBorder(new EmptyBorder(0, 0, 886, 331));
		contentPane_1.setLayout(null);
		add(contentPane_1);

		contentPane_2 = new JPanel();
		contentPane_2.setBounds(0, 0, 1000, 800);
		contentPane_2.setBorder(new EmptyBorder(0, 0, 886, 331));
		contentPane_2.setLayout(null);
		add(contentPane_2);
		contentPane_2.setVisible(false);
	}

	/**
	 * Setzt die Schuelerinfo entsprechend der Parameter
	 * @param selectedIndex
	 * @param klasse
	 * @throws IOException
	 */
	public void setSchuelerInfo(int selectedIndex, String klasse) throws IOException {
		String[] splitBuffer = { " ", " ", "1000" };
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

	/**
	 * Aktualisiert die Liste der Faechernamen
	 * @param notenDropDown
	 */
	public void updateFaechernamen(JComboBox notenDropDown) {
		notenDropDown.removeAllItems();
		faecher.clear();
		faecher = csvReader.getFaecherNamen(klasse);
		notenDropDown.addItem("-----Bitte Auswählen------");
		for (String item : faecher) {
			notenDropDown.addItem(item.replace(".csv", ""));
		}
	}

	/**
	 * Ruft die Noten des entsprechenden Schülers und der entsprechenden Testart auf
	 * @param subject
	 * @param klasse
	 * @param selectedIndex
	 * @param testform
	 * @return
	 * @throws IOException
	 */
	private int[] getNoten(String subject, String klasse, int selectedIndex, int testform) throws IOException {
		Datenleser csvReader = new Datenleser();
		csvReader.setFilePath(subject, klasse);
		csvReader.initReader();
		int[] noten1 = csvReader.getNoten(selectedIndex, testform);
		csvReader.closeFile();
		return noten1;

	}

	// TrimMethoden:

	/**
	 * Schneidet das ".csv" anhängsel ab
	 * @param name
	 * @return
	 */
	private String trimCSVName(String name) {
		return name.replace(".csv", "");

	}

	/**
	 * Rundet den Durchschnitt 
	 * @param averageGrade
	 * @return
	 */
	private double trimAverage(double averageGrade) {
		averageGrade = Math.round(averageGrade * 100);
		averageGrade /= 100;
		return averageGrade;

	}


	/**
	 * 
	 * @param klausur
	 * @param hue
	 * @param epochalnote
	 */
	private void addRowArray(int[] klausur, int[] hue, int[] epochalnote) {
		tableModel.setRowCount(0);
	    int maxLength = Math.max(klausur.length, Math.max(hue.length, epochalnote.length));

	    for (int i = 0; i < maxLength; i++) {
	        Object[] row = new Object[3];

	        if (i < klausur.length && !(klausur[i] == 0) ) {
	            row[0] = klausur[i];
	        }

	        if (i < hue.length && !(hue[i] == 0)) {
	            row[1] = hue[i];
	        }

	        if (i < epochalnote.length && !(epochalnote[i] == 0)) {
	            row[2] = epochalnote[i];
	        }

	        tableModel.addRow(row);
	    }
	}
	
	/**
	 * Erweitert die Tabelle
	 */
	public void addRowArrayForTabl()  {
		try {
			addRowArray(getNoten(trimCSVName(selectedClassName), klasse, schuelerID , klausur),
			        getNoten(trimCSVName(selectedClassName), klasse, schuelerID , epo),
			        getNoten(trimCSVName(selectedClassName), klasse, schuelerID , hue)
			      );
		} catch (IOException e) {
			System.err.println("Fehler beim erstellen der Tabelle");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param grades
	 * @return
	 */
	private String buildNoteResult(int[] grades) {
		StringBuilder result = new StringBuilder();

		for (int note : grades) {
			if (note == 0 && grades.length == 1) {
			} else {
				result.append(note).append(" | ");
			}
		}
		return result.toString();
	}
	
	/**
	 * 
	 */
	private void giveValue() {
		table_2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()) {
		            int selectedRow = table_2.getSelectedRow();
		            int selectedColumn = table_2.getSelectedColumn();

		            if (selectedRow != -1 && selectedColumn != -1) {
		                Object value = table_2.getValueAt(selectedRow, selectedColumn);
		                String columnName = table_2.getColumnName(selectedColumn);

		                System.out.println("Ausgewählter Wert: " + value);
		                System.out.println("Name der Tabellenspalte: " + columnName);
		            }
		        }
		    }
		});
	}
}
