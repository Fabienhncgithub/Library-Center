
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
    <head>

        <%
            Enumeration keys = session.getAttributeNames();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                out.println(key + ": " + session.getValue(key) + "<br>");
            }
        %>


        <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <script>

            var dates = ["20/01/2020", "21/01/2020", "22/01/2020", "23/01/2020"];

            function DisableDates(date) {
                var string = jQuery.datepicker.formatDate('dd/mm/yy', date);
                return [dates.indexOf(string) == -1];
            }

            $(function () {
                $("#date").datepicker({
                    beforeShowDay: DisableDates
                });
            });

        </script>
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
        <form action="MyServletExemplaire.do" method="post">  
            <div class="conteneur">
                <table class="table1">
                    <c:if test="${not empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
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
                                <button class="btn btn-lg btn-primary btn-block"  name="Submit" value="dateLocation" type="Submit"> Reserve 
                            </td>

                        </tr>


                </table>
                <div>
                    <input type="text" class="form-control" name="datepicker" id="date" placeholder="Date" required=""/>
                </div>
        </form>

    </div>
</form>
<c:if test="${not empty errorMessage}">
    <c:out value="${errorMessage}"/>
</c:if>
</body>

</html>
