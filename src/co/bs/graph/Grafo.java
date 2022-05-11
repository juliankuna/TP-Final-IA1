/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.bs.graph;

import co.bs.list.BSList;
import java.awt.Color;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Grafo {
    
    public Vertice cab;
    public Vertice fin;
    private final String sepDato = "@";
    private final String sepDato2 = "*";
    BSList listEtiquetas = new BSList();
    private BSBuilderGraph builder = null;
    BSList objetosrevisado = null;//lista de objetos comprobados

    private boolean visibleExpansion = false;//establece si se muestra la expansion minima
    public enum expansionMinima {
        Grafo
    };
    private expansionMinima metodoExpansion = expansionMinima.Grafo;
    private boolean proceso = false;//es para indicar que se esta realizando un proceso
    //y no realizar continuas actualizaciones cuando este se esté ejecutando, sino cuando termine

    public Grafo(javax.swing.JPanel contenedor) {
        builder = new BSBuilderGraph(contenedor, this);
    }
    public void setAreaGrafica(javax.swing.JPanel contenedor) {
        if (builder != null) {
            builder.setArea(contenedor);
        }
    }
    public void eliminarVertices() {
        cab = null;
        fin = null;
        if (builder != null) {
            builder.setExpansionMinima();
            builder.setCi(new Point(0, 0));
        }
    }
    public void eliminarAdyacencias() {
        setVisibleExpansion(false);
        if (cab != null) {
            Vertice v = cab;
            while (v != null) {
                v.numeroVerticesEntrantes = 0;
                v.numeroVerticesSalientes = 0;
                v.listaAdyacenciaSaliente = null;
                v.listaAdyacenciaEntrante = null;
                v = v.siguienteVerticeEnLista;
            }
        }
    }
    private boolean isNombreVerticeValido(String nombre) {
        if ((nombre.trim().equals("") | nombre.equals(sepDato)
                | nombre.equals(sepDato2))) {
            return false;
        }
        return true;
    }
    public boolean agregarVertices(String nombres) {
        String inicios[] = nombres.trim().split(",");

        for (int i = 0; i < inicios.length; i++) {
            if (isNombreVerticeValido(inicios[i]) == false || buscarVertice(inicios[i]) != null) {
                continue;
            }
            Vertice q = new Vertice(inicios[i]);
            if (cab == null) {
                cab = q;
                fin = q;
            } else {
                fin.siguienteVerticeEnLista = q;
                fin = q;
            }
            if (builder != null) {
                builder.agregarVerticeGrafico(q);
            }
        }
        if (builder != null) {
            builder.updateAndRepaint();
        }
        return true;
    }
    public boolean agregarAdyacencia(Vertice verticeInicio, Vertice verticeFinal, double peso, boolean dirigido) {
        boolean valor = verticeInicio.agregarAdyacencia(verticeFinal, peso, dirigido);
        if (!proceso) {//si no se está realizando un proceso de añadir varios adyacentes
            if (builder != null) {//si tenemos un visor de grafo
                if (isVisibleExpansion()) {
                    builder.setExpansionMinima();
                }
            }
        }
        return valor;
    }
    public boolean agregarAdyacencia(String nombreVerticeInicio, String nombreVerticeFinal, double peso, boolean dirigido) {
        //este procedimiento crea el adyacente si no existe
        nombreVerticeFinal = nombreVerticeFinal.replace(',', " ".charAt(0));
        Vertice vi = buscarVertice(nombreVerticeInicio);
        Vertice vf = buscarVertice(nombreVerticeFinal);
        if (vi != null) {
            if (vf == null) {
                if (agregarVertices(nombreVerticeFinal) == false) {
                    return false;
                }
                vf = buscarVertice(nombreVerticeFinal);
            }
            if (agregarAdyacencia(vi, vf, peso, dirigido)) {
                if (builder != null) {
                    if (isVisibleExpansion()) {
                        builder.setExpansionMinima();
                    } else {
                        builder.agregarAdyacenteGrafico(vi, vf, dirigido);
                    }

                }
            }
            return true;
        }
        return false;
    }
    boolean eliminarAdyacente(Vertice nomVer, Vertice nomAdy) {
        boolean estado = nomVer.eliminarAdyacencia(nomAdy.getNombreVertice());
        if (estado) {//si se eliminó algo
            if (builder != null) {//y tenemos un visor
                builder.listaResaltados.vaciar();//eliminados la lista de resaltados
                if (isVisibleExpansion()) {
                    builder.setExpansionMinima();
                }
            }
        }
        return estado;
    }
    public Vertice buscarVertice(String verticeBusqueda) {
        Vertice p = cab;
        while (p != null) {
            if (p.getNombreVertice().equals(verticeBusqueda)) {
                return p;
            } else {
                p = p.siguienteVerticeEnLista;
            }
        }
        return null;
    }
    public boolean eliminarVertice(String nombre) {
        if (cab != null) {
            if (cab.getNombreVertice().equals(nombre) && cab.siguienteVerticeEnLista == null) {
                cab.quitarRelaciones();
                cab = null;
                fin = null;
                
            } else if (cab.getNombreVertice().equals(nombre) && cab.siguienteVerticeEnLista != null) {
                cab.quitarRelaciones();
                cab = cab.siguienteVerticeEnLista;
            } else {
                Vertice ant = cab;
                Vertice act = cab.siguienteVerticeEnLista;
                while (act != null) {
                    if (act.getNombreVertice().equals(nombre)) {
                        act.quitarRelaciones();
                        ant.siguienteVerticeEnLista = act.siguienteVerticeEnLista;
                        if (act == fin) {
                            fin = ant;
                        }
                        break;
                    }
                    ant = act;
                    act = act.siguienteVerticeEnLista;
                }
            }
        }
        
        return false;
    }
    public boolean isVertice2AdyacenteVertice1(String nombreVertice1, String nombreVertice2) {
        Vertice bus = buscarVertice(nombreVertice1);
        if (bus != null) {
            Vertice bus2 = buscarVertice(nombreVertice2);
            if (bus2 != null) {
                return bus.isInAdyacentes(bus2);
            }
        }
        return false;
    }
    @Override
    public String toString() {
        String t = "Contenido del Grafo: \n\n";
        Vertice p = cab;
        while (p != null) {
            t += p.toString() + "\n";
            p = p.siguienteVerticeEnLista;
        }
        return t;
    }
    public String toString(String fin) {
        String t = "Contenido del Grafo: \n\n";
        Vertice p = cab;
        Vertice aux= cab;
        Vertice aux2=null;
        while (aux != null) {
            if (aux.getNombreVertice().equals(fin)) {
                aux2 = aux;
            }
            aux = aux.siguienteVerticeEnLista;
        }
        while (p != null) {
            t += p.toString(aux2) + "\n";
            p = p.siguienteVerticeEnLista;
        }
        return t;
    }
    public void allVerWithAll(int var) {
        int min = 1;
		int max = 10;

        proceso = true;
        
        double maxYV = 0.0;
        
        double maxXV = 0.0;
        double minYV = 99999.0;
        double minXV = 99999.0;

        Vertice aux = cab;
        while (aux != null) {
            if(maxXV<aux.x){
                maxXV = aux.x;
            }
            if(maxYV<aux.y){
                maxYV = aux.y;
            }
            if(minXV>aux.x){
                minXV = aux.x;
            }
            if(minYV>aux.y){
                minYV = aux.y;
            }
            aux = aux.siguienteVerticeEnLista;
        }

        double difX = maxXV - minXV;
        double difY = maxYV - minYV;

        int distacia = (int)(Math.sqrt((difX*difX)+(difY*difY))/(4.0));
                if (cab != null) {
                    Vertice tcab = cab;
                    while (tcab != null) {
                        Vertice tact = cab;
                        
                        while (tact != null) {
                            if (tact != tcab && ThreadLocalRandom.current().nextInt(min, max + 1)%5 == 0) {
                                switch (var) {
                                    case 0:
                                        //Auto Adyacencia del Menu
                                        agregarAdyacencia(tcab, tact, Math.random()*100, false);
                                    break;
                                    case 1:
                                        //Auto Adyacencia G<<H
                                        agregarAdyacencia(tcab, tact, ThreadLocalRandom.current().nextInt(distacia, distacia*2)*10, false);
                                    break;
                                    case 2:
                                        //Auto Adyacencia G Aprox H
                                        agregarAdyacencia(tcab, tact, ThreadLocalRandom.current().nextInt(distacia, distacia*2), false);
                                    break;
                                    case 3:
                                        //Auto Adyacencia G>>H
                                        agregarAdyacencia(tcab, tact, ThreadLocalRandom.current().nextInt(distacia, distacia*4)/10, false);
                                    break;
                                    default:
                                    break;
                                }
                                
                            }
                            tact = tact.siguienteVerticeEnLista;
                        }
                        tcab = tcab.siguienteVerticeEnLista;
                    }
                }
			
		
        
        proceso = false;
        updateGraphics();
        
    }
    //Guardar grafo
    public void archivoGuardar(String nomarchivo) throws IOException {
        FileOutputStream archivo = null;
        try {
            archivo = new FileOutputStream(nomarchivo);
            DataOutputStream escritura = new DataOutputStream(archivo);

            escritura.writeBoolean(this.isVisibleExpansion());
            escritura.writeBoolean(builder.dibujarCuadricula);
            escritura.writeInt(builder.getCi().x);
            escritura.writeInt(builder.getCi().y);
            
            Vertice v = cab;
            ///guardo los vertices
            while (v != null) {
                escritura.writeUTF(v.getNombreVertice());
                escritura.writeInt((int) v.getX());
                escritura.writeInt((int) v.getY());
                
                v = v.siguienteVerticeEnLista;
            }
            escritura.writeUTF(sepDato);//termine de guardar los vertices
            v = cab;
            while (v != null) {
                if (v.listaAdyacenciaSaliente != null) {
                    escritura.writeUTF(v.getNombreVertice());//guardo el vertice
                    
                    Adyacente ady = v.listaAdyacenciaSaliente;
                    while (ady != null) {
                        escritura.writeUTF(ady.vertice.getNombreVertice());//con sus correspondientes adyacentes y pesos
                        escritura.writeDouble(ady.peso);
                        escritura.writeBoolean(ady.dirigido);
                        
                        ady = ady.sig;
                    }
                    escritura.writeUTF(sepDato2);//guardo el separador
                    
                }

                v = v.siguienteVerticeEnLista;
            }
            escritura.writeUTF(sepDato);
            escritura.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ioe) {
        }
        try {
            archivo.close();
        } catch (IOException ex) {

        }
    }
    public void archivoCargar(String nomarchivo) throws IOException {
        builder.cargandoArchivo = true;
        builder.dibujarInmediato = false;
        FileInputStream archivo = null;
        try {
            archivo = new FileInputStream(nomarchivo);
            DataInputStream lectura = new DataInputStream(archivo);
            lectura.readBoolean();
            builder.dibujarCuadricula = lectura.readBoolean();
            Point tci = new Point(0, 0);
            tci.x = lectura.readInt();//coordenadas de inicio
            tci.y = lectura.readInt();//coordenadas de inicio

            builder.setCi(new Point(tci));

            cab = null;
            fin = null;
            ///guardo los vertices
            String nombre = null;
            while (true) {
                nombre = lectura.readUTF();
                if (nombre.equals(sepDato)) {
                    break;//cuando obtenga el separador se detiene
                }
                Point coordenadasVertice = new Point();
                coordenadasVertice.x = lectura.readInt();
                coordenadasVertice.y = lectura.readInt();
                builder.coordenadasVertice = coordenadasVertice;
                agregarVertices(nombre);
            }
            if (cab == null) {
                archivo.close();
            } else {
                String nadya = null;
                double padya = 0;
                while (true) {
                    nombre = lectura.readUTF();
                    if (nombre.equals(sepDato)) {
                        break;
                    }
                    while (true) {
                        nadya = lectura.readUTF();
                        if (nadya.equals(sepDato2)) {
                            break;
                        }
                        padya = lectura.readDouble();
                        boolean dir = lectura.readBoolean();
                        agregarAdyacencia(nombre, nadya, padya, dir);
                    }

                }
                archivo.close();
                if (isVisibleExpansion()) {
                    builder.setExpansionMinima();
                }
            }
        } catch (IOException ioe) {
        }
        try {
            archivo.close();
            if (isVisibleExpansion()) {
                builder.setExpansionMinima();
            }
        } catch (NullPointerException npe) {
        } catch (IOException ex) {

        }
        builder.dibujarInmediato = true;
        builder.cargandoArchivo = false;
        builder.updateGraphics();
        builder.repaint();
    }
    public void repaint() {
        builder.repaint();
    }
    public void updateGraphics() {
        builder.updateAndRepaint();
    }
    public boolean isDibujarCuadricula() {
        return builder.dibujarCuadricula;
    }
    public void setDibujarCuadricula(boolean dibujarCuadricula) {
        this.builder.dibujarCuadricula = dibujarCuadricula;
        updateGraphics();
        repaint();
    }
    public void interfazTerminarArrastre(int x, int y, int button) {
        builder.interfazTerminarArrastre(x, y, button);
    }
    public void interfazClickOIniciarArrastre(int x, int y, int button) {
        builder.interfazClickOIniciarArrastre(x, y, button);
    }
    public void interfazArrastre(int x, int y, boolean limitar) {
        builder.interfazArrastre(x, y, limitar);
    }
    public String interfazMovimientoMouse(int x, int y) {
        return builder.interfazMovimientoMouse(x, y);
    }
    public void setPesoGrafico(int peso) {
        if (peso > 0) {
            builder.pesoAdyacenteGrafico = peso;
        }
    }
    public boolean isVisibleExpansion() {
        return visibleExpansion;
    }
    public void setVisibleExpansion(boolean visibleExpansion) {
        this.visibleExpansion = visibleExpansion;
        if (builder != null) {
            builder.setMetodoExpansion(metodoExpansion);
            if (visibleExpansion) {
                if (metodoExpansion == expansionMinima.Grafo) {
                    builder.colorExpansion = Color.RED;
                    builder.setExpansionMinima();
                } else {
                    builder.colorExpansion = Color.orange;
                    builder.setExpansionMinima();
                }

            } else {
                builder.setExpansionMinima();
            }
        }
    }
    public void setMetodoExpansion( ) {
        setVisibleExpansion(visibleExpansion);

    }
}


