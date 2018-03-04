<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <fmt:setLocale value="${locale}" scope="session" />
    <fmt:setBundle basename="resource.pagecontent" />
    <title>${pageTitle}</title>
</head>
<body>
<h3>${language}</h3>
<br/>
<form action="main_page">
    <select title='language' name="localeName">
    <option value="en_US">English</option>
    <option value="ru_Ru">Русский</option>
</select>
    <input type='submit' value='${submit}'/>
</form>
</body>
</html>
