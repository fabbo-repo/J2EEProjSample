package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import model.ConnectionHandler;
import model.MenuLoader;
import view.AppFrame;

public class MenuController extends WindowAdapter{
	private MenuLoader menuLoader;
	private AppFrame frame;
	
	public MenuController(AppFrame aF, ConnectionHandler cH) {
		this.menuLoader = new MenuLoader(cH);
		this.frame = aF;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		try {
			this.menuLoader.executeQuery();
			while(this.menuLoader.getLastSecResult().next())
				this.frame.getSectionsBox().addItem(
						this.menuLoader.getLastSecResult().getString("seccion"));
			while(this.menuLoader.getLastCntResult().next())
				this.frame.getCountryBox().addItem(
						this.menuLoader.getLastCntResult().getString("paisdeorigen"));
			this.menuLoader.closeSecResult();
			this.menuLoader.closeCntResult();
		} 
		catch (SQLException | IOException | ParseException e1) {
			JOptionPane.showMessageDialog(this.frame, "Error: "+e1.getMessage());
			System.exit(-1);
		}
	}
}