package OneInAll_GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import csv_reader_stuff.Datenleser;
import javax.swing.JLabel;

public class Panel_Notenvergabe_Historie extends JPanel {

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
    private JTable table;
    private DefaultTableModel tableModel;
    private String[] columnNames = {"Fach", "Klausur", "HÜ", "Epochalnote", "Durchschnittsnote"};

    /**
     * Erstellt das Panel
     * @param selectedIndex
     * @param klasse
     * @throws IOException
     */
    public Panel_Notenvergabe_Historie(int selectedIndex, String klasse) throws IOException {
        this.selectedIndex = selectedIndex;
        this.klasse = klasse;
        initialize();
    }

    /**
     * Initialisiert den Inhalt des Panels
     * @throws IOException
     */
    private void initialize() throws IOException {
        setLayout(null);
        setPreferredSize(new Dimension(600, 500));

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 5, 566, 398);
        add(scrollPane);
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

        JLabel lblNewLabel = new JLabel("Die Zeugnisnote beträgt: " + getZeugnisAverage(faecher));
        lblNewLabel.setBounds(222, 436, 261, 14);
        add(lblNewLabel);
    }

    // Logik die GUI baut:

    /**
     * Funktionale Logik
	 * DatenleserMethode
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
  		double average = csvReader.getAverage(0);
  		csvReader.closeFile();
  		return average;

  	}

    /**
     * Funktionale Logik
	 * DatenleserMethode
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
	 * TrimMethoden
     * @param name
     * @return
     */
  	private String trimCSVName(String name) {
  		return name.replace(".csv", "");

  	}

    /**
     * Funktionale Logik
     * TrimMethoden
     * @param averageGrade
     * @return
     */
  	private double trimAverage(double averageGrade) {
  		averageGrade = Math.round(averageGrade * 100);
  		averageGrade /= 100;
  		return averageGrade;

  	}
  	
    /**
     * Funktionale Logik
     * TrimMethoden
     * @param subject
     * @param klausur
     * @param hü
     * @param epochalnote
     * @param averageGrade
     */
  	private void addRowArray(String subject, int[] klausur, int[] hue, int[] epochalnote, double averageGrade) {
  		Object[] row = { subject, buildNoteResult(klausur), buildNoteResult(hue), buildNoteResult(epochalnote),
  				trimAverage(averageGrade) };
  		tableModel.addRow(row);
  	}

    /**
     * Funktionale Logik
     * TrimMethoden
     * @param grades
     * @return
     */
  	private String buildNoteResult(int[] grades) {
  		StringBuilder result = new StringBuilder();
  		
  		for (int note : grades) {
  			if (note == 0 && grades.length == 1  ) {
  			} else {
  				result.append(note).append(" | ");
  			}
  		}
  		return result.toString();
  	}
  	
  	
    /**
     * Funktionale Logik
     * TrimMethoden
     * @param faecher
     * @return
     * @throws IOException
     */
  	private double getZeugnisAverage(ArrayList<String>  faecher) throws IOException {
  		double average = 0;
  		for (String fach : faecher) {
  			
  			average += getAverage(trimCSVName(fach), klasse, selectedIndex);
  			
  		}
  		return trimAverage(average / faecher.size());
  		

  	}

  	
  }
