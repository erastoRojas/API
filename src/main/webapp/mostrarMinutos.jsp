<%-- 
    Document   : vista
    Created on : 19-ene-2018, 9:03:56
    Author     : daw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
            
        <div class="container">
            <h1>Ruta: ${nombreParada}</h1> 
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Segundos restantes</th>
                        <th>Distancia</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${paradas}" var="parada"> 
                    <tr onclick="location='http://localhost:8080/API/apiBus?'" style="cursor:pointer">
                        <td>
                            
                        <script>
                            var segundos = '<c:out value="${parada.busTimeLeft}"/>';
                        
                            if((segundos/60) > 60){
                                document.write("+60 min"); 
                            
                            }else if(segundos > 10){
                                var d=new Date(segundos*1000);
                                var hora = (d.getHours()==0)?23:d.getHours()-1;
                                var hora = (hora<9)?"0"+hora:hora;
                                var minuto = (d.getMinutes()<9)?"0"+d.getMinutes():d.getMinutes();
                                var segundo = (d.getSeconds()<9)?"0"+d.getSeconds():d.getSeconds();
                              
                              
                                document.write(minuto+":"+segundo+" min"); 
                            }else{
                                document.write(segundos+" seg"); 
                            }
                        </script>
       
                        </td> 
                        <td>
                            ${parada.busDistance} m
                        </td> 
                    </tr>
                </c:forEach> 
                </tbody>            
            </table>
        </div>
    </body>
</html>
