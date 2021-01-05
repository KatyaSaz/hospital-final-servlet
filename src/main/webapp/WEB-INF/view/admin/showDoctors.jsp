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

<form method="POST" action="#">
    <select id="field" name="field">
        <option value=""></option>
        <option value="name" ><fmt:message key="sort.value.name"/></option>
        <option value="experience"><fmt:message key="doc.sort.value.experience"/></option>
    </select>
    <select id="direction" name="direction">
        <option value=""></option>
        <option value="ASC"><fmt:message key="sort.value.direction.asc"/></option>
        <option value="DESC" ><fmt:message key="sort.value.direction.desc"/></option>
    </select>
    <button type="submit"><fmt:message key="sort.button"/></button>
</form>

<form method="POST" action="#">
    <select id="type" name="type">
        <option value=""></option>
        <option value="dermatologist"><fmt:message key="doc.type.dermatologist"/></option>
        <option value="pediatrician"><fmt:message key="doc.type.pediatrician"/></option>
        <option value="surgeon"><fmt:message key="doc.type.surgeon"/></option>
        <option value="optometrist"><fmt:message key="doc.type.optometrist"/></option>
    </select>
    <button type="submit"><fmt:message key="doc.find.button"/></button>
</form>

<c:forEach items="${doctors}" var="doctor">
    <a href="/admin-doctor?docId=${doctor.id}">${doctor.name} ${doctor.surname}</a>
    <form method="POST" action="#">
        <input type="submit" value="<fmt:message key="delete.button"/>"/>
    </form>
    <br/>
</c:forEach>

</body>
</html>