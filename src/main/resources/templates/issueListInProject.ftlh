<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список задач в проете</title>
    <style>
        .layer1 {
            float: right;
            margin-right: 520px;
        }
    </style>
</head>
<body>
<div class="layer1">
    <h2>Список пользователей</h2>
    <#list users as user>
        <div>
            <span>${user.id!"none"}</span>
            <span>${user.firstName!"none"}</span>
            <span>${user.lastName!"none"}</span>
        </div>
    </#list>
</div>
<h1>Project info</h1>
<table>
    <tr>
        <td>Id</td>
        <td>${projectID!"none"}</td>
    </tr>
    <tr>
        <td>Title</td>
        <td>${title!"none"}</td>
    </tr>
    <tr>
        <td>Description</td>
        <td>${description!"none"}</td>
    </tr>
    <tr>
        <td>Subdivision</td>
        <td>${subdivision!"none"}</td>
    </tr>
    <tr>
        <td>Supervisor</td>
        <td>${supervisorID!"none"}</td>
    </tr>
    <tr>
        <td>Admin</td>
        <td>${adminID!"none"}</td>
    </tr>
    <tr>
        <td>Backlog</td>
        <td>${backlogTitle!"none"}</td>
    </tr>
</table>
<a href="/project/${id}/projectInfo">Back</a>
<div class="layer1">
<h2>Список задач в бэклоге</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Issue type</th>
        <th>Workflow</th>
        <th>Priority</th>
        <th>Executor</th>
        <th>Reporter</th>
        <th>Data create</th>
        <th>Выберите родительскую задачу и спринт</th>
    </tr>
    <#list issues as issue>
        <tr>
            <td><a href="project/${issue.id}">${issue.id!"none"}</a></td>
            <td>${issue.title!"none"}</td>
            <td>${issue.issueType!"none"}</td>
            <td>${issue.workFlowIssue!"none"}</td>
            <td>${issue.issuePriority!"none"}</td>
            <td>${issue.executor.firstName + " " + issue.executor.lastName!"none"}</td>
            <td>${issue.reporter.firstName + " " + issue.reporter.lastName!"none"}</td>
            <td>${issue.dataCreate!"none"}</td>
            <td>
                <form method="post" action="/project/${id}/issueListInProject/moveToSprint/issue/${issue.id}">
                    <input type="text" list="parentIssueName" name="parentIssueName">
                    <datalist id="parentIssueName">
                        <#list issues as issue>
                        <option>
                                ${issue.title}
                        </option>
                        </#list>
                    </datalist>
                    <input type="text" list="sprintInProject" name="sprintInProject">
                    <datalist id="sprintInProject">
                        <#list sprints as sprint>
                            <option>
                                ${sprint.title}
                            </option>
                        </#list>
                    </datalist>
                    <button type="submit">Move to sprint</button>
                </form>
            </td>
        </tr>
    </#list>
</table>
</div>
<div>
    <h1>Фильтры</h1>
    <p>Выберите приоритет задачи</p>
    <div>
    <form method="post" action="/project/${id}/issueListInProject/priorityFilter">
        <input type="text" list="issuePriority" name="issuePriority">
        <datalist id="issuePriority">
            <option value="LOW">LOW</option>
            <option value="MIDDLE">MIDDLE</option>
            <option value="HIGH">HIGH</option>
        </datalist>
        <button type="submit">Сортировать по приоритету</button>
    </form>
    </div>
    <div>
    <form method="post" action="/project/${id}/issueListInProject/dateFilter">
        <input type="date" name="date"><br>
        <button type="submit">Сортировать по дате</button>
    </form>
    </div>
    <div>
    <form method="post" action="/project/${id}/issueListInProject/titleFilter">
        <input type="text" name="title"><br>
        <button type="submit">Сортировать по названию</button>
    </form>
    </div>
    <div>
    <form method="post" action="/project/${id}/issueListInProject/executorFilter">
        <input type="text" list="executorsInProject" name="executor">
        <datalist id="executorsInProject">
            <#list executorsInProject as executor>
                <option>
                    ${executor.firstName + " " + executor.lastName}
                </option>
            </#list>
        </datalist>
        <button type="submit">Сортировать по исполнителю</button>
    </form>
    </div>
    <div>
    <form method="post" action="/project/${id}/issueListInProject/reporterFilter">
        <input type="text" list="reportersInProject" name="reporter">
        <datalist id="reportersInProject">
            <#list reportersInProject as reporter>
                <option>
                    ${reporter.firstName + " " + reporter.lastName}
                </option>
            </#list>
        </datalist>
        <button type="submit">Сортировать по репортеру</button>
    </form>
    </div>
</div>
</body>
</html>