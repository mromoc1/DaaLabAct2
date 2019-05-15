package Controlador;

import Vista.Crud;

public class Main {
	public static void main(String[] args) {
		Crud principal = new Crud();
		controladorCrud controlador = new controladorCrud(principal);
		controlador.iniciarVentana();
		
	}

}