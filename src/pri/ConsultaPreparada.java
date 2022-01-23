package pri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DBInfo;
import utils.DBLink;

public class ConsultaPreparada {
	/*public static void main (String[] args) {
		try {
	        DBInfo db_info = new DBInfo();
	        DBLink db_link = new DBLink(db_info);
	        db_link.connect();
	        
	        // 1. CREAR CONEXION
			Connection myConn = db_link.getConnection();
			
			// 2. PREPARAR CONSULTA
			PreparedStatement ps = myConn.prepareStatement("SELECT nombrearticulo FROM productos WHERE codigoarticulo=? AND precio=?");
			
			// 3. ESTABLECER PARÁMETROS DE CONSULTA
			ps.setInt(1, 123);
			ps.setDouble(2, 4.5);
			
			// 4. EJECUTAR Y RECORRER CONSULTA
			ResultSet res  = ps.executeQuery();
			
			while(res.next()) {
				System.out.println(res.getString(1));  // Imprime 'nombrearticulo' de la consulta anterior
			}
			
			res.close();
			
			//REUTILIZACIÓN DE CONSULTA SQL
			
			System.out.println("\nEJECUCIÓN SEGUNDA CONSULTA\n");
			
			ps.setInt(1, 002);
			ps.setDouble(2, 15.99);
			
			// 4. EJECUTAR Y RECORRER CONSULTA
			res  = ps.executeQuery();
			
			while(res.next()) {
				System.out.println(res.getString(1));  // Imprime 'nombrearticulo' de la consulta anterior
			}
			
			res.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (Exception e) {
			System.out.println("NO CONECTA!!");
			e.printStackTrace();
		}
	}*/
}
