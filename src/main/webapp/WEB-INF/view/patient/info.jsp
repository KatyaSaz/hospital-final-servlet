<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <meta charset="UTF-8">
    <title>My Info</title>
</head>
<div class="container">
    <form action="/logout" method="POST">
        <button type="submit">Log Out</button>
    </form>
</div>
<p>Patient name: ${patient.name} ${patient.surname}</p>
<p>Patient genger: ${patient.gender}</p>
<p>Year of birth: ${patient.year}</p>
<p>Phone: ${patient.phone}</p>
<p>Doctor name: ${patient.doctor.name} ${patient.doctor.surname}</p>
<br/>
<p>---> CARD RECORDS <---</p>
<c:forEach items="${patient.records}" var="record">
    <p>RecordType: ${record.recordType}</p>
    <p>Description: ${record.description}</p>
<%--    @{/patient/download/{id}(id=${record.getId()})}--%>
    <form method="POST" action="#">
        <c:if test="${record.recordType=='Diagnosis'}">
            <input type="submit" value="SAVE"/>
        </c:if>
    </form>
    <br/>
</c:forEach>
<%--<p th:if="${patient.getRecords().size() == 0}"></p>--%>
<%--<div th:each="record : ${patient.getRecords()}">--%>
<%--    <p th:text="#{patient.record.type} + ${record.getRecordType()}"></p>--%>
<%--    <p th:text="#{patient.record.description} + ${record.getDescription()}"></p>--%>
<%--    <form th:method="POST" th:action="@{/patient/download/{id}(id=${record.getId()})}">--%>
<%--        <input th:if="${record.getRecordType().name() == 'Diagnosis'}" type="submit" th:value="#{patient.record.save}"/>--%>
<%--    </form>--%>

</div>
</body>
</html>
