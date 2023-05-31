package OneInAll_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;
import OneInAll_GUI.Panel_Klasse;
import OneInAll_GUI.Panel_Schueler;
import OneInAll_GUI.Panel_Fach;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import csv_reader_stuff.Datenleser;

public class Dashboard_Gui extends JFrame {

	private JPanel Header;
	private Panel_Klasse klassePanel;
	private Panel_Fach fachPanel;
	private Panel_Schueler schuelerPanel;
	private Panel_Notenvergabe_Screen notenvergabe;

	private boolean mouseClickedActivated1 = false;
	private boolean mouseClickedActivated2 = false;
	private boolean mouseClickedActivated3 = false;
	Datenleser dateReader = new Datenleser();

	JPanel headerPanel = new JPanel();
	JPanel schuelerPanelButton = new JPanel();
	JPanel fachVerwaltungButton = new JPanel();
	JPanel[] allButtonPanels = { headerPanel, schuelerPanelButton, fachVerwaltungButton };
	private boolean[] mouseClicked = { mouseClickedActivated1, mouseClickedActivated2, mouseClickedActivated3 };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard_Gui frame = new Dashboard_Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Dashboard_Gui() throws IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 645);
		Header = new JPanel();
		Header.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Header);
		Header.setLayout(null);

		createPanel();
		initPanels();
		createSchuelerPanelButton();
		createFachVerwaltungButton();
		createKlassenVerwaltungButton();
		createHeaderLabels();
		createNotenVerwaltungButton();

		JPanel MainContent = new JPanel();
		MainContent.setBounds(0, 143, 886, 331);
		Header.add(MainContent);
		MainContent.setLayout(null);

		MainContent.add(klassePanel);
		MainContent.add(fachPanel);
		MainContent.add(schuelerPanel);
		MainContent.add(notenvergabe);

	}

	private void createPanel() {
		headerPanel.setBackground(new Color(102, 139, 170));
		headerPanel.setBounds(0, 0, 886, 143);
		Header.add(headerPanel);
		headerPanel.setLayout(null);
	}

	private void initPanels() throws FileNotFoundException {
		klassePanel = new Panel_Klasse();
		fachPanel = new Panel_Fach();
		schuelerPanel = new Panel_Schueler();
		notenvergabe = new Panel_Notenvergabe_Screen();
	}

	private void createSchuelerPanelButton() {
		schuelerPanelButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				schuelerPanelButton.setBackground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				if (!mouseClickedActivated2) {
					schuelerPanelButton.setBackground(new Color(63, 90, 114));
				} else {
					schuelerPanelButton.setBackground(Color.BLACK);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(schuelerPanel);
				schuelerPanel.refreshCombo();
				String[] classNamesArray = dateReader.getClassNames()
						.toArray(new String[dateReader.getClassNames().size()]);

				if (classNamesArray.length > 0) {
					schuelerPanel.refreshRow(classNamesArray[0]);
				} else {
					schuelerPanel.resetTable();
				}
				updateMouseClickedActivated(false, true, false);

				fachVerwaltungButton.setBackground(new Color(63, 90, 114));

			}
		});
		schuelerPanelButton.setLayout(null);
		schuelerPanelButton.setBackground(new Color(63, 90, 114));
		schuelerPanelButton.setBounds(251, 112, 131, 31);
		headerPanel.add(schuelerPanelButton);

		JLabel lblSchlerverwaltung = new JLabel("Schülerverwaltung");
		lblSchlerverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchlerverwaltung.setForeground(Color.WHITE);
		lblSchlerverwaltung.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblSchlerverwaltung.setBackground(Color.MAGENTA);
		lblSchlerverwaltung.setBounds(21, 11, 96, 12);
		schuelerPanelButton.add(lblSchlerverwaltung);
	}

	private void createFachVerwaltungButton() {
		fachVerwaltungButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				fachVerwaltungButton.setBackground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				if (!mouseClickedActivated3) {
					fachVerwaltungButton.setBackground(new Color(63, 90, 114));
				} else {
					fachVerwaltungButton.setBackground(Color.BLACK);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(fachPanel);
				updateMouseClickedActivated(false, false, true);
				fachPanel.refreshCombo();

				String[] classNamesArray = dateReader.getClassNames()
						.toArray(new String[dateReader.getClassNames().size()]);

				if (classNamesArray.length > 0) {
					fachPanel.refreshRow(classNamesArray[0]);
				}

				schuelerPanelButton.setBackground(new Color(63, 90, 114));

			}
		});
		fachVerwaltungButton.setLayout(null);
		fachVerwaltungButton.setBackground(new Color(63, 90, 114));
		fachVerwaltungButton.setBounds(448, 112, 131, 31);
		headerPanel.add(fachVerwaltungButton);

		JLabel lblNotenverwaltung = new JLabel("Fachverwaltung");
		lblNotenverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotenverwaltung.setForeground(Color.WHITE);
		lblNotenverwaltung.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblNotenverwaltung.setBackground(Color.MAGENTA);
		lblNotenverwaltung.setBounds(21, 11, 96, 12);
		fachVerwaltungButton.add(lblNotenverwaltung);
	}

	private void createKlassenVerwaltungButton() {

		JButton klassenVerwaltungButton = new JButton("Klassenverwaltung");
		klassenVerwaltungButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		klassenVerwaltungButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(klassePanel);

				updateMouseClickedActivated(true, false, false);

				fachVerwaltungButton.setBackground(new Color(63, 90, 114));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		klassenVerwaltungButton.setBounds(52, 120, 141, 23);
		headerPanel.add(klassenVerwaltungButton);
	}

	private void createNotenVerwaltungButton() {
		JPanel notenVerwaltungButton = new JPanel();
		notenVerwaltungButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(notenvergabe);

				notenvergabe.refreshCombo();
				String[] classNamesArray = dateReader.getClassNames()
						.toArray(new String[dateReader.getClassNames().size()]);

				if (classNamesArray.length > 0) {
					notenvergabe.refreshRow(classNamesArray[0]);
				}

			}
		});
		notenVerwaltungButton.setLayout(null);
		notenVerwaltungButton.setBackground(new Color(63, 90, 114));
		notenVerwaltungButton.setBounds(668, 112, 131, 31);
		headerPanel.add(notenVerwaltungButton);

		JLabel lblNotenverwaltung_1 = new JLabel("Notenvergabe");
		lblNotenverwaltung_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotenverwaltung_1.setForeground(Color.WHITE);
		lblNotenverwaltung_1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblNotenverwaltung_1.setBackground(Color.MAGENTA);
		lblNotenverwaltung_1.setBounds(21, 11, 96, 12);
		notenVerwaltungButton.add(lblNotenverwaltung_1);

	}

	private void createHeaderLabels() {
		JLabel whiteTrashLabel = new JLabel("A W Trash Project  X ");
		whiteTrashLabel.setForeground(new Color(255, 255, 255));
		whiteTrashLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		whiteTrashLabel.setBounds(89, 11, 207, 40);
		headerPanel.add(whiteTrashLabel);

		JLabel sloganLabel = new JLabel("Noten im Griff");
		sloganLabel.setForeground(Color.WHITE);
		sloganLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		sloganLabel.setBounds(89, 49, 207, 40);
		headerPanel.add(sloganLabel);
	}

	public void updateMouseClickedActivated(boolean activate1, boolean activate2, boolean activate3) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mouseClickedActivated1 = activate1;
				mouseClickedActivated2 = activate2;
				mouseClickedActivated3 = activate3;
			}
		});
	}

	public void selectClick(JPanel panel) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				menuClicked(klassePanel);
				panel.setBackground(Color.BLACK);
				for (JPanel buttonPanel : allButtonPanels) {
					if (buttonPanel != panel) {
						buttonPanel.setBackground(new Color(63, 90, 114));
					}
				}

			}
		});

	}

	public void menuClicked(JPanel panel) {
		klassePanel.setVisible(false);
		fachPanel.setVisible(false);
		schuelerPanel.setVisible(false);
		notenvergabe.setVisible(false);

		panel.setVisible(true);

	}
}
