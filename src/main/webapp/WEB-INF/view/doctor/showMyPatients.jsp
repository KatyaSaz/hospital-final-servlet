<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="doc.show.patients.title"/></title>
</head>
<body>
<div class="topMenu">
    <div class="logOut" style="float:left;">
        <form action="/login" method="GET">
            <button type="submit"><fmt:message key="logout"/></button>
        </form>
    </div>
    <div class="goBack" style="float:left;">
        <form action="./doctor?docId=${doctor.id}" method="GET">
            <button type="submit"><fmt:message key="back.to.main.button"/></button>
        </form>
    </div>
</div>
<br/>
<br/>

<h3><fmt:message key="doc.show.patients.title"/></h3>
<form method="POST" action="#">
    <select id="field" name="field">
        <option value=""></option>
        <option value="name" ><fmt:message key="sort.value.name"/></option>
        <option value="year"><fmt:message key="pat.sort.value.year"/></option>
    </select>
    <select id="direction" name="direction">
        <option value=""></option>
        <option value="ASC"><fmt:message key="sort.value.direction.asc"/></option>
        <option value="DESC"><fmt:message key="sort.value.direction.desc"/></option>
    </select>
    <button type="submit"><fmt:message key="sort.button"/></button>
</form>

<br/>
<hr/>
<%--<p c:if="${doctor.patients == 'null'}"><fmt:message key="doctor.no.patients" /></p>--%>

<c:forEach items="${doctor.patients}" var="patient">
    <a href="/doc-patient?patId=${patient.id}">${patient.name} ${patient.surname}</a>
    <br/>
</c:forEach>

<%--<div th:each="patient : ${patients}">--%>
<%--    <a th:href="@{/doctor/patients/{id}(id=${patient.getId()})}"--%>
<%--       th:text="${patient.getName() + '   ' + patient.getSurname()}">user</a>--%>
<%--    <br/>--%>
<%--</div>--%>
</body>
</html>