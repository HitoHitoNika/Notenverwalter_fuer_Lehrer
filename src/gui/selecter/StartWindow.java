package gui.selecter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import gui.*;
import gui.darkmode.*;

public class StartWindow extends JFrame{
	
	private JButton lightmodeButton = new JButton("Lightmode 🤢");
	private JButton darkmodeButton = new JButton("Darkmode 😎");
	private JPanel contentPane;
	
	
	public StartWindow() {
		initWindow();
		initLightmodeButton();
		initDarkmodeButton();
	}

	private void initLightmodeButton() {
		lightmodeButton.setBackground(UIManager.getColor("Button.background"));
		lightmodeButton.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 try {
						KlassenAuswaehlen_Frame frame = new KlassenAuswaehlen_Frame();
						 frame.setVisible(true);
						 setVisible(false);
					} catch (IOException e1) {
						System.err.println("Fehler beim Aufruf von Dark_Frame 1");
						e1.printStackTrace();
					}
			    }			
		});
		lightmodeButton.setBounds(28, 25, 376, 76);
		getContentPane().add(lightmodeButton);
	}

	private void initDarkmodeButton() {
		darkmodeButton.setBackground(UIManager.getColor("Button.background"));
		darkmodeButton.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 try {
					KlassenAuswaehlen_Frame_Dark frame = new KlassenAuswaehlen_Frame_Dark();
					 frame.setVisible(true);
					 setVisible(false);
				} catch (IOException e1) {
					System.err.println("Fehler beim Aufruf von Dark_Frame 1");
					e1.printStackTrace();
				}
			    }			
		});
		darkmodeButton.setBounds(28, 135, 376, 76);
		getContentPane().add(darkmodeButton);		
	}

	private void initWindow() {
		setTitle("Hauptmenü");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 268);
		contentPane = new JPanel();
	    contentPane.setBackground(Color.WHITE);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	}
}