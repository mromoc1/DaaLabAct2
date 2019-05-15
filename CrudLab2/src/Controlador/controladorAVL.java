package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import AVL.AVL;
import Vista.panelAVL;

public class controladorAVL implements ActionListener,KeyListener{
	AVL arbol = new AVL();
	panelAVL panelavl;
	
	public controladorAVL(panelAVL panelavl) {
		this.panelavl = panelavl;
		
		this.panelavl.botonInsertar.addActionListener(this);
		this.panelavl.botonEliminar.addActionListener(this);
		this.panelavl.botonBuscar.addActionListener(this);
		this.panelavl.botonEstadoActual.addActionListener(this);
		
		this.panelavl.fieldInsertar.addKeyListener(this);
		this.panelavl.fieldEliminar.addKeyListener(this);
		this.panelavl.fieldBuscar.addKeyListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelavl.botonInsertar) {
			arbol.nodoraiz = arbol.Insertar(arbol.nodoraiz,Integer.parseInt(panelavl.fieldInsertar.getText()));	
			this.panelavl.textArea.setText("");
		}
		if(e.getSource() == panelavl.botonEliminar) {
			arbol.nodoraiz = arbol.Eliminar(arbol.nodoraiz,Integer.parseInt(panelavl.fieldEliminar.getText()));
			this.panelavl.textArea.setText("");
		}
		if(e.getSource() == panelavl.botonBuscar) {
			if(arbol.Buscar(arbol.nodoraiz, Integer.parseInt(panelavl.fieldBuscar.getText()))==null) {
				JOptionPane.showMessageDialog(null, "El valor Buscado no se encuentra",null,JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Valor Encontrado",null,JOptionPane.INFORMATION_MESSAGE);
			}
				
			this.panelavl.textArea.setText("");
		}
		if(e.getSource() == panelavl.botonEstadoActual) {
			int altura = arbol.getAlturaArbol(arbol.nodoraiz) -1;
			if(arbol.nodoraiz !=null) {
				this.panelavl.textArea.setText("Arbol de Busqueda Binaria\n"
						+ "Altura : " + altura +"\n"
						+ "N° de nodos : " +arbol.getNnodos(arbol.nodoraiz)+"\n"
						+ "N° de Elementos : " + arbol.getNnodos(arbol.nodoraiz)+"\n"
						+ "Elementos : "+arbol.MostrarArbol(arbol.nodoraiz));
				arbol.vectorarbol.clear();
				
			}if(arbol.nodoraiz == null) {
				JOptionPane.showMessageDialog(null, "El arbol no se ha inicializado",null,JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	
	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		if(e.getSource() == panelavl.fieldInsertar){
			char caracter = e.getKeyChar();
			if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')){
			         e.consume();
			}
		}
		if(e.getSource() == panelavl.fieldEliminar) {
			char caracter = e.getKeyChar();
			if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')){
			         e.consume();
			}
		}
		if(e.getSource() == panelavl.fieldBuscar) {
			char caracter = e.getKeyChar();
			if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')){
			         e.consume();
			}
		}
	}
}
