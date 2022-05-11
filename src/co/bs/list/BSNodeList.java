package co.bs.list;

/**
 * Nodo Para una Lista Doblemente Enlazada
 *
 * @author BELSOFT
 */
public class BSNodeList {

    private BSNodeList sig;//lista doblemente enlazada
    private BSNodeList ant;
    private BSNodeValue nodeValue = new BSNodeValue();

    public BSNodeList(Object obj, Object objrelacion) {
        this.nodeValue.setObj(obj);
        this.nodeValue.setObjrelacion(objrelacion);
    }

    public BSNodeList(Object obj, double rec) {
        this.nodeValue.setObj(obj);
        this.nodeValue.setRec(rec);
    }

    public BSNodeList(Object obj, Object obj2, double rec, boolean estado) {
        this.nodeValue.setObj(obj);
        this.nodeValue.setObjrelacion(obj2);
        this.nodeValue.setRec(rec);
        this.nodeValue.setEstado(estado);
    }

    public BSNodeList getSig() {
        return sig;
    }

    public void setSig(BSNodeList sig) {
        this.sig = sig;
    }

    public BSNodeList getAnt() {
        return ant;
    }

    public void setAnt(BSNodeList ant) {
        this.ant = ant;
    }

    public BSNodeValue getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(BSNodeValue nodeValue) {
        this.nodeValue = nodeValue;
    }

}
