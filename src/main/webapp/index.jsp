<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="resource.pagecontent"/>
    <title>${pageTitle}</title>
</head>
<body>
<h3>${language}</h3>
<form action="main_page">
    <select title='language' name="localeName">
        <option value="en_US">English</option>
        <option value="ru_Ru">Русский</option>
    </select>
    <input type="hidden" name="command" value="localisation">
    <input type='submit' value='${submit}'/>
    <br/>
</form>
<h3>${fileUpload}</h3>
${selectFile} <br/>
<form action="UploadFile" method="post" enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <br/>
    <input type="hidden" name="command" value="upload">
    <select name="parserType">
        <option value="DOM">DOM</option>
        <option value="SAX">SAX</option>
        <option value="STAX">STAX</option>
        <option value="JAXB">JAXB</option>
    </select>
    <input type="submit" value=${parseFile}/>
</form>
</body>
</html>
