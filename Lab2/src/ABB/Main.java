package ABB;

public class Main {
	public static void main(String[] args) {
		ABB arbol = new ABB();
		
		arbol.Insertar(13);
		arbol.Insertar(6);
		arbol.Insertar(20);
		arbol.Insertar(2);
		arbol.Insertar(8);
		arbol.Insertar(15);
		arbol.Insertar(23);
		arbol.Insertar(7);
		arbol.Insertar(1);
		arbol.Insertar(17);
		arbol.Insertar(16);
		arbol.Insertar(10);
		//arbol.MostrarArbol(arbol.nodoraiz);
		System.out.println();
		System.out.println("Cantidad de Nodos = " +arbol.getNnodos(arbol.nodoraiz));
		System.out.println("Altura = " +arbol.getAlturaArbol(arbol.nodoraiz));
	}
}
