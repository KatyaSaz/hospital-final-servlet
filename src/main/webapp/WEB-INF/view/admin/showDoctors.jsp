<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="admin.all.doctors.title"/></title>
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
<h3><fmt:message key="admin.all.doctors.title"/></h3>

<form method="POST" action="/admin-doctors">
    <select id="field" name="sortField">
        <option value=""></option>
        <option value="name" <c:if test="${fieldS eq 'name'}">selected</c:if>><fmt:message key="sort.value.name"/></option>
        <option value="experience" <c:if test="${fieldS eq 'experience'}">selected</c:if>><fmt:message key="doc.sort.value.experience"/></option>
    </select>
    <select id="direction" name="sortDirection">
        <option value=""></option>
        <option value="ASC" <c:if test="${directS eq 'ASC'}">selected</c:if>><fmt:message key="sort.value.direction.asc"/></option>
        <option value="DESC" <c:if test="${directS eq 'DESC'}">selected</c:if>><fmt:message key="sort.value.direction.desc"/></option>
    </select>
    <button type="submit"><fmt:message key="sort.button"/></button>
</form>

<form method="POST" action="/admin-doctors">
    <select id="type" name="searchType">
        <option value=""></option>
        <option value="dermatologist" <c:if test="${typeDoc eq 'dermatologist'}">selected</c:if>><fmt:message key="doc.type.dermatologist"/></option>
        <option value="pediatrician" <c:if test="${typeDoc eq 'pediatrician'}">selected</c:if>><fmt:message key="doc.type.pediatrician"/></option>
        <option value="surgeon" <c:if test="${typeDoc eq 'surgeon'}">selected</c:if>><fmt:message key="doc.type.surgeon"/></option>
        <option value="optometrist" <c:if test="${typeDoc eq 'optometrist'}">selected</c:if>><fmt:message key="doc.type.optometrist"/></option>
    </select>
    <button type="submit"><fmt:message key="doc.find.button"/></button>
</form>

<c:forEach items="${doctors}" var="doctor">
    <a href="/admin-doctor?docId=${doctor.id}">${doctor.name} ${doctor.surname}</a>
    <form method="post" action="/admin-doctors">
        <input type="hidden" name="deleteDocId" value="${doctor.id}"/>
        <input type="submit" value="<fmt:message key="delete.button"/>"/>
    </form>
    <br/>
</c:forEach>

</body>
</html>