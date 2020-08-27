<html lang="eng">
<head>
    <title>Create project</title>
</head>
<body>
<h2>Список пользователей</h2>
<#list users as user>
    <div>
        <span>${user.id!"none"}</span>
        <span>${user.firstName!"none"}</span>
        <span>${user.lastName!"none"}</span>
    </div>
</#list>
<a href="addUser">Add participant</a>
<div>
    <form method="post" action="createProject">
        <label>Название</label>
        <input type="text" name="title" /><br>
        <label>Описание</label>
        <input type="text" name="description" /><br>
        <label>Название подразделения</label>
        <input type="text" name="subdivision" /><br>
        <label>ID руководителя</label>
        <input type="text" name="supervisor" /><br>
        <label>ID администратора</label>
        <input type="text" name="admin" /><br>
        <button type="submit">Добавить</button>
    </form>
</div>
<a href="/projectsList">To projects list</a>
</body>
</html>
