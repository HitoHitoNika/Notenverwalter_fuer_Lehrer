package OneInAll_GUI;

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
import javax.swing.ImageIcon;
import csv_reader_stuff.Datenleser;

/**
 * Die Klasse Dashboard_Gui ist ein GUI-Fenster, das ein Dashboard zur
 * Verwaltung von Schülern, Fächern und Noten bietet. Es erbt von der Klasse
 * JFrame.
 */

public class Dashboard_Gui extends JFrame {

	private JPanel Header;
	private Panel_Klasse klassePanel;
	private Panel_Fach fachPanel;
	private Panel_Schueler schuelerPanel;
	private Panel_Notenvergabe_Screen notenvergabe;

	private boolean mouseClickedActivated1 = false;
	private boolean mouseClickedActivated2 = false;
	private boolean mouseClickedActivated3 = false;
	private boolean mouseClickedActivated4 = false;

	Datenleser dateReader = new Datenleser();

	JPanel headerPanel = new JPanel();
	JPanel schuelerPanelButton = new JPanel();
	JPanel fachVerwaltungButton = new JPanel();
	JPanel KlassenPanelButton_1 = new JPanel();
	JPanel notenVerwaltungButton = new JPanel();
	JPanel[] allButtonPanels = { schuelerPanelButton, fachVerwaltungButton, KlassenPanelButton_1,
			notenVerwaltungButton };


