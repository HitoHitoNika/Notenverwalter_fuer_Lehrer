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

public class Dashboard_Gui extends JFrame {

	private JPanel Header;
	private Panel_Klasse klassePanel;
	private Panel_Fach fachPanel;
	private Panel_Schueler schuelerPanel;
	private Panel_Notenvergabe_Screen notenvergabe ;
	private boolean mouseClickedActivated1 = false;
	private boolean mouseClickedActivated2 = false;
	private boolean mouseClickedActivated3 = false;
	
	JPanel panel = new JPanel();
	JPanel panel_1_1 = new JPanel();
	JPanel panel_1_2 = new JPanel();
	JPanel[] allButtonPanels = { panel, panel_1_1, panel_1_2 };
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
	 * @throws IOException 
	 */
	public Dashboard_Gui() throws IOException {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 645);
		Header = new JPanel();
		Header.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Header);
		Header.setLayout(null);

		panel.setBackground(new Color(102, 139, 170));
		panel.setBounds(0, 0, 886, 143);
		Header.add(panel);
		panel.setLayout(null);

		klassePanel = new Panel_Klasse();
		fachPanel = new Panel_Fach();
		schuelerPanel = new Panel_Schueler();
		notenvergabe = new Panel_Notenvergabe_Screen();

		panel_1_1.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				panel_1_1.setBackground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				if (!mouseClickedActivated2) {
					panel_1_1.setBackground(new Color(63, 90, 114));
				} else {
					panel_1_1.setBackground(Color.BLACK);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(schuelerPanel);
				
				schuelerPanel.refreshCombo();
				updateMouseClickedActivated(false, true, false);

			
				panel_1_2.setBackground(new Color(63, 90, 114));

			}
		});
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(63, 90, 114));
		panel_1_1.setBounds(251, 112, 131, 31);
		panel.add(panel_1_1);

		JLabel lblSchlerverwaltung = new JLabel("Schülerverwaltung");
		lblSchlerverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchlerverwaltung.setForeground(Color.WHITE);
		lblSchlerverwaltung.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblSchlerverwaltung.setBackground(Color.MAGENTA);
		lblSchlerverwaltung.setBounds(21, 11, 96, 12);
		panel_1_1.add(lblSchlerverwaltung);

		panel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1_2.setBackground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				if (!mouseClickedActivated3) {
					panel_1_2.setBackground(new Color(63, 90, 114));
				} else {
					panel_1_2.setBackground(Color.BLACK);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(fachPanel);

				updateMouseClickedActivated(false, false, true);
			fachPanel.refreshCombo(); 
				
				panel_1_1.setBackground(new Color(63, 90, 114));

			}
		});
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(63, 90, 114));
		panel_1_2.setBounds(448, 112, 131, 31);
		panel.add(panel_1_2);

		JLabel lblNotenverwaltung = new JLabel("Fachverwaltung");
		lblNotenverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotenverwaltung.setForeground(Color.WHITE);
		lblNotenverwaltung.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblNotenverwaltung.setBackground(Color.MAGENTA);
		lblNotenverwaltung.setBounds(21, 11, 96, 12);
		panel_1_2.add(lblNotenverwaltung);

		JLabel lblNewLabel_1 = new JLabel("A W Trash Project  X ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(89, 11, 207, 40);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Noten im Griff");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(89, 49, 207, 40);
		panel.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(klassePanel);

				updateMouseClickedActivated(true, false, false);

			
				panel_1_2.setBackground(new Color(63, 90, 114));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		btnNewButton.setBounds(52, 120, 141, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(notenvergabe);

			}
		});
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBackground(new Color(63, 90, 114));
		panel_1_2_1.setBounds(668, 112, 131, 31);
		panel.add(panel_1_2_1);
		
		JLabel lblNotenverwaltung_1 = new JLabel("Notenvergabe");
		lblNotenverwaltung_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotenverwaltung_1.setForeground(Color.WHITE);
		lblNotenverwaltung_1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));
		lblNotenverwaltung_1.setBackground(Color.MAGENTA);
		lblNotenverwaltung_1.setBounds(21, 11, 96, 12);
		panel_1_2_1.add(lblNotenverwaltung_1);

		JPanel MainContent = new JPanel();
		MainContent.setBounds(0, 143, 886, 331);
		Header.add(MainContent);
		MainContent.setLayout(null);

		MainContent.add(klassePanel);
		MainContent.add(fachPanel);
		MainContent.add(schuelerPanel);
		MainContent.add(notenvergabe);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(141, 544, 1, 1);
		Header.add(desktopPane);

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
