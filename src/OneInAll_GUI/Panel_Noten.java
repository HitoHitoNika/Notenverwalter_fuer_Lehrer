package OneInAll_GUI;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_Noten extends JPanel {
	private JTextField txtSeite;

	/**
	 * Create the panel.
	 */
	public Panel_Noten() {
		setBounds(0, 0, 886, 331);
setLayout(null);
txtSeite = new JTextField();
txtSeite.setFont(new Font("Tahoma", Font.PLAIN, 41));
txtSeite.setText("Seite2");
txtSeite.setBounds(129, 115, 538, 59);
add(txtSeite);
txtSeite.setColumns(10);
	}
}
