package apoio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientCepWS {

    private final String USER_AGENT = "Mozilla/5.0";
    private String url;
    private String cep;

    public static void main(String args[]) {

        ClientCepWS c = new ClientCepWS("95912378");  
        try {
            
            
            String json = c.get();
            
            System.out.println(json);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClientCepWS(String cep) {

        this.cep = cep;
        this.url = "https://viacep.com.br/ws/"+this.cep+ "/json/";
        
        System.out.println(this.url);

    }
    
    public String getUrl(){
        return this.url;
    }
    
    public String get() throws Exception {

        URL obj = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        System.out.println("Send get to url: " + url);
        
        System.out.println("Response code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }

}
