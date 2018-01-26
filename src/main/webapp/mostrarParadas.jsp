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
                <div class="col-sm-8"><h1>Linea ${nParada}</h1> </div>
                <div class="col-sm4" style="padding-top:20px;text-align: right;"><p><a href="http://localhost:8080/API/apiBus?" style="font-size: 25px">Volver</a></p></div>
            </div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nº Parada</th>
                        <th>Codigo de Parada</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${paradas}" var="parada"> 
                    <tr onclick="location='http://localhost:8080/API/apiBus?op=verMinutos&idParada=${parada.node}&nombreParada=${parada.name}'" style="cursor:pointer">
                        <td>
                            ${parada.name}
                        </td> 
                        <td>
                            ${parada.node}
                        </td>
                    </tr>
                </c:forEach> 
                </tbody>            
            </table>
        </div>
    </body>
</html>
