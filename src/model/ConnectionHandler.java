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
	
	public ConnectionHandler() throws FileNotFoundException, 
						IOException, ParseException, SQLException {
		DBInfo db_info = new DBInfo();
        DBLink db_link = new DBLink(db_info);
        db_link.connect();
        this.myConn = db_link.getConnection();
	}
	
	public Connection getConnection() {
		return this.myConn;
	}
}
