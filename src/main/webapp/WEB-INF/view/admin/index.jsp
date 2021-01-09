<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="admin.index.title"/></title>
</head>
<style>
    li {
        list-style-type: none; /* no markers in list */
    }
</style>
<body>
<div class="container">
    <a href="?sessionLocale=en">EN</a>
    <a href="?sessionLocale=ru">RU</a>
    <form action="/login" method="post">
        <input type="hidden" name="LogOut"/>
        <button type="submit" id="logOUT"><fmt:message key="logout" /></button>
    </form>
</div>

<h3><fmt:message key="admin.index.main.page"/></h3>

<div id="menu">
    <ul>
        <li><a href="/admin-patients"><fmt:message key="admin.index.menu.all.patients"/></a></li>
        <li><a href="/admin-doctors"><fmt:message key="admin.index.menu.all.doctors"/></a></li>
        <li><a href="/admin-non-reg"><fmt:message key="admin.index.menu.all.non.reg"/></a></li>
    </ul>
</div>
</body>
</html>
