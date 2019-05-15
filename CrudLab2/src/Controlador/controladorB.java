package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import Vista.panelB;
import btree.Btree;

public class controladorB implements ActionListener,KeyListener {
	
	Btree arbol = new Btree();
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
			arbol.insertarEnBtree(arbol.laRaiz,Integer.parseInt(panelb.fieldInsertar.getText()));
		}
		if(e.getSource() == panelb.botonEliminar) {
			arbol.eliminarEnBtree(arbol.laRaiz,Integer.parseInt(panelb.fieldEliminar.getText()));
		}
		if(e.getSource() == panelb.botonBuscar) {
			arbol.busquedaClave(arbol.laRaiz,Integer.parseInt(panelb.fieldBuscar.getText()));
		}
		if(e.getSource() == panelb.botonEstadoActual) {
			if(arbol.laRaiz !=null) {
				this.panelb.textArea.setText("Arbol de Busqueda Binaria\n"
						+ "Altura : " + arbol.calcularAlturaBtree(arbol.laRaiz) +"\n"
						+ "N° de nodos : " +arbol.numeroNodosBtree(arbol.laRaiz)+"\n"
						+ "N° de Elementos : " + arbol.numeroClavesBtree(arbol.laRaiz)+"\n"
						+ "Elementos : ");
				arbol.imprimirBtree(arbol.laRaiz);
				
			}else {
				JOptionPane.showMessageDialog(null, "El arbol no se ha inicializado",null,JOptionPane.WARNING_MESSAGE);
			}
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
