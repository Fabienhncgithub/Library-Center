<%-- 
    Document   : admin
    Created on : 28 nov. 2020, 22:49:36
    Author     : Fabien
--%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <jsp:include page="menu-admin.jsp"/>
    </head>
    <body>
    <h1>user</h1>
<c:out value="${user}"/>
        
    </body> 
</html>
