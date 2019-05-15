package AVL;

import java.util.ArrayList;

public class AVL {
	public Nodo nodoraiz;
	public ArrayList<Integer> vectorarbol = new ArrayList<>();
	
	public AVL() {
		nodoraiz = null;
	}
	
	/**
	 * Metodo para Insertar en el Arbol
	 * @param nodo
	 * @param valor
	 * @return
	 */
	public Nodo Insertar(Nodo nodo, int valor) {
		if(nodo == null) {
			return (new Nodo(valor));
		}
		if(valor < nodo.getDato()) {//Si el valor del nodo entrante es menor al del nodo que esta en el arbol
			nodo.setNodoIzq(Insertar(nodo.getNodoIzq(),valor));
		}else if(valor == nodo.getDato()){
		}else if(valor > nodo.getDato()) {// si el nodo entrante tiene un valor mayor
			nodo.setNodoDer(Insertar(nodo.getNodoDer(),valor));
		}//else {System.out.print("---"); return nodo; }
	
		nodo.setAltura(Math.max(alturaNodo(nodo.getNodoIzq()),alturaNodo(nodo.getNodoDer()))+1);
		
		//Comprobacion de balanceo              
		int balance = Balance(nodo);
		if(balance > 1 && valor < nodo.getNodoIzq().getDato()) {
			return RotacionDer(nodo);
		}
		if(balance < -1 && valor > nodo.getNodoDer().getDato()) {
			return RotacionIzq(nodo);
		}
		if(balance > 1 && valor > nodo.getNodoIzq().getDato()) {
			nodo.setNodoIzq(RotacionIzq(nodo.getNodoIzq()));
			return RotacionDer(nodo);
		}
		if(balance < -1 && valor < nodo.getNodoDer().getDato()) {
			nodo.setNodoDer(RotacionDer(nodo.getNodoDer()));
			return RotacionIzq(nodo);
		}
		return nodo;
	}
	
	/**
	 * Metodo para Eliminar el nodo con el valor señalado
	 * @param nodo
	 * @param valor
	 * @return
	 */
	public Nodo Eliminar(Nodo nodo, int valor) {
		if(nodo == null) {
			return nodo;
		}
		if(valor < nodo.getDato()) {//si el valor a eliminar es menor, avanza al nodo izquierdo
			nodo.setNodoIzq(Eliminar(nodo.getNodoIzq(),valor));
		}else if (valor > nodo.getDato()) {//si el valor a eliminar es mayor, avanza al nodo derecho
			nodo.setNodoDer(Eliminar(nodo.getNodoDer(),valor));
		}else {//si el valor a eliminar es igual al del nodo buscado
			if(nodo.getNodoIzq() == null || nodo.getNodoDer() == null) {//Caso de nodo con solo 1 hijo o sin hijos
				Nodo aux = null;
				if(nodo.getNodoIzq()==aux) {
					aux = nodo.getNodoDer();
				}else {
					aux = nodo.getNodoIzq();
				}
				if(aux == null) {//Si no tiene hijos
					aux = nodo;
					nodo = null;
				}else {//Si tiene 1 hijo
					nodo = aux;
				}
			}else {//Caso de que el nodo tenga 2 hijos
				Nodo aux = Sucesor(nodo.getNodoDer());
				nodo.setDato(aux.getDato());
				nodo.setNodoDer(Eliminar(nodo.getNodoIzq(),aux.getDato()));
			}
		}
		if(nodo == null) {
			return nodo;
		}
		//Comprobacion de balanceo   
		nodo.setAltura(Math.max(alturaNodo(nodo.getNodoIzq()),alturaNodo(nodo.getNodoDer())));
		           
		int balance = Balance(nodo);
		if(balance > 1 && Balance(nodo.getNodoIzq())>=0) {
			return RotacionDer(nodo);
		}else if(balance < -1 && Balance(nodo.getNodoDer())<=0) {
			return RotacionIzq(nodo);
		}else if(balance > 1 && Balance(nodo.getNodoIzq())<0) {
			nodo.setNodoIzq(RotacionIzq(nodo.getNodoIzq()));
			return RotacionDer(nodo);
		}else if(balance < -1 && Balance(nodo.getNodoDer())>0) {
			nodo.setNodoDer(RotacionDer(nodo.getNodoDer()));
			return RotacionIzq(nodo);
		}
		return nodo;
	}

	/**
	 * Metodo para obtener el balance en un nodo y comprobar si requiere balanceo
	 * @param padre
	 * @return
	 */
	public int Balance(Nodo padre) { 
	     if (padre == null) 
	         return 0; 

	     return alturaNodo(padre.getNodoIzq()) - alturaNodo(padre.getNodoDer());
	 }
	
	/**
	 * Metodo rotacion Izq simple
	 * @param k1
	 * @return
	 */
	public Nodo RotacionIzq(Nodo k1) {
		Nodo k2 = k1.getNodoDer();
		Nodo aux = k2.getNodoIzq();
		k2.setNodoIzq(k1);
		k1.setNodoDer(aux);
		k2.setAltura(Math.max(alturaNodo(k2.getNodoIzq()),alturaNodo(k2.getNodoDer()))+1);
		k1.setAltura(Math.max(alturaNodo(k1.getNodoIzq()),alturaNodo(k1.getNodoDer()))+1);
		
		return k2;
	}
	
	/**
	 * Metodo rotacion Der simple
	 * @param k2
	 * @return
	 */
	public Nodo RotacionDer(Nodo k2) {
		Nodo k1 = k2.getNodoIzq();
		Nodo aux = k1.getNodoDer();
		k1.setNodoDer(k2);
		k2.setNodoIzq(aux);
		k2.setAltura(Math.max(alturaNodo(k2.getNodoIzq()),alturaNodo(k2.getNodoDer()))+1);
		k1.setAltura(Math.max(alturaNodo(k1.getNodoIzq()),alturaNodo(k1.getNodoDer()))+1);
	
		return k1;
	}
	
	/**
	 * Obtiene la altura del nodo para hacer el balanceo.
	 * @param nodo
	 * @return
	 */
	public int alturaNodo(Nodo nodo) {
		if(nodo == null) {
			return 0;
		}
		return nodo.getAltura();
	}

	/**
	 * Metodo para obtener el nodo reemplazante o sucesor al eliminar
	 * @param nodo
	 * @return
	 */
	public Nodo Sucesor(Nodo nodo) {
		Nodo aux = nodo;
		while(aux.getNodoIzq()!=null){
			aux = aux.getNodoIzq();
		}
		return aux;
	}
	
	/**
	 * Metodo para recorrer el arbol y mostrar
	 * @param nodo
	 */
	public ArrayList<Integer> MostrarArbol(Nodo nodo) {
		if(nodo!=null) {
			vectorarbol.add(nodo.getDato());
			MostrarArbol(nodo.getNodoIzq());
			MostrarArbol(nodo.getNodoDer());
		}
		return vectorarbol;
	}
	
	/**
	 * Metodo para obtener el total de nodos en el arbol
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





