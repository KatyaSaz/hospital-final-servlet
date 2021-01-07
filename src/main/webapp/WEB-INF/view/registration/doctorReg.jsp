<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="reg.doc.title"/></title>
</head>
<body>
<form method="post" action="/registration-doctor">
    <label for="name"><fmt:message key="reg.name"/></label>
    <input type="text" name="name" id="name"/>
    <br/>
    <label for="surname"><fmt:message key="reg.surname"/></label>
    <input type="text" name="surname" id="surname"/>
    <br/>
    <label for="type"><fmt:message key="reg.doc.type"/></label>
    <select id="type" name="type">
        <option value="dermatologist"><fmt:message key="doc.type.dermatologist"/></option>
        <option value="pediatrician"><fmt:message key="doc.type.pediatrician"/></option>
        <option value="surgeon"><fmt:message key="doc.type.surgeon"/></option>
        <option value="optometrist"><fmt:message key="doc.type.optometrist"/></option>
    </select>
    <br/>
    <label for="experience"><fmt:message key="reg.doc.experience"/></label>
    <input type="text" name="experience" id="experience"/>
    <br/>
    <input type="submit" value="<fmt:message key="reg.register"/>"/>
    <br/>
</form>
</body>