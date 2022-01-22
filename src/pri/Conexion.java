package pri;
import java.sql.*;

public class Conexion {

	public static void main(String[] args) {
		try {
			//1. CREAR CONEXION
			Connection myConn = DriverManager.getConnection("jdbc:postgresql://192.168.0.35:5432/javadb1", "javauser1db", "j4a7vapqw3");
			//2. CREAR OBJETO STATEMENT
			Statement myState = myConn.createStatement();
			//3. EJECUTAR SQL
			ResultSet res = myState.executeQuery("SELECT * FROM productos");
			//4. RECOGER EL RESULTSET
			while(res.next()) {
				System.out.println(res.getString("NOMBREARTICULO") + " " + res.getString("CODIGOARTICULO") + " " + res.getDouble("PRECIO")*2
						+ " " + res.getDate("FECHA"));
			}
		}
		catch (Exception e) {
			System.out.println("NO CONECTA!!");
			e.printStackTrace();
		}
	}
}
