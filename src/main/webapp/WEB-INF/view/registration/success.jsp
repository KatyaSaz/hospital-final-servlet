<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="success.title"/></title>
</head>

<div class="container">
    <form action="/login" method="get">
        <button type="submit"><fmt:message key="success.back.button"/></button>
    </form>
    <h3><fmt:message key="success.completed"/></h3>
    <p><fmt:message key="success.text"/></p>
</div>
</body>
</html>