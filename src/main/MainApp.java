package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.AppFrame;

public class MainApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	JFrame marco = new AppFrame();
        		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		marco.setVisible(true);
            }
        });
	}
}
