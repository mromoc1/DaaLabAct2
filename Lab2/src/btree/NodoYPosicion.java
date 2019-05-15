package btree;
/**
 *
 * @author Diego
 */

//Clase para poder retornar el par ordenado nodo y posicion
// nodo es el nodo donde esta la clave a buscar  y
// posicion es el indice donde se encuentra.

public class NodoYPosicion {
    private NodoBtree nodo;
    private int posicion;
    
    public NodoYPosicion( NodoBtree nodo , int posicion) {
           this.nodo = nodo;
           this.posicion  = posicion;
    }
    
    public NodoBtree getNodo() {
       return this.nodo;
    } 
     
    public int getPosicion() {
      return this.posicion ;
    }
}
