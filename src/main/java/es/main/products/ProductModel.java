/**
 * 
 */
package es.main.products;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author Fabbo
 *
 */
public class ProductModel {
	private DataSource dbData;

	/**
	 * @param dbData
	 */
	public ProductModel(DataSource dbData) {
		this.dbData = dbData;
	}
	
	public List<Product> getProducts() throws SQLException {
		List<Product> products = new ArrayList<>();
		
		// Establecer la conexion:
		Connection conn = this.dbData.getConnection();
		// Consulta sql y statement:
		String query = "SELECT * "
					 + "FROM productos "
					 + "ORDER BY codigoarticulo";
		Statement stat = conn.createStatement();
		// Ejecutar consulta sql:
		ResultSet rS = stat.executeQuery(query);
		// Obtener resultados:
		while(rS.next())
			products.add(new Product(
					rS.getInt("codigoarticulo"),
					rS.getString("nombrearticulo"),
					rS.getString("seccion"),
					rS.getDouble("precio"),
					rS.getString("paisdeorigen")));
		
		return products;
	}
}
