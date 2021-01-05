<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%@page import="java.util.Enumeration"%>
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
    </head>
    <body>


        <form action="MyServletGestionLocation.do" method="post">  
            <div class="conteneur">
                <table class="table table-striped">
                    <tr>
                        <th>TITRE</th>
                        <th>AUTEUR</th>
                        <th>EDITEUR</th>
                        <th>PAGE</th>
                        <th>TYPE</th>
                        <th>DATE  LOCATION</th>
                        <th>NOTE TOTAL</th>
                        <th>RETOUR</th>
                        <th>SUPPRIMER</th>
                    </tr>
                    <c:forEach items="${listAllLocationBibliotheque}" var="location" >
                        <tr>
                            <td>${location.exemplaire.livre.titre}</td>
                            <td>${location.exemplaire.livre.auteur}</td>
                            <td>${location.exemplaire.livre.editeur}</td>
                            <td>${location.exemplaire.livre.page}</td>
                            <td>${location.exemplaire.type}</td>
                            <td>${location.dateLocation}</td>
                            <td>${location.exemplaire.livre.noteTotal}</td>
                        <form action="MyServletGestionLocation.do" method="post">  
                            <c:choose>
                                <c:when test = "${location.exemplaire.rendu == true && location.exemplaire.verifier == false}">
                                    <td> 
                                        <input type="submit" value=" valider retour " class="btn btn-primary btn-sm">
                                        <input type="hidden" name="idExemplaireValider" value="${location.exemplaire.idExemplaire}"/>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td> </td>
                                </c:otherwise> 
                            </c:choose>
                        </form>
                        <form action="MyServletSupprimerExemplaire.do" method="post"> 
                            <c:choose>
                                <c:when test = "${location.exemplaire.type == 'livre' && location.exemplaire.verifier == false && location.dateLocation < currentDate}">
                                    <td> 
                                        <input type="submit" value=" supprimer " class="btn btn-primary btn-sm">
                                        <input type="hidden" name="idExemplaireSupprimer" value="${location.idLocation}"/>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td> </td>
                                </c:otherwise> 
                            </c:choose>
                        </form>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </body>
</html>

