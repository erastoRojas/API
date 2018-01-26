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
            
            <div class="row">
                <div class="col-sm-8"><h1>Ruta: ${nombreParada}</h1> </div>
                <div class="col-sm4" style="padding-top:20px;text-align: right;"><p><a href="http://localhost:8080/API/apiBus?op=verParadas" style="font-size: 25px">Volver</a></p></div>
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Minutos restantes</th>
                        <th>Distancia</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${paradas}" var="parada"> 
                    <tr style="cursor:pointer">
                        <td>
                            
                        <script>
                            var segundos = '<c:out value="${parada.busTimeLeft}"/>';
                        
                            if((segundos/60) > 60){
                                document.write("+60 min"); 
                            
                            }else if(segundos > 60){
                                var d=new Date(segundos*1000);
                                var hora = (d.getHours()==0)?23:d.getHours()-1;
                                var hora = (hora<9)?"0"+hora:hora;
                                var minuto = d.getMinutes();
                                if(d.getSeconds()<10){}
                                
                                if(d.getSeconds()<10 && minuto < 1){
                                     var segundo = "0"+d.getSeconds();
                                     document.write(minuto+":"+segundo+" seg");
                                }else{
                                    var segundo = (d.getSeconds()<10)?"0"+d.getSeconds():d.getSeconds();
                                    document.write(minuto+":"+segundo+" min"); 
                                }
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
