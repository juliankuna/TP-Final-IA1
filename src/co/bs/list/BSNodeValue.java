package co.bs.list;

/**
 * @author BELSOFT
 */
public class BSNodeValue {

    private Object obj = null;//es un objeto guardado en la lista
    private Object objrelacion = null;//es un objeto que relaciona al primero;
    private double rec = 0;//es un numero que identifica al(os)objeto(s)
    private boolean estado = false;//es otra identificacion de los objetos

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getObjrelacion() {
        return objrelacion;
    }

    public void setObjrelacion(Object objrelacion) {
        this.objrelacion = objrelacion;
    }

    public double getRec() {
        return rec;
    }

    public void setRec(double rec) {
        this.rec = rec;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
}
