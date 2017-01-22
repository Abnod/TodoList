<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: ganer
  Date: 1/22/2017
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TODO List - Edit Task</title>
</head>
<body>
<h1>Edit Task</h1>
<c:url var="saveUrl" value="/edit_task?id=${taskAttribute.id}" />
<form:form modelAttribute="taskAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="task">Description:</form:label></td>
            <td><form:input path="task"/></td>
        </tr>
    </table>
    <input type="submit" value="Edit Task" />
</form:form>
</body>
</html>