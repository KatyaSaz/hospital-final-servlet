<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="one.doctor.title"/></title>
</head>
<body>
<div class="topMenu">
    <div class="logOut" style="float:left;">
        <form action="/login" method="GET">
            <button type="submit"><fmt:message key="logout"/></button>
        </form>
    </div>
    <div class="goBack" style="float:left;">
        <form action="/admin" method="GET">
            <button type="submit"><fmt:message key="back.to.main.button"/></button>
        </form>
    </div>
</div>
<br/>
<br/>
<p><fmt:message key="my.name" /> ${patient.name} ${patient.surname}</p>
<p><fmt:message key="patient.gender" /> ${patient.gender}</p>
<p><fmt:message key="patient.year" /> ${patient.year}</p>
<p><fmt:message key="patient.phone" /> ${patient.phone}</p>
<p><fmt:message key="patient.doctor" /> ${patient.doctor.name} ${patient.doctor.surname}</p>
<br/>
<br/>
<p>---> <fmt:message key="patient.record.title" /> <---</p>
<%--FIX IT--%>
<%--<p c:if="${patient.records.size() == 0}"><fmt:message key="patient.no.records"/></p>--%>
<c:forEach items="${patient.records}" var="record">
    <p><fmt:message key="patient.record.type" /> ${record.recordType}</p>
    <p><fmt:message key="patient.record.description" /> ${record.description}</p>
    <br/>
</c:forEach>
</body>
</html>