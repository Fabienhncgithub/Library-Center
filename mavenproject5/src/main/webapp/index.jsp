<%-- 
    Document   : index
    Created on : 30 oct. 2020, 15:36:44
    Author     : Fabien
--%>

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <header>
        <title>JSP</title>
    </header>
    <body>
        <nav>
            <h1>Bienvenue dans l'application bibliothèque</h1>
        </nav>  
        
        <form action="MyServletLogin.do" method="post">  
            Email:<input type="text" name="email"/><br/><br/>  
            Password:<input type="password" name="password"/><br/><br/>  

            <b>Bibliotheque:</b>
            <select name="bibliotheque" >
                <c:forEach items="${listeDeBibliotheque}" var="bibliotheque">
                    <option value="${bibliotheque.idBibliotheque}">${bibliotheque.nom}</option>
                </c:forEach>
            </select>
            <input type="submit" value="login"/>
            <a href="SignIn.jsp">Sign in</a>
        </form>
        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>

    </body> 
</html>


