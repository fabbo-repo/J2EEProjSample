package pri;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Conexion {

	public static void main(String[] args) {
		try {
	        Object ob = new JSONParser().parse(new FileReader("data"+System.getProperty("file.separator")+"database.json"));
			JSONObject js = (JSONObject) ob;

	        String ip_addr = (String) js.get("ip_addr");
	        String port = (String) js.get("port");
	        String database = (String) js.get("database");
	        String user = (String) js.get("user");
	        String password = (String) js.get("password");
	        System.out.println(System.getProperty("user.dir"));
	        
	        //1. CREAR CONEXION
			Connection myConn = DriverManager.getConnection("jdbc:postgresql://"+ip_addr+":"+port+"/"+database, 
					user, password);
			//2. CREAR OBJETO STATEMENT
			Statement myState = myConn.createStatement();
			//3. EJECUTAR SQL
			ResultSet res = myState.executeQuery("SELECT * FROM productos");
			//4. RECOGER EL RESULTSET
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
