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
        <form action="/login" method="post">
            <input type="hidden" name="LogOut"/>
            <button type="submit"><fmt:message key="logout"/></button>
        </form>
    </div>
    <div class="goBack" style="float:left;">
        <form action="/doctor" method="get">
            <input type="hidden" name="docId" value="${doctor.id}"/>
            <button type="submit"><fmt:message key="back.to.main.button"/></button>
        </form>
    </div>
</div>
<br/>
<br/>
<h3><fmt:message key="doc.show.patients.title"/></h3>
<form method="POST" action="/doctor-patients">
    <select id="field" name="sortField">
        <option value=""></option>
        <option value="name" <c:if test="${fieldS eq 'name'}">selected</c:if>><fmt:message key="sort.value.name"/></option>
        <option value="year" <c:if test="${fieldS eq 'year'}">selected</c:if>><fmt:message key="pat.sort.value.year"/></option>
    </select>
    <select id="direction" name="sortDirection">
        <option value=""></option>
        <option value="ASC" <c:if test="${directS eq 'ASC'}">selected</c:if>><fmt:message key="sort.value.direction.asc"/></option>
        <option value="DESC" <c:if test="${directS eq 'DESC'}">selected</c:if>><fmt:message key="sort.value.direction.desc"/></option>
    </select>
    <input type="hidden" name="docID" value="${doctor.id}"/>
    <button type="submit"><fmt:message key="sort.button"/></button>
</form>
<br/>
<hr/>
<c:if test="${patients.size() == 0}"><fmt:message key="doctor.no.patients" /></c:if>
<c:forEach items="${patients}" var="patient">
    <a href="/doctor-patient?patId=${patient.id}">${patient.name} ${patient.surname}</a>
    <br/>
</c:forEach>
</body>
</html>