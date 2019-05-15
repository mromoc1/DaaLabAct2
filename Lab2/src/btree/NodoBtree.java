
package btree;

/**
 *
 * @author diego
 */
public class NodoBtree {
    
    private final int T = 3; // t = 3 maxt = 2*t-1 = 5.
    private int[] claves; // debe ser 2*t -1.
    private NodoBtree[] hijos; // debe ser 2*t.
    private int numClaves; // numero de claves actualmente.
    private boolean esHoja; //true : es hoja ; false : nodo interno.

    public void setEsHoja(boolean esHoja) {
        this.esHoja = esHoja;
    }

    public void setClaves(int[] claves) {
        this.claves = claves;
    }

    public void setHijos(NodoBtree[] hijos) {
        this.hijos = hijos;
    }

    public void setNumClaves(int numClaves) {
        this.numClaves = numClaves;
    }
    
    public int getT() {
        return T;
    }
   
    public int[] getClaves() {
        return claves;
    }

    public NodoBtree[] getHijos() {
        return hijos;
    }

    public boolean getEsHoja() {
        return esHoja;
    }
    
    public int getNumClaves() {
        return numClaves;
    }
    
    
    //CREA UN NODO RAIZ 
    public NodoBtree () {
        claves =new int[2*T -1];
        hijos = new NodoBtree[2*T];
        numClaves = 0;
        esHoja = false;   
    }
    
    //crea un nodo hoja de grado T con 1 clave entregada
    public NodoBtree( int clave) {
        claves =new int[2*T -1];
        claves[0] = clave;
        hijos = new NodoBtree[2*T];
        numClaves = 1;
        esHoja = true;
    }
    
    // crea un nodo interno con las claves entregadas.
    public NodoBtree (int... claves) {
        
        numClaves = claves.length; 
        esHoja = false;
    }
    
}
