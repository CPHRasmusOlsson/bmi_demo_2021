<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         List of sports
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        This is a list of all sports:


        <form action="${pageContext.request.contextPath}/fc/mangesports" method="post">
        <table>
            <thead><th>ID</th><th>Navn</th><th></th></thead>
            <c:forEach var="sportItem" items="${applicationScope.sportList}">
                <tr>
                    <td>${sportItem.id}</td>
                    <td>${sportItem.name}</td>
                    <td><button type="submit" name="delete" value="${sportItem.id}">Fjern</button></td>
                </tr>
            </c:forEach>
        </table>

            <c:if test="${not empty requestScope.error}">
                <p style="color:red;font-size: large">${requestScope.error}</p>
            </c:if>
        </form>

    </jsp:body>
</t:genericpage>
