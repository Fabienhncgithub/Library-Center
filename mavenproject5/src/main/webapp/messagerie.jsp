<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
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
        <h1>Messagerie</h1>
        <div class="conteneur">
            <table class="table table-striped">
                <form action="MyServletmessaqerie.do" method="post">
                    <th>ID QUESTION</th><th>ID USER</th><th>QUESTION</th>
                        <c:choose>
                            <c:when test = "${user.role.idRole == 4}">
                            <th>REPONSE</th>
                            <th>ID USER REPONSE</th>
                            </c:when>
                        </c:choose>
                    <th>REPONSE</th>
                        <c:forEach items="${listeQuestionByUser}" var="faq" >
                        <tr>
                            <td>${faq.idQuestion}</td>
                            <td>${faq.idUserQuestion}</td>          
                            <td>${faq.question}</td>
                            <td>${faq.reponse}</td> 
                            <td>${faq.idUserReponse}</td> 


                            <c:choose>
                                <c:when test = "${user.role.idRole == 4 && faq.reponse == null}">


                            </form>
                            <form action="MyServletMessagerie.do" method="post">
                                <td> 
                                    <input type="submit" value=" Repondre " class="btn btn-primary btn-sm">
                                    <input type="hidden" name="idQuestionSelected" value="${faq.idQuestion}"/>
                                    <input type="hidden" name="QuestionSelected" value="${faq.question}"/>
                                </td>
                            </c:when>
                        </c:choose>
                        </tr>
                    </c:forEach>





            </table>
            <a href="MyServletQuestion.do" class="btn btn-primary btn-sm"> contactez nous ici </a>
        </div>
    </form>
</body>
</html>
