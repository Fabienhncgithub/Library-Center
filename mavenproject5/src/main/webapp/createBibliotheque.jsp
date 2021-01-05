<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management Bibliothèque</title>
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
        <input type="submit" value="create" class="btn btn-primary btn-sm"/>

        <a href="MyServletManageManager.do">create Manager</a> 

    </form>
    <c:if test="${not empty errorMessage}">
        <c:out value="${errorMessage}"/>
    </c:if>
</body>
</html>
