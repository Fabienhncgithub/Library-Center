<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body id="signin">
        <nav>
            <h1>Inscrivez vous!</h1>
        </nav>  



        <div class="form-group row">
            <form action="MyServletSignIn.do" method="post">
                <!<!-- INPUTS -->
                <label for="nom">  Nom: </label><input type="text" name="nom" required/><br/>
                <label for="prenom"> Prenom:</label><input type="text" name="prenom" required/><br/>
                <label for="email">  Email:</label><input type="text" name="email" required/><br/>
                <label for="password"> Password:</label><input type="password" name="password" required/><br/>
                <label for="adresse">Adresse:</label><input type="text" name="adresse" required/><br/>

                <div class="input">
                    <b>Bibliotheque:</b>
                    <select name="bibliotheque" >
                        <p>${empty listeDeBibliotheque}</p>
                        <c:forEach items="${listeDeBibliotheque}" var="bibliotheque">
                            <option value="${bibliotheque.idBibliotheque}">${bibliotheque.nom}</option>
                        </c:forEach>
                    </select>
                </div>
            </form>

        </div>
        <!<!-- ACTIONS -->
        <div class="actions">
            <input type="submit" value="S'inscrire" class="btn btn-outline-primary"/>
            <a href="index.jsp" class="btn btn-outline-primary">Login</a>
        </div>

        <!<!-- ERROR MESSAGE --> 
        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>
    </body>
</html>
