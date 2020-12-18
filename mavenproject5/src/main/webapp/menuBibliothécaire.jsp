<%-- 
    Document   : menuUser
    Created on : 30 oct. 2020, 15:37:33
    Author     : Fabien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenue Bibliothécaire!</h1>
        <p>Bienvenue ${user.login}</p>
        <p>password ${user.password}</p>

    </body>
</html>