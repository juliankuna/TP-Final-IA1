package co.bs.visual;
/**
 * Frame creado para visualizar en pantalla los resultados obtenidos por el algoritmo, y el resultado de cada iteración del mismo.
 * @author Diego Zitelli
 */
public class FrmResultado extends javax.swing.JFrame {

    public FrmResultado(String mensaje) {
        initComponents();
       
        txtmensajes.setText(mensaje);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtmensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36));
        jLabel1.setText("Resultado:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 410, 50);

        setTitle("Resultado");
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
        jScrollPane1.setBounds(10, 60, 740, 470);
        setSize(new java.awt.Dimension(784, 584));
        setResizable(true);
        setLocationRelativeTo(null);
    }
    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {
    }
    /**
    * @param args
    */
    
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtmensajes;

}
