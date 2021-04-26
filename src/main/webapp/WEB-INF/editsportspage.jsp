<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Edit sport
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">

        <h1>Hello ${sessionScope.email} </h1>
        <h3 class="mb-3">Edit and update text below:</h3>


        <form action="${pageContext.request.contextPath}/fc/managesports" method="post">
            <input type="hidden" name="sports_id" value="${requestScope.sportItem.sport_id}">

            <div class="input-group input-group-sm mb-3">
                <div class="form-group">
                <label class="form-check-label" for="name" >Navn:</label>
                <td><input id="name" class="form-control" type="text" name="name" value="${requestScope.sportItem.name}"/></td>
                <td><button class="btn btn-sm btn-primary mt-2" type="submit" name="update">Rediger</button></td>
                </div>
            </div>

            <c:if test="${not empty requestScope.error}">
                <p style="color:red;font-size: large">${requestScope.error}</p>
            </c:if>

        </form>
        </div>
        <div class="col-sm-4">

    </jsp:body>
</t:genericpage>
