package OneInAll_GUI;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import csv_reader_stuff.Datenleser;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Panel_Notenvergabe_MainScreen extends JPanel {

  private JPanel contentPane;
  private JPanel contentPane_1;
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
  private JTable table_1;
  private JTable table_2;
  private JTable table;
  private final JPanel panel = new JPanel();
  private ArrayList<String> faecher = new ArrayList<>();
  private JComboBox fachDropdown = new JComboBox();
  private int schuelerID;
  JComboBox testformBox = new JComboBox();

  public Panel_Notenvergabe_MainScreen(int selectedIndex, String klasse) throws IOException {
    this.schuelerID = selectedIndex;
    this.klasse = klasse;
    
   
	
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
    eMailField.setBounds(52, 292, 200, 20);
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
    klasseField.setBounds(52, 261, 200, 20);
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
    nameField.setBounds(52, 232, 200, 20);
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
    lblNewLabel.setBounds(93, 75, 130, 132);
    panel.add(lblNewLabel);
    lblNewLabel.setIcon(new ImageIcon(System.getProperty("user.dir") + "/misc/student.png"));
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
    table.setBounds(830, 131, 83, 256);
    contentPane_1.add(table);
    panel.setBackground(SystemColor.activeCaption);
    panel.setBounds(-30, -3, 269, 413);
    contentPane_1.add(panel);
    panel.setLayout(null);
  }

  /**
   * Generierung der Notentabelle des Schülers
   */
  private void initNotenTable() {
    table_2 = new JTable();
    table_2.setShowHorizontalLines(false);
    table_2.setBackground(Color.LIGHT_GRAY);
    table_2.setFont(new Font("Tahoma", Font.BOLD, 14));
    table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
    table_2.setModel(
        new DefaultTableModel(new Object[][] { { "Klausuren", "Epos", "H\u00DCs" }, { null, null, null }, },
            new String[] { "New column", "New column", "New column" }) {

          boolean[] columnEditables = new boolean[] { false, false, false };

          @Override
          public boolean isCellEditable(int row, int column) {
            return columnEditables[column];
          }
        });
    table_2.setBounds(287, 116, 416, 30);
    contentPane_1.add(table_2);
  }

  /**
   * Generierung des Notendropdownmenüs
   */
  private void initNotenDropdown() {
    JComboBox notenDropDown = new JComboBox();
    notenDropDown.setBackground(Color.LIGHT_GRAY);
    notenDropDown.setModel(new DefaultComboBoxModel(
        new String[] { "--Bitte auswählen--", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
            "14", "15" }));
    notenDropDown.setBounds(315, 19, 127, 30);
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
    hinzufButton.setBounds(283, 370, 89, 23);
    contentPane_1.add(hinzufButton);
    hinzufButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Datenleser meinReader = new Datenleser();
          System.out.println("fach " + fach + " klasse " + klasse);
          meinReader.setFilePath(fach, klasse);
          meinReader.writeNote(note, schuelerID, test);
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

      }
    });

  }

  /**
   * Generierung des Fachlabels
   */
  private void initFachLabel() {
    JLabel lblNewLabel_1 = new JLabel("Fach:");
    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
    lblNewLabel_1.setBounds(732, 21, 59, 25);
    contentPane_1.add(lblNewLabel_1);
  }

  /**
   * Generierung der Testartcombobox
   */
  private void initTestComboBox() {
    testformBox.setBackground(Color.LIGHT_GRAY);
    testformBox.setModel(new DefaultComboBoxModel(
        new String[] { "--Bitte auswählen--", "Klausur (50%)", "Epo(30%)", "HÜ(20%)" }));
    testformBox.setBounds(560, 20, 138, 30);
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
    testformLabel.setBounds(464, 14, 104, 40);
    contentPane_1.add(testformLabel);
  }

  /**
   * 
   */
  private void initNotenLabel() {
    JLabel notenLabel = new JLabel("Note:");
    notenLabel.setHorizontalAlignment(SwingConstants.CENTER);
    notenLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
    notenLabel.setBounds(256, 18, 59, 28);
    contentPane_1.add(notenLabel);
  }

  private void initFachDropdown() {
    fachDropdown.setBackground(Color.LIGHT_GRAY);
    fachDropdown.setBounds(787, 20, 127, 30);
    contentPane_1.add(fachDropdown);
    fachDropdown.setModel(new DefaultComboBoxModel(
        new String[] { "--bitte auswählen--", "Deutsch", "Englisch", "Mathe", "Physik", "Chemie", "Biologie",
            "Sozialkunde", "Erdkunde", "Religion", "Informatik", "Sport", "Kunst", "Musik" }));
    updateFaechernamen(fachDropdown);

    fachDropdown.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        fach = (String) fachDropdown.getSelectedItem();
        System.out.println("Fach " + fach);
      }
    });
  }

  private void createWindow() throws IOException {
	  
	  setBounds(0, 0, 886, 331);
		
		
	    setSchuelerInfo(schuelerID, klasse);
	    setBackground(new Color(255, 255, 255));
	 

	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(0, 0, 886, 331));

	    contentPane_1 = new JPanel();
	    contentPane_1.setBorder(new EmptyBorder(0, 0, 886, 331));

	    
	    contentPane_1.setLayout(null);
  }

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

  public void updateFaechernamen(JComboBox notenDropDown) {
    notenDropDown.removeAllItems();
    faecher.clear();
    faecher = csvReader.getFaecherNamen(klasse);
    for (String item : faecher) {
      notenDropDown.addItem(item);
    }
  }

}