package co.bs.list;

public class BSList {
    //Esta clase es para manejar conjunto de controles

    BSNodeList ptr;
    BSNodeList fin;
    private int tamano = 0;

    public boolean isVacia() {
        return ptr == null;
    }

    public BSList() {
    }

    public boolean eliminar(Object obj) {
        if (ptr != null) {
            BSNodeList nl = ptr;
            while (nl != null) {
                if (nl.getNodeValue().getObj() == obj) {
                    if (ptr.getSig() == null) {
                        ptr = null;
                        fin = null;
                    } else if (ptr.getSig() != null) {
                        ptr = ptr.getSig();
                        ptr.setAnt(null);
                    } else if (nl == fin) {
                        fin.getAnt().setSig(null);
                        fin = fin.getAnt();
                    } else {
                        nl.getAnt().setSig(nl.getSig());
                        nl.getSig().setAnt(nl.getAnt());
                    }
                    tamano--;
                    return true;
                }
                nl = nl.getSig();
            }
        }
        return false;
    }

    public BSNodeList buscar(Object obj) {
        if (obj == null) {
            return null;
        }

        if (ptr != null) {
            BSNodeList act = ptr;
            while (act != null) {
                if (act.getNodeValue().getObj() == obj) {
                    return act;
                }
                act = act.getSig();
            }
        }
        return null;
    }

    public BSNodeList buscarbyRel(Object obj) {
        if (obj == null) {
            return null;
        }

        if (ptr != null) {
            BSNodeList act = ptr;
            while (act != null) {
                if (act.getNodeValue().getObjrelacion() == obj) {
                    return act;
                }
                act = act.getSig();
            }
        }
        return null;
    }

    public boolean eliTodosContenganRelacion(Object obj) {
        if (ptr != null) {
            BSNodeList nl = getPtr();
            while (nl != null) {
                if (nl.getNodeValue().getObjrelacion() == obj) {
                    if (ptr.getSig() == null) {
                        ptr = null;
                        fin = null;
                        nl = null;
                        continue;
                    } else if (ptr.getSig() != null) {
                        ptr = ptr.getSig();
                        ptr.setAnt(null);
                        nl = ptr;
                        continue;
                    } else if (nl == fin) {
                        fin.getAnt().setSig(null);
                        fin = fin.getAnt();
                    } else {
                        nl.getAnt().setSig(nl.getSig());
                        nl.getSig().setAnt(nl.getAnt());
                    }
                    tamano--;
                }
                nl = nl.getSig();
            }
        }
        return false;
    }

    public boolean contiene(Object obj) {
        return !(null == buscar(obj));
    }

    public boolean contieneRelacion(Object obj) {
        return !(null == buscarbyRel(obj));
    }

