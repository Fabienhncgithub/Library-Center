<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>cotisation</title>
        <jsp:include page="menu-client.jsp"/>
    <h1>Payement cotisation</h1>
</head>
<body>
    <c:out value="${statutCotisation}"/>
</body> 
<c:if test="${not empty paiementCotisation}">
    <c:out value="${paiementCotisation}"/>
    <form action="MyServletCotisation.do" method="post">  
        <input type="submit" value=" Paiement bancaire"/>
        <input type="submit" value=" Paiement VISA "/>
    </form>
</c:if>

</html>
