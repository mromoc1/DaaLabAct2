package ABB;

import java.util.ArrayList;

public class ABB {
	public Nodo nodoraiz;
	public ArrayList<Integer> vectorarbol = new ArrayList<>();
	
	public ABB() {
		nodoraiz = null;
	}
	/**
	 * Metodo para recorrer el arbol y mostrar
	 * @param nodo
	 */
	public ArrayList<Integer> obtenerArbol(Nodo nodo) {
		if(nodo != null) {
			vectorarbol.add(nodo.getDato());
			obtenerArbol(nodo.getNodoIzq());
			obtenerArbol(nodo.getNodoDer());
		}
		return vectorarbol;
	}

	/**
	 * Metodo para Contar todos los nodos del arbol
	 * @param nodo
	 * @return
	 */
	public int getNnodos(Nodo nodo) {
		int contador = 1;
		if(nodo.getNodoIzq() != null) {
			contador += getNnodos(nodo.getNodoIzq());
		}
		if(nodo.getNodoDer() != null) {
			contador += getNnodos(nodo.getNodoDer());
		}
		return contador;
	}
	
	/**
	 * Metodo para obtener la altura del arbol
	 * @param nodo
	 * @return
	 */
	public int getAlturaArbol(Nodo nodo ) {
		if(nodo == null) {
			return 0;
		}else {
			return (Math.max(getAlturaArbol(nodo.getNodoIzq()),getAlturaArbol(nodo.getNodoDer()))+1);
		}
	}
	
	/**
	 * Metodo para Insertar en el arbol
	 * @param valor
	 */
	public void Insertar(int valor) {
		Nodo nuevo = new Nodo(valor);
		if(nodoraiz == null) {
			nodoraiz = nuevo;
		}else{
			Nodo aux = nodoraiz;
			Nodo padre;
			while(true) {
				padre = aux;
				if(valor < aux.getDato()) {
					aux = aux.getNodoIzq();
					if(aux == null) {
						padre.setNodoIzq(nuevo);
						return;
					}
				}else if(valor == aux.getDato()){
					return;
				}else {
					aux = aux.getNodoDer();
					if(aux == null) {
						padre.setNodoDer(nuevo);
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Metodo para Eliminar del arbol
	 * @param valor
	 */
	public void EliminarNodo(int valor) {
		Nodo padre = nodoraiz;
		Nodo nodo = nodoraiz;
		boolean eshijoizq = false;
		while (nodo.getDato() != valor) {
			padre = nodo;
			if(nodo.getDato() > valor) {
				eshijoizq = true;
				nodo = nodo.getNodoIzq();
			}else {
				eshijoizq = false;
				nodo = nodo.getNodoDer();
			}
			if(nodo == null) {
				return;
			}
		}
		
		if(nodo.getNodoDer() == null && nodo.getNodoIzq()==null) {
			if(nodo == nodoraiz) {
				nodoraiz = null;
			}
			if(eshijoizq == true) {
				padre.setNodoDer(null);
			}else {
				padre.setNodoDer(null);
			}
		}else if(nodo.getNodoDer()==null) {
			if(nodo==nodoraiz) {
				nodoraiz = nodo.getNodoIzq();
			}else if(eshijoizq) {
				padre.setNodoIzq(nodo.getNodoDer());
			}else {
				padre.setNodoDer(nodo.getNodoIzq());
			}
		}
		else if(nodo.getNodoIzq()==null) {
			if(nodo == nodoraiz) {
				nodoraiz = nodo.getNodoDer();
			}else if(eshijoizq) {
				padre.setNodoIzq(nodo.getNodoDer());
			}else {
				padre.setNodoDer(nodo.getNodoDer());
			}
		}else if(nodo.getNodoIzq() != null && nodo.getNodoDer() != null) {
			Nodo reemplazante = getNodoReemplazante(nodo);
			if(nodo == nodoraiz) {
				nodoraiz = reemplazante;
			}else if(eshijoizq) {
				padre.setNodoIzq(reemplazante);
			}else {
				padre.setNodoDer(reemplazante);
			}
			reemplazante.setNodoIzq(nodo.getNodoIzq());
		}
		return;
	}	
	
	/**
	 * Metodo para obtener el nodo reemplazante al eliminar
	 * 
	 * (el menor de los mayores o el mayor de los menores)
	 * @param nodoaborrar
	 * @return
	 */
	public Nodo getNodoReemplazante(Nodo nodoaborrar) {
		Nodo reemplazante = null;
		Nodo padrereemplazante = null;
		Nodo nodo = nodoaborrar.getNodoDer();
		while(nodo != null) {
			padrereemplazante = reemplazante;
			reemplazante = nodo;
			nodo = nodo.getNodoIzq();
		}
		if(reemplazante != nodoaborrar.getNodoDer()) {
			padrereemplazante.setNodoIzq(reemplazante.getNodoDer());
			reemplazante.setNodoDer(nodoaborrar.getNodoDer());
		}
		return reemplazante;
	}
	
	/**
	 * Busca el valor desde la raiz consultando si su valor coincide,
	 * mienstras no se llegue a un nodo null o un valor igual al buscado
	 * se consultara por el subarbol izquierdo o derecho segun sea
	 * mayor o menor
	 * 
	 * @param nodo
	 * @param valor
	 * @return
	 */
	public Nodo Buscar(Nodo nodo, int valor) {
		//Si el nodo en el nodo que se esta consultando actualmente no existe, lo retorna.
		if(nodo==null || nodo.getDato()==valor) {
			return nodo;
		}
		if(nodo.getDato() > valor) {
			return Buscar(nodo.getNodoIzq(),valor);
		}
		return Buscar(nodo.getNodoDer(),valor);
	}
	
}
