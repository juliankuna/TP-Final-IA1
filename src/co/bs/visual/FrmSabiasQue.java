package co.bs.visual;

public class FrmSabiasQue extends javax.swing.JFrame {

    public FrmSabiasQue() {
        initComponents();
        String mensaje=new String(
                "....\n"
                +"*Crear Vertice Completando Nombre + (*Enter | Agregar) "
                +"\n*Mover Vertices Arrastrandolos con Click-Izq"
                +"\n*El Peso se Asigna con el valor ingresado"
                +"\n*Crear adyacencia soltando un vertice sobre otro"
                +"\n*Eliminar adyacencias presionando click sobre su linea"
                +"\n*Eliminar vertices seleccionados con la tecla Suprimir"
                +"\n*...."
                +"\n*Buscar Camino A*");
        txtmensajes.setText(mensaje);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtmensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36));
        jLabel1.setText("Modo De Uso");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 410, 50);

        setTitle("Modo De Uso");
        txtmensajes.setBackground(java.awt.SystemColor.control);
        txtmensajes.setColumns(20);
        txtmensajes.setEditable(false);
        txtmensajes.setFont(new java.awt.Font("Monospaced", 0, 12)); 
        txtmensajes.setRows(5);
        txtmensajes.setAutoscrolls(false);
        txtmensajes.setBorder(null);
        txtmensajes.setOpaque(false);
        jScrollPane1.setViewportView(txtmensajes);
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 440, 170);
        setSize(new java.awt.Dimension(484, 284));
        setLocationRelativeTo(null);
    }
    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {
    }
    /**
    * @param args
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSabiasQue().setVisible(true);
            }
        });
    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtmensajes;

}
