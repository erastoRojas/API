package servicios;

import Utils.Constantes;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static javax.ws.rs.client.Entity.json;

/**
 *
 * @author erasto
 */
public class apiServicios {
    
    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    JsonFactory JSON_FACTORY = new JacksonFactory();
    
    HttpRequestFactory requestFactory
          = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
              @Override
              public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));
              }
          });
    
    public GenericData data = new GenericData();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date today = Calendar.getInstance().getTime();
    public String reportDate = df.format(today);

    public GenericJson dataParadas(String parada) throws IOException {
        
        data.put("idClient", "WEB.SERV.loginnavidad@gmail.com");
        data.put("passKey", "B573104A-F864-4064-8083-E633F60BD8D2");
        data.put("SelectDate",reportDate);
        data.put("Lines",parada);
        
        GenericUrl url = new GenericUrl(Constantes.URL_PARADAS);
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        GenericJson json = requestGoogle.execute().parseAs(GenericJson.class);

        return json;
    }

    public GenericJson dataTiempo(String idParada) throws IOException {
            
        data.put("idClient", "WEB.SERV.loginnavidad@gmail.com");
        data.put("passKey", "B573104A-F864-4064-8083-E633F60BD8D2");
        data.put("idStop",idParada);
        
        GenericUrl url = new GenericUrl(Constantes.URL_TIEMPO);
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        GenericJson json = requestGoogle.execute().parseAs(GenericJson.class);
        
        return json;
    }

    public GenericJson dataLineas() throws IOException {
        
        data.put("idClient", "WEB.SERV.loginnavidad@gmail.com");
        data.put("passKey", "B573104A-F864-4064-8083-E633F60BD8D2");
        data.put("SelectDate",reportDate);
        
        GenericUrl url = new GenericUrl(Constantes.URL_LINEAS);
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        GenericJson json = requestGoogle.execute().parseAs(GenericJson.class);
        
        return json;
    }
}
