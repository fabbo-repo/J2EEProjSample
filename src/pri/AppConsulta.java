package pri;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import utils.DBInfo;
import utils.DBLink;

public class AppConsulta {
	public static void main(String[] args) {
		
		JFrame marco = new AppMarco();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setVisible(true);
	}
}

class AppMarco extends JFrame {
	private Connection myConn;
	private PreparedStatement preparedStat;
	private JComboBox sectionsBox;
	private JComboBox countryBox;
	private JTextArea resultado;
	private final String sectionQuery = ""
			+ "SELECT nombrearticulo, seccion, precio, paisdeorigen "
			+ "FROM productos "
			+ "WHERE seccion=?";
	private final String countryQuery = ""
			+ "SELECT nombrearticulo, seccion, precio, paisdeorigen "
			+ "FROM productos "
			+ "WHERE paisdeorigen=?";
	private final String scQuery = ""
			+ "SELECT nombrearticulo, seccion, precio, paisdeorigen "
			+ "FROM productos "
			+ "WHERE seccion=? AND paisdeorigen=?";
	
	public AppMarco() {
		setTitle("Consulta BBDD");
		setBounds(500, 300, 400, 400);
		setLayout(new BorderLayout());
		
		JPanel menus = new JPanel();
		menus.setLayout(new FlowLayout());
		
		sectionsBox = new JComboBox();
		sectionsBox.setEditable(false);
		sectionsBox.addItem("Todos");
		
		countryBox = new JComboBox();
		countryBox.setEditable(false);
		countryBox.addItem("Todos");
		
		resultado = new JTextArea(4,50);
		resultado.setEditable(false);
		this.add(resultado);
		
		menus.add(sectionsBox);
		menus.add(countryBox);
		
		this.add(menus, BorderLayout.NORTH);
		this.add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta = new JButton("Consulta");
		botonConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutaConsulta();
			}
		});
		this.add(botonConsulta, BorderLayout.SOUTH);
		
		//--------CONEXION CON BBDD---------------------
		try {
	        DBInfo db_info = new DBInfo();
	        DBLink db_link = new DBLink(db_info);
	        try{ db_link.connect(); } 
	        catch(SQLException e) {
	        	JOptionPane.showMessageDialog(this, "Error de conexion");
	        	System.exit(-1);
	        }
	        this.myConn = db_link.getConnection();
			Statement st = myConn.createStatement();
			
			// Carga JcomboBox secciones
			String consulta = "SELECT DISTINCT seccion FROM productos";
			ResultSet res = st.executeQuery(consulta);
			while(res.next())
				this.sectionsBox.addItem(res.getString("seccion"));
			// Tambien es valido res.getString(1), 
			// hace referencia a la primera columna
			
			// Carga JcomboBox secciones
			consulta = "SELECT DISTINCT paisdeorigen FROM productos";
			res = st.executeQuery(consulta);
			while(res.next())
				this.countryBox.addItem(res.getString("paisdeorigen"));
			res.close();
		} 
		catch(Exception e) {
			JOptionPane.showConfirmDialog(this, "Error de tipo: "
					+e.getMessage());
			System.exit(-1);
		}
	}
	
	private void ejecutaConsulta() {
		ResultSet res = null;
		try {
			// Resetear el cuadro de resultado
			this.resultado.setText("");
			// Obtener valores de los ComboBox
			String section = (String) this.sectionsBox.getSelectedItem();
			String country = (String) this.countryBox.getSelectedItem();
			
			if(!section.equals("Todos") && country.equals("Todos")) {
				this.preparedStat = this.myConn.prepareStatement(
						this.sectionQuery);
				// Modificar el primer interrogante:
				this.preparedStat.setString(1, section); 
				res = this.preparedStat.executeQuery();
			}
			else if(section.equals("Todos") && !country.equals("Todos")) {
				this.preparedStat = this.myConn.prepareStatement(
						this.countryQuery);
				// Modificar el primer interrogante:
				this.preparedStat.setString(1, country); 
				res = this.preparedStat.executeQuery();
			}
			else if(!section.equals("Todos") && !country.equals("Todos")) {
				this.preparedStat = this.myConn.prepareStatement(
						this.scQuery);
				// Modificar el primer interrogante:
				this.preparedStat.setString(1, section); 
				// Modificar el segundo interrogante:
				this.preparedStat.setString(2, country); 
				res = this.preparedStat.executeQuery();
			}
			while(res.next()) {
				this.resultado.append(res.getString("nombrearticulo")+", ");
				this.resultado.append(res.getString("seccion")+", ");
				this.resultado.append(res.getString("precio")+", ");
				this.resultado.append(res.getString("paisdeorigen"));
				this.resultado.append("\n");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error de tipo: "
					+e.getMessage());
		}
	}
}