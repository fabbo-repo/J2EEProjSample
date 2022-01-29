package storedProc;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import utils.DBInfo;
import utils.DBLink;

public class Example {
	public static void main2(String[] args) {
		DBInfo db_info;
		try {
			db_info = new DBInfo();
			DBLink db_link;
			db_link = new DBLink(db_info);
			db_link.connect();
			Connection myConn = db_link.getConnection();
			CallableStatement mySentence = myConn.prepareCall(
					" call update_prod(?, ?) ");
			mySentence.setInt(1, 5);
			mySentence.setString(2, "Barbie");
			mySentence.execute();
	    } 
		catch (IOException | ParseException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}
