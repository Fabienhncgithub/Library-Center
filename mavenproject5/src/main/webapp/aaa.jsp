<%-- 
    Document   : locationExemplaire
    Created on : 15 nov. 2020, 10:26:34
    Author     : Fabien
--%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>PAGE DE TEST</h1>

        <p>${avis.note}</p>
        <p>${user.idUser}</p>
        <p>${avis.user}</p>
        <p>${titreLivreAvis}</p>
        <p>${avis.idLivre}</p>
        <p>${idLivre}</p>
        
        
    </body>
</html>
