package es.main.products;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/products")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 *  Definir o establecer el DataSource 
	 *  name se define en el fichero context.xml
	 */
	@Resource(name="jdbc/postgres")
	private DataSource pool;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Creacion de objeto writer
		PrintWriter output = response.getWriter();
		response.setContentType("text/plain");
		
		// Crear conexion con la base de datos
		try {
			Connection connection = this.pool.getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM productos";
			ResultSet rS = statement.executeQuery(query);
			
			while(rS.next()) 
				output.println(rS.getString("nombrearticulo"));
		} 
		catch (SQLException e) {
			output.print(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
