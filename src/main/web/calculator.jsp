<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="errorMessage.jsp" %>

<html>
<head>
    <title>Calculator</title>
    <link rel="stylesheet" href="css/css.css">

</head>
<body>
<h1>Калькулятор онлайн</h1>
<%
    String firstOperand = "";
    String secondOperand = "";
    Enumeration names = request.getParameterNames();
    if (request.getParameter("First") != null) {
        firstOperand = request.getParameter("First");
    }
    if (request.getParameter("Second") != null) {
        secondOperand = request.getParameter("Second");
    }
%>
<form name="SimpleForm" action="calculator.jsp">
    <h2>Число 1:</h2> <input type="text" name="First" value="<%=firstOperand%>" size="30"/>
    <br>
    <br>
    <h2>Число 2:</h2>  <input type="text" name="Second" value="<%=secondOperand%>" size="30"/>

    <br>
    <br>
    <input class="submit" type="submit" value="+" name="operation"/>
    <input class="submit" type="submit" value="-" name="operation"/>
    <input class="submit" type="submit" value="x" name="operation"/>
    <input class="submit" type="submit" value="/" name="operation"/>
</form>
<br>
<%
    if (names.hasMoreElements()) {
        float value = 0;
        float first = Float.parseFloat(firstOperand);
        float second = Float.parseFloat(secondOperand);
        String operation = request.getParameter("operation");
        if (operation.equals("+")) {
            value = first + second;
        }
        if (operation.equals("-")) {
            value = (first - second);
        }
        if (operation.equals("x")) {
            value = (first * second);
        }
        if (operation.equals("/")) {
            value = (first / second);
        }%>
<input class="submit" id = "my-button" type="submit" value="<%=first%><%=operation%><%=second%>=<%=value%>" name="operation" width="30"/>



<% }
%>

<script>
    function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    $("#my-button").hover(function () {
        $(this).css({
            top: getRandomInt(0, 150) + "px",
            left: getRandomInt(0, 150) + "px"
        });
    }, function () {
    });
</script>

</body>
</html>