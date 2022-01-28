package view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;

import javax.swing.*;

import org.json.simple.parser.ParseException;

import model.ConnectionHandler;
import controller.MenuController;
import controller.QueryBtnController;
import utils.DBInfo;
import utils.DBLink;

public class AppFrame extends JFrame {
	private JComboBox sectionsBox;
	private JComboBox countryBox;
	private JTextArea resultArea;
	
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
		
		resultArea = new JTextArea(4,50);
		resultArea.setEditable(false);
		this.add(resultArea);
		
		menus.add(sectionsBox);
		menus.add(countryBox);
		
		this.add(menus, BorderLayout.NORTH);
		this.add(resultArea, BorderLayout.CENTER);
		
		JButton botonConsulta = new JButton("Consulta");
		this.add(botonConsulta, BorderLayout.SOUTH);
		
		ConnectionHandler cH;
		cH = new ConnectionHandler();
		botonConsulta.addActionListener(new QueryBtnController(this, cH));
		this.addWindowListener(new MenuController(this, cH));
		
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


	public JTextArea getResultArea() {
		return resultArea;
	}


	public void setResultArea(JTextArea result) {
		this.resultArea = result;
	}
}