<#--<#import "/spring.ftl" as spring />-->

<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>Form Data Binding and Validation</title>-->
<#--</head>-->
<#--<body>-->
<#--<h2>Form Data Binding and Validation</h2>-->
<#--<@spring.bind 'user'/>-->
<#--<#if user?? && noErrors??>-->
<#--    First name: ${user.firstName}<br>-->
<#--    Last name: ${user.lastName}<br>-->
<#--<#else>-->
<#--    <form method="post">-->
<#--        First name:<br>-->
<#--        <@spring.formInput "user.firstName"/>-->
<#--        <@spring.showErrors "<br>"/>-->
<#--        <br><br>-->
<#--        Last name:<br>-->
<#--        <@spring.formInput "user.lastName"/>-->
<#--        <@spring.showErrors "<br>"/>-->
<#--        <br><br>-->
<#--        <input type="submit" value="Submit">-->
<#--    </form>-->
<#--</#if>-->
<#--</body>-->
<#--</html>-->

<#--<#import "/spring.ftl" as spring/>-->
<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>Form Data Binding and Validation</title>-->
<#--</head>-->
<#--<body>-->
<#--<fieldset>-->
<#--    <h1>Create your Account</h1>-->
<#--    <form action="<@spring.url '/addUser'/>" method="post">-->
<#--        <@spring.bind 'user.firstName'/>-->
<#--        Username: <input type="text" name="${spring.status.expression}" value="${spring.status.value}"/>-->
<#--        <#list spring.status.errorMessages as error>-->
<#--            <span class="error">${error}</span>-->
<#--            <br>-->
<#--        </#list>-->
<#--        <@spring.bind 'user.lastName'/>-->
<#--        Password: <input type="password" name="${spring.status.expression}" value="${spring.status.value}"/>-->
<#--        <#list spring.status.errorMessages as error>-->
<#--            <span class="error">${error}</span>-->
<#--            <br>-->
<#--        </#list>-->
<#--        <label class="main-label" style="width: 91px;">&nbsp;</label>-->
<#--        <input type="submit" value="submit">-->
<#--    </form>-->
<#--</fieldset>-->
<#--</body>-->
<#--</html>-->


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add participant</title>
</head>
<body>
<h1>Add participant</h1>
<div>
<#if errors??>
    <#list errors?keys as key>
        ${key}: ${errors[key]}<br>
    </#list>
</#if>
    <form method="post">
        <label>firstName</label>
        <input type="text" name="firstName" /><br>

        <label>lastName</label>
        <input type="text" name="lastName" /><br>
        <button type="submit">Добавить</button>
    </form>
</div>
</body>
</html>

<#--<#import "/spring.ftl" as spring/>-->
<#--<html>-->
<#--<head>-->
<#--    <title>Add Person</title>-->
<#--    <link rel="stylesheet"-->
<#--          type="text/css" href="<@spring.url '/css/style.css'/>"/>-->
<#--</head>-->
<#--<body>-->
<#--<#if errorMessage??>-->
<#--    <div style="color:red;font-style:italic;">-->
<#--        ${errorMessage}-->
<#--    </div>-->
<#--    <#else>-->
<#--    Nothing-->

<#--</#if>-->
<#--<div>-->
<#--    <fieldset>-->
<#--        <legend>Add Person</legend>-->
<#--        <form method="POST">-->
<#--            First Name: <@spring.formInput "personForm.firstName" "" "text"/>    <br/>-->
<#--            Last Name: <@spring.formInput "personForm.lastName" "" "text"/>    <br/>-->
<#--            <input type="submit" value="Create" />-->
<#--        </form>-->
<#--    </fieldset>-->
<#--</div>-->
<#--</body>-->
<#--</html>-->