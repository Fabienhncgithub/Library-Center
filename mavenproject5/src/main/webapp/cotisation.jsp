

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
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
