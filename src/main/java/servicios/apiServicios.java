/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

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
    GenericData data = new GenericData();

    public GenericJson requestGoogle(GenericUrl url, GenericData data) throws IOException {
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        GenericJson json = requestGoogle.execute().parseAs(GenericJson.class);
        return json;
    }

    public GenericData dataParadas(String parada) throws IOException {
        data.put("idClient", "WEB.SERV.loginnavidad@gmail.com");
        data.put("passKey", "B573104A-F864-4064-8083-E633F60BD8D2");
        data.put("SelectDate","19/01/2018");
        data.put("Lines",parada);

        return data;
    }

    public GenericData dataTiempo(String idParada) {
        data.put("idClient", "WEB.SERV.loginnavidad@gmail.com");
        data.put("passKey", "B573104A-F864-4064-8083-E633F60BD8D2");
        data.put("idStop",idParada);
        
        return data;
    }

    public GenericData dataLineas() {
        data.put("idClient", "WEB.SERV.loginnavidad@gmail.com");
        data.put("passKey", "B573104A-F864-4064-8083-E633F60BD8D2");
        data.put("SelectDate","19/01/2018");
        
        return data;
    }
    
}
