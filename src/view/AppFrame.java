package view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;

import javax.swing.*;

import org.json.simple.parser.ParseException;

import model.ConnectionHandler;
import controller.MenuController;
import utils.DBInfo;
import utils.DBLink;

public class AppFrame extends JFrame {
	private JComboBox sectionsBox;
	private JComboBox countryBox;
	private JTextArea resultado;
	
	public AppFrame() {
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
		this.add(botonConsulta, BorderLayout.SOUTH);
		
		ConnectionHandler cH;
		try {
			cH = new ConnectionHandler();
			this.addWindowListener(new MenuController(this, cH));
		} 
		catch (IOException | ParseException | SQLException e) {
			JOptionPane.showMessageDialog(this, "Error: "+e.getMessage());
		}
		
	}
	

	public JComboBox getSectionsBox() {
		return sectionsBox;
	}

	public void setSectionsBox(JComboBox sectionsBox) {
		this.sectionsBox = sectionsBox;
	}

	public JComboBox getCountryBox() {
		return countryBox;
	}

	public void setCountryBox(JComboBox countryBox) {
		this.countryBox = countryBox;
	}
}