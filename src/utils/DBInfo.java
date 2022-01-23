package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
* Clase encargada de leer los datos necesarios para la conexion con la 
* base de datos desde un fichero JSON
*
* @author Fabian Scherle
*/
public class DBInfo {
	private String ip_addr;
	private String port;
	private String database;
	private String user;
	private String password;
	
	/**
     * Inicializa los atributos haciendo llamada a reload()
     */
	public DBInfo() throws FileNotFoundException, IOException, ParseException {
		this.reload();
	}
	
	/**
     * Inicializa los atributos haciendo llamada a reload(path)
     * @param path Ruta del fichero JSON 
     */
	public DBInfo(String path) throws FileNotFoundException, IOException, ParseException {
		this.reload(path);
	}
	
	/**
     * Este metodo llama reload con el fichero JSON ubicado en el directorio data
     */
	public void reload() throws FileNotFoundException, IOException, ParseException {
		this.reload("data"+System.getProperty("file.separator")+"database.json"); 
	}
	

	/**
     * Este metodo llama reload con el fichero JSON ubicado en el directorio data
     * @param path Ruta del fichero JSON 
     */
	public void reload(String path) throws FileNotFoundException, IOException, ParseException {
		Object ob = new JSONParser().parse(
        		new FileReader("data"+System.getProperty("file.separator")+"database.json"));
		JSONObject js = (JSONObject) ob;

        this.ip_addr = (String) js.get("ip_addr");
        this.port = (String) js.get("port");
        this.database = (String) js.get("database");
        this.user = (String) js.get("user");
        this.password = (String) js.get("password");
        //System.out.println(System.getProperty("user.dir"));
	}

	/**
     * @return String Direccion ip del servidor donde se ubica la base de datos
     */
	public String getIpAddr() {
		return ip_addr;
	}

	/**
     * @return String Puerto del servidor donde se ubica la base de datos
     */
	public String getPort() {
		return port;
	}

	/**
     * @return String Nombre de la base de datos
     */
	public String getDatabase() {
		return database;
	}

	/**
     * @return String Nombre del usuario a usar para administrar la base de datos
     */
	public String getUser() {
		return user;
	}

	/**
     * @return String Contraseña de usuario
     */
	public String getPassword() {
		return password;
	}
}
