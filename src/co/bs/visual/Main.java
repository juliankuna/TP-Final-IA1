package co.bs.visual;


public class Main {
    
    public static void main(String[] args) throws Exception {
        Controlador controlador = new Controlador();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmPrincipal frame1 = new FrmPrincipal(controlador);
                frame1.setVisible(true);
                
                new FrmSabiasQue().setVisible(true);
            }
        });
    }
}
