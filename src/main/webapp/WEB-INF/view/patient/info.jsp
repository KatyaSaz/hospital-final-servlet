<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="one.patient.title" /></title>
</head>
<div class="container">
    <a href="?patId=${patient.id}&sessionLocale=en">EN</a>
    <a href="?patId=${patient.id}&sessionLocale=ru">RU</a>
    <form action="/login" method="post">
        <input type="hidden" name="LogOut"/>
        <button type="submit"id="logOUT"><fmt:message key="logout" /></button>
    </form>
</div>
<p><fmt:message key="my.name" /> ${patient.name} ${patient.surname}</p>
<p><fmt:message key="patient.gender"/>
    <c:choose>
        <c:when test="${lang eq 'ru'}">
            ${patient.gender.gender_ru}
        </c:when>
        <c:otherwise>
            ${patient.gender}
        </c:otherwise>
    </c:choose>
</p>
<p><fmt:message key="patient.year" /> ${patient.year}</p>
<p><fmt:message key="patient.phone" /> ${patient.phone}</p>
<p><fmt:message key="patient.doctor" /> ${patient.doctor.name} ${patient.doctor.surname}</p>
<br/>
<p>---> <fmt:message key="patient.record.title" /> <---</p>
<c:if test="${patient.records.size() == 0}"><fmt:message key="patient.no.records"/></c:if>
<c:forEach items="${patient.records}" var="record">
    <p><fmt:message key="patient.record.type" />
        <c:choose>
            <c:when test="${lang eq 'ru'}">
                ${record.recordType.type_ru}
            </c:when>
            <c:otherwise>
                ${record.recordType}
            </c:otherwise>
        </c:choose>
    </p>
    <p><fmt:message key="patient.record.description" /> ${record.description}</p>
    <form id="saveButton" action="./patient" method="post">
        <c:if test="${record.recordType=='Diagnosis'}">
            <input type="submit" value="<fmt:message key="patient.record.save"/>"/>
<%--            <input type="hidden" name="sessionLocale" value="${lang}"/>--%>
            <input type="hidden" name="downloadCardId" value ="${record.id}"/>
        </c:if>
    </form>
    <br/>
</c:forEach>
</div>
</body>
</html>
