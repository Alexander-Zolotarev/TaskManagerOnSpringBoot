<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Backlog info</title>
</head>
<body>
<h2>Info about backlog</h2>
<#list backlogs as backlog>
    <div>
        <span>${backlog.id!"none"}</span>
        <span>${backlog.title!"none"}</span>
        <span>${backlog.project!"none"}</span>
    </div>
</#list>
<h1>Создание задачи</h1>
<div>
    <form name="issue" method="post">
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
</body>
</html>