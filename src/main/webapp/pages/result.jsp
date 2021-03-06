<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
${uploadMessage} ${uploadFileName}
<br/>
${errorMessage}
<br/>
${parseMessage}
<br/>
<table>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><c:out value="${item}" /></td>
        </tr>
    </c:forEach>
</table>
<br/>
<form action="${pageContext.request.contextPath}/index.jsp">
    <input type="submit" value="${back}"/>
</form>
</body>
</html>
