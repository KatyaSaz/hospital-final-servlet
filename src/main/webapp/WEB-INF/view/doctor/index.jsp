<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="my.info"/></title>
</head>
<body>
<div class="container">
    <a href="?docId=${doctor.id}&sessionLocale=en">EN</a>
    <a href="?docId=${doctor.id}&sessionLocale=ru">RU</a>
    <form action="/login" method="post">
        <input type="hidden" name="LogOut"/>
        <button type="submit"id="logOUT"><fmt:message key="logout" /></button>
    </form>
</div>

<h3><fmt:message key="my.info"/></h3>
<p><fmt:message key="my.name"/> ${doctor.name} ${doctor.surname}</p>
<p><fmt:message key="doctor.type"/>
    <c:choose>
        <c:when test="${lang eq 'ru'}">
            ${doctor.type.name_ru}
        </c:when>
        <c:otherwise>
            ${doctor.type}
        </c:otherwise>
    </c:choose>
</p>
<p><fmt:message key="doctor.experience"/> ${doctor.experience} <fmt:message key="doctor.experience.years"/></p>
<br/>
<a href="/doctor-patients?docId=${doctor.id}&sessionLocale=${lang}">--><fmt:message key="doctor.show.my.patients"/><--</a>
</body>
</html>
