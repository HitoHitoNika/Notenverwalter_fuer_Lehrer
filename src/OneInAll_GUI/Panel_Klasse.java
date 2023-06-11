package OneInAll_GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import csv_reader_stuff.Datenleser;
import csv_reader_stuff.DateWriter;
import javax.swing.ImageIcon;

public class Panel_Klasse extends JPanel {

	private JTextField textField;
	private DateWriter dateWriter = new DateWriter();
	private Datenleser dateReader = new Datenleser();;
	public List<String> classNames = dateReader.getClassNames();
	public String[] classNamesArray = classNames.toArray(new String[classNames.size()]);

	/**
	 * Erstellt das Panel
	 * 
	 * @throws FileNotFoundException
	 */
	public Panel_Klasse() throws FileNotFoundException {

		setBounds(0, 0, 886, 331);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 280, 331);
		add(panel);
		panel.setLayout(null);
		textField = new JTextField();
		textField.setBounds(10, 67, 122, 20);
		panel.add(textField);
		textField.setColumns(10);
		JLabel lblNewLabel = new JLabel("Neue Klasse Hinzufügen:");
		lblNewLabel.setBounds(10, 11, 163, 14);
		panel.add(lblNewLabel);
		JLabel lblKlassenname = new JLabel("Klassenname:");
		lblKlassenname.setBounds(10, 42, 136, 14);
		panel.add(lblKlassenname);
		JButton btnNewButton = new JButton("Hinzufügen");

		btnNewButton.setBounds(10, 98, 105, 23);
		panel.add(btnNewButton);
		
		JButton importButton = new JButton("Import");
		importButton.setIcon(new ImageIcon("misc\\import.png"));
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateReader.importKlasse();
			}
		});
		importButton.setBounds(10, 297, 122, 23);
		panel.add(importButton);
		
		JButton exportButton = new JButton("Export");
		exportButton.setIcon(new ImageIcon("misc\\export.png"));
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateReader.exportKlasse();
			}
		});
		exportButton.setBounds(148, 297, 122, 23);
		panel.add(exportButton);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(281, 0, 595, 331);
		add(panel_1);
		panel_1.setLayout(null);
		JLabel lblKlassenliste = new JLabel("Klassenliste");
		lblKlassenliste.setBounds(10, 11, 70, 14);
		panel_1.add(lblKlassenliste);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = classNamesArray;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 50, 575, 219);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String eingabe = textField.getText();
				if (dateWriter.createClassFolder(eingabe)) {
					List<String> classNames = dateReader.getClassNames();
					classNamesArray = classNames.toArray(new String[classNames.size()]);
					updateClassList(list);
					list.revalidate();
					list.repaint();

					JOptionPane.showMessageDialog(null, "Erfolgreich", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Fehler beim Hinzufügen der Klasse, die Klasse existiert bereits!", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		panel_1.add(list);
		JButton btnNewButton_1 = new JButton("Klasse Löschen");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex >= 0) {
					String selectedClassName = classNamesArray[selectedIndex];
					dateWriter.deleteClass(selectedClassName);
					classNames = dateReader.getClassNames();
					classNamesArray = classNames.toArray(new String[classNames.size()]);
					updateClassList(list);
				}
			}
		});
		btnNewButton_1.setBounds(10, 280, 151, 23);
		panel_1.add(btnNewButton_1);
	}
	/**
	 * Lädt den Inhalt der Liste mit Klassennamen neu
	 * @param list
	 */
	public void updateClassList(JList list) {

		classNames = dateReader.getClassNames();
		classNamesArray = classNames.toArray(new String[classNames.size()]);
		list.setModel(new AbstractListModel() {
			String[] values = classNamesArray;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
}
