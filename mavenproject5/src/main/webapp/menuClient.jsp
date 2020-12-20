<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<%
    Enumeration keys = session.getAttributeNames();
    while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        out.println(key + ": " + session.getValue(key) + "<br>");
    }
%>


<html>
    <head>
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
        <c:choose>
            <c:when test = "${user.amende > 0}">
            <td> 
                <p>!! Vous devez payer une amende de ${user.amende} € pour pouvoir louer un livre !! </p>
                <form action="MyServletPayerAmende.do" method="post">  
                    <input type="submit" value=" cliquez sur ce lien pour payer et régulariser votre situation "/>
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
                <input type="submit" value=" search ">
                <input type="hidden" name="search"/>   
            </c:when>
        </c:choose>
    </form>
    <form action="MyServletLivre.do" method="post">  
        <div class="conteneur">
            <table class="table1">
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
                            <input type="submit" value=" avis ">
                            <input type="hidden" name="idLivreSelected" value="${livre.idLivre}"/>        
                        </td>
                    </form>
                    <form action="MyServletLivre.do" method="post">
                        <c:choose>
                            <c:when test = "${paiementCoti == false && user.amende == 0}">
                                <td> 
                                    <input type="submit" value=" louer ">
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