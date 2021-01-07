<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="reg.pat.title"/></title>
</head>

<body>
<form method="post" action="/registration-patient">
    <label for="name"><fmt:message key="reg.name"/></label>
    <input type="text" name="name" id="name"/>
    <br/>
    <label for="surname"><fmt:message key="reg.surname"/></label>
    <input type="text" name="surname" id="surname"/>
    <br/>
    <label><fmt:message key="reg.pat.gender"/></label>
    <input type="radio" name="gender" id="male" value="male"/><fmt:message key="reg.pat.gender.male"/>
    <input type="radio" name="gender" id="female" value="female"/><fmt:message key="reg.pat.gender.female"/>
    <br/>
    <label for="year"><fmt:message key="reg.pat.year"/></label>
    <input type="text" name="year" id="year"/>
    <br/>
    <label for="phone"><fmt:message key="reg.pat.phone"/></label>
    <input type="text" name="phone" id="phone"/>
    <br/>
    <input type="submit" value="<fmt:message key="reg.register"/>"/>
    <br/>
</form>
</body>
</html>


