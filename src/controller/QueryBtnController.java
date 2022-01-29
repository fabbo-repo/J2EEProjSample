package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import model.ConnectionHandler;
import model.QueryExecutor;
import view.AppFrame;

public class QueryBtnController implements ActionListener {
	private AppFrame frame;
	private QueryExecutor queryExec;
	private ConnectionHandler connectionHandler;
	
	public QueryBtnController(AppFrame aF, ConnectionHandler cH) {
		this.frame = aF;
		this.connectionHandler = cH;
		this.queryExec = new QueryExecutor(cH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*String selectedSection = (String) frame.getSectionsBox()
				.getSelectedItem();
		String selectedCountry = (String) frame.getCountryBox()
				.getSelectedItem();
		try {
			frame.getResultArea().setText("");
			ResultSet rs = this.queryExec.dbFilter(selectedSection, selectedCountry);
			while(rs.next()) {
				frame.getResultArea().append(
						rs.getString("nombrearticulo") + ", " 
						+ rs.getString("seccion") + ", " 
						+ rs.getString("precio") + ", " 
						+ rs.getString("paisdeorigen") + "\n");
			}
			rs.close();
		} 
		catch (IOException | ParseException | SQLException e1) {
			JOptionPane.showMessageDialog(frame, e1.getMessage());
		}*/
	}
}
