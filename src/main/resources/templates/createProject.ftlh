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
<a href="addUser">Add participant</a><br>
<a href="/projectsList" id="tpl">To projects list</a>
<h1>Create project</h1>
<div>
    <form method="post" action="createProject">
        <label>Название</label>
        <input type="text" name="title" />
        <p><label>Описание</label></p>
        <textarea name="description"></textarea>
        <label>Название подразделения</label>
        <p><input type="text" name="subdivision" /></p>
        <label>ID руководителя</label>
        <p><input type="text" name="supervisor" /></p>
        <label>ID администратора</label>
        <p><input type="text" name="admin" /></p>
        <button type="submit">Добавить</button>
    </form>
</div>
</body>
</html>
