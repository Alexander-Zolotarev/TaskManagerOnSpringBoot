<html lang="eng">
<head>
    <title>Create project</title>
</head>
<body>
<h2>Список пользователей</h2>
<#if users??>
<#list users as user>
    <div>
        <span>${user.id!"none"}</span>
        <span>${user.firstName!"none"}</span>
        <span>${user.lastName!"none"}</span>
    </div>
</#list>
</#if>
<a href="addUser">Add participant</a><br>
<a href="/projectsList" id="tpl">To projects list</a>
<h1>Create project</h1>
<div>
    <#if errors??>
        <#list errors as error>
            ${error.toString()}<br>
        </#list>
    </#if>
    <form method="post" action="createProject">
        <label>Название</label>
        <input type="text" name="title" /><br>
        <label>Описание</label>
        <textarea name="description"></textarea><br>
        <label>Название подразделения</label>
        <input type="text" name="subdivision" /><br>
        <label>Руководитель</label>
        <input type="text"name="supervisor" /><br>
<#--        <select>-->
<#--            <#list users as user>-->
<#--                <option name="supervisor">-->
<#--                    ${user.firstName + " " + user.lastName}-->
<#--                </option>-->
<#--            </#list>-->
<#--        </select>-->
<#--        <input type="text" list="supervisor" name="supervisor">-->
<#--        <datalist id="supervisor">-->
<#--            <#list users as user>-->
<#--                <option>-->
<#--                    ${user.firstName + " " + user.lastName}-->
<#--                </option>-->
<#--            </#list>-->
<#--        </datalist><br>-->
        <label>Администратор</label>
        <input type="text" list="admin" name="admin">
        <datalist id="admin">
            <#list users as user>
                <option>
                    ${user.firstName + " " + user.lastName}
                </option>
            </#list>
        </datalist><br>
        <button type="submit">Добавить</button>
    </form>
</div>
</body>
</html>
