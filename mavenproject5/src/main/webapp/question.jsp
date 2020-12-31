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


        <c:choose>
            <c:when test = "${user.role.idRole == 4}">
                <jsp:include page="menu-admin.jsp"/>
            </c:when>
            <c:when test = "${user.role.idRole == 1}">
                <jsp:include page="menu-client.jsp"/>
            </c:when>
        </c:choose>
    </head>
    <body>
        <h1>Nous contacter</h1>
        <form action="MyServletQuestion.do" method="post">
            <label for="avis">  Question : </label><textarea type="text" name="question" required></textarea><br/>
            <input type="submit" value=" ajouter "/>
        </form>
    </body>
</html>
