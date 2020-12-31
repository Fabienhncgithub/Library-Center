<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="menu-admin.jsp"/>
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
            <label for="prix">Prix d'achat: </label><input type="text" name="prix" required/><br/>
            <div>
             
         
            <input type="submit" value=" Ajouter "/>
        </form>
    </body> 
