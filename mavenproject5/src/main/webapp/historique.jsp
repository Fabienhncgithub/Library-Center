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

        <form action="MyServletHistorique.do" method="post">  
            <div class="conteneur">
                <table class="table table-striped">
                    <c:if test="${not empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>

                    <tr>
                        <th>TITRE</th><th>AUTEUR</th><th>EDITEUR</th><th>PAGE</th><th>TYPE</th><th>DATE  LOCATION</th><th>NOTE TOTAL</th><th>AVIS & NOTE</th><th>RENDRE LIVRE</th>
                    </tr>
                    <c:forEach items="${listeLocation}" var="location" >
                        <tr>
                            <td>${location.exemplaire.livre.titre}</td>
                            <td>${location.exemplaire.livre.auteur}</td>
                            <td>${location.exemplaire.livre.editeur}</td>
                            <td>${location.exemplaire.livre.page}</td>
                            <td>${location.exemplaire.type}</td>
                            <td>${location.dateLocation}</td>
                            <td>${location.exemplaire.livre.noteTotal}</td>
                            </form>
                        <form action="MyServletHistorique.do" method="post">  
                            <td> 
                                <input type="submit" value=" Avis & noter " class="btn btn-primary btn-sm">
                                <input type="hidden" name="titreLivreAvis" value="${location.exemplaire.livre.titre}"/>
                            </td>
                        </form>


                        <c:choose>
                            <c:when  test="${location.exemplaire.type == 'livre' && location.exemplaire.rendu == false}">
                                <form action="MyServletRendreLocation.do" method="post">  
                                    <td> 
                                        <input type="submit" value=" Rendre Livre " class="btn btn-primary btn-sm">
                                        <input type="hidden" name="idLocationRendu" value="${location.idLocation}"/>
                                    </td>
                                </form>
                            </c:when>
                        </c:choose>
                        <c:if test="${not empty ancienAvis}">
                            <c:out value="${ancienAvis}"/>
                            <input type="submit" value=" Paiement bancaire"/>
                            <input type="submit" value=" Paiement VISA "/>
                        </c:if>
                        <td>
                        </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

    </body>
</html>
