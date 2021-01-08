<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="login" /></title>
</head>
<body>
<a href="?sessionLocale=en">EN</a>
<a href="?sessionLocale=ru">RU</a>
<div class="container">
    <form class="form-signin" method="post" action="/login">
        <h2 class="form-signin-heading"><fmt:message key="authorisation" /></h2>
        <p>
            <label for="username"><fmt:message key="email"/></label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
        </p>
        <p>
            <label for="password"><fmt:message key="password"/></label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>
        <a href="/registration"><fmt:message key="registr"/></a>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signin" /></button>
    </form>
</div>
</body>

</html>
