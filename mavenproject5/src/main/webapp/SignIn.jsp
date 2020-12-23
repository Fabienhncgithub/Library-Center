<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
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
    <body id="signin">
        <h1>Page d'enregistrement</h1>

        <form action="MyServletSignIn.do" method="post">

            <label for="nom">  Nom: </label><input type="text" name="nom"/><br/>
            <label for="prenom"> Prenom:</label><input type="text" name="prenom"/><br/>
            <label for="email">  Email:</label><input type="text" name="email"/><br/>
            <label for="password"> Password:</label><input type="password" name="password"/><br/>
            <label for="adresse">Adresse:</label><input type="text" name="adresse"/><br/>


            <b>Bibliotheque:</b>
            <select name="bibliotheque" >
                <p>${empty listeDeBibliotheque}</p>
                <c:forEach items="${listeDeBibliotheque}" var="bibliotheque">
                    <option value="${bibliotheque.idBibliotheque}">${bibliotheque.nom}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Sign in"/>
        </form>

        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>
    </body>
</html>
