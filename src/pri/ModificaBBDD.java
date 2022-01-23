package pri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.parser.ParseException;

import utils.DBInfo;
import utils.DBLink;

public class ModificaBBDD {
	
	/*public static void main(String[] args) {
		try {
	        DBInfo db_info = new DBInfo();
	        DBLink db_link = new DBLink(db_info);
	        db_link.connect();
	        
	        // 1. CREAR CONEXION
			Connection myConn = db_link.getConnection();
			
			// 2. CREAR OBJETO STATEMENT
			Statement st = myConn.createStatement();
			//String instSQL = "INSERT INTO productos (codigoarticulo, nombrearticulo, precio) VALUES (002, 'Pantalón', 15.99)";
			//st.executeUpdate(instSQL);
			System.out.println("Datos insertados correctamente");
			
		}
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (ParseException e) {e.printStackTrace();}
		catch (Exception e) {
			System.out.println("NO CONECTA!!");
			e.printStackTrace();
		}
	}*/
	
}
