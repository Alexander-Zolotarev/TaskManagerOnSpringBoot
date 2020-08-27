<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список проектов</title>
</head>
<body>
<h1>List of projects</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Subdivision</th>
        <th>SupervisorID</th>
        <th>AdminID</th>
    </tr>
    <#list projects as project>
        <tr>
            <td><a href="project/${project.id}/projectInfo">${project.id}</a></td>
            <td>${project.title}</td>
            <td>${project.description}</td>
            <td>${project.subdivision}</td>
            <td>${project.supervisor}</td>
            <td>${project.admin}</td>
        </tr>
    </#list>
</table>
</body>
</html>