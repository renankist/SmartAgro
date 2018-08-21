package smartagro;

import java.text.DateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telas.FrmPrincipal;

public class SmartAgro {

    public static void main(String[] args) {

        /* Set the Synthetica Plain look and feel */
        try {
            javax.swing.UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        new FrmPrincipal().setVisible(true);

    }

}

//public class SmartAgro {
//
//    final static Logger LOGGER = LoggerFactory.getLogger(SmartAgro.class);
//
//    public SmartAgro() {
//
//        LOGGER.info("Inside App Constructor...creating instance"+ DateFormat.getInstance().format(new Date()));
//    }
//
//    public static void main(String[] args) {
//        LOGGER.warn("main...");
//        System.out.println("start logging");
//        try {
//            long s = System.currentTimeMillis();
//            for (int i = 0; i < 2; i++) {
//                new SmartAgro().writeToDB();
//                LOGGER.debug("Writing to DB");
//            }
//            LOGGER.error("Exception ", new RuntimeException("Testing Exception"));
//            long e = System.currentTimeMillis();
//            System.out.println(" Time taken to write to DB = " + (e - s) / 1000 + " seconds");
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    private void writeToDB() {
//        LOGGER.debug("Writing to DB");
//    }
//}
