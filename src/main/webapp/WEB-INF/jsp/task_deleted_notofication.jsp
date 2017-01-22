<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: ganer
  Date: 1/22/2017
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TODO List - Task Deleted</title>
</head>
<body>
<h1>TODO List Updated</h1>
<p>You have deleted a task</p>
<c:url var="mainUrl" value="/" />
<p>Return to <a href="${mainUrl}">TODO List</a></p>
</body>
</html>