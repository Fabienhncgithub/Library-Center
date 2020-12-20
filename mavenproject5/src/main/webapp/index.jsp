<%-- 
    Document   : index
    Created on : 30 oct. 2020, 15:36:44
    Author     : Fabien
--%>

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <header>
        <title>JSP</title>
    </header>
    <body id="index">
        <nav>
            <h1>Bienvenue dans l'application bibliothèque</h1>
        </nav>  

        <!<!-- IMAGE -->
        <div class='container'>
            <a href='#'>
                <img class='resize_fit_center'
                     src='<c:url value="/picture/logo.png"></c:url>' />
                </a>
            </div>
            <div class="login">


                <form action="MyServletLogin.do" method="post">  

                    <!<!-- INPUTS -->
                    <div class="input"><label>Email:</label>     <input type="text" name="email"/></div>
                    <div class="input"><label>Password:</label> <input type="password" name="password"/></div>


                    <!<!-- BIBLIO -->
                    <div class="input">
                        <label>Bibliotheque:</label>
                        <select name="bibliotheque" >
                        <c:forEach items="${listeDeBibliotheque}" var="bibliotheque">
                            <option value="${bibliotheque.idBibliotheque}">${bibliotheque.nom}</option>
                        </c:forEach>
                    </select>
                </div>
                <!<!-- ACTIONS -->
                <div class="actions">
                    <a href="SignIn.jsp">Sign in</a>
                    <input type="submit" value="login"/>
                </div>
            </form>
            <c:if test="${not empty errorMessage}">
                <c:out value="${errorMessage}"/>
            </c:if>
        </div>
    </body> 
</html>


