<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project Info</title>
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
        <td>${supervisor!"none"}</td>
    </tr>
    <tr>
        <td>Admin</td>
        <td>${admin!"none"}</td>
    </tr>
    <tr>
        <td>Backlog</td>
        <td>${backlogTitle!"none"}</td>
    </tr>
</table>
<a href="/projectsList">Back</a>
<div class="layer1">
    <h1>Создание спринта</h1>
    <form method="post" action="/project/${id}/projectInfo/createSprint">
        <label>Название</label>
        <input type="text" name="title" /><br>
        <label>Дата начала спринта</label>
        <input type="date" name="startDate"><br>
        <label>Дата окончания спринта</label>
        <input type="date" name="endDate"><br>
        <button type="submit">Добавить</button>
    </form>
</div>
<h1>Создание задачи</h1>
<div>
    <form method="post">
        <label>Название</label>
        <input type="text" name="title" /><br>
        <label>Описание</label>
        <input type="text" name="description" /><br>
        <label>Id исполнителя</label>
        <input type="text" name="executor" /><br>
        <label>Id репортера</label>
        <input type="text" name="reporter" /><br>
        <label>Приоритет задачи</label>
        <select name="issuePriority">
            <option disabled>Выберите приоритет задачи</option>
            <option value="LOW">LOW</option>
            <option value="MIDDLE">MIDDLE</option>
            <option value="HIGH">HIGH</option>
        </select><br>
        <label>Тип задачи</label>
        <select name="issueType">
            <option disabled>Выберите тип задачи</option>
            <option value="EPIC">EPIC</option>
            <option value="STORY">STORY</option>
            <option value="TASK">TASK</option>
            <option value="BUG">BUG</option>
        </select>
        <button type="submit">Добавить</button>
    </form>
</div>
<div class="layer1">
    <h1>Список спринтов в проекте</h1>
    <form method="get" name="sprints">
        <table>
            <tr>
                <th>Project title</th>
                <th>Sprint title</th>
                <th>Start date</th>
                <th>End date</th>
            </tr>
            <#list sprints as sprint>
                <tr>
                    <td>${sprint.project.getTitle()!"none"}</a></td>
                    <td>${sprint.getTitle()!"none"}</a></td>
                    <td>${sprint.startDate!"none"}</a></td>
                    <td>${sprint.endDate!"none"}</a></td>
                </tr>
            </#list>
        </table>
    </form>
</div>
<div>
<h1>Список задач в бэклоге</h1>
<form method="get" name="issues">
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Issue type</th>
            <th>Workflow</th>
            <th>Priority</th>
            <th>Data create</th>
            <th>Change workflow</th>
        </tr>
        <#list issues as issue>
            <tr>
                <td>${issue.id!"none"}</a></td>
                <td>${issue.title!"none"}</td>
                <td>${issue.issueType!"none"}</td>
                <td>${issue.workFlowIssue!"none"}</td>
                <td>${issue.issuePriority!"none"}</td>
                <td>${issue.dataCreate!"none"}</td>
                <td>
                    <form method="post" action="/project/${id}/projectInfo/changeWorkFlow/issue/${issue.id}" id="wf">
                        <input type="text" list="workflow" name="workflow">
                        <datalist id="workflow">
                            <#list workflows as workflow>
                                <option>
                                    ${workflow.name()}
                                </option>
                            </#list>
                        </datalist>
                        <button type="submit">Change workflow</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
</form>
</div>
<a href="/project/${id}/issueListInProject">Перейти к списку задач</a>
</body>
</html>