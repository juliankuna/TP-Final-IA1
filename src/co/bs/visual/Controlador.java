package co.bs.visual;

import java.util.*;

import co.bs.graph.Adyacente;
import co.bs.graph.Grafo;
import co.bs.graph.Vertice;


public class Controlador {

    public Controlador(){
                
    }

    private List<CostoSalto> costoSaltos = new ArrayList<CostoSalto>();

    /**
     * Método que permite la interacción entre la GUI implementada y el algoritmo desarollado por nuestro grupo
     * @author Diego Zitelli, Julian Kunaschik
     * @throws Exception
     */
    public void proceso_de_Inicio(Grafo grafo,String nombreInicio,String nombreFin) throws Exception{
        Nodo nodoInicial = null;
        Nodo nodoFinal = null;
        Adyacente inicioAd;       

        List<Nodo> nodosDelSistema=new ArrayList<Nodo>();
        Vertice verticeInicio = grafo.cab;


        while(verticeInicio!=null){
            Nodo nodo = new Nodo(verticeInicio.getNombreVertice(),verticeInicio.getCenterX(),verticeInicio.getCenterY());
            nodosDelSistema.add(nodo);            
            verticeInicio=verticeInicio.siguienteVerticeEnLista;
        }
       
        for (Nodo nodo : nodosDelSistema) {
            if(nodo.getId().equals(nombreInicio)){
                nodoInicial=nodo;
            }
            if (nodo.getId().equals(nombreFin)) {
                nodoFinal=nodo;
            }
        }
        
        
        for (Nodo nodo : nodosDelSistema) {
            verticeInicio = grafo.cab;
            while(verticeInicio!=null){
                if (verticeInicio.getNombreVertice().equals(nodo.getId())) {
                    inicioAd=verticeInicio.listaAdyacenciaSaliente;
                    while (inicioAd !=null) {
                        for (Nodo nodo2 : nodosDelSistema) {
                            
                            if (inicioAd.vertice.getNombreVertice().equals(nodo2.getId())) {
                                
                                //cargando los nodos vecinos
                                nodo.getVecinos().add(nodo2);
                                nodo2.setH(this.calcularDistanciaEuclidea(nodo2, nodoFinal));
                                CostoSalto costoSalto = new CostoSalto(nodo, nodo2, inicioAd.peso);
                                this.costoSaltos.add(costoSalto);
                                break;
                            }
                        }
                        inicioAd= inicioAd.sig;
                    }
                    break;
                } 
                verticeInicio=verticeInicio.siguienteVerticeEnLista;
            }
        }
       
        if ((nodoInicial == null) || (nodoFinal == null)) {
            throw new Exception("Elija Vertices Inicial-Final Correctos.");

        }

     this.iniciarAlgoritmo(nodoInicial, nodoFinal, nodosDelSistema);

    }

    /**
    *Logica pura y dura del algoritmo
    *@author: Julian Kunaschik, Diego Zitelli, Santiago Saucedo
    *
    */
    public void iniciarAlgoritmo(Nodo nodoInicial, Nodo nodoFinal, List<Nodo>nodosDelSistema){
        //Inicio del algoritmo A *

        List<Nodo>nodosAbiertos = new ArrayList<Nodo>();
        List<Nodo>nodosCerrados = new ArrayList<Nodo>();
        Nodo nodoViejo = null;            
        boolean nodoFinalEncontrado = false;
        nodosAbiertos.add(nodoInicial);
        Nodo nodoActual = nodoInicial;
        List<Nodo>camino = new ArrayList<Nodo>();
        int cont=0;
        StringBuilder stringAlgoritmo = new StringBuilder();  //Variable que nos va a ayudar a documentar cada iteración del algoritmo para posteriormente mostrarlo por pantalla
        stringAlgoritmo.append("Nodo inicial: "+nodoInicial.getId() + " ; " + " Nodo final: "+ nodoFinal.getId() + "\n");

        while (nodoFinalEncontrado == false){
            nodoActual = this.obtenerNodoConMenorF(nodosAbiertos);
            nodosAbiertos.remove(nodoActual);
            nodosCerrados.add(nodoActual);            
            if (nodoActual == nodoFinal){
                nodoFinal.setPadre(nodoViejo);
                nodoFinalEncontrado = true;
                camino= this.obtenerCamino(nodoFinal);
                this.documentarProceso(stringAlgoritmo,cont,nodoActual,nodosAbiertos,nodosCerrados);
                break; //llegamos al nodo final, por ende, salimos del bucle
            }
            nodoViejo=nodoActual;
            this.documentarProceso(stringAlgoritmo,cont,nodoActual,nodosAbiertos,nodosCerrados);
            this.ponerNodosVecinosEnListaAbiertos(nodoActual, nodosAbiertos, nodosCerrados);
            cont++;        
        }
        this.imprimirResultadosEnConsola(nodosAbiertos, nodosCerrados, camino, nodosDelSistema);
        
    }

