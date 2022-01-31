/**
 * 
 */
package es.main.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
					rS.getDate("fecha"),
					rS.getString("paisdeorigen")));
		
		return products;
	}

	public void insertProduct(Product prod) throws SQLException {
		// Obtener conexion:
		Connection conn = this.dbData.getConnection();
		// Instrucción SQL para insertar el producto y statement
		String insertSQL = ""
				+ "INSERT INTO productos "
				+ "(codigoarticulo, nombrearticulo, seccion, "
				+ "precio, fecha, paisdeorigen) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stat = conn.prepareStatement(insertSQL);
		// Establecer parametros para el producto:	
		stat.setInt(1, prod.getCodProduct());
		stat.setString(2, prod.getProductName());
		stat.setString(3, prod.getSection());
		stat.setDouble(4, prod.getPrice());
		// Para el caso de la fecha, se debe convertir de 
		// java.util.Date a java.sql.Date
		// La clase de sql carece de hora a diferencia de la de util
		stat.setDate(5, new java.sql.Date(
				prod.getOriginDate().getTime()));
		stat.setString(6, prod.getOriginCountry());
		// Ejecutar instruccion SQL
		stat.execute();
	}

	public Product getProduct(int codProduct) throws IllegalArgumentException, SQLException {
		// Establecer conexion con la base de datos
		Connection cnn = this.dbData.getConnection();
		// Crear consulta SQL
		String query = "SELECT * "
					 + "FROM productos "
					 + "WHERE codigoarticulo=?";
		// Crear consulta preparada
		PreparedStatement stat = cnn.prepareStatement(query);
		// Establecer parametros
		stat.setInt(1, codProduct);
		// Ejecutar consulta
		ResultSet rS = stat.executeQuery();
		// Obtener datos
		if(rS.next())
			return new Product(
					rS.getInt("codigoarticulo"),
					rS.getString("nombrearticulo"),
					rS.getString("seccion"),
					rS.getDouble("precio"),
					rS.getDate("fecha"),
					rS.getString("paisdeorigen"));
		else throw new IllegalArgumentException("Producto con codigo"
					+codProduct+" no disponible");
	}

	public void updateProduct(Product prod) throws SQLException {
		// Establecer conexion
		Connection cnn = this.dbData.getConnection();
		// Crear instruccion SQL
		String update = "UPDATE productos "
					  + "SET nombrearticulo=?, seccion=?, precio=?, "
					  + "fecha=?, paisdeorigen=? "
					  + "WHERE codigoarticulo=?";
		// Crear sentencia preparada
		PreparedStatement stat = cnn.prepareStatement(update);
		// Establecer parametros
		stat.setString(1, prod.getProductName());
		stat.setString(2, prod.getSection());
		stat.setDouble(3, prod.getPrice());
		// Para el caso de la fecha, se debe convertir de 
		// java.util.Date a java.sql.Date
		// La clase de sql carece de hora a diferencia de la de util
		stat.setDate(4, new java.sql.Date(
				prod.getOriginDate().getTime()));
		stat.setString(5, prod.getOriginCountry());
		stat.setInt(6, prod.getCodProduct());
		// Ejecutar instruccion SQL
		stat.execute();	
	}

	public void deleteProduct(int codProduct) throws SQLException {
		// Establecer conexion
		Connection cnn = this.dbData.getConnection();
		// Crear instruccion SQL
		String update = "DELETE "
					  + "FROM productos "
					  + "WHERE codigoarticulo=?";
		// Crear sentencia preparada
		PreparedStatement stat = cnn.prepareStatement(update);
		// Establecer parametros
		stat.setInt(1, codProduct);
		// Ejecutar instruccion SQL
		stat.execute();	
	}
}
