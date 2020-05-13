<#import "parts/common.ftlh" as cmn>
<#import "parts/login.ftlh" as lgn>
<@cmn.page>
    <#--Login page-->
    <div class="my-3">
        <h4>Войти:</h4>
    </div>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger form-group row col-sm-3" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message?replace("Bad credentials", "Неверное имя пользователя или пароль!")}
        </div>
    </#if>
    <@lgn.login "/login" false/>
</@cmn.page>

