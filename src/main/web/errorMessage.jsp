<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="css/css.css">
</head>
<body>
Что-то случилось!
Message:
<%=exception.getMessage()%>
</body>
</html>
