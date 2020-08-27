<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список задач в проете</title>
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
<a href="/project/${id}/projectInfo">Back</a>
<h2>Список задач</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Issue type</th>
        <th>Workflow</th>
    </tr>
    <#list issues as issue>
        <tr>
            <td><a href="project/${issue.id}">${issue.id!"none"}</a></td>
            <td>${issue.title!"none"}</td>
            <td>${issue.issueType!"none"}</td>
            <td>${issue.workFlowIssue!"none"}</td>
        </tr>
    </#list>
</table>
</body>
</html>