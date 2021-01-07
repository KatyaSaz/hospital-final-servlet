<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="reg.title"/></title>
</head>
<body>

<form method="post" action="/registration">
    <label for="email"><fmt:message key="reg.email"/></label>
    <input type="text" name="email" id="email"/>
    <br/>
    <label for="password"><fmt:message key="reg.password"/></label>
    <input type="password" name="password" id="password"/>
    <br/>
    <label for="role"><fmt:message key="reg.role"/></label>
    <select id="role" name="role">
        <option value="PATIENT"><fmt:message key="reg.role.patient"/></option>
        <option value="DOCTOR"><fmt:message key="reg.role.doctor"/></option>
    </select>
    <br/>
    <input type="submit" value="<fmt:message key="reg.next"/>"/>
    <br/>
</form>
</body>
</html>
