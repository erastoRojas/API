/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

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
import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author erasto
 */
@WebServlet(name = "ApiBus", urlPatterns = {"/apiBus"})
public class ApiBus extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        JsonFactory JSON_FACTORY = new JacksonFactory();
        HttpRequestFactory requestFactory
          = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
              @Override
              public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));
              }
          });
        
        String op = request.getParameter("op");
        
        if(op != null){
            
            String parada = request.getParameter("parada");
            
            GenericUrl url = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/bus/GetRouteLines.php");
            GenericData data = new GenericData();
            data.put("idClient", "WEB.SERV.loginnavidad@gmail.com");
            data.put("passKey", "B573104A-F864-4064-8083-E633F60BD8D2");
            data.put("SelectDate","19/01/2018");
            data.put("Lines",parada);
            
            HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));

            GenericJson json = requestGoogle.execute().parseAs(GenericJson.class);

            ArrayList stops = (ArrayList) json.get("resultValues");
           
            request.setAttribute("paradas", stops);
            
            request.getRequestDispatcher("mostrarParadas.jsp").forward(request, response);

        }else{
            GenericUrl url = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/bus/GetListLines.php");

            GenericData data = new GenericData();
            data.put("idClient", "WEB.SERV.loginnavidad@gmail.com");
            data.put("passKey", "B573104A-F864-4064-8083-E633F60BD8D2");
            data.put("SelectDate","19/01/2018");

            HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));

            GenericJson json = requestGoogle.execute().parseAs(GenericJson.class);

            ArrayList stops = (ArrayList) json.get("resultValues");
           
            request.setAttribute("paradas", stops);
            
            request.getRequestDispatcher("vista.jsp").forward(request, response);
        }
    
        
    }
    
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
