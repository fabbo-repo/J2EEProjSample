package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import utils.DBInfo;
import utils.DBLink;

public class ConnectionHandler {
	private Connection myConn;
	private boolean connected;
	
	public ConnectionHandler() {
		this.connected = false;
	}
	
	public synchronized void connectDB() throws FileNotFoundException, 
				IOException, ParseException, SQLException {
		DBInfo db_info = new DBInfo();
        DBLink db_link = new DBLink(db_info);
		db_link.connect();
        this.myConn = db_link.getConnection();
        this.connected = true;
	}
	
	public synchronized void closeDB() throws SQLException {
		this.myConn.close();
		this.connected = false;
	}
	
	public Connection getConnection() {
		return this.myConn;
	}
	
	public synchronized boolean isConnected() {
		return this.connected;
	}
	
	public synchronized void tryConnect() throws FileNotFoundException, 
								IOException, ParseException, SQLException {
		this.connectDB();
		this.closeDB();
	}
}
