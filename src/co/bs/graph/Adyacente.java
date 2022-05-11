package co.bs.graph;

public class Adyacente {

    public Vertice vertice;//cambio en la clase para el manejo de punteros de memoria
    public double peso;//el peso es de este tipo para acercarlo a la realidad....
    public Adyacente sig;
    boolean dirigido = true;
    public Adyacente(Vertice nombre, double peso, boolean dirigido) {
        this.vertice = nombre;
        this.peso = peso;
        this.sig = null;
        this.dirigido = dirigido;
    }

    
    public double getPeso(){
        return this.peso;
    }
    
    @Override
    public String toString() {
        return vertice.getNombreVertice() + ":  " + (int) peso + "-" +  "Peso";
    }
}
