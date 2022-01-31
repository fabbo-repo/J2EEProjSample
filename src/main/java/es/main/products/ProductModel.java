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
		Connection cnn = null;
		Statement stat = null;
		ResultSet rS = null;
		try {
			// Establecer la conexion:
			cnn = this.dbData.getConnection();
			// Consulta sql y statement:
			String query = "SELECT * "
						 + "FROM productos "
						 + "ORDER BY codigoarticulo";
			stat = cnn.createStatement();
			// Ejecutar consulta sql:
			rS = stat.executeQuery(query);
			// Obtener resultados:
			while(rS.next())
				products.add(new Product(
						rS.getInt("codigoarticulo"),
						rS.getString("nombrearticulo"),
						rS.getString("seccion"),
						rS.getDouble("precio"),
						rS.getDate("fecha"),
						rS.getString("paisdeorigen")));
		}
		finally {
			if(rS != null) rS.close();
			if(stat != null) stat.close();
			if(cnn != null) cnn.close();
		}
		
		return products;
	}

	public void insertProduct(Product prod) throws SQLException {
		Connection cnn = null;
		PreparedStatement stat = null;
		try {
			// Obtener conexion:
			cnn = this.dbData.getConnection();
			// Instrucción SQL para insertar el producto y statement
			String insertSQL = ""
					+ "INSERT INTO productos "
					+ "(codigoarticulo, nombrearticulo, seccion, "
					+ "precio, fecha, paisdeorigen) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			stat = cnn.prepareStatement(insertSQL);
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
		finally {
			if(stat != null) stat.close();
			if(cnn != null) cnn.close();
		}
	}

	public Product getProduct(int codProduct) throws IllegalArgumentException, SQLException {
		Connection cnn = null;
		PreparedStatement stat = null;
		ResultSet rS = null;
		try {
			// Establecer conexion con la base de datos
			cnn = this.dbData.getConnection();
			// Crear consulta SQL
			String query = "SELECT * "
						 + "FROM productos "
						 + "WHERE codigoarticulo=?";
			// Crear consulta preparada
			stat = cnn.prepareStatement(query);
			// Establecer parametros
			stat.setInt(1, codProduct);
			// Ejecutar consulta
			rS = stat.executeQuery();
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
		finally {
			if(rS != null) rS.close();
			if(stat != null) stat.close();
			if(cnn != null) cnn.close();
		}
	}

	public void updateProduct(Product prod) throws SQLException {
		Connection cnn = null;
		PreparedStatement stat = null;
		try {
			// Establecer conexion
			cnn = this.dbData.getConnection();
			// Crear instruccion SQL
			String update = "UPDATE productos "
						  + "SET nombrearticulo=?, seccion=?, precio=?, "
						  + "fecha=?, paisdeorigen=? "
						  + "WHERE codigoarticulo=?";
			// Crear sentencia preparada
			stat = cnn.prepareStatement(update);
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
		finally {
			if(stat != null) stat.close();
			if(cnn != null) cnn.close();
		}
	}

	public void deleteProduct(int codProduct) throws SQLException {
		Connection cnn = null;
		PreparedStatement stat = null;
		try {
			// Establecer conexion
			cnn = this.dbData.getConnection();
			// Crear instruccion SQL
			String update = "DELETE "
						  + "FROM productos "
						  + "WHERE codigoarticulo=?";
			// Crear sentencia preparada
			stat = cnn.prepareStatement(update);
			// Establecer parametros
			stat.setInt(1, codProduct);
			// Ejecutar instruccion SQL
			stat.execute();
		}
		finally {
			if(stat != null) stat.close();
			if(cnn != null) cnn.close();
		}
	}
}
