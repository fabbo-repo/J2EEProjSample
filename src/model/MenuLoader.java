package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class MenuLoader {
	private ConnectionHandler connHandler;
	private ResultSet lastSecResult;
	private ResultSet lastCntResult;
	
	public MenuLoader(ConnectionHandler cH) {
		this.connHandler = cH;
	}
	
	public void executeQuery() throws SQLException, 
		FileNotFoundException, IOException, ParseException {
		// En caso de que la conexion no esté abierta, se abre
		if(!this.connHandler.isConnected()) 
			this.connHandler.connectDB();
		// Obtener acceso a la conexion
		Connection accessDB = this.connHandler.getConnection();
		// Crear consultas preparadas
		Statement statement = accessDB.createStatement();
		this.lastSecResult = statement.executeQuery(""
				+ "SELECT DISTINCT seccion "
				+ "FROM productos");
		// Es necesario actualizar la statement para otra query
		statement = accessDB.createStatement();
		this.lastCntResult = statement.executeQuery(""
				+ "SELECT DISTINCT paisdeorigen "
				+ "FROM productos");
		// Cerrar la conexion una vez finalizada la query
		this.connHandler.closeDB();
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
