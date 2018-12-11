package apoio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Backup {

    private String dir;
    private String dbName = "smartagro";
    private String dbUser = "postgres";

    public Backup(String dir) {
        this.dir = dir;
    }

    public Backup() {

    }

    public boolean restaurar(String arq) {

        boolean retorno = false;

        this.dir = arq;

        String[] executeCmd = {"arquivos/pg_restore", "-d", dbName, "-h", "localhost", "-p 5432", "-U", dbUser, "--clean", "-w", dir};

        if (executarComandoBanco(executeCmd)) {
            System.out.println(("Restauração executada com sucesso"));
            retorno = true;
        } else {
            System.out.println(("Restauração executada com erro"));
        }
        return retorno;
    }

    public boolean fecharConexoes() {
        boolean retorno = false;

        String select = "SELECT pg_terminate_backend(pid) FROM pg_stat_activity where datname='smartagro'";

        String[] executeCmd = {"arquivos/psql", "-d", dbName, "-h", "localhost", "-p 5432", "-U", dbUser, "--no-password", "-c " + select};

        if (executarComandoBanco(executeCmd)) {
            System.out.println(("Restauração executada com sucesso"));
            retorno = true;
        } else {
            System.out.println(("Restauração executada com erro"));
        }
        return retorno;
    }

    public boolean fazBackup() throws FileNotFoundException {
        boolean retorno = false;

        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy-hh:mm");
        Calendar c = Calendar.getInstance();
        String fileName = df.format(c.getTime()) + "_smartAgro.backup";
        String savePath = this.dir + "/" + fileName;
        /*NOTE: Used to create a cmd command*/
        String[] executeCmd = {"arquivos/pg_dump", "-h", "localhost", "-p", "5432", "-U", "postgres", "-F", "c", "-b", "-v", "-f", savePath, dbName};

        if (executarComandoBanco(executeCmd)) {
            System.out.println(("Backup executado com sucesso"));
            retorno = true;

        } else {
            System.out.println(("Erro ao executar backup"));
        }
        return retorno;

    }

    public boolean executarComandoBanco(String[] executeCmd) {

        boolean retorno = false;

        for (String string : executeCmd) {
            System.out.print(string + " ");
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(executeCmd);
            pb.redirectErrorStream(true);
            pb.environment().put("PGPASSWORD", "postgres");
            String line = null;
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            retorno = true;

    }
    catch (Exception e

    
        ) {
            e.printStackTrace();
    }

    return retorno ;
}

}
