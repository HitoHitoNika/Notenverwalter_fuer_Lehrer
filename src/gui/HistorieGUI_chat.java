package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import csv_reader_stuff.Datenleser;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HistorieGUI_chat {
	
	Datenleser csvReader;
	  String buffer;
	  String schuelername;
	  String email;
	  String klasse;
	  int klausur = 1;
	  int hue = 2;
	  int epo = 3;
	  private ArrayList<String> faecher = new ArrayList<>();
			  
	  private int selectedIndex;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private String[] columnNames = { "Fach", "Klausur", "HÜ", "Epochalnote", "Durchschnittsnote" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorieGUI_chat window = new HistorieGUI_chat(1,"BSIT22a" );
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HistorieGUI_chat(int selectedIndex, String klasse) throws IOException  {
		this.selectedIndex = selectedIndex;
	    this.klasse = klasse;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 602, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		frame.setContentPane(contentPane);

		tableModel = new DefaultTableModel(columnNames, 0); {
			
		}
		contentPane.setLayout(null);
		table = 	new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 5, 566, 427);
		contentPane.add(scrollPane);
		table.setRowHeight(70);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	        {
	            setOpaque(true);
	        }
	        
	        public Component getTableCellRendererComponent(JTable table, Object value,
	                boolean isSelected, boolean hasFocus, int row, int column) {
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
		csvReader.getNoten(selectedIndex, klausur);
		csvReader.getNoten(selectedIndex, hue);
		csvReader.getNoten(selectedIndex, epo);
		csvReader.getFaecherNamen(klasse);
		for (String item : faecher) {
	    addRow(item, " 1.02 |  3.02   | 2.02 \n 1.02 | 2.02 ", "0.0", "0.0", 2.0);
		
		}
		
		
		addRow("Mathematik", " 1.02 |  3.02   | 2.02 \n 1.02 | 2.02 ", "0.0", "0.0", 2.0);
		addRow("Deutsch", "0.0", "3.5", "0.0", 3.5);
		addRow("Englisch", "0.0", "0.0", "1.8", 1.8);
		addRow("Chemie", "4.0", "0.0", "0.0", 4.0);
		addRow("Physik", "0.0", "2.5", "0.0", 2.5);
		
		for (int c = 0; c < table.getColumnCount(); c++) {
		    Class<?> colClass = table.getColumnClass(c);
		    table.setDefaultEditor(colClass, null); // disable editing for all columns
		}
	}

	/**
	 * Add a new row to the table.
	 */
	private void addRow(String subject, String klausur, String hü, String epochalnote, double averageGrade) {
		Object[] row = { subject, klausur, hü, epochalnote, averageGrade };
		tableModel.addRow(row);
	}
	
}
