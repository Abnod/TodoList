<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<base href="${pageContext.request.contextPath}">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TODO List All Tasks</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>
    <div class="container">
        <c:url var="addUrl" value="/add_task" />
        <c:url var="mainListUrl" value="/tasks_List" />
        <c:url var="doneListUrl" value="/tasks_List_Completed" />
        <c:url var="unDoneListUrl" value="/tasks_List_Active" />

        <div class="header">
            <a href="${mainListUrl}" class="title"><img src="${pageContext.request.contextPath}/resources/logo.png" alt="Todo List logo">TODO List</a>
            <div class="menu">
                <a href="${mainListUrl}" class="active">All tasks</a>
                <div class="whiteline"></div>
                <a href="${doneListUrl}">Completed tasks</a>
                <div class="whiteline"></div>
                <a href="${unDoneListUrl}">Active tasks</a>
            </div>
        </div>

        <main>
            <div class="task_table">
                <div class="task_header"></div>

                <div class="task_block">
                    <c:forEach items="${tasks}" var="task">
                        <c:url var="setDoneUrl" value="/complete_task" />
                        <c:url var="editUrl" value="/edit_task" />
                        <c:url var="deleteUrl" value="/delete_task"/>
                        <form class="task_row">
                            <input name="id" value="${task.id}" hidden>
                            <input name="page" value="${page}" hidden>
                            <input type="text" class="task_task" id="task_task_${task.id}" value="${task.task}" name="edit_text" readonly>
                            <button formaction="${setDoneUrl}" formmethod="POST" class="task_button">
                                <c:if test="${task.done==1}">
                                    <img src="${pageContext.request.contextPath}/resources/affirmative-156538_960_720.png" alt="task completed"/>
                                </c:if>
                                <c:if test="${task.done==0}">
                                    Mark Complete
                                </c:if>
                            </button>
                            <div class="whiteline"></div>
                            <div class="task_button_edit">
                                <div class="task_button_edit_text" onclick="toggle(${task.id})">Edit</div>
                                <div class="edit_window" id="edit_window_${task.id}">
                                    <button type="submit" formaction="${editUrl}" formmethod="POST" class="task_button_save">Save</button>
                                </div>
                            </div>
                            <div class="whiteline"></div>
                            <button type="submit" formaction="${deleteUrl}" formmethod="POST" class="task_button">Delete</button>
                        </form>
                    </c:forEach>
                    <script>
                        function toggle(id)
                        {
                            var element = document.getElementById('edit_window_'+id);
                            var valuetext = document.getElementById('task_task_'+id).getAttribute("value");
                            if(getComputedStyle(element).getPropertyValue("display") === "flex"){
                                element.style.display = "none";
                                document.getElementById('task_task_'+id).value = valuetext;
                                document.getElementById('task_task_'+id).readOnly = true;
                                document.getElementById('task_task_'+id).style.backgroundColor = lightgray;
                            } else {
                                element.style.display = "flex";
                                document.getElementById('task_task_'+id).readOnly = false;
                                document.getElementById('task_task_'+id).style.backgroundColor = ghostwhite;
                            }
                        }
                    </script>
                </div>
            </div>

            <c:if test="${empty tasks}">
                <p>There are currently no tasks in the list.</p>
            </c:if>

            <div class="button_header">
                <div class="page_buttons">
                    <c:forEach begin="1" end="${pages}" step="1" varStatus="i">
                        <c:choose>
                            <c:when test="${page == i.index}">
                                <span class="page_button_current">${i.index}</span>
                            </c:when>
                            <c:otherwise>
                                <c:url var="url" value="/tasks_List">
                                    <c:param name="page" value="${i.index}"/>
                                </c:url>
                                <a href='<c:out value="${url}"/>'  class="page_button">${i.index}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>

                <form  method="POST" action="${addUrl}" class="add">
                    <input type="text" name="name" placeholder="Input new task here!" class="task_text_field" id="name">
                    <button type="submit" class="add_button">Add new task</button>
                    <button type="reset" class="add_button reset">Clear field</button>
                </form>
            </div>
        </main>

        <div class="footer">
            <div class="block">
                <div class="text">Contact Info</div>
                <div class="icons">
                    <a href="https://github.com/Abnod" class="social_link" target="_blank"><img src="${pageContext.request.contextPath}/resources/git.png" alt="github link"></a>
                    <a href="https://www.linkedin.com/in/ganeroth/" class="social_link" target="_blank"><img src="${pageContext.request.contextPath}/resources/linkedin.png" alt="github link"></a>
                    <a href="skype:ganeroth?chat" class="social_link" target="_blank"><img src="${pageContext.request.contextPath}/resources/skype.png" alt="github link"></a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>