    /**
     * Método inicial usado para harcodear un grafo de prueba para analizar el funcionamiento del algoritmo ya que no teniamos GUI. Actualmente no se utiliza.
     * @author Julian Kunaschik, Diego Zitelli, Santiago Saucedo
     */
    public List<Nodo> cargarTodosLosNodos() {
        /**
         * Esto se haria con la logica de interacción de la interfaz, pero para provar
         * podemos cargar 6, 7
         * nodos a manopla por codigo cargar cuantos nodos se van a querer tener
         * determinar nodo inicial y
         * nodo final
         **/

        // harcodeando nodos
        // Los nodos inicial y final ya bienen determinados por el usuario antes de
        // ejecutar el algortimo por primera vez
        Nodo nodoInicial = new Nodo("1", 4, 3); // nodo origen tiene G=0 ya que es el punto de partida por el cual inicia
                                              // el algoritmo, por lo que no tiene un costo de salto
        nodoInicial.setH(20);
        Nodo nodoFinal = new Nodo("7", 5, 5); // nodo final tiene H=0 ya que es el distino al que todos los nodos deben
                                            // llegar
        nodoFinal.setH(0);
        Nodo nodo2 = new Nodo("2", 6, 7);
        nodo2.setH(18);
        nodo2.setG(0);
        Nodo nodo3 = new Nodo("3", 6, 5);
        nodo3.setH(16);
        nodo3.setG(0);
        Nodo nodo4 = new Nodo("4", 6, 3);
        nodo4.setH(14);
        nodo4.setG(0);
        Nodo nodo5 = new Nodo("5", 8, 7);
        nodo5.setH(12);
        nodo5.setG(0);
        Nodo nodo6 = new Nodo("6", 8, 4);
        nodo6.setH(7);
        nodo6.setG(0);
        nodoFinal.setH(0);
        nodoFinal.setG(0);


        CostoSalto costo1 = new CostoSalto(nodoInicial, nodo2, 6);
        CostoSalto costo2 = new CostoSalto(nodoInicial, nodo3, 3);
        CostoSalto costo3 = new CostoSalto(nodoInicial, nodo4, 7); // originalmente era 8
        CostoSalto costo4 = new CostoSalto(nodo2, nodo3, 2);
        CostoSalto costo5 = new CostoSalto(nodo2, nodo5, 2);
        CostoSalto costo6 = new CostoSalto(nodo3, nodo4, 6); // originalmente era =2
        CostoSalto costo7 = new CostoSalto(nodo3, nodo5, 7);
        CostoSalto costo8 = new CostoSalto(nodo4, nodo6, 5);
        CostoSalto costo9 = new CostoSalto(nodo4, nodoFinal, 1);
        CostoSalto costo10 = new CostoSalto(nodo5, nodo6, 3);
        CostoSalto costo11 = new CostoSalto(nodo6, nodoFinal, 5);

        this.costoSaltos.add(costo1);
        this.costoSaltos.add(costo2);
        this.costoSaltos.add(costo3);
        this.costoSaltos.add(costo4);
        this.costoSaltos.add(costo5);
        this.costoSaltos.add(costo6);
        this.costoSaltos.add(costo7);
        this.costoSaltos.add(costo8);
        this.costoSaltos.add(costo9);
        this.costoSaltos.add(costo10);
        this.costoSaltos.add(costo11);

        List<Nodo> nodosVecinos1 = new ArrayList<Nodo>();
        nodosVecinos1.add(nodo2);
        nodosVecinos1.add(nodo3);
        nodosVecinos1.add(nodo4);
        nodoInicial.setVecinos(nodosVecinos1);

        List<Nodo> nodosVecinos2 = new ArrayList<Nodo>();
        nodosVecinos2.add(nodoInicial);
        nodosVecinos2.add(nodo3);
        nodosVecinos2.add(nodo5);
        nodo2.setVecinos(nodosVecinos2);

        List<Nodo> nodosVecinos3 = new ArrayList<Nodo>();
        nodosVecinos3.add(nodoInicial);
        nodosVecinos3.add(nodo2);
        nodosVecinos3.add(nodo4);
        nodosVecinos3.add(nodo5);
        nodo3.setVecinos(nodosVecinos3);

        List<Nodo> nodosVecinos4 = new ArrayList<Nodo>();
        nodosVecinos4.add(nodoInicial);
        nodosVecinos4.add(nodo3);
        nodosVecinos4.add(nodo6);
        nodosVecinos4.add(nodoFinal);
        nodo4.setVecinos(nodosVecinos4);

        List<Nodo> nodosVecinos5 = new ArrayList<Nodo>();
        nodosVecinos5.add(nodo2);
        nodosVecinos5.add(nodo3);
        nodosVecinos5.add(nodo6);
        nodo5.setVecinos(nodosVecinos5);

        List<Nodo> nodosVecinos6 = new ArrayList<Nodo>();
        nodosVecinos6.add(nodo5);
        nodosVecinos6.add(nodo4);
        nodosVecinos6.add(nodoFinal);
        nodo6.setVecinos(nodosVecinos6);

        List<Nodo> nodosVecinos7 = new ArrayList<Nodo>();
        nodosVecinos7.add(nodo6);
        nodosVecinos7.add(nodo4);
        nodoFinal.setVecinos(nodosVecinos7);

        // cargamos todos los nodos en la lista del controlador

        List<Nodo> nodosVecinos8 = new ArrayList<Nodo>();
        nodosVecinos8.add(nodoInicial);
        nodosVecinos8.add(nodo2);
        nodosVecinos8.add(nodo3);
        nodosVecinos8.add(nodo4);
        nodosVecinos8.add(nodo5);
        nodosVecinos8.add(nodo6);
        nodosVecinos8.add(nodoFinal);
        return nodosVecinos8;

    }

