package view;

import javax.swing.*;


public class AppFrame extends JFrame {
	
	public AppFrame() {
		setTitle("Tables DB");
		setBounds(300, 300, 700, 700);
		PaneDB paneDB = new PaneDB(this);
		this.add(paneDB);
	}
}