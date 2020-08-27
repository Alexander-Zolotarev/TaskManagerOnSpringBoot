<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project Info</title>
</head>
<body>
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
<a href="/projectsList">Back</a>
<h1>Создание задачи</h1>
<div>
    <form method="post">
        <label>Название</label>
        <input type="text" name="title" /><br>
        <label>Описание</label>
        <input type="text" name="description" /><br>
        <label>Исполнитель</label>
        <input type="text" name="executor" /><br>
        <label>Репортер</label>
        <input type="text" name="reporter" /><br>
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
<h1>Список задач в бэклоге</h1>
<form method="post" action="/project/${id}/projectInfo/titleFilter">
    <input type="text" name="title">
    <button type="submit">Сортировать по названию</button>
</form>
<form method="get" name="issues">
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Issue type</th>
            <th>Workflow</th>
        </tr>
        <#list issues as issue>
            <tr>
                <td>${issue.id!"none"}</a></td>
                <td>${issue.title!"none"}</td>
                <td>${issue.issueType!"none"}</td>
                <td>${issue.workFlowIssue!"none"}</td>
            </tr>
        </#list>
    </table>
</form>
<a href="/project/${id}/issueListInProject">Перейти к списку задач</a>
</body>
</html>