<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <form action="MyServletModifyProfil.do" method="post">

        <label for="nom">  Nom: </label><input type="text" name="nom" value="${user.nom}"/><br/>
        <label for="prenom"> Prenom:</label><input type="text" name="prenom" value="${user.prenom}"/><br/>
        <label for="email">  Email:</label><input type="text" name="email"value="${user.email}"/><br/>
        <label for="password"> Password:</label><input type="text" name="password"value="${user.password}"/><br/>
        <label for="adresse">Adresse:</label><input type="text" name="adresse" value="${user.adresse}"/><br/>

        <c:choose>
            <c:when test = "${user.role.idRole == 4}">
                <select name="role">
                    <c:forEach items="${listRole}" var="role">
                        <option value="${role.idRole}">${role.nomRole}</option>
                    </c:forEach>
                </select>
            </c:when>
        </c:choose>

        <input type="submit" value=" enregistrer les modifications "/>
    </form>
             <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        </c:if>
</body>
</html>