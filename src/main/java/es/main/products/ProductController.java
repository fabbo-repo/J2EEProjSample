package es.main.products;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// Leer parametro que recibe en la peticion
		String param = request.getParameter("instruction");
		// En caso de que no exista parametro se trata de listar productos
		if(param == null) param = "productList";
		// Clasificar peticion según parametro
		if(param.equals("productList"))
			this.getProducts(request, response);
		else if(param.equals("productInsert"))
			this.insertProduct(request, response);
		else if(param.equals("loadUpdate"))
			this.loadUpdate(request, response);
		else if(param.equals("productUpdate"))
			this.updateProduct(request, response);
		else if(param.equals("productDelete"))
			this.deleteProduct(request, response);
		// Por defecto: listar productos
		else this.getProducts(request, response);
	}

	private void getProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// Obtener lista de productos:
			List<Product> products = this.prodModel.getProducts();
			// Agregar lista de productos al request
			request.setAttribute("product_list", products);
			// ENviar rquest a la JSP
			RequestDispatcher disp = request.getRequestDispatcher("/jsps/product-list.jsp");
			disp.forward(request, response);
		}
		catch (SQLException | IOException e) {
			throw new ServletException(e);
		}
	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// Leer formulario y rear objeto de tipo Product:
			Product prod = new Product(
						// Nota: se deben usar los ids definidos en el formulario
						Integer.parseInt(request.getParameter("cod_product")),
						request.getParameter("product_name"),
						request.getParameter("section"),
						Double.parseDouble(request.getParameter("price")),
						(new SimpleDateFormat("yyyy-MM-dd")).parse(
								request.getParameter("origin_date")),
						request.getParameter("origin_country")
					);
			// Enviar objeto al modelo e insertar en base de datos:
			this.prodModel.insertProduct(prod);
			// Regresar al listado de productos
			this.getProducts(request, response);
		}
		catch(NumberFormatException | ParseException | SQLException e) {
			throw new ServletException(e);
		}
	}

	private void loadUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// Leer codigo articulo del producto a actualizar
			String codProduct = request.getParameter("codproduct");
			// Enviar codigo articulo al modelo
			Product product = this.prodModel.getProduct(Integer.parseInt(codProduct));
			// Actualizar codigo articulo
			request.setAttribute("prod", product);
			// Enviar Producto al formulario a actualizar
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/update-product.jsp");
			dispatcher.forward(request, response);
		} 
		catch (IOException | SQLException | IllegalArgumentException e) {
			throw new ServletException(e);
		}
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// Leer los datos que le vienen del formulario de actualizar
			Product prod = new Product(
					// Nota: se deben usar los ids definidos en el formulario
					Integer.parseInt(request.getParameter("cod_product")),
					request.getParameter("product_name"),
					request.getParameter("section"),
					Double.parseDouble(request.getParameter("price")),
					(new SimpleDateFormat("yyyy-MM-dd")).parse(
							request.getParameter("origin_date")),
					request.getParameter("origin_country")
				);
			// Actualizar base de datos
			this.prodModel.updateProduct(prod);
			// Regresar al listado de productos
			this.getProducts(request, response);
		} 
		catch (NumberFormatException | ParseException | SQLException e) {
			throw new ServletException(e);
		}
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			// Leer codigo articulo del producto a eliminar
			String codProduct = request.getParameter("codproduct");
			// Eliminarproducto
			this.prodModel.deleteProduct(Integer.parseInt(codProduct));
			// Regresar al listado de productos
			this.getProducts(request, response);
		}
		catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
