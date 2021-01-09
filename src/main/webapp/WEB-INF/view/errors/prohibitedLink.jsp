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
    <h3><fmt:message key="login.error.user.prohibited.link.title"/></h3>
    <p><fmt:message key="login.error.user.prohibited.link"/></p>
</div>
</body>
</html>
