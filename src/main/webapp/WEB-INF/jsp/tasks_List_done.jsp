<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: ganer
  Date: 1/22/2017
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TODO List Completed Tasks</title>
</head>
<body>
<h1>TODO List</h1>
<c:url var="addUrl" value="/add_task" />
<c:url var="mainListUrl" value="/" />
<c:url var="doneListUrl" value="/tasks_List_done" />
<c:url var="unDoneListUrl" value="/tasks_List_undone" />
<p><a href="${mainListUrl}">All</a> | <a href="${doneListUrl}">Completed</a> | <a href="${unDoneListUrl}">Active</a></p>
<table style="border: 1px solid; width: 500px; text-align:center">
    <thead style="background:#c3c3c3">
    <tr>
        <th>Task</th>
        <th>State</th>
        <th colspan="2"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tasks}" var="task">
        <c:url var="setDoneUrl" value="/complete_task?id=${task.id}" />
        <c:url var="editUrl" value="/edit_task?id=${task.id}" />
        <c:url var="deleteUrl" value="/delete_task?id=${task.id}" />
        <tr>
            <td><c:out value="${task.task}" /></td>
            <td><c:out value="${task.done}" /></td>
            <td><a href="${setDoneUrl}">Mark Completed</a></td>
            <td><a href="${editUrl}">Edit</a></td>
            <td><a href="${deleteUrl}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<c:if test="${empty tasks}">
    <p>There are currently no tasks in the list.</p>
</c:if>
<p><a href="${addUrl}">Add new task</a></p>
</body>
</html>