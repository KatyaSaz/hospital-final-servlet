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
        <form action="/login" method="post">
            <input type="hidden" name="LogOut"/>
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
<p><fmt:message key="my.name"/> ${doctor.name} ${doctor.surname}</p>
<p><fmt:message key="doctor.type"/> ${doctor.type}</p>
<p><fmt:message key="doctor.experience"/> ${doctor.experience} <fmt:message key="doctor.experience.years"/></p>
<br/>
<p>---> <fmt:message key="admin.one.doctor.his.patients"/> <---</p>
<c:if test="${doctor.patients.size() == 0}"><fmt:message key="doctor.no.patients" /></c:if>
<c:forEach items="${doctor.patients}" var="patient">
    <p>${patient.name} ${patient.surname}</p>
</c:forEach>
</body>
</html>