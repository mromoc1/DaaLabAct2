package AVL;

public class Nodo {
	int dato;
	int altura;
	Nodo izq;
	Nodo der;
	public Nodo(int dato) {
		this.dato = dato;
		this.altura = 1;
		this.izq = null;
		this.der = null;
	}
	
	public int getDato() {
		return dato;
	}
	
	public Nodo getNodoIzq() {
		return izq;
	}

	public Nodo getNodoDer() {
		return der;
	}
	
	public void setNodoDer(Nodo nodo) {
		der = nodo;
	}
	
	public void setNodoIzq(Nodo nodo) {
		izq = nodo;
	}
	
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public int getAltura() {
		return altura;
	}
	
	public void setDato(int valor) {
		this.dato = valor;
	}
}