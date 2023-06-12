package gui;

import java.awt.EventQueue;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JInternalFrame;

public class HistorieGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel model;
    private JTextField searchField;
    private JTable table;
    private JTextField textField;

    public HistorieGUI() {
        setTitle("Schulnotenhistorie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(766, 469);
        setLocationRelativeTo(null);
        model = new DefaultTableModel();
        model.addColumn("Fach");
        model.addColumn("Testform");
        model.addColumn("Note");
        model.addColumn("Durchschnittsnote");

        // Sortierung hinzufügen
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);

        // Scrollpane hinzufügen
        JScrollPane scrollPane = new JScrollPane();

        // Suchfeld und Such-Button hinzufügen
        searchField = new JTextField();
        JButton searchButton = new JButton("Suchen");
        searchButton.addActionListener(e -> {
            String search = searchField.getText().trim();
            if (!search.isEmpty()) {
                sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + search));
            } else {
                sorter.setRowFilter(null);
            }
        });
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // GUI-Layout erstellen
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[20][20]
        	,
        	new String[] {
        		"Fach", "Note", "Testform", "Durchschnitt"
        	}
        ));
        scrollPane.setViewportView(table);
        
        textField = new JTextField();
        scrollPane.setColumnHeaderView(textField);
        textField.setColumns(10);
        contentPane.add(searchPanel, BorderLayout.NORTH);
        setContentPane(contentPane);

        // Beispiel-Daten zur Tabelle hinzufügen
        addRow("Mathematik", "Klausur", 1.7, 2.3);
        addRow("Englisch", "Test", 2.0, 2.5);
        addRow("Deutsch", "Hausaufgabe", 1.0, 1.8);
        addRow("Geschichte", "Referat", 2.3, 2.7);
        addRow("Chemie", "Experiment", 3.0, 3.1);
    }

    public void addRow(String fach, String testform, double note, double durchschnittsnote) {
        model.addRow(new Object[] { fach, testform, note, durchschnittsnote });
    }
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorieGUI frame = new HistorieGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
