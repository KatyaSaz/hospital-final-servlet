<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="admin.non.reg.title"/></title>
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
<h3><fmt:message key="admin.non.reg.doctors"/></h3>
<c:forEach items="${doctors}" var="doctor">
    <a href="/admin-doctor?docId=${doctor.id}">${doctor.name} ${doctor.surname}</a>
    <form method="POST" action="#">
        <input type="submit" value="<fmt:message key="admin.register.button"/>"/>
    </form>
    <br/>
</c:forEach>

<hr/>
<h3><fmt:message key="admin.non.reg.patients"/></h3>
<c:forEach items="${patients}" var="patient">
    <a href="/admin-patient?patId=${patient.id}">${patient.name} ${patient.surname}</a>
    <form method="POST" action="#">
        <input type="submit" value="<fmt:message key="admin.register.button"/>"/>
    </form>
    <br/>
</c:forEach>

</body>
</html>