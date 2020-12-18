<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="menu-admin.jsp"/>
    </head>
    <body>
        <form action="MyServletManageManager.do" method="post">


            <p>Manager Bibliotheque</p>
            <label for="nom">  Nom: </label><input type="text" name="nom"/><br/>
            <label for="prenom"> Prenom:</label><input type="text" name="prenom"/><br/>
            <label for="email">  Email:</label><input type="text" name="email"/><br/>
            <label for="password"> Password:</label><input type="password" name="password"/><br/>
            <label for="adresse">Adresse:</label><input type="text" name="adresse"/><br/>
            
            <select name="role">
                <c:forEach items="${listRole}" var="role">
                    <option value="${role.idRole}">${role.nomRole}</option>
                </c:forEach>
            </select>
            
            <input type="submit" value=" create "/>
        </form>
    </body>
</html>
