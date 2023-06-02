package OneInAll_GUI;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import csv_reader_stuff.Datenleser;

import javax.swing.JLabel;

public class HistorieGUI_chat extends JFrame {

	Datenleser csvReader = new Datenleser();
	String buffer;
	String schuelername;
	String email;
	String klasse;

	private static final int klausur = 1;
	private static final int epo = 2;
	private static final int hue = 3;
	private ArrayList<String> faecher = new ArrayList<>();

	private int selectedIndex;
	private JFrame frmNotenhistorieVon;
	private JTable table;
	private DefaultTableModel tableModel;
	public JPanel contentPane;
	private JScrollPane scrollPane;
	private String[] columnNames = { "Fach", "Klausur", "hue", "Epochalnote", "Durchschnittsnote" };

	/**
	 * Konstruktor der Oberfläche
	 */
	public HistorieGUI_chat(int selectedIndex, String klasse) throws IOException {
		this.selectedIndex = selectedIndex;
		this.klasse = klasse;
		initialize();
		frmNotenhistorieVon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Setzen Sie das Schließen-Verhalten auf
																				// DISPOSE_ON_CLOSE
		frmNotenhistorieVon.setVisible(true);

	}

	/**
	 * Initialisiert den Inhalt des Frames
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frmNotenhistorieVon = new JFrame();
		frmNotenhistorieVon.setTitle("Notenhistorie von ");
		frmNotenhistorieVon.setBounds(100, 100, 602, 500);
		frmNotenhistorieVon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		frmNotenhistorieVon.setContentPane(contentPane);

		tableModel = new DefaultTableModel(columnNames, 0);
		{
		}
		contentPane.setLayout(null);
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 5, 566, 398);
		contentPane.add(scrollPane);
		table.setRowHeight(70);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setOpaque(true);
			}

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (value != null) {
					String text = value.toString();
					if (text.contains("<html>")) {
						return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					}
					setText("<html><div style='text-align: center;'>" + text.replaceAll("\n", "<br>") + "</html>");
				} else {
					setText("");
				}
				return this;
			}
		});

		csvReader.setFilePath(klasse);
		csvReader.initReader();
		faecher = csvReader.getFaecherNamen(klasse);
		csvReader.closeFile();
		for (String item : faecher) {
			addRowArray(trimCSVName(item), getNoten(trimCSVName(item), klasse, selectedIndex, klausur),
					getNoten(trimCSVName(item), klasse, selectedIndex, epo),
					getNoten(trimCSVName(item), klasse, selectedIndex, hue),
					getAverage(trimCSVName(item), klasse, selectedIndex));

		}
		disableTable(table);

		JLabel lblNewLabel = new JLabel("Die Zeugnisnote beträgt: " + getZeugnisAverage(faecher));
		lblNewLabel.setBounds(222, 436, 261, 14);
		contentPane.add(lblNewLabel);
	}

	// Logik die GUI baut:

	/**
	 * Funktionale Logik
	 * DatenleserMethode
	 * 
	 * @param subject
	 * @param klasse
	 * @param selectedIndex
	 * @return
	 * @throws IOException
	 */
	private double getAverage(String subject, String klasse, int selectedIndex) throws IOException {
		Datenleser csvReader = new Datenleser();
		csvReader.setFilePath(subject, klasse);
		csvReader.initReader();
		double average = csvReader.getAverage(selectedIndex);
		csvReader.closeFile();
		return average;

	}

	/**
	 * Funktionale Logik
	 * DatenleserMethode
	 * 
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

	/**
	 * Funktionale Logik
	 * Trimmethode
	 * 
	 * @param name
	 * @return
	 */
	private String trimCSVName(String name) {
		return name.replace(".csv", "");

	}

	/**
	 * Funktionale Logik
	 * Trimmethode
	 * 
	 * @param averageGrade
	 * @return
	 */
	private double trimAverage(double averageGrade) {
		// averageGrade = Math.round(averageGrade * 100);
		// averageGrade /= 100;
		return averageGrade;

	}

	/**
	 * Logik für die Tabelle
	 * 
	 * @param table
	 */
	private void disableTable(JTable table) {
		for (int c = 0; c < table.getColumnCount(); c++) {
			Class<?> colClass = table.getColumnClass(c);
			table.setDefaultEditor(colClass, null); // disable editing for all columns
		}

	}

	/**
	 * Logik für die Tabelle
	 * 
	 * @param subject
	 * @param klausur
	 * @param hue
	 * @param epochalnote
	 * @param averageGrade
	 */
	private void addRowArray(String subject, int[] klausur, int[] hue, int[] epochalnote, double averageGrade) {
		Object[] row = { subject, buildNoteResult(klausur), buildNoteResult(hue), buildNoteResult(epochalnote),
				trimAverage(averageGrade) };
		tableModel.addRow(row);
	}

	/**
	 * Logik für die Tabelle
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
	 * Logik für die Tabelle
	 * 
	 * @param faecher
	 * @return
	 * @throws IOException
	 */
	private double getZeugnisAverage(ArrayList<String> faecher) throws IOException {
		double average = 0;
		for (String fach : faecher) {

			average += getAverage(trimCSVName(fach), klasse, selectedIndex);

		}
		return trimAverage(average / faecher.size());

	}

	/**
	 * Fensteraufruf ermöglichen
	 * 
	 * @param visibility
	 */
	public void setWindowVisible(boolean visibility) {
		frmNotenhistorieVon.setVisible(visibility);
	}

}
