package co.bs.visual;

import co.bs.graph.Grafo;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FrmPrincipal extends javax.swing.JFrame  {

    public Grafo grafo;
    public FrmInformacion finfo;
    private Controlador control;
    int warea = 0, harea = 0;
    public FrmPrincipal(Controlador control) {
        this.control = control;
        initComponents();
        warea = areagrafica.getWidth();
        harea = areagrafica.getHeight();
    }
    private void initComponents() {
        USO = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nomVertice = new javax.swing.JTextField();
        vertice_Inicio = new javax.swing.JTextField();
        vertice_Fin = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        caminoA = new javax.swing.JButton();
        AutoAdyacencia = new javax.swing.JButton();
        Agregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelExpansion = new javax.swing.JPanel();
        grupoExpansion = new javax.swing.ButtonGroup();
        opcionGMayor = new javax.swing.JRadioButton();
        opcionGMenor = new javax.swing.JRadioButton();
        opcionGAprox = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        areagrafica = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtinfoV = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuInformacion = new javax.swing.JMenuItem();
        menueliminarVertices = new javax.swing.JMenuItem();
        menuEliminarAdys = new javax.swing.JMenuItem();
        menuadyacenciauno = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menucuadricula = new javax.swing.JCheckBoxMenuItem();
        USO.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        USO.setResizable(false);
        USO.getContentPane().setLayout(null);
        jLabel7.setFont(new java.awt.Font("Traditional Arabic", 0, 36));
        jLabel7.setText("Modo De Uso");
        USO.getContentPane().add(jLabel7);
        jLabel7.setBounds(70, 10, 240, 60);
        jLabel9.setText("jLabel9");
        USO.getContentPane().add(jLabel9);
        jLabel9.setBounds(140, 120, 35, 14);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(20, 50, 200, 20);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(20, 215, 200, 20);
        panelExpansion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelExpansion.setLayout(null);
        grupoExpansion.add(opcionGMayor);
        opcionGMayor.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        opcionGMayor.setSelected(false);
        opcionGMayor.setText("G >> H");
        opcionGMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionActionPerformed(evt);
            }
        });
        panelExpansion.add(opcionGMayor);
        opcionGMayor.setBounds(20, 10, 71, 23);
        grupoExpansion.add(opcionGAprox);
        opcionGAprox.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        opcionGAprox.setSelected(false);
        opcionGAprox.setText("G aprox. H");
        opcionGAprox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionActionPerformed(evt);
            }
        });
        panelExpansion.add(opcionGAprox);
        opcionGAprox.setBounds(110, 10, 90, 23);
        grupoExpansion.add(opcionGMenor);
        opcionGMenor.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        opcionGMenor.setSelected(false);
        opcionGMenor.setText("G << H");
        opcionGMenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionActionPerformed(evt);
            }
        });
        panelExpansion.add(opcionGMenor);
        opcionGMenor.setBounds(220, 10, 71, 23);
        getContentPane().add(panelExpansion);
        panelExpansion.setBounds(430, 10, 300, 80);
        caminoA.setText("Buscar Camino A*");
        caminoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaminoAActionPerformed(evt);
            }
        });
        getContentPane().add(caminoA);
        caminoA.setBounds(40, 175, 140, 23);
        grupoExpansion.add(AutoAdyacencia);
        AutoAdyacencia.setText("Auto-Adyacencia");
        AutoAdyacencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuadyacenciaunoActionPerformed(evt,1);
            }
        });
        getContentPane().add(AutoAdyacencia);
        panelExpansion.add(AutoAdyacencia);
        AutoAdyacencia.setBounds(90, 40, 130, 23);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trabajo Final IA - 1");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(null);
        nomVertice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomVerticeActionPerformed(evt);
            }
        });
        nomVertice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomVerticeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomVerticeKeyReleased(evt);
            }
        });
        getContentPane().add(nomVertice);
        nomVertice.setBounds(160, 10, 150, 20);
        getContentPane().add(vertice_Inicio);
        vertice_Inicio.setBounds(160, 70, 30, 20);
        getContentPane().add(vertice_Fin);
        vertice_Fin.setBounds(160, 100, 30, 20);
        txtPeso.setText("1");
        txtPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoActionPerformed(evt);
            }
        });
        getContentPane().add(txtPeso);
        txtPeso.setBounds(160, 135, 30, 20);
        Agregar.setText("Agregar");
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(Agregar);
        Agregar.setBounds(320, 8, 90, 23);
        jLabel2.setFont(new java.awt.Font("Courier New", 0, 12)); 
        jLabel2.setText("Nombre de Vertice:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 13, 140, 14);
        jLabel10.setFont(new java.awt.Font("Courier New", 0, 12)); 
        jLabel10.setText("Vertice Inicio:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 75, 140, 14);
        jLabel11.setFont(new java.awt.Font("Courier New", 0, 12)); 
        jLabel11.setText("Vertice Fin:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(20, 105, 140, 14);
        jLabel1.setFont(new java.awt.Font("Courier New", 0, 12)); 
        jLabel1.setText("Info");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 230, 140, 20);
        jLabel3.setFont(new java.awt.Font("Courier New", 0, 12)); 
        jLabel3.setText("Peso:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 135, 140, 20);
        areagrafica.setForeground(new java.awt.Color(0, 0, 255));
        areagrafica.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        areagrafica.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                areagraficaMouseWheelMoved(evt);
            }
        });
        areagrafica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                areagraficaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                areagraficaMouseReleased(evt);
            }
        });
        areagrafica.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                areagraficaMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                areagraficaMouseMoved(evt);
            }
        });
        areagrafica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                areagraficaKeyPressed(evt);
            }
        });
        getContentPane().add(areagrafica);
        areagrafica.setBounds(250, 100, 730, 470);
        txtinfoV.setBackground(new java.awt.Color(240, 240, 240));
        txtinfoV.setColumns(20);
        txtinfoV.setEditable(false);
        txtinfoV.setFont(new java.awt.Font("Monospaced", 0, 11));
        txtinfoV.setRows(5);
        jScrollPane2.setViewportView(txtinfoV);
        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 260, 200, 200);
        jMenu1.setText("Archivo");
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenuItem2.setText("Modo De Uso");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenuBar1.add(jMenu1);
        jMenu2.setText("Vertices");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        menuInformacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menuInformacion.setText("Información");
        menuInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInformacionActionPerformed(evt);
            }
        });
        jMenu2.add(menuInformacion);
        menueliminarVertices.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/bs/images/application_osx_terminal.png")));
        menueliminarVertices.setText("Eliminar Vertices");
        menueliminarVertices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menueliminarVerticesActionPerformed(evt);
            }
        });
        jMenu2.add(menueliminarVertices);
        menuEliminarAdys.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/bs/images/chart_line_delete.png")));
        menuEliminarAdys.setText("Eliminar Adyacencias");
        menuEliminarAdys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEliminarAdysActionPerformed(evt);
            }
        });
        jMenu2.add(menuEliminarAdys);
        menuadyacenciauno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/bs/images/arrow_out.png")));
        menuadyacenciauno.setText("Adyacencia - Automaticas");
        menuadyacenciauno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuadyacenciaunoActionPerformed(evt,0);
            }
        });
        jMenu2.add(menuadyacenciauno);
        jMenu2.add(jSeparator4);
        menucuadricula.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        menucuadricula.setText("Rejilla");
        menucuadricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/bs/images/application_view_columns.png")));
        menucuadricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menucuadriculaActionPerformed(evt);
            }
        });
        jMenu2.add(menucuadricula);
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);
        setSize(new java.awt.Dimension(1015, 656));
        setLocationRelativeTo(null);
    }
    private void btnAgregar_VerticeActionPerformed(java.awt.event.ActionEvent evt) {
        grafo.agregarVertices(nomVertice.getText());
    }
    @Override
    public void paint(Graphics g) {
        try {
            paintComponents(g);
            grafo.repaint();
        } catch (Exception e) {
        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        grafo = new Grafo(areagrafica);
        try {
            grafo.archivoCargar("grafo.bs");
            menucuadricula.setSelected(grafo.isDibujarCuadricula());
        } catch (IOException ex) {
        }
    }
    private void areagraficaMouseReleased(java.awt.event.MouseEvent evt) {
        grafo.interfazTerminarArrastre(evt.getX(), evt.getY(), evt.getButton());
    }
    private void areagraficaMouseMoved(java.awt.event.MouseEvent evt) {
        if (grafo != null)
        txtinfoV.setText(grafo.interfazMovimientoMouse(evt.getX(), evt.getY()));
    }
    private void opcionActionPerformed(java.awt.event.ActionEvent evt) {
        grafo.setMetodoExpansion();
    }
    private void areagraficaMouseDragged(java.awt.event.MouseEvent evt) {
        try {
            grafo.setPesoGrafico(Integer.parseInt(txtPeso.getText()));
        } catch (Exception e) {
        }
        grafo.interfazArrastre(evt.getX(), evt.getY(), true);
    }
    private void areagraficaMousePressed(java.awt.event.MouseEvent evt) {
        grafo.interfazClickOIniciarArrastre(evt.getX(), evt.getY(), evt.getButton());
    }
    private void menuadyacenciaunoActionPerformed(java.awt.event.ActionEvent evt,int var) {
        if (var==0) {
            grafo.allVerWithAll(0);
            return;   
        }
        if (var==1) {
            if (opcionGMayor.isSelected()) {
                grafo.allVerWithAll(1);
            return; 
            }
            if (opcionGAprox.isSelected()) {
                grafo.allVerWithAll(2);
            return; 
            }
            if (opcionGMenor.isSelected()) {
                grafo.allVerWithAll(3);
            return; 
            }
        }
    }
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    private void nomVerticeKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == 10) {
            btnAgregar_VerticeActionPerformed(null);
            nomVertice.requestFocus();
            nomVertice.selectAll();
        }
    }
    private void formWindowClosed(java.awt.event.WindowEvent evt) {
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        try {
            grafo.archivoGuardar("grafo.bs");
        } catch (IOException ex) {
        }
    }
    private void menueliminarVerticesActionPerformed(java.awt.event.ActionEvent evt) {
        grafo.eliminarVertices();
        grafo.updateGraphics();
        grafo.repaint();
    }
    private void menuEliminarAdysActionPerformed(java.awt.event.ActionEvent evt) {
        grafo.eliminarAdyacencias();
        grafo.updateGraphics();
        grafo.repaint();
    }
    private void btncaminoAActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nombreInicio = vertice_Inicio.getText();
            String nombreFin = vertice_Fin.getText();
            if (nombreInicio.equals("") || nombreFin.equals("")) {
                throw new Exception("Indique Vertice Inicio-Fin.");
            }
            control.proceso_de_Inicio(grafo, nombreInicio, nombreFin);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        btnAgregar_VerticeActionPerformed(null);
        nomVertice.requestFocus();
        nomVertice.selectAll();
    }
    private void nomVerticeActionPerformed(java.awt.event.ActionEvent evt) {
        
    }
    private void nomVerticeKeyReleased(java.awt.event.KeyEvent evt) {
    }
    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void menucuadriculaActionPerformed(java.awt.event.ActionEvent evt) {
        grafo.setDibujarCuadricula(!grafo.isDibujarCuadricula());
        menucuadricula.setSelected(grafo.isDibujarCuadricula());

    }
    private void areagraficaMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
    }
    private void areagraficaKeyPressed(java.awt.event.KeyEvent evt) {
    }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {
    }
    private void formComponentResized(java.awt.event.ComponentEvent evt) {
        if (this.getWidth() <= warea + areagrafica.getX()) {
            this.setSize(warea + areagrafica.getX() + 50, this.getHeight());
        } else if (this.getWidth() >= areagrafica.getX() + areagrafica.getWidth() + 50) {
            areagrafica.setSize(this.getWidth() - areagrafica.getX() - 50, areagrafica.getHeight());
        }
        if (this.getHeight() <= harea + areagrafica.getY()) {
            this.setSize(this.getWidth(), harea + areagrafica.getY() + 100);
        } else if (this.getHeight() >= areagrafica.getY() + areagrafica.getHeight() + 100) {
            areagrafica.setSize(areagrafica.getWidth(), this.getHeight() - areagrafica.getY() - 100);
        }
        if (grafo != null) {
            grafo.setAreaGrafica(areagrafica);
        }
    }
    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void menuInformacionActionPerformed(java.awt.event.ActionEvent evt) {
        if (finfo == null) {
            String fin=vertice_Fin.getText();
            finfo = new FrmInformacion(this,fin);
            finfo.setVisible(true);
        }
    }
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        new FrmSabiasQue().setVisible(true);
    }
    /**
     * @param args
     */
    private javax.swing.JPanel areagrafica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem menuEliminarAdys;
    private javax.swing.JMenuItem menuInformacion;
    private javax.swing.JMenuItem menuadyacenciauno;
    private javax.swing.JCheckBoxMenuItem menucuadricula;
    private javax.swing.JMenuItem menueliminarVertices;
    private javax.swing.JDialog USO;
    public javax.swing.JTextField nomVertice;
    public javax.swing.JTextField vertice_Inicio;
    private javax.swing.JTextField txtPeso;
    public javax.swing.JTextField vertice_Fin;
    private javax.swing.JTextArea txtinfoV;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton Agregar;
    private javax.swing.JButton caminoA;
    private javax.swing.JButton AutoAdyacencia;
    private javax.swing.JRadioButton opcionGMayor;
    private javax.swing.JRadioButton opcionGMenor;
    private javax.swing.JRadioButton opcionGAprox;
    private javax.swing.ButtonGroup grupoExpansion;
    private javax.swing.JPanel panelExpansion;
}
