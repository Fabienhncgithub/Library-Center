<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
    <head>
        <title>selection location</title>

        <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <script>

    
   
            $(function () {
                $("#date").datepicker({
                });
            });

        </script>
        
        
        
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>location</title>
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

    <h1>Choisir le format du livre & la date de location</h1>
</head>
<body>
    <form action="MyServletExemplaire.do" method="post">  
        <div class="conteneur">
            <table class="table table-striped">
          
                <tr>
                    <th>TITRE</th>
                    <th>TYPE</th>
                    <th>ACTION</th>
                </tr>
                <form action="MyServletExemplaire.do" method="post">      
                    <tr>
                        <td>${listeExemplaire.get(0).livre.titre}</td>

                        <td >
                            <select name="idExemplaireSelected">
                                <c:forEach  var="exemplaire" items="${listeExemplaire}">
                                    <option value="${exemplaire.idExemplaire}">${exemplaire.type}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td>
                            <input type="hidden" name="idExemplaireSelected" value="${exemplaire.idExemplaire}"/>
                            <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="dateLocation" type="Submit"  class="btn btn-primary btn-sm"> Reserve 
                        </td>

                    </tr>


            </table>
            <div>
                <input type="text"  name="datepicker" id="date" placeholder="Introduire date" required=""/>
            </div>
            <c:if test="${not empty errorMessage}">
                <c:out value="${errorMessage}"/>
            </c:if>
    </form>

</div>
</form>

</body>

</html>
