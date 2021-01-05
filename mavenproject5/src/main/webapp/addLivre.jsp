<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <c:choose>
            <c:when test = "${user.role.idRole == 4}">
                <jsp:include page="menu-admin.jsp"/>
            </c:when>
            <c:when test = "${user.role.idRole == 3 }">
                <jsp:include page="menu-manager.jsp"/>
            </c:when>
            <c:when test = "${user.role.idRole == 2 }">
                <jsp:include page="menu-bibliothecaire.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="menu-client.jsp"/>
            </c:otherwise>
        </c:choose>
          <link rel="stylesheet" href="css/dropzone.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        <body>
        <h1>Rajouter un livre </h1>
        <form   action="MyServletAddLivre.do" method="POST" >

            <label for="titre">  Titre: </label><input type="text" name="titre" required/><br/>
            <label for="auteur"> Auteur:</label><input type="text" name="auteur" required/><br/>
            <label for="editeur">  Editeur: </label><input type="text" name="editeur" required/><br/>
            <label for="page"> Page Total:</label><input type="number" name="page" required/><br/>
            <label for="prix">Prix d'achat: </label><input type="number" min="0" step="1" name="prix" required/><br/>
            <div>
             
            <input type="submit" value=" Ajouter " class="btn btn-primary btn-sm"/>
        </form>
    </body> 
