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
        <th>Supervisor</th>
        <th>Admin</th>
    </tr>
    <#list projects as project>
        <tr>
            <td><a href="project/${project.id}/projectInfo">${project.id}</a></td>
            <td>${project.title}</td>
            <td>${project.description}</td>
            <td>${project.subdivision}</td>
            <td>${project.supervisor.firstName + " " + project.supervisor.lastName}</td>
            <td>${project.admin.firstName + " " + project.admin.lastName}</td>
        </tr>
    </#list>
</table>
<a href="createProject">Back</a>
</body>
</html>