

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style.css" rel="stylesheet" type="text/css">
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
        <div class="conteneur">
            <table class="table1">
                <tr>
                    <th>USER</th><th>COMMENTAIRE</th><th>NOTE</th>
                </tr>
                
                <c:forEach items="${listeAvis}" var="avis" >
                    <tr>
                        <td>${avis.user.nom}</td>
                        <td>${avis.commentaire}</td>
                        <td>${avis.note}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
