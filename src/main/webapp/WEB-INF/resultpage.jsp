<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Resultatside for beregning af BMI
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${true}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <div>
        <div class="row">
            <div class="col-sm-4"></div>

            <div class="col-sm-4">
                <h2>BMI Result</h2>


                <p></p>
                <c:out value="Din højde er ${height}"/> <br/>
                <c:out value="Din vægt er ${weight}"/> <br/>
                <c:out value="Din BMI er beregnet til ${bmi}"/> <br/>
                <c:out value="Du er ${category}"/> <br/><br/>
                <c:out value="Køn: ${gender}"/> <br/>
                <c:out value="Sport id: ${sport_id}"/> <br/>
                <c:forEach var ="hobbyItem" items ="${requestScope.hobbies}">
                    hobby: ${hobbyItem}<br/>
                </c:forEach>

                <div class="col-sm-4"></div>
            </div>
        </div>

    </jsp:body>
</t:genericpage>