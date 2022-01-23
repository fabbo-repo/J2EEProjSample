package pri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

import org.json.simple.parser.ParseException;

import utils.DBInfo;
import utils.DBLink;

public class Conexion {

	public static void main(String[] args) {
		try {
	        DBInfo db_info = new DBInfo();
	        DBLink db_link = new DBLink(db_info);
	        db_link.connect();
	        
	        // 1. CREAR CONEXION
			Connection myConn = db_link.getConnection();
			// 2. CREAR OBJETO STATEMENT
			Statement myState = myConn.createStatement();
			// 3. EJECUTAR SQL
			ResultSet res = myState.executeQuery("SELECT * FROM productos");
			// 4. RECOGER EL RESULTSET
			while(res.next()) {
				System.out.println(res.getString("nombrearticulo") + " " + res.getString("codigoarticulo") + " " + 
						res.getDouble("precio")*2 + " " + res.getDate("fecha"));
			}
		}
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (ParseException e) {e.printStackTrace();}
		catch (Exception e) {
			System.out.println("NO CONECTA!!");
			e.printStackTrace();
		}
	}
}