	/**
	 * Der Konstruktor der Klasse Dashboard_Gui erzeugt das GUI-Fenster und
	 * initialisiert die enthaltenen Panels und Buttons.
	 *
	 * @throws IOException, falls ein Fehler beim Einlesen von Daten auftritt.
	 */
	public Dashboard_Gui() throws IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 504);
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

	/**
	 * Die Methode createPanel erstellt das Header-Panel des Dashboards.
	 */
	private void createPanel() {
		headerPanel.setBackground(new Color(193, 225, 193));
		headerPanel.setBounds(0, 0, 886, 143);
		Header.add(headerPanel);
		headerPanel.setLayout(null);
	}

	/**
	 * Die Methode initPanels initialisiert die enthaltenen Panels.
	 *
	 * @throws FileNotFoundException, falls eine Datei nicht gefunden wird.
	 */
	private void initPanels() throws FileNotFoundException {
		klassePanel = new Panel_Klasse();
		fachPanel = new Panel_Fach();
		schuelerPanel = new Panel_Schueler();
		notenvergabe = new Panel_Notenvergabe_Screen();
	}

	/**
	 * Die Methode createSchuelerPanelButton erstellt den Button für die
	 * Schülerverwaltung im Header-Panel.
	 */
	private void createSchuelerPanelButton() {
		schuelerPanelButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				schuelerPanelButton.setBackground(new Color(88,88,90));
			}

			public void mouseExited(MouseEvent e) {
				if (!mouseClickedActivated2) {
					schuelerPanelButton.setBackground(new Color(0, 129, 198));
				} else {
					schuelerPanelButton.setBackground(new Color(88,88,90));
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
				updateMouseClickedActivated(false, true, false, false);

				selectClick(schuelerPanelButton);

			}
		});
		schuelerPanelButton.setLayout(null);
		schuelerPanelButton.setBackground(new Color(0, 129, 198));
		schuelerPanelButton.setBounds(274, 112, 131, 31);
		headerPanel.add(schuelerPanelButton);

		JLabel lblSchlerverwaltung = new JLabel("Schülerverwaltung");
		lblSchlerverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchlerverwaltung.setForeground(Color.WHITE);
		lblSchlerverwaltung.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblSchlerverwaltung.setBackground(Color.MAGENTA);
		lblSchlerverwaltung.setBounds(21, 11, 96, 12);
		schuelerPanelButton.add(lblSchlerverwaltung);
	}

	/**
	 * Die Methode createFachVerwaltungButton erstellt den Button für die
	 * Fachverwaltung im Header-Panel.
	 */
	private void createFachVerwaltungButton() {
		
		
		
		fachVerwaltungButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				fachVerwaltungButton.setBackground(new Color(88,88,90));
			}

			public void mouseExited(MouseEvent e) {
				if (!mouseClickedActivated3) {
					fachVerwaltungButton.setBackground(new Color(0, 129, 198));
				} else {
					fachVerwaltungButton.setBackground(new Color(88,88,90));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(fachPanel);
				fachPanel.refreshCombo();

				String[] classNamesArray = dateReader.getClassNames()
						.toArray(new String[dateReader.getClassNames().size()]);

				if (classNamesArray.length > 0) {
					fachPanel.refreshRow(classNamesArray[0]);
				}
				
				selectClick(fachVerwaltungButton);
				updateMouseClickedActivated(false, false, true, false);

				

			}
		});
		fachVerwaltungButton.setLayout(null);
		fachVerwaltungButton.setBackground(new Color(0, 129, 198));
		fachVerwaltungButton.setBounds(468, 112, 131, 31);
		headerPanel.add(fachVerwaltungButton);

		JLabel lblNotenverwaltung = new JLabel("Fachverwaltung");
		lblNotenverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotenverwaltung.setForeground(Color.WHITE);
		lblNotenverwaltung.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblNotenverwaltung.setBackground(Color.MAGENTA);
		lblNotenverwaltung.setBounds(21, 11, 96, 12);
		fachVerwaltungButton.add(lblNotenverwaltung);
	}

	/**
	 * Die Methode createKlassenVerwaltungButton erstellt den Button für die
	 * Klassenverwaltung im Header-Panel.
	 */
	private void createKlassenVerwaltungButton() {
	}

	/**
	 * Die Methode createNotenVerwaltungButton erstellt den Button für die
	 * Notenverwaltung im Header-Panel.
	 */
	private void createNotenVerwaltungButton() {

		notenVerwaltungButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                menuClicked(notenvergabe);

                updateMouseClickedActivated(false, false, false, true);

                selectClick(notenVerwaltungButton);

                notenvergabe.refreshCombo();
                String[] classNamesArray = dateReader.getClassNames()
                        .toArray(new String[dateReader.getClassNames().size()]);

                if (classNamesArray.length > 0) {
                    notenvergabe.refreshRow(classNamesArray[0]);
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                notenVerwaltungButton.setBackground(new Color(88,88,90));
            }

            public void mouseExited(MouseEvent e) {
                if (!mouseClickedActivated4) {
                    notenVerwaltungButton.setBackground(new Color(0, 129, 198));
                } else {
                    notenVerwaltungButton.setBackground(new Color(88,88,90));
                }
            }
        });
		notenVerwaltungButton.setLayout(null);
		notenVerwaltungButton.setBackground(new Color(0, 129, 198));
		notenVerwaltungButton.setBounds(668, 112, 131, 31);
		headerPanel.add(notenVerwaltungButton);

		JLabel lblNotenverwaltung_1 = new JLabel("Notenvergabe");
		lblNotenverwaltung_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotenverwaltung_1.setForeground(Color.WHITE);
		lblNotenverwaltung_1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblNotenverwaltung_1.setBackground(Color.MAGENTA);
		lblNotenverwaltung_1.setBounds(21, 11, 96, 12);
		notenVerwaltungButton.add(lblNotenverwaltung_1);

		KlassenPanelButton_1.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				KlassenPanelButton_1.setBackground(new Color(88,88,90));
			}

			public void mouseExited(MouseEvent e) {
				if (!mouseClickedActivated1) {
					KlassenPanelButton_1.setBackground(new Color(0, 129, 198));
				} else {
					KlassenPanelButton_1.setBackground(new Color(88,88,90));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(klassePanel);
				schuelerPanel.refreshCombo();
				String[] classNamesArray = dateReader.getClassNames()
						.toArray(new String[dateReader.getClassNames().size()]);

				if (classNamesArray.length > 0) {
					schuelerPanel.refreshRow(classNamesArray[0]);
				} else {
					schuelerPanel.resetTable();
				}
				updateMouseClickedActivated(true, false, false, false);

				selectClick(KlassenPanelButton_1);

			}
		});
		KlassenPanelButton_1.setLayout(null);
		KlassenPanelButton_1.setBackground(new Color(0, 129, 198));
		KlassenPanelButton_1.setBounds(78, 112, 141, 31);
		headerPanel.add(KlassenPanelButton_1);

		JLabel lblKlassenverwaltung = new JLabel("Klassenverwaltung");
		lblKlassenverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblKlassenverwaltung.setForeground(Color.WHITE);
		lblKlassenverwaltung.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblKlassenverwaltung.setBackground(Color.MAGENTA);
		lblKlassenverwaltung.setBounds(21, 11, 96, 12);
		KlassenPanelButton_1.add(lblKlassenverwaltung);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				"misc/CBS_Logo.png"));
		lblNewLabel_1.setBounds(10, -19, 287, 133);
		headerPanel.add(lblNewLabel_1);

	}

	/**
	 * Die Methode createHeaderLabels erstellt die Labels für den Titel und den
	 * Slogan im Header-Panel.
	 */
	private void createHeaderLabels() {
		JLabel whiteTrashLabel = new JLabel("© W Trash Project");
		whiteTrashLabel.setBackground(new Color(192, 192, 192));
		whiteTrashLabel.setForeground(Color.DARK_GRAY);
		whiteTrashLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		whiteTrashLabel.setBounds(761, -1, 123, 27);
		headerPanel.add(whiteTrashLabel);
	}

	/**
	 * Die Methode updateMouseClickedActivated aktualisiert den Zustand der
	 * mouseClickedActivated-Flags.
	 * 
	 * @param activate1 Der Aktivierungszustand für mouseClickedActivated1.
	 * @param activate2 Der Aktivierungszustand für mouseClickedActivated2.
	 * @param activate3 Der Aktivierungszustand für mouseClickedActivated3.
	 */
	public void updateMouseClickedActivated(boolean activate1, boolean activate2, boolean activate3,
			boolean activate4) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mouseClickedActivated1 = activate1;
				mouseClickedActivated2 = activate2;
				mouseClickedActivated3 = activate3;
				mouseClickedActivated4 = activate4;
			}
		});
	}

	/**
	 * Die Methode selectClick wählt das angegebene Panel aus und aktualisiert den
	 * Hintergrund aller anderen Panel-Buttons.
	 * 
	 * @param panel Das ausgewählte Panel.
	 */
	public void selectClick(JPanel panel) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				panel.setBackground(new Color(88,88,90));
				for (JPanel buttonPanel : allButtonPanels) {
					if (buttonPanel != panel) {
						buttonPanel.setBackground(new Color(0, 129, 198));
					}
				}

			}
		});

	}

	/**
	 * Die Methode menuClicked markiert das angegebene Panel als ausgewählt.
	 * 
	 * @param panel Das ausgewählte Panel.
	 */
	public void menuClicked(JPanel panel) {
		klassePanel.setVisible(false);
		fachPanel.setVisible(false);
		schuelerPanel.setVisible(false);
		notenvergabe.setVisible(false);

		panel.setVisible(true);

	}
}
