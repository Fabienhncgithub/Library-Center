<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <jsp:include page="menu-admin.jsp"/>
    <h1>Rajouter un livre</h1>
</head>
<body>


    <form action="MyServletAddBook.do" method="post">

        <label for="titre">  Titre: </label><input type="text" name="titre"/><br/>
        <label for="auteur"> Auteur:</label><input type="text" name="auteur"/><br/>
        <label for="editeur">  Editeur: </label><input type="text" name="editeur"/><br/>
        <label for="page"> Page Total:</label><input type="number" name="page"/><br/>
        <label for="prix">Prix d'achat: </label><input type="text" name="prix"/><br/>

     
        <select name="type" >
            <option value="livre">livre</option>
            <option value="ebook">ebook</option>

        </select>

        <input type="submit" value=" Ajouter "/>

    </form>

</body> 

</html>