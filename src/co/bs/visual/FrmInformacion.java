

package co.bs.visual;

public class FrmInformacion extends javax.swing.JFrame {

    
    FrmPrincipal f1=null;
    String fin;
    public FrmInformacion(FrmPrincipal f1,String fin) {
        initComponents();
        this.fin=fin;
        this.f1=f1;
        if (fin.equals("")) {
            txtinfo.setText(f1.grafo.toString());
        } else {
            txtinfo.setText(f1.grafo.toString(fin));
        }
    }
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtinfo = new javax.swing.JTextArea();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informacion Grafo");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });
        getContentPane().setLayout(null);

        txtinfo.setColumns(20);
        txtinfo.setRows(5);
        jScrollPane1.setViewportView(txtinfo);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 11, 540, 310);

        setSize(new java.awt.Dimension(578, 372));
        setLocationRelativeTo(null);
    }

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {
        f1.finfo=null;
        this.dispose();
    }
    

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtinfo;

}
