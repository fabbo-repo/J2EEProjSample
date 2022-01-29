package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.json.simple.parser.ParseException;

import controller.TableController;
import model.ConnectionHandler;

public class PaneDB extends JPanel {
	private JComboBox tableBox;
	private JTextArea infoArea;
	private ConnectionHandler connHandler;
	private JFrame parent;
	
	public PaneDB(JFrame parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());
		
		this.tableBox = new JComboBox();
		// Bug fixed:
		this.tableBox.setLightWeightPopupEnabled(false);
		
		this.infoArea = new JTextArea();
		this.connHandler = new ConnectionHandler();
		try {
			this.connHandler.tryConnect();
		} 
		catch (IOException | ParseException | SQLException e) {
			JOptionPane.showMessageDialog(this.parent, e);
			System.exit(-1);
		}
		this.loadTableBox();
		
		this.add(this.infoArea, BorderLayout.CENTER);
		this.add(this.tableBox, BorderLayout.NORTH);
		
		this.tableBox.addActionListener(
				new TableController(this, this.connHandler));
	}
	
	public void loadTableBox() {
		try {
			if(!this.connHandler.isConnected())
				this.connHandler.connectDB();
			DatabaseMetaData dbData = this.connHandler.getConnection()
					.getMetaData();
			// Si no se especifica el schema, se muestran todas las tablas
			// de la base de datos incluyendo las internas de postgres
			ResultSet rS = dbData.getTables(null, "public", null, null);
			while(rS.next()) 
				if(!rS.getString("table_name").contains("pkey"))
					this.tableBox.addItem(rS.getString("table_name"));
			rS.close();
			this.connHandler.closeDB();
		} 
		catch (IOException | ParseException | SQLException e) {
			JOptionPane.showMessageDialog(this.parent, e);
		}
		
	}

	/**
	 * @return the tableBox
	 */
	public JComboBox getTableBox() {
		return tableBox;
	}

	/**
	 * @return the infoArea
	 */
	public JTextArea getInfoArea() {
		return infoArea;
	}

	/**
	 * @return the parent
	 */
	public JFrame getParent() {
		return parent;
	}
}