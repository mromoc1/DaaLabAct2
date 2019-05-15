package AVL;

public class Main {
	public static void main(String[] args) {
		AVL arbol = new AVL();
		
		arbol.nodoraiz = arbol.Insertar(arbol.nodoraiz, 5);
		System.out.println();
		arbol.MostrarArbol(arbol.nodoraiz);
		arbol.nodoraiz = arbol.Insertar(arbol.nodoraiz, 3);
		
		System.out.println();
		arbol.MostrarArbol(arbol.nodoraiz);
		
		arbol.nodoraiz = arbol.Insertar(arbol.nodoraiz, 4);
		
		System.out.println();
		arbol.MostrarArbol(arbol.nodoraiz);
		arbol.nodoraiz = arbol.Insertar(arbol.nodoraiz, 7);
		
		System.out.println();
		arbol.MostrarArbol(arbol.nodoraiz);
		arbol.nodoraiz = arbol.Insertar(arbol.nodoraiz, 6);
		
		System.out.println();
		arbol.MostrarArbol(arbol.nodoraiz);
		arbol.nodoraiz = arbol.Insertar(arbol.nodoraiz, 9);
		System.out.println();
		arbol.MostrarArbol(arbol.nodoraiz);
		System.out.println();
		System.out.println("Cantidad de Nodos = " +arbol.getNnodos(arbol.nodoraiz));
		
		System.out.print("Altura "+arbol.getAlturaArbol(arbol.nodoraiz));
		
		
		arbol.nodoraiz = arbol.Eliminar(arbol.nodoraiz, 7);
		System.out.println();
		arbol.MostrarArbol(arbol.nodoraiz);
		System.out.println();
		System.out.println("Cantidad de Nodos = " +arbol.getNnodos(arbol.nodoraiz));
		arbol.nodoraiz = arbol.Eliminar(arbol.nodoraiz, 9);
		System.out.println();
		arbol.MostrarArbol(arbol.nodoraiz);
		
		System.out.println();
		System.out.println("Cantidad de Nodos = " +arbol.getNnodos(arbol.nodoraiz));
		
		
		if(arbol.Buscar3(arbol.nodoraiz, 5)==null) {
			System.out.print("No se encuentra");
		}else
			System.out.print("Si se encuentra "+arbol.Buscar3(arbol.nodoraiz, 5).getDato());
	}
}  

