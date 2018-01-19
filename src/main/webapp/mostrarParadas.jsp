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
            <h1>API BUS</h1> 
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nº Parada</th>
                        <th>Inicio</th>
                        <th>Fin</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${paradas}" var="parada"> 
                    <tr onclick="location='http://localhost:8080/ApiBus/apiBus?op=verParadas&parada=${parada.line}'" style="cursor:pointer">
                        <td>
                            ${parada.name}
                        </td> 
                        <td>
                            ${parada.distance}
                        </td>

                        <td>
                            ${parada.name}
                        </td>

                </c:forEach> 
                </tbody>            
            </table>
        </div>
    </body>
</html>
