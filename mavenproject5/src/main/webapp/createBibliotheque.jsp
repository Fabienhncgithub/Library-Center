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
        <jsp:include page="menu-admin.jsp"/>
    <h1>Management Bibliothèque</h1>
</head>
<body>
    <form action="MyServletManageBibliotheque.do" method="post">
          <p>Manager manager</p>
        
        <label for="nom">  Nom: </label><input type="text" name="nom" required/><br/>
        <label for="adresse">Adresse:</label><input type="text" name="adresse" required/><br/>
        
        <p>Manager Bibliotheque</p>
        
            <select name="manager" >
                <p>${empty listeDeManager}</p>
                <c:forEach items="${listeDeManager}" var="manager">
                    <option value="${manager.idUser}">${manager.nom}</option>
                </c:forEach>
            </select>
        <input type="submit" value="create"/>
        
        <a href="MyServletManageManager.do">create Manager</a> 
        
    </form>
</body>
</html>
