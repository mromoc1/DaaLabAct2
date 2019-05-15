package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Vista.panelB;

public class controladorB implements ActionListener,KeyListener {
	
	panelB panelb;

	public controladorB(panelB panelb) {
		this.panelb = panelb;
		
		this.panelb.botonInsertar.addActionListener(this);
		this.panelb.botonEliminar.addActionListener(this);
		this.panelb.botonBuscar.addActionListener(this);
		this.panelb.botonEstadoActual.addActionListener(this);
		
		this.panelb.fieldBuscar.addKeyListener(this);
		this.panelb.fieldEliminar.addKeyListener(this);
		this.panelb.fieldInsertar.addKeyListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelb.botonInsertar) {
			
		}
		if(e.getSource() == panelb.botonEliminar) {
			
		}
		if(e.getSource() == panelb.botonBuscar) {
			
		}
		if(e.getSource() == panelb.botonEstadoActual) {
			
		}
	}


	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		if(e.getSource() == panelb.fieldInsertar){
			char caracter = e.getKeyChar();
			if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')){
			         e.consume();
			}
		}
		if(e.getSource() == panelb.fieldEliminar) {
			char caracter = e.getKeyChar();
			if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')){
			         e.consume();
			}
		}
		if(e.getSource() == panelb.fieldBuscar) {
			char caracter = e.getKeyChar();
			if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')){
			         e.consume();
			}
		}
	}
	
}
