package ABB;

public class Nodo {
	private int dato;
	private Nodo izq;
	private Nodo der;
	
	public Nodo(int dato) {
		this.dato = dato;
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
}
