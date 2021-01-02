<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="one.patient.title" /></title>
</head>
<div class="container">
    <form action="/login" method="GET">
        <a href="?patId=${patient.id}&sessionLocale=en">EN</a>
        <a href="?patId=${patient.id}&sessionLocale=ru">RU</a>
        <button type="submit"id="logOUT"><fmt:message key="logout" /></button>
    </form>
</div>
<p><fmt:message key="my.name" /> ${patient.name} ${patient.surname}</p>
<p><fmt:message key="patient.gender" /> ${patient.gender}</p>
<p><fmt:message key="patient.year" /> ${patient.year}</p>
<p><fmt:message key="patient.phone" /> ${patient.phone}</p>
<p><fmt:message key="patient.doctor" /> ${patient.doctor.name} ${patient.doctor.surname}</p>
<br/>
<p>---> <fmt:message key="patient.record.title" /> <---</p>
<p c:if="${patient.getRecords().size() == 0}"><fmt:message key="patient.no.records" /></p>
<c:forEach items="${patient.records}" var="record">
    <p><fmt:message key="patient.record.type" /> ${record.recordType}</p>
    <p><fmt:message key="patient.record.description" /> ${record.description}</p>
<%--    @{/patient/download/{id}(id=${record.getId()})}--%>
    <form id="saveButton" action="./patient" method="post">
        <c:if test="${record.recordType=='Diagnosis'}">
            <input type="submit" value="<fmt:message key="patient.record.save"/>"/>
            <input type="hidden" name="saveCardId" value ="${record.id}"/>
        </c:if>
    </form>
    <br/>
</c:forEach>
</div>
</body>
</html>
