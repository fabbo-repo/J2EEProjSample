package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import model.ConnectionHandler;
import view.PaneDB;

public class TableController implements ActionListener{
	private ConnectionHandler connHandler;
	private PaneDB paneDB;
	
	public TableController(PaneDB pDB, ConnectionHandler cH) {
		super();
		this.paneDB = pDB;
		this.connHandler = cH;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String tableName = (String) ((JComboBox) e.getSource()).
				getSelectedItem();
		this.showTableInfo(tableName);
	}

	public void showTableInfo(String table) {
		ArrayList<String> fields = new ArrayList<>();
		String query = "SELECT * FROM " + table;
		
		try {
			this.paneDB.getInfoArea().setText("");
			if(!this.connHandler.isConnected()) 
				this.connHandler.connectDB();
			Statement statement = this.connHandler.getConnection()
					.createStatement();
			ResultSet rS = statement.executeQuery(query);
			ResultSetMetaData rsDB = rS.getMetaData();
			
			for(int i = 1; i <= rsDB.getColumnCount(); i++) 
				fields.add(rsDB.getColumnLabel(i));
			
			while(rS.next()) { 
				for(String f : fields) 
					this.paneDB.getInfoArea().append(rS.getString(f)+"   ");
				this.paneDB.getInfoArea().append("\n");
			}
		} 
		catch (IOException | ParseException | SQLException e) {
			JOptionPane.showMessageDialog(this.paneDB.getParent(), e);
		}
	}
}
