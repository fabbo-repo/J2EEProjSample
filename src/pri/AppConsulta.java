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
	
	public AppMarco() {
		setTitle("Consulta BBDD");
		setBounds(500,300,400,400);
		setLayout(new BorderLayout());
		
		JPanel menus = new JPanel();
		menus.setLayout(new FlowLayout());
		
		codigoarticulos = new JComboBox();
		codigoarticulos.setEditable(false);
		codigoarticulos.addItem("Todos");
		
		resultado = new JTextArea(4,50);
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(codigoarticulos);
		
		add(menus, BorderLayout.NORTH);
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta = new JButton("Consulta");
		botonConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ejecutaConsulta();
			}
			
		});
		add(botonConsulta, BorderLayout.SOUTH);
		
		//--------CONEXION CON BBDD---------------------
		try {
	        DBInfo db_info = new DBInfo();
	        DBLink db_link = new DBLink(db_info);
	        db_link.connect();
	        
			myConn = db_link.getConnection();
			Statement st = myConn.createStatement();
			
			// Carga JcomboBox codigoarticulos
			String consulta = "SELECT DISTINCT codigoarticulo FROM productos";
			ResultSet res = st.executeQuery(consulta);
			
			while(res.next()) {
				codigoarticulos.addItem(res.getString(1));
			}
			
			res.close();
		} 
		catch(Exception e) {
			
		}
	}
	
	private void ejecutaConsulta() {
		ResultSet res = null;
		try {
			if(((String) codigoarticulos.getSelectedItem()).equals("Todos")) return;
			int codigoarticulo = Integer.parseInt((String) codigoarticulos.getSelectedItem());
			enviaConsultaCodArticulo = myConn.prepareStatement(consultaCodArticulo);
			enviaConsultaCodArticulo.setInt(1, codigoarticulo); // Interrogante 1 
			res = enviaConsultaCodArticulo.executeQuery();
			while(res.next()) {
				resultado.append(res.getString(1)); //nombrearticulo (consultaCodArticulo)
				resultado.append(", ");
				resultado.append(res.getString(2)); //precio (consultaCodArticulo)
				resultado.append("\n");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Connection myConn;
	private PreparedStatement enviaConsultaCodArticulo;
	private final String consultaCodArticulo = "SELECT nombrearticulo, precio FROM productos WHERE codigoarticulo=?";
	private JComboBox codigoarticulos;
	private JTextArea resultado;
}