    /**
     * Devuelve el costo de paso del nodo origen hacia el nodo destino, incluyendo el costo acumulado desde el nodo de origen
     *@author Julian Kunaschik, Santiago Saucedo
     **/
    public double getCostoSalto(Nodo nodoOrigen, Nodo nodoDestino) {
        double costo = 0;
        for (CostoSalto salto : this.costoSaltos) {
            if ((salto.getOrigen() == nodoOrigen && salto.getDestino() == nodoDestino)
                    || (salto.getOrigen() == nodoDestino && salto.getDestino() == nodoOrigen)) {
                costo = salto.getCosto() + nodoOrigen.getG();
                break;
            }
        }
        return costo;
    }

    /**
     * Devuelve el valor del segmento de la recta que comunica a ambos nodos
     * @author Santiago Saucedo, Zitelli Diego
     * @return
     */
    public double calcularDistanciaEuclidea(Nodo nodoOrigen, Nodo nodoDestino) { // cambiar a float
        // Calculando la pendiente de la recta retornamos el valor absoluto de las diferencias de las imagenes de cada nodo
        // evaluado en la recta que los comunica 
        double xx = (nodoDestino.getCoordenadaX() - nodoOrigen.getCoordenadaX());
        double yy = (nodoDestino.getCoordenadaY() - nodoOrigen.getCoordenadaY());
        return  Math.sqrt(xx*xx + yy*yy);
    }
   
