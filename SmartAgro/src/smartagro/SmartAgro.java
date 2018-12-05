package smartagro;

import apoio.RSAcriptografia;
import dao.GenericDAO;
import entidade.Licenca;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import telas.jfrLogin;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import telas.DlgLicenca;

public class SmartAgro {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SmartAgro().start();
            }
        });
    }

    // Tela de splash
    JWindow window = new JWindow();

    public void start() {

        window.getContentPane().add(new pnlSplash());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        new SmartAgro.StartWorker().execute();

    }

    private class StartWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {

            // Faz a primeira consulta no banco para carregar o hibernate
            new GenericDAO().consultarPorId(1, "Cidade");
            return null;
        }

        @Override
        protected void done() {

            // Dispose window when background process is finished
            window.dispose();

            // Abre a tela de login
            jfrLogin telaLogin = new jfrLogin();
            telaLogin.setVisible(true);

        }
    }
}
