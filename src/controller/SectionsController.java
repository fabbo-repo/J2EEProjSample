package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.SectionsLoader;
import view.AppFrame;

public class SectionsController extends WindowAdapter{
	private SectionsLoader secLoader;
	private AppFrame frame;
	
	public SectionsController(AppFrame aF, ConnectionHandler cH) {
		this.secLoader = new SectionsLoader(cH);
		this.frame = aF;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		try {
			ArrayList<String> sections = this.secLoader.executeQuery();
			for(String s : sections)
				this.frame.getSectionsBox().addItem(s);
		} 
		catch (SQLException e1) {
			JOptionPane.showMessageDialog(this.frame, "Error: "+e1.getMessage());
			System.exit(-1);
		}
	}
}