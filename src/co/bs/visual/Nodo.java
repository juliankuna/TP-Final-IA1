package co.bs.visual;

import java.util.*;

public class Nodo {
   
    private double coordenadaY;
    private double coordenadaX;
    private String id;
    private double G;
    private double F;
    private double H; //Valor de la Eurísitica
    private Nodo padre;
    private List<Nodo> vecinos= new ArrayList<Nodo>();   

    public Nodo(){
        this.vecinos = new ArrayList<Nodo>();        
    }

    //El nodo inicial no tiene nodo padre
    public Nodo (String id, double x, double y, double g, double h, double f, List<Nodo>vecinos){
        this.id = id;
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.G = g;
        this.H = h;
        this.F = f;
        this.vecinos = vecinos;
    }
    
    public Nodo (String id, double x, double y, double g, double h, double f, Nodo padre, List<Nodo>vecinos){
        this.id = id;
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.G = g;
        this.H = h;
        this.F = f;
        this.padre = padre;
        this.vecinos = vecinos;
    }

    public Nodo (String id, double x, double y){
        this.id = id;
        this.coordenadaX = x;
        this.coordenadaY = y;
    }


    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;        
    } 

    public double getCoordenadaX(){
        return this.coordenadaX;
    }
    
    public void setCordenadaX(double x){
        this.coordenadaX = x;        
    } 

    public double getCoordenadaY(){
        return this.coordenadaY;
    }
    
    public void setCoordenadaX(double y){
        this.coordenadaY = y;        
    } 


    public double getG(){
        return this.G;
    }

    public void setG(double costoSalto){
        this.G = costoSalto;        
    }

    public void setF(double Fn){
        this.F = Fn;
    }

    public double getF(){
        return this.F;
    }

    public void setH(double h){
        this.H = h;
    }
    
    public double getH(){
        return this.H;
    }

    public void setPadre(Nodo padre){
        this.padre = padre;
    }

    public Nodo getPadre(){
        return this.padre;
    }

    public List<Nodo> getVecinos(){
        return this.vecinos;
    }

    public void setVecinos(List<Nodo> vecinos){
        this.vecinos = vecinos;
    }

}
