package Servlet;

import Utils.Constantes;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.apiServicios;

/**
 *
 * @author erasto
 */
@WebServlet(name = "ApiBus", urlPatterns = {"/apiBus"})
public class ApiBus extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        apiServicios as = new apiServicios(); 
        GenericData data = new GenericData();
        String op = request.getParameter("op");
        
        if(op != null){
            
            switch(op){
                
                case("verParadas"):
            
                    String parada = request.getParameter("parada");
                    String nParada = request.getParameter("nParada");
                    
                    GenericUrl url = new GenericUrl(Constantes.URL_PARADAS);
                    GenericJson json = as.requestGoogle(url,as.dataParadas(parada));
                    
                    ArrayList stops = (ArrayList) json.get("resultValues");
                    
                    request.setAttribute("nParada", nParada);
                    request.setAttribute("paradas", stops);
                    request.getRequestDispatcher("mostrarParadas.jsp").forward(request, response);
                    
                    break;
                
                case("verMinutos"):
                    
                    String idParada = request.getParameter("idParada");
                    String nombreParada = request.getParameter("nombreParada");
                    
                    url = new GenericUrl(Constantes.URL_TIEMPO);
                    json = as.requestGoogle(url,as.dataTiempo(idParada));
                    
                    stops = (ArrayList) json.get("arrives");
                    
                    request.setAttribute("paradas", stops);
                    request.setAttribute("nombreParada", nombreParada);
                    request.getRequestDispatcher("mostrarMinutos.jsp").forward(request, response);
                    
                    break;
            }

        }else{
            GenericUrl url = new GenericUrl(Constantes.URL_LINEAS);
            GenericJson json = as.requestGoogle(url,as.dataLineas());
            
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