    /**
     * Devuelve el nodo con menor valor de F de la lista de nodos abiertos
     * @author Julian Kunaschik, Diego Zitelli
     * @return mejorNodo
     */
    public Nodo obtenerNodoConMenorF(List<Nodo> nodosAbiertos) {
        if (nodosAbiertos.size() == 1) {
            // Significa que un solo nodo existe en la lista, por lo que no hace falta iterar la lista.
            // Ejemplo: cuando solo esté cargado el nodo inicial en la primer iteración del algoritmo
            return nodosAbiertos.get(0);
        } else {
            Nodo nodoConMenorF = null;
            double fAux = 1000000; // se coloca un valor arbitrariamente alto para que se sobrescriba con el valor de la F del primer nodo de la lista
            for (Nodo nodo : nodosAbiertos) {
                if (nodo.getF() < fAux) {
                    nodoConMenorF = nodo;
                    fAux = nodo.getF();
                }
            }
            return nodoConMenorF;
        }
    }

    /**
     * Carga los vecinos del nodo actual en la lista de nodos abiertos. Además, invoca al método que calcula los valores respectivos de F para cada nodo.
     * @author Julian Kunaschik, Diego Zitelli
     */
    public void ponerNodosVecinosEnListaAbiertos(Nodo nodoActual, List<Nodo> nodosAbiertos, List<Nodo> nodosCerrados) {
        List<Nodo> nodosVecinos = nodoActual.getVecinos();
        for (Nodo nodo : nodosVecinos) {
            if (!nodosAbiertos.contains(nodo) && !nodosCerrados.contains(nodo)) {      
                nodosAbiertos.add(nodo);
            }
            if (!nodosCerrados.contains(nodo))
            this.calcularCostoDePasoAlNodo(nodoActual, nodo, nodosAbiertos, nodosCerrados);
        }

    }    

    /**
     * Calcula el costo de salto desde el nodo actual hacia su nodo vecino.
     * Si mejora el valor de F actual del nodo vecino, actualiza el valor de G y el padre del nodo vecino.
     * Y si además, el nodo vecino se encontraba en la lista de cerrados, se invoca al método que clausure esa rama ineficiente para que no se vuelva a evaluar
     * @author Julian Kunaschik, Saucedo Santiago
     */
    public void calcularCostoDePasoAlNodo(Nodo nodoActual, Nodo nodoVecino, List<Nodo> nodosAbiertos, List<Nodo> nodosCerrados) {
        double valorSalto = this.getCostoSalto(nodoActual, nodoVecino); 
        if (nodoVecino.getG() == 0) {
            nodoVecino.setG(valorSalto);          
            nodoVecino.setPadre(nodoActual);
        }
        if (nodoVecino.getF() > (valorSalto + nodoVecino.getH())) {
            if(nodosCerrados.contains(nodoVecino)){
                //Significa que encontramos un camino mas eficiente hacia ese nodo de la lista de nodos cerrados, por ende, borramos la rama ineficiente de ambas listas
                this.clausurarRamaIneficiente(nodoVecino, nodosAbiertos);
                this.clausurarRamaIneficiente(nodoVecino, nodosCerrados);
            }
            // significa que el nuevo F es mejor, por lo tanto actualizamos G en el nodo vecino y el nodo padre
            nodoVecino.setG(valorSalto);          
            // Actualizamos el padre del nodoVecino para indicar cual es su nuevo padre con un camino mas eficiente
            nodoVecino.setPadre(nodoActual);            
        }
    }

