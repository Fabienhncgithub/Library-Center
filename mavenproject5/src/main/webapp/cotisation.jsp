<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        
        <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>cotisation</title>
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
</head>
        
        
        <title>cotisation</title>
      
    <h1>Payement cotisation</h1>
</head>
<body>
    <c:out value="${statutCotisation}"/>
</body> 
<c:if test="${not empty paiementCotisation}">
    <c:out value="${paiementCotisation}"/>
    <form action="MyServletCotisation.do" method="post">  
        <input type="submit" value=" Paiement bancaire" class="btn btn-outline-primary"/>
        <input type="submit" value=" Paiement VISA " class="btn btn-outline-primary"/>
    </form>
</c:if>

</html>
