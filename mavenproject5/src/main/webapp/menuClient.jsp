<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>





<html>
    <head>
        <title>cotisation</title>
        <c:choose>
            <c:when test = "${user.role.idRole == 4}">
                <jsp:include page="menu-admin.jsp"/>
            </c:when>
            <c:when test = "${user.role.idRole == 1}">
                <jsp:include page="menu-client.jsp"/>
            </c:when>
        </c:choose>
    <h1>Bienvenue ${user.prenom} dans le catalogue de la bibliothèque de ${bibliotheque.nom}</h1>
</head>
<body>

    <c:choose>
        <c:when test = "${user.amende > 0}">
        <td> 
            <p>!! Vous devez payer une amende de ${user.amende} € pour pouvoir louer un livre !! </p>
            <form action="MyServletPayerAmende.do" method="post">  
                <input type="submit" value=" cliquez sur ce lien pour payer et régulariser votre situation " class="btn btn-primary btn-sm"/>
            </form>
        </td>
    </c:when>

</c:choose>
<c:if test="${not empty errorMessage}">
    <c:out value="${errorMessage}"/>
</c:if>

<form action="MyServletSearch.do" method="post">  
    <c:choose>
        <c:when test = "${user.role.idRole == 4}">

            Rechercher:  <input type="text" name="search"/><br/><br/>  
            <input type="submit" value=" search " class="btn btn-primary btn-sm">
            <input type="hidden" name="search"/>   
        </c:when>
    </c:choose>
</form>
<form action="MyServletLivre.do" method="post">  
    <div class="conteneur">
        <table class="table table-striped">
            <c:if test="${not empty errorMessage}">
                <c:out value="${errorMessage}"/>
            </c:if>
            <tr>
                <th>I.D</th>
                <th>TITRE</th>
                <th>EDITEUR</th>
                <th>PAGE</th><th>NOTE</th>
                <th>AVIS</th>
                    <c:choose>
                        <c:when test = "${paiementCoti == false && user.amende == 0}">
                        <th>ACTION</th>
                        </c:when>
                    </c:choose>
            </tr>
            <c:forEach items="${listeDeLivre}" var="livre" >
                <tr>
                    <td>${livre.idLivre}</td>
                    <td>${livre.titre}</td>
                    <td>${livre.editeur}</td>
                    <td>${livre.page}</td>
                    <td>${livre.noteTotal}</td>
                    </form>
                <form action="MyServletConsulterAvis.do" method="post">
                    <td> 
                        <input type="submit" value=" avis " class="btn btn-primary btn-sm">
                        <input type="hidden" name="idLivreSelected" value="${livre.idLivre}"/>        
                    </td>
                </form>
                <form action="MyServletLivre.do" method="post">
                    <c:choose>
                        <c:when test = "${paiementCoti == false && user.amende == 0}">
                            <td> 
                                <input type="submit" value=" louer " class="btn btn-primary btn-sm">
                                <input type="hidden" name="idLivreSelected" value="${livre.idLivre}"/>   
                            </td>
                        </c:when>
                    </c:choose>
                    </tr>
                </c:forEach>
        </table>
    </div>
</form>
</body> 
</html>