    /**
     * Quita de la lista de nodos recibida, todos los nodos cuyo padre sea el nodo recibido por parámetro
     * @author Saucedo Santiago
     */
    public void clausurarRamaIneficiente(Nodo nodoAModificar, List<Nodo> nodos){
        //Borramos en la lista de nodos abiertos y de nodos cerrados, los nodos que descienden del nodo que se va a actualizar 
        for(Nodo nodo : nodos){
            if(nodo.getPadre().equals(nodoAModificar)){
                nodos.remove(nodo); 
                this.clausurarRamaIneficiente(nodo, nodos); 
            }
        }        
    }

    /**
     * Devuelve el camino recorrido desde el nodo inicial hasta el nodo final, aplicando backtracking desde este último.
     * @author Julian Kunaschik, Diego Zitelli
     * @return
     */
    public List<Nodo> obtenerCamino(Nodo nodoFinal) {
        List<Nodo> camino = new ArrayList<Nodo>();
        camino.add(nodoFinal); // agrega el nodo final
        boolean bandera = false;
        Nodo nodoAux = nodoFinal.getPadre(); // nodoAux = 4
        while (bandera == false) {
            Nodo nodo2 = nodoAux.getPadre(); // nodo2 = null
            camino.add(0, nodoAux); // camino = 1,4,7
            if (nodo2 == null) {
                bandera = true;
                break;
            }
            nodoAux = nodo2; // nodoAux=1
        }

        return camino;
    }
    
     /**
    *Método para documentar el avance de cada iteración del algoritmo en un string que se mostrará en un popup
    *@author: Julian Kunaschik
    *
    */
    public void documentarProceso(StringBuilder stringAlgoritmo, int cont, Nodo nodoActual, List<Nodo> nodosAbiertos, List<Nodo>nodosCerrados){
        stringAlgoritmo.append("\n------------------------------------------------------------\n");
        stringAlgoritmo.append("Resultado de la Iteración: "+cont+"\n");
        stringAlgoritmo.append("Nodo actual: "+nodoActual.getId() + "; G: "+String.format("%.2f", nodoActual.getG()) + "; H: " +String.format("%.2f", nodoActual.getH()) 
                                + ";  F: "+String.format("%.2f", nodoActual.getF()));
        stringAlgoritmo.append("\nNodos abiertos:");
        for(Nodo nodo : nodosAbiertos){
            stringAlgoritmo.append("\n    Nodo: "+nodo.getId()+ "; G: "+String.format("%.2f", nodo.getG()) + "; H: " +String.format("%.2f", nodo.getH())
                                    + ";  F: "+String.format("%.2f", nodo.getF()));    
        }
        stringAlgoritmo.append("\nNodos cerrados:");
        for(Nodo nodo : nodosCerrados){
            stringAlgoritmo.append("\n    Nodo: "+nodo.getId()+ "; G: "+String.format("%.2f", nodo.getG()) + "; H: " +String.format("%.2f", nodo.getH())
                                    + ";  F: "+String.format("%.2f", nodo.getF()));    
        }

    }

     /**
    *Método para visualizar en la consola los resultados de la ejecución del algoritmo
    *@author: Santiago Saucedo
    *
    */
    public void imprimirResultadosEnConsola(List<Nodo> nodosAbiertos, List<Nodo>nodosCerrados, List<Nodo>camino, List<Nodo>nodosDelSistema){
        //camino = controlador.obtenerCamino(nodoFinal,camino);
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("NODOS: ");
        for (Nodo nodos : nodosDelSistema){
            System.out.println("nodo: "+nodos.getId() + "; G: "+String.format("%.2f", nodos.getG()) + "; H: " +String.format("%.2f", nodos.getH()) + ";  F: "+String.format("%.2f", nodos.getF()));
        }

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("El camino recorrido es: ");
        
        for(Nodo nodos : camino){
            System.out.println(nodos.getId());    
        }
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Lista Abierta es: ");
        for(Nodo nodos : nodosAbiertos){
            System.out.println(nodos.getId());    
        }
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Lista Cerrada es: ");
        for(Nodo nodos : nodosCerrados){
            System.out.println(nodos.getId());    
        }
    }



}
