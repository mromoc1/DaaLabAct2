package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import Controlador.controladorABB;
import Controlador.controladorAVL;
import Controlador.controladorB;

public class Crud extends JFrame {
	private Font f = new Font("Century Gothic", Font.PLAIN, 12);
	
	public Crud() {
		getContentPane().add(panelCentral(), BorderLayout.CENTER);
	}
	
	public JTabbedPane panelCentral() {
		JTabbedPane panel = new JTabbedPane();
		panel.setFont(f);
		
		panel.addTab("ABB", null, panelABB(), null);
		
		panel.addTab("AVL", null, panelAVL(), null);
		
		panel.addTab("B-tree", null, panelB(), null);
		
		return panel;
	}
	
	public JPanel panelABB() {
		panelABB panel = new panelABB();
		controladorABB controlador = new controladorABB(panel);
		
		return panel;
	}
	
	public JPanel panelAVL() {
		panelAVL panel = new panelAVL();
		controladorAVL controlador = new controladorAVL(panel);
		
		return panel;
	}
	
	public JPanel panelB() {
		panelB panel = new panelB();
		controladorB controlador = new controladorB(panel);
		
		return panel;
		
	}
	

}
