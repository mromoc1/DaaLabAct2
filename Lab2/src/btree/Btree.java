
package btree;

/**
 *
 * @author Diego
 */
public class Btree {
     public NodoBtree laRaiz;
     
     public NodoYPosicion busquedaClave (NodoBtree nodo , int k) {
         int i = 0;
         //int numVueltas = 1;
         while ( (i <= nodo.getNumClaves()-1) && (k > (nodo.getClaves())[i] ) )  {
            i = i + 1;
         }
         if ( (i <= nodo.getNumClaves()-1) && (k == ((nodo.getClaves())[i]))  ) {
            return new NodoYPosicion(nodo,i); 
         }
         else if (nodo.getEsHoja() ) {
             return null;
         }
         else {
              //LECTURA DEL DISCO( nodo );
              //busquedaClave(nodo , k);
         }
         return null;
     }
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //SI LA RAIZ ESTA LLENA SE CREA UN NUEVO NODO RAIZ Y SE AGREGA EL DATO DEL MEDIO
     //SINO SE AGREGA DIRECTO.
     public void insertarEnBtree( NodoBtree nodo , int k) {
        if (nodo.getNumClaves() == nodo.getT() * 2 - 1) {
            NodoBtree nodoRaiz = new NodoBtree();
            nodoRaiz.getHijos()[0] = nodo; 
            laRaiz = nodoRaiz;
            splitEnBtree (nodoRaiz , 0 );
            insertarEnBtreeNoCompleto( nodoRaiz , k);
        }
        else  insertarEnBtreeNoCompleto( nodo , k);
         
     }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void eliminarEnBtree(NodoBtree nodo , int k) { //AQUI TENGO QUE HACER LOS CAMBIO DEL NODO RAIZ.
        if (nodo != null) {
         
        NodoBtree nizq;
        NodoBtree nder;
        //int numClaves = nodo.getNumClaves();
        int t = nodo.getT(); //OBTENGO EL T DE ALGUN NODO
        if (!nodo.getEsHoja()) {
               //SI EL NODO CONTIENE LA CLAVE.
               NodoYPosicion nodoBuscado = busquedaClave(nodo , k);
               if ( nodoBuscado != null) {
                    //HEMOS ENCONTRADO EL NODO AQUI.
                     int posicion = nodoBuscado.getPosicion();
                     //////////////////////////////////
                     if (posicion == 0 ) {
                         nizq = nodo.getHijos()[0];
                     }
                     else    {
                          nizq = nodo.getHijos()[ posicion - 1 ];
                     }            
                     nder =nodo.getHijos()[posicion + 1];
                     ///////////////////////////////////////////
                     if ( (nder.getNumClaves() == t-1) && (nizq.getNumClaves() == t-1)   ) { 
                         mergeEnBtree(nodo , posicion , posicion-1 , posicion + 1);
                         if (nodo.getNumClaves() == 1){
                             laRaiz = nodo.getHijos()[posicion]; 
                             nodo = null;
                         }
                     }
                     else if (nder.getNumClaves() > t-1) {
                        int menorDeLosMayores = menorDeLosMayores(nodo , posicion);  //TENGO MIS DUDAS SI ES ASI
                        nodo.getClaves()[posicion] = menorDeLosMayores;  
                        eliminarEnBtreeValido(nder , menorDeLosMayores);
                     }
                     else if (nizq.getNumClaves() > t-1)  {
                        int mayorDeLosMenores = mayorDeLosMenores(nodo, posicion); //TENGO MIS DUDAD IGUAL XD
                        nodo.getClaves()[posicion] = mayorDeLosMenores;  
                        eliminarEnBtreeValido(nizq , mayorDeLosMenores);
                     }
               }
               else  {
                     NodoBtree hder = null;
                     NodoBtree hizq = null;
                     //BUSCO LA CLAVE POR DONDE DEBERIA BAJAR AL NODO HIJO.   
                     int i = 0;
                     while ( (i <= nodo.getNumClaves()-1) && (k > (nodo.getClaves())[i] ) )  {
                        i = i + 1;
                     }
                     //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     //CON LA INDICE OBTENIDO BUSCO EL HIJO POR EL CUAL BAJARA.
                     NodoBtree descendiente ;
                     int indiceDesc;
                     if ( nodo.getClaves()[i] > k) {
                         if (i == 0) {
                              descendiente = nodo.getHijos()[0];
                              indiceDesc = 0;
                         }
                         else {
                             descendiente = nodo.getHijos()[i-1];
                             indiceDesc = i-1;
                         }
                     }
                     else {
                         descendiente = nodo.getHijos()[i+1];
                         indiceDesc = i+1;
                     }
                     ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     ///PREGUNTO SI EL INDICE TIENE MENOS DE T CLAVES
                     if (descendiente.getNumClaves() < t) {
                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            //OBTENER LOS HERMANOS DEL NODO DESCENDIENTES 
                            if (indiceDesc > 0) {
                               hizq = nodo.getHijos()[indiceDesc - 1];
                             }
                            /////////////////////////
                            if (indiceDesc  < 2*t - 1) {
                               hder = nodo.getHijos()[indiceDesc + 1];
                            }
                           //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                           //BUSCAMOS AYUDA EN ALGUNO DE LOS HERMANOS.
                           if (indiceDesc == 0 ) { //CASO DONDE EL DESCENDIENTE NO TIENE HERMANO IZQUIERDO.
                               if (hder.getNumClaves() > t) {
                                     pedirAyudaHermanoDer(nodo , i , indiceDesc , indiceDesc +1);
                               }
                              else {
                                      mergeEnBtree(nodo , i , indiceDesc , indiceDesc + 1);
                              }
                           }
                           else  if  (indiceDesc == 2*t - 1) { //CASO DONDE EL DESCENDIENTE NO TIENE HERMANO DERECHO.
                                  if(hizq.getNumClaves() > t) {
                                      pedirAyudaHermanoIzq(nodo , i , indiceDesc-1 , indiceDesc);
                                   }
                                   else {
                                      mergeEnBtree( nodo , i , indiceDesc + 1 , indiceDesc);
                                   }
                          }
                          else  { //CASO DONDE TIENE DOS HERMANOS.
                                if(  (hizq.getNumClaves() > t ) && (hder.getNumClaves() > t) ) {
                                    pedirAyudaHermanoDer(nodo , i , indiceDesc , indiceDesc + 1);
                                }
                                else { 
                                  mergeEnBtree( nodo , i, indiceDesc , indiceDesc + 1);
                               }
                     
                          }
                    
                     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     }
                     else {
                            eliminarEnBtreeValido(descendiente,k);
                     }
                     
                     
            
               }
            
             
        }
        else {
             eliminarEnBtreeValido(nodo , k); 
             
        }
      }
        else  {
           System.out.println("No se puede eliminar en un arbol nulo");
        }  
     
   }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void eliminarEnBtreeValido (NodoBtree nodo , int k) {
         int t = nodo.getNumClaves();
         NodoYPosicion nodoBuscado = busquedaClave(nodo , k);
         if ( nodoBuscado != null) {
                if (nodoBuscado.getNodo().getEsHoja() ) {
                     int i = nodoBuscado.getPosicion();
                     //CORRIMIENTO DE LAS CLAVES EN EL NODO
                     for (int j = i ; j < nodo.getNumClaves() - 1  ; j++) {
                         nodo.getClaves() [j] = nodo.getClaves()[j+1];
                     }
                     nodo.getClaves()[nodo.getT() * 2 - 2] = 0;
                }
                else {
                      //BUSCO LOS HIJOS DE LA CLAVE 
                      int i = 0;
                      while ( (i <= nodo.getNumClaves()-1) && (k > (nodo.getClaves())[i] ) )  {
                         i = i + 1;
                      }
                      NodoBtree hizq  = nodo.getHijos()[i-1] ;
                      NodoBtree hder  = nodo.getHijos()[i+1]; 
                      if ( (hizq.getNumClaves() == t-1) && (hder.getNumClaves() == t-1) ) {
                         mergeEnBtree( nodo , i , i-1 , i+1);
                      }
                      else if (hder.getNumClaves() > t-1) {
                         pedirAyudaHijoDer(nodo , i , i+1);
                      }
                      else if (hizq.getNumClaves() > t-1) {
                         pedirAyudaHijoIzq(nodo , i , i-1);
                      }
                }
         }
         else  {
                     NodoBtree hder = null;
                     NodoBtree hizq = null;
                    //BUSCO LA CLAVE POR DONDE DEBERIA BAJAR AL NODO HIJO.   
                     int i = 0;
                     while ( (i <= nodo.getNumClaves()-1) && (k > (nodo.getClaves())[i] ) )  {
                        i = i + 1;
                     }
                     //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     //CON LA INDICE OBTENIDO BUSCO EL HIJO POR EL CUAL BAJARA.
                     NodoBtree descendiente ;
                     int indiceDesc;
                     if ( nodo.getClaves()[i] > k) {
                         if (i == 0) {
                              descendiente = nodo.getHijos()[0];
                              indiceDesc = 0;
                         }
                         else {
                             descendiente = nodo.getHijos()[i-1];
                             indiceDesc = i-1;
                         }
                     }
                     else {
                         descendiente = nodo.getHijos()[i+1];
                         indiceDesc = i+1;
                     }
                     ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     ///PREGUNTO SI EL INDICE TIENE MENOS DE T CLAVES
                     if (descendiente.getNumClaves() < t) {
                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            //OBTENER LOS HERMANOS DEL NODO DESCENDIENTES 
                            if (indiceDesc > 0) {
                               hizq = nodo.getHijos()[indiceDesc - 1];
                             }
                            /////////////////////////
                            if (indiceDesc  < 2*t - 1) {
                               hder = nodo.getHijos()[indiceDesc + 1];
                            }
                           //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                           //BUSCAMOS AYUDA EN ALGUNO DE LOS HERMANOS.
                           if (indiceDesc == 0 ) { //CASO DONDE EL DESCENDIENTE NO TIENE HERMANO IZQUIERDO.
                               if (hder.getNumClaves() > t) {
                                     pedirAyudaHermanoDer( nodo , i  , indiceDesc , indiceDesc +  1);
                               }
                              else {
                                      mergeEnBtree(nodo , i , indiceDesc , indiceDesc + 1);
                              }
                           }
                           else  if  (indiceDesc == 2*t - 1) { //CASO DONDE EL DESCENDIENTE NO TIENE HERMANO DERECHO.
                                  if(hizq.getNumClaves() > t) {
                                      pedirAyudaHermanoIzq( nodo , i , indiceDesc - 1 , indiceDesc);
                                   }
                                   else {
                                      mergeEnBtree(nodo , i , indiceDesc -1 , indiceDesc );
                                   }
                          }
                          else  { //CASO DONDE TIENE DOS HERMANOS.
                                if(  (hizq.getNumClaves() > t ) && (hder.getNumClaves() > t) ) {
                                    pedirAyudaHermanoDer( nodo , i , indiceDesc , indiceDesc +1);
                                }
                                else { //PUEDO ESCOGER CUALQUIERA DE LOS 2
                                  mergeEnBtree( nodo , i , indiceDesc  , indiceDesc + 1);
                               }
                     
                          }
                    
                     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     }
                     else {
                            eliminarEnBtreeValido(descendiente,k);
                     }
         }
     }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void mergeEnBtree ( NodoBtree nodo , int i , int izq  , int der) {
           //LEO HIJO IZQUIERDO Y DERECHO
           int clave = nodo.getClaves()[i];
           NodoBtree nizq  = nodo.getHijos()[izq];
           NodoBtree nder  = nodo.getHijos()[der];
           nizq.setNumClaves(2 * nizq.getT() - 1);  //ASIGNO 2T-1 CLAVES
           if (nizq.getEsHoja()) {
                nizq.getClaves()[ nizq.getT() - 1 ]  = clave;
                ///////////////////////////////////////////////////////////////////////////////////////////////
                //RELLENAMOS LAS CLAVES DEL HIJO IZQUIERDO CON LAS DEL DERECHO.
                int k = 0 ;
                for ( int j = nizq.getT()  ; j <= 2 *nizq.getT() - 1 ;  j++ ) {
                     nizq.getClaves()[j] = nder.getClaves()[k];
                     k++;
                }
               ////////////////////////////////////////////////////////////////////////////////////////////////
               //CORRIMIENTO DE LAS CLAVES EN EL NODO
               for (int j = i ; j < nodo.getNumClaves() - 1  ; j++) {
                   nodo.getClaves() [j] = nodo.getClaves()[j+1];
               }
               nodo.getClaves()[nodo.getT() * 2 - 2] = 0;
               ////////////////////////////////////////////////////////////////////////////////////////////////
                nodo.getHijos()[der] = null;
                eliminarEnBtreeValido( nizq , clave);
           }
           else {
                nizq.getClaves()[ nizq.getT() - 1 ]  = clave;
                ///////////////////////////////////////////////////////////////////////////////////////////////
                //CORRIMIENTO DE LAS CLAVES EN EL NODO.
               for (int j = i ; j < nodo.getNumClaves() - 1  ; j++) {
                   nodo.getClaves() [j] = nodo.getClaves()[j+1];
               }
               nodo.getClaves()[nodo.getT() * 2 - 2] = 0;
               ////////////////////////////////////////////////////////////////////////////////////////////////
               //CORRIMIENTO DE LOS HIJOS EN EL NODO.
               for (int j = der ; j < nodo.getNumClaves() ; j++) {
                    nodo.getHijos()[j] = nodo.getHijos()[j+1];
               }
               nodo.getHijos()[nodo.getT() * 2 - 1] = null; 
               ////////////////////////////////////////////////////////////////////////////////////////////////
               //RELLENAMOS LAS CLAVES DEL DERECHO AL IZQUIERDO
               int k = 0;
               for( int j = nizq.getT() ; j < nizq.getT() * 2 - 1 ; j ++) {
                   nizq.getHijos()[j] = nder.getHijos()[k]; 
                   k++;
               }
               /////////////////////////////////////////////////////////////////////////////////////////////////
                nodo.getHijos()[der] = null;
                eliminarEnBtreeValido( nizq , clave);
           
           }
     }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void pedirAyudaHermanoDer(NodoBtree nodo , int i , int izq , int der) {
         int clave = nodo.getClaves()[i];
         //LEER HIJO IZQUIERDO.
         NodoBtree nizq = nodo.getHijos()[izq];
         nizq.getClaves()[ nizq.getNumClaves()] = clave;
         pedirAyudaHijoDer(nodo , i , der);
         
    }
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void pedirAyudaHermanoIzq(NodoBtree nodo , int i , int izq , int der){
         int clave = nodo.getClaves()[i];
         //LEER HIJO DERECHO.
          NodoBtree nder = nodo.getHijos()[der];
         nder.getClaves()[ nder.getNumClaves()] = clave;
         pedirAyudaHijoIzq(nodo , i , izq);
     }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void pedirAyudaHijoDer ( NodoBtree nodo , int i , int der) {
       //LEER NODO DERECHO.
       NodoBtree nder  = nodo.getHijos()[der];
       int sucesor = nder.getClaves()[0];
       nodo.getClaves()[i] = sucesor;
       eliminarEnBtreeValido(nder,sucesor);
    } 
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
     public void pedirAyudaHijoIzq( NodoBtree nodo , int i , int izq) {
       //LEER NODO IZQUIERDO
       NodoBtree nizq = nodo.getHijos()[izq];
       int predecesor = nizq.getClaves()[ nodo.getNumClaves() - 1];
       nodo.getClaves()[i] = predecesor;
       eliminarEnBtreeValido(nizq,predecesor);
     }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public int menorDeLosMayores ( NodoBtree nodo ,  int i) {
         //LEER EL NODO nder 
         NodoBtree nder = nodo.getHijos()[i+1];
         while (!nder.getEsHoja()){
             //LEER EL NUEVO NODO ??
             nder = nder.getHijos()[0];
         }
         return nder.getClaves()[0];
     }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public int mayorDeLosMenores ( NodoBtree nodo  ,  int i) {
         NodoBtree nizq;
         if ( i== 0 ) {
             //LEER NODO NIZQ
              nizq = nodo.getHijos()[0];
         }
         else  {
             //LEER NODO NIZQ
             nizq = nodo.getHijos()[i-1];
         }
         while (!nizq.getEsHoja()){
             //LEER EL NUEVO NODO ??
             nizq = nizq.getHijos()[ nizq.getNumClaves() + 1];
         }
         return nizq.getClaves()[ nizq.getNumClaves() - 1];
     }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void splitEnBtree (NodoBtree x , int i) {
         NodoBtree z  = new NodoBtree();
         NodoBtree y = x.getHijos()[i];
         int t = x.getT();                                      //OBTENGO EL T DE ALGUN NODO.
         z.setEsHoja( y.getEsHoja() ) ;
         z.setNumClaves( t - 1 );
         for ( int j = 0 ; j < t-1  ;  j++ ) {
            z.getClaves()[j] = y.getClaves()[j + t];   
         }
         if (!y.getEsHoja()) {
             for (int j = 0 ; j < t ; j++)  {
                 z.getHijos()[j] = y.getHijos()[j + t]; 
             }
         }
         y.setNumClaves(t-1);
         //////////////////////////////////////////////////////////////////////////////////
         for (int j = x.getNumClaves() + 1 ; j >= i+1 ; j--) {  //  CORRIMIENTO DE LOS HIJOS.
                x.getHijos()[j + 1] = x.getHijos()[j]; 
         } 
         x.getHijos()[ i + 1] = z;
         for (int j = x.getNumClaves() ; j >= i  ; j--) {
             x.getClaves()[i] = y.getClaves()[j];
         }
         x.getClaves()[i] = y.getClaves()[t];
         x.setNumClaves( x.getNumClaves() + 1);
         //ESCRIBIR EN EL DISCO Y
         //ESCRIBIR EN EL DISCO Z
         //ESCRIBIR EN EL DISCO X
     }
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void insertarEnBtreeNoCompleto (NodoBtree x , int k) {
        int i = x.getNumClaves();
        if (x.getEsHoja()) {
               while (i >= 1 && k < x.getClaves()[i-1]) {
                   x.getClaves()[i] = x.getClaves()[i-1];
                   i = i-1;
               }
               x.getClaves()[i] = k;
               x.setNumClaves( x.getNumClaves() + 1);
               //DISK_WRITE(x)               
        }
        else {
                while ( ( i >= 1 ) && (k < x.getClaves()[i-1])  ) {
                    i = i - 1;
                }   
                //i = i + 1; NO 
                
                
               //DISK_READ(X.Ci) GUARDARLO Y COLOCARLO ABAJO EN EL IF.
               if (x.getHijos()[i].getNumClaves() == x.getHijos()[i].getT() * 2 - 1) {
                   splitEnBtree(x,i);
                   if (k > x.getClaves()[i]) {
                           i = i +1;
                   }
               }
               insertarEnBtreeNoCompleto(x.getHijos()[i] ,k);
        }
     }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public int calcularAlturaBtree (NodoBtree nodo) {
         int h=0;
         if (nodo != null) {
             if (nodo.getEsHoja()) {
                 return 0;
             }  
             else {
                      NodoBtree nder = nodo.getHijos()[0];
                      while (!nder.getEsHoja()) {
                            nder = nder.getHijos()[0];
                            h = h + 1;
                      }
                      return h;                
             }
         }
         return -1;
     }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void imprimirBtree(NodoBtree nodo) {
         if (nodo.getEsHoja()) {
             for (int i = 0  ; i < nodo.getNumClaves() ; i++) {
                System.out.println(" " +  nodo.getClaves()[i]  + " ");
             }
         }
         else {
                  for (int i = 0 ; i < nodo.getNumClaves() ; i ++) {
                       imprimirBtree( nodo.getHijos()[i]);
                       System.out.println(" " + nodo.getClaves()[i] + " ");
                       if (i == nodo.getNumClaves() - 1) {
                           imprimirBtree(nodo.getHijos()[i+1]);
                       }
                  }
         }
     }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public int numeroNodosBtree (NodoBtree nodo) {
      if (nodo.getEsHoja()) {
            return 1;
         }
         else {
                   int[] array = new int[ nodo.getNumClaves() + 1 ];
                   for (int i = 0  ; i < array.length ; i++) {
                       array[i] = numeroNodosBtree( nodo.getHijos()[i] );
                   }
                   int suma = 0 ;
                   for (int  i = 0 ; i < array.length ; i++) {
                       suma  = suma + array[i];
                   }
                   return suma;
         }   
     }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public int numeroClavesBtree(NodoBtree nodo) {
         if (nodo.getEsHoja()) {
            return nodo.getNumClaves();
         }
         else {
                   int[] array = new int[ nodo.getNumClaves() + 1 ];
                   for (int i = 0  ; i < array.length-1 ; i++) {
                       array[i] = numeroClavesBtree( nodo.getHijos()[i] );
                   }
                   array[array.length-1] = numeroClavesBtree(nodo.getHijos()[nodo.getNumClaves()]);
                   int suma = 0 ;
                   for (int  i = 0 ; i < array.length ; i++) {
                       suma  = suma + array[i];
                   }
                   return suma;
         }
     }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
