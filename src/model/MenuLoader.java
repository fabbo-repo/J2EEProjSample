package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MenuLoader {
	private ConnectionHandler connHandler;
	private ResultSet lastSecResult;
	private ResultSet lastCntResult;
	
	public MenuLoader(ConnectionHandler cH) {
		this.connHandler = cH;
	}
	
	public void executeQuery() throws SQLException {
		Connection accessDB = this.connHandler.getConnection();
		Statement statement = accessDB.createStatement();
		this.lastSecResult = statement.executeQuery(""
				+ "SELECT DISTINCT seccion "
				+ "FROM productos");
		this.lastCntResult = statement.executeQuery(""
				+ "SELECT DISTINCT paisdeorigen "
				+ "FROM productos");
	}
	
	public ResultSet getLastSecResult() {
		return this.lastSecResult;
	}

	public ResultSet getLastCntResult() {
		return this.lastCntResult;
	}
	
	public void closeSecResult() throws SQLException {
		this.lastSecResult.close();
	}
	
	public void closeCntResult() throws SQLException {
		this.lastCntResult.close();
	}
}
