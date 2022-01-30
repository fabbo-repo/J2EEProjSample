package es.main.products;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/products")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductModel prodModel;

	/**
	 *  Definir o establecer el DataSource 
	 *  name se define en el fichero context.xml
	 */
	@Resource(name="jdbc/postgres")
	private DataSource pool;
	
	/**
	 *  Metodo que se ejecuta al arrancar el servlet
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			prodModel = new ProductModel(this.pool);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
																							IOException {
		try {
			// Obtener lista de productos:
			List<Product> products = this.prodModel.getProducts();
			// Agregar lista de productos al request
			request.setAttribute("product_list", products);
			// ENviar rquest a la JSP
			RequestDispatcher disp = request.getRequestDispatcher("/jsps/product-list.jsp");
			disp.forward(request, response);
		}
		catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
