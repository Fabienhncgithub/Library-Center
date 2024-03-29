<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create manager</title>
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
            <h1>Create profil</h1>
    </head>
    <body>

        <div class="login">
            <form action="MyServletManageManager.do" method="post">

                <div class="input">
         
                    <div class="input"><label for="nom">  Nom: </label><input type="text" name="nom" required/></div>
                    <div class="input"> <label for="prenom"> Prenom:</label><input type="text" name="prenom" required/></div>
                    <div class="input"> <label for="email">  Email:</label><input type="text" name="email" required/></div>
                    <div class="input"> <label for="password"> Password:</label><input type="password" name="password" required/></div>
                    <div class="input"> <label for="adresse">Adresse:</label><input type="text" name="adresse" required/></div>
                </div>
                <select name="role">
                    <c:forEach items="${listRole}" var="role">
                        <option value="${role.idRole}">${role.nomRole}</option>
                    </c:forEach>
                </select>

                <input type="submit" value=" create " class="btn btn-primary btn-sm"/>
            </form>
        </div>


        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>
    </body>
</html>
