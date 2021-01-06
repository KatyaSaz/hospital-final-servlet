<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title><fmt:message key="card.title"/></title>
</head>
<body>
<div class="topMenu">
    <div class="logOut" style="float:left;">
        <form action="/login" method="GET">
            <button type="submit"><fmt:message key="logout"/></button>
        </form>
    </div>
    <div class="goBack" style="float:left;">
        <form action="/doctor" method="GET">
            <input type="hidden" name="docId" value="${patient.doctor.id}"/>
            <button type="submit"><fmt:message key="back.to.main.button"/></button>
        </form>
    </div>
</div>
<br/>
<br/>

<h3><fmt:message key="card.create.title"/></h3>

<form method="post" action="/doctor-patient-write" >
    <label for="descrip"><fmt:message key="card.description"/></label>
    <textarea id="descrip" name="description"></textarea>
    <br/>
    <label for="type"><fmt:message key="card.record.type"/></label>
    <select  id="type" name="type">
        <option value="Direction"><fmt:message key="record.type.direction"/></option>
        <option value="Execution"><fmt:message key="record.type.execution"/></option>
        <option value="Diagnosis"><fmt:message key="record.type.diagnosis"/></option>
    </select>
    <br/>
    <input type="hidden" name="patientId" value="${patient.id}"/>
    <input type="submit" value="<fmt:message key="record.add.button"/>"/>
    <br/>
</form>
</body>
</html>