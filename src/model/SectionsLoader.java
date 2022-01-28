package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.ConnectionHandler;

public class SectionsLoader {
	private ConnectionHandler connHandler;
	private ResultSet lastResult;
	
	public SectionsLoader(ConnectionHandler cH) {
		this.connHandler = cH;
	}
	
	public ArrayList<String> executeQuery() throws SQLException {
		Connection accessDB = this.connHandler.getConnection();
		Statement sections = accessDB.createStatement();
		this.lastResult = sections.executeQuery(""
				+ "SELECT DISTINCT seccion "
				+ "FROM productos");
		ArrayList<String> sectionList = new ArrayList<>();
		while(this.lastResult.next()) {
			sectionList.add(this.lastResult.getString("seccion"));
		}
		return sectionList;
	}
	
	public ResultSet getLastResult() {
		return this.lastResult;
	}
}