    public boolean contieneGrupo(Object obj, Object objRel) {
        BSNodeList n = buscar(obj);
        if (n == null) {
            return false;
        }
        if (n.getNodeValue().getObjrelacion() == objRel) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estaObjeto1AntesObjeto2(Object obj, Object obj2) {
        BSNodeList n = buscar(obj);
        if (n != null) {
            if (n.getSig() != null) {
                if (n.getSig().getNodeValue().getObj() == obj2) {
                    return true;
                }
            }
        }
        return false;
    }

    public BSNodeList extraerPrimero() {
        if (ptr != null) {
            BSNodeList objeto = ptr;
            ptr = ptr.getSig();
            if (ptr == null) {
                fin = null;
            }
            tamano--;
            return objeto;
        }
        return null;
    }

    public void insertar(Object obj, Object objrelacion) {
        BSNodeList nl = new BSNodeList(obj, objrelacion);
        if (ptr == null) {
            ptr = nl;
            fin = nl;
        } else {
            fin.setSig(nl);
            nl.setAnt(fin);
            fin = fin.getSig();
        }
        tamano++;
    }

    public void insertar(Object obj, int rec) {
        BSNodeList nl = new BSNodeList(obj, rec);
        if (ptr == null) {
            ptr = nl;
            fin = nl;
        } else {
            fin.setSig(nl);
            nl.setAnt(fin);
            fin = fin.getSig();
        }
        tamano++;
    }

    public void insertar(Object obj) {
        insertar(obj, 1);
    }

    public void insertarSinRepetir(Object obj) {
        insertarSinRepetir(obj, 1);
    }

    public boolean insertarSinRepetir(Object obj, int rec) {
        BSNodeList b = buscar(obj);
        if (b == null) {//si no existe el objeto
            insertar(obj, rec);
            return true;
        } else {
            return false;
        }
    }

    public void insertarPorOrdenRec(Object obj, Object obj2, double rec, boolean estado) {
        //inserta objetos ordenados por una variable de recorrido
        if (obj == null && obj2 == null) {
            return;
        }
        BSNodeList nuevo = new BSNodeList(obj, obj2, rec, estado);
        if (ptr == null) {
            ptr = nuevo;
            fin = nuevo;
        } else {
            BSNodeList tcab = getPtr();
            while (tcab != null) {
                if (nuevo.getNodeValue().getRec() <= tcab.getNodeValue().getRec()) {//si el nodo es menor que el del recorrido
                    if (tcab == ptr) {
                        nuevo.setSig(ptr);
                        ptr.setAnt(nuevo);
                        ptr = nuevo;
                        break;
                    } else {
                        if (tcab.getAnt().getNodeValue().getRec() <= nuevo.getNodeValue().getRec()) {
                            tcab.getAnt().setSig(nuevo);
                            nuevo.setAnt(tcab.getAnt());
                            nuevo.setSig(tcab);
                            tcab.setAnt(nuevo);
                            break;
                        }
                    }
                }
                tcab = tcab.getSig();
            }
            if (tcab == null) {
                fin.setSig(nuevo);
                nuevo.setAnt(fin);
                fin = nuevo;
            }
        }
        tamano++;
    }

    public BSNodeList getPtr() {
        return ptr;
    }

    public void setPtr(BSNodeList nuevoPtr) {
        if (nuevoPtr != null) {
            ptr = nuevoPtr;
            nuevoPtr.setAnt(null);
            BSNodeList rec = ptr;
            tamano = 0;
            while (rec != null) {
                tamano++;
                if (rec.getSig() == null) {
                    fin = rec;
                }
                rec = rec.getSig();
            }
        }
    }

    public BSNodeList getFin() {
        return fin;
    }

    public void setFin(BSNodeList nuevoFin) {
        if (nuevoFin != null) {
            fin = nuevoFin;
            fin.setSig(null);
            BSNodeList rec = fin;
            tamano = 0;
            while (rec != null) {
                tamano++;
                if (rec.getAnt() == null) {
                    ptr = rec;
                }
                rec = rec.getAnt();
            }
        }
    }

    public Object getObject(int pos) {
        BSNodeList ret = null;
        if (ptr != null) {
            if (ptr.getSig() == null) {
                return ptr.getNodeValue().getObj();
            } else {
                BSNodeList tmptr = ptr;
                int rec = 0;
                do {
                    rec++;
                    if (rec == pos) {
                        return tmptr.getNodeValue().getObj();
                    }
                    tmptr = tmptr.getSig();
                } while (tmptr != null);
            }
        }
        return ret;
    }

    public Object getObjectCasting(int pos) {
        BSNodeList nl = (BSNodeList) getObject(pos);
        return nl.getNodeValue().getObj();
    }

    @Override
    public String toString() {
        String cad = "";
        BSNodeList n = ptr;
        while (n != null) {
            cad += n.getNodeValue().getObj().toString() + "\n";
            n = n.getSig();
        }
        return cad;
    }

    public String toString2() {
        String cad = "";
        BSNodeList n = ptr;
        while (n != null) {
            cad += n.getNodeValue().getObjrelacion().toString() + "\n";
            n = n.getSig();
        }
        return cad;
    }

    public String toString3() {
        String cad = "";
        BSNodeList n = ptr;
        while (n != null) {
            cad += n.getNodeValue().getRec() + "\n";
            n = n.getSig();
        }
        return cad;
    }

    public int getTamano() {
        return tamano;
    }

    public void vaciar() {
        ptr = null;
        fin = null;
        this.tamano = 0;
    }

}
