package apoio;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    public Backup(){
        
    }
    
    public boolean restaurar(String arq){
        
        boolean retorno = false; 
        
        
        String[] executeCmd = {"pg_restore","-1","-d",dbName,"--username",dbUser,"-w",arq};
        
        if(executarComandoBanco(executeCmd)){
            System.out.println(("Restauração executada com sucesso"));
            retorno = true;
        }else{
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
        String[] executeCmd = {
            "pg_dump", "-h", "localhost", "-p", "5432", "-U", "postgres", "-F", "c", "-b", "-v", "-f", savePath, dbName};
        
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
        System.out.print("execute: ");
        for (String string : executeCmd) {
            System.out.print(string + " ");
        }
        System.out.println("");
      
        ProcessBuilder runtimeProcess = new ProcessBuilder(executeCmd);
        
        System.out.println(dir);
        
        runtimeProcess.environment().put("PGPASSWORD", "postgres");
        
        try {
            int processComplete = runtimeProcess.start().waitFor();
            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Sucesso no executarComandoBanco");
                retorno = true;
            } else {
                System.out.println("Erro no executarComandoBanco");
                retorno = false;
            }
        } catch (IOException | InterruptedException ex) {
            retorno = false;

        }
        
        return retorno;
    }

}
