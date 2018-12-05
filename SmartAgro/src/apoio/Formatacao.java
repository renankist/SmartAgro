package apoio;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import javax.swing.text.*;
import java.math.BigDecimal;

public class Formatacao {

    static DecimalFormat df = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static JFormattedTextField getFormatado(String formato) {
        JFormattedTextField campoFormatado = null;
        MaskFormatter format = new MaskFormatter();

        format.setPlaceholderCharacter(' ');
        format.setValueContainsLiteralCharacters(false);

        try {
            format.setMask(formato);
            campoFormatado = new JFormattedTextField(format);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return campoFormatado;
    }

    public static void formatarDecimal(JTextField campo) {
        String valor = campo.getText();
        campo.setText(df.format(Double.parseDouble(valor)));
    }
    public static String DataDMA(Date data) {
        if (data == null) {
            return null;
        } else {
            String dataFormatada = null;
            try {
                dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data);
            } catch (Exception e) {
                System.err.println(e);
            }
            return (dataFormatada);
        }
    }
    
    public static String formatarDecimal(double valor) {
        NumberFormat formatter = new DecimalFormat("##,###,###,##0.00");
        return (formatter.format(valor));
    }

    public static JFormattedTextField getTelefone() {
        return getFormatado("(##) #####-####");
    }

    public static JFormattedTextField getCNPJ() {
        return getFormatado("##.###.###/####-##");
    }

    public static JFormattedTextField getCPF() {
        return getFormatado("###.###.###-##");
    }

    public static JFormattedTextField getData() {
        return getFormatado("##/##/####");
    }

    public static JFormattedTextField getDataHora() {
        return getFormatado("##/##/#### ##:##");
    }

    public static void formatoDecimal(JTextField campo) {
        campo.setText(df.format(Double.parseDouble(campo.getText())));
    }

    public static void reformatarData(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##/##/####");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarCpf(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("###.###.###-##");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarCnpj(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##.###.###/####-##");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarTelefone(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("(##) #####-####");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void reformatarDecimal(JFormattedTextField campo) {
        try {
            MaskFormatter m = new MaskFormatter();
            m.setPlaceholderCharacter(' ');
            m.setMask("##,###,###,##0.00");
            campo.setFormatterFactory(null);
            campo.setFormatterFactory(new DefaultFormatterFactory(m));
            campo.setValue(null);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static String ajustaDataDMA(String data) {
        String dataFormatada = null;
        try {
            Date dataAMD = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataAMD);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }

    public static BigDecimal converteStringParaBigDecimal(String valor) {
        // Create a DecimalFormat that fits your requirements
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        // Parse the string
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = (BigDecimal) decimalFormat.parse(valor);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return bigDecimal;
    }

    public static Date converteStringParaDate(String data) {
        java.sql.Date dataDate = null;
        try {
            Date dataAMD = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = format.format(dataAMD);
            dataDate = new java.sql.Date(format.parse(dataFormatada).getTime());
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataDate);
    }

    public static String ajustaDataAMD(String data) {
        String dataFormatada = null;
        try {
            Date dataDMA = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(dataDMA);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }

    public static String removerFormatacao(String dado) {
        String retorno = "";
        for (int i = 0; i < dado.length(); i++) {
            if (dado.charAt(i) != '.' && dado.charAt(i) != '/' && dado.charAt(i) != '-'
                    && dado.charAt(i) != ')' && dado.charAt(i) != '(') {
                retorno = retorno + dado.charAt(i);
            }
        }
        return (retorno);
    }

    public static String getDataAtual() {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dataHoje = df.format(now);

        return dataHoje;
    }

    public static String getDataHoraAtual() {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataHoje = df.format(now);

        return dataHoje;
    }
}
