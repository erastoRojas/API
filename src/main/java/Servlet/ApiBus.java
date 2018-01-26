package Servlet;

import com.google.api.client.json.GenericJson;
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
        String op = request.getParameter("op");
        GenericJson json;
        
        if(op != null){
            
            switch(op){
                
                case("verParadas"):
            
                    String parada = request.getParameter("parada");
                    String nParada = request.getParameter("nParada");
                    
                    json = as.dataParadas(parada);//llamada a servicios
                    ArrayList stops = (ArrayList) json.get("resultValues");
                    
                    request.setAttribute("nParada", nParada);
                    request.setAttribute("paradas", stops);
                    request.getRequestDispatcher("mostrarParadas.jsp").forward(request, response);
                    
                    break;
                
                case("verMinutos"):
                    
                    String idParada = request.getParameter("idParada");
                    String nombreParada = request.getParameter("nombreParada");
                    
                    json = as.dataTiempo(idParada);//llamada a servicios
                    stops = (ArrayList) json.get("arrives");
                    
                    request.setAttribute("paradas", stops);
                    request.setAttribute("nombreParada", nombreParada);
                    request.getRequestDispatcher("mostrarMinutos.jsp").forward(request, response);
                    
                    break;
            }

        }else{
            
            json = as.dataLineas();//llamada a servicios
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
