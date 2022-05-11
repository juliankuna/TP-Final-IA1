package co.bs.visual;


public class Main {
    
    public static void main(String[] args) throws Exception {
        Controlador controlador = new Controlador();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal(controlador).setVisible(true);
                new FrmSabiasQue().setVisible(true);
            }
        });
    }
}
