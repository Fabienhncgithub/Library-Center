
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
        </c:choose>
    </head>
    <body>
        
 
        
        
        <form action="MyServletGestionLocation.do" method="post">  
            <div class="conteneur">
                <table class="table1">
                    <tr>
                        <th>TITRE</th>
                        <th>AUTEUR</th>
                        <th>EDITEUR</th>
                        <th>PAGE</th>
                        <th>TYPE</th>
                        <th>DATE  LOCATION</th>
                        <th>NOTE TOTAL</th>
                        <th>VALIDER</th>
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
                                <c:when test = "${location.exemplaire.verifier == 'false'}">
                                    <td> 
                                        <input type="submit" value=" valider ">
                                        <input type="hidden" name="idExemplaireValider" value="${location.exemplaire.idExemplaire}"/>

                                    </td>
                                </c:when>
                            </c:choose>
                        </form>
                        <form action="MyServletSupprimerExemplaire.do" method="post"> 
                            <c:choose>
                                <c:when test = "${location.exemplaire.type == 'livre' && location.exemplaire.verifier == 'false' && location.dateLocation < currentDate}">
                                    <td> 
                                        <input type="submit" value=" supprimer ">
                                        <input type="hidden" name="idExemplaireSupprimer" value="${location.idLocation}"/>
                                    </td>
                                </c:when>
                            </c:choose>
                        </form>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </body>
</html>

