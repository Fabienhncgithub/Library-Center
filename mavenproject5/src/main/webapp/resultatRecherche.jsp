

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <p>Messagerie</p>
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
        <h1>Resultat de votre recherche</h1>
        
        <div class="conteneur">
            <table class="table1">
      
                <tr>
                    <th>TITRE</th>
                    <th>NOMBRE D'EXEMPLAIRE</th>

                </tr>
                <c:forEach items="${searchResult}" var="exemplaire" >
                    <tr>
                        <td>${exemplaire.key}</td>
                        <td>${exemplaire.value}</td>
                        </form>
                        </tr>
                    </c:forEach>
            </table>
        </div>
        
    </body>
</html>
