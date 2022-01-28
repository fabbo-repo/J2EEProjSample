package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* Clase encargada de realizar la conexion con la base de datos 
* 
* @author Fabian Scherle
*/
public class DBLink {
	private DBInfo db_info;
	private Connection db_connection;
	
	/**
     * Inicializa la información de la base de datos para
     * establecer la conexión
	 * @param db_info Información de la base de datos
     */
	public DBLink(DBInfo db_info) throws SQLException {
		this.db_info = db_info;
	}
	
	/**
     * Realiza la conexion con la base de datos
     * @throws SQLException En caso de no establecer la conexion
     */
	public void connect() throws SQLException {
		this.db_connection = DriverManager.getConnection(
				"jdbc:postgresql://"+db_info.getIpAddr()+":"
				+db_info.getPort()+"/"+db_info.getDatabase(), 
				db_info.getUser(), db_info.getPassword());
	}

	/**
     * @return DBInfo Informacion de la base de datos
     */
	public DBInfo getDBInfo() {
		return db_info;
	}
	
	/**
     * @return Connection Driver de la base de datos
     */
	public Connection getConnection() {
		return db_connection;
	}
}
