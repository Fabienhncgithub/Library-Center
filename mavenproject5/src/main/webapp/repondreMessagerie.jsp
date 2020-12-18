

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style.css" rel="stylesheet" type="text/css">
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

    <p>Question : </p>
    <p>${QuestionSelected}</p>

    <p>Votre réponse : </p>
    <form action="MyServletRepondreMessagerie.do" method="post">
        <label for="reponse">  réponse : </label><textarea type="text" name="reponse"></textarea><br/>
        <input type="submit" value=" repondre "/>
             <input type="hidden" name="idQuestion" value="${idQuestionSelected}"/>
        
    </form>



</body>
</html>