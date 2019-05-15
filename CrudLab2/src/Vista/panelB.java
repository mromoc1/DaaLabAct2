package Vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class panelB extends JPanel {
	
	private Font f = new Font("Century Gothic", Font.PLAIN, 16);
	
	public JTextField fieldInsertar;
	public JTextField fieldEliminar;
	public JTextField fieldBuscar;
	
	public JButton botonInsertar;
	public JButton botonEliminar;
	public JButton botonBuscar;
	public JButton botonEstadoActual;
	
	private JScrollPane scrollPane;
	public JTextArea textArea;
	
	public panelB() {
		setLayout(new BorderLayout());
		
		add(panelBotones(),BorderLayout.WEST);
		add(panelMensaje(),BorderLayout.CENTER);
		
	}
	
	public JPanel panelBotones(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		
		//panel Oeste
		JPanel panelOeste = new JPanel();
		panelOeste.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel insertarlabel = new JLabel(" Insertar ");
		insertarlabel.setFont(f);
		panelOeste.add(insertarlabel);
		
		JLabel eliminarlabel = new JLabel(" Eliminar ");
		eliminarlabel.setFont(f);
		panelOeste.add(eliminarlabel);
		
		JLabel buscarlabel = new JLabel(" Buscar ");
		buscarlabel.setFont(f);
		panelOeste.add(buscarlabel);
		
		panel.add(panelOeste, BorderLayout.WEST);
		/////////////////////////////////////////
		
		//pannel Central
		
		JPanel panelCentral = new JPanel();
		
		panel.add(panelCentral,BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 1, 0, 0));
		fieldInsertar = new JTextField();
		panelCentral.add(fieldInsertar);
		fieldInsertar.setColumns(5);
		fieldInsertar.setFont(f);
		
		fieldEliminar = new JTextField();
		panelCentral.add(fieldEliminar);
		fieldEliminar.setColumns(10);
		fieldEliminar.setFont(f);
		
		fieldBuscar = new JTextField();
		panelCentral.add(fieldBuscar);
		fieldBuscar.setColumns(10);
		fieldBuscar.setFont(f);
			
		///////////////////////////////////////
		
		//panel Este
		JPanel panelEste = new JPanel();
		
		panel.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new GridLayout(0, 1, 0, 0));
		
		botonInsertar = new JButton(">");
		botonInsertar.setFont(f);
		botonInsertar.setBackground(Color.LIGHT_GRAY);
		panelEste.add(botonInsertar);
		
		botonEliminar = new JButton(">");
		botonEliminar.setFont(f);
		botonEliminar.setBackground(Color.LIGHT_GRAY);
		panelEste.add(botonEliminar);
		
		botonBuscar = new JButton(">");
		botonBuscar.setFont(f);
		botonBuscar.setBackground(Color.LIGHT_GRAY);
		panelEste.add(botonBuscar);
		
		//////////////////////////////////////////
		
		
		botonEstadoActual= new JButton("Estado Actual");
		botonEstadoActual.setBackground(Color.LIGHT_GRAY);
		botonEstadoActual.setFont(f);
		panel.add(botonEstadoActual,BorderLayout.SOUTH);
		
		return panel;
	}
	
	public JPanel panelMensaje() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		
		return panel;
	}

}