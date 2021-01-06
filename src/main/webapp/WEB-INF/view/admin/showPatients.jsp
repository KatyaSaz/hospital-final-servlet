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
        <form action="/admin" method="GET">
            <button type="submit"><fmt:message key="back.to.main.button"/></button>
        </form>
    </div>
</div>
<br/>
<br/>

<h3><fmt:message key="admin.all.patients.list"/></h3>
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

<c:forEach items="${patients}" var="patient">
    <a href="/admin-patient?patId=${patient.id}">${patient.name} ${patient.surname}</a>
    <form method="post" action="/admin-patients">
        <select class="form-control" name="newDocId">
            <c:forEach items="${doctors}" var="doc">
                <option value="${doc.id}" <c:if test="${doc.id eq patient.doctor.id}">selected</c:if>>
                        ${doc.name} (${doc.type})
                </option>
            </c:forEach>
        </select>
        <input type="hidden" name="patId" value="${patient.id}"/>
        <button type="submit"><fmt:message key="admin.appoint.button"/></button>
    </form>

    <form method="POST" action="/admin-patients">
        <input type="hidden" name="deletePatId" value="${patient.id}"/>
        <input type="submit" value="<fmt:message key="admin.delete.patient.button"/>"/>
    </form>
    <br/>
</c:forEach>

</body>
</html>
