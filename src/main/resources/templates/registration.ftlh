<#import "parts/common.ftlh" as cmn>
<#import "parts/login.ftlh" as lgn>
<@cmn.page>
    <div class="my-3">
        <h4>Добавить пользователя:</h4>
    </div>
    <@lgn.login "/registration" true/>
</@cmn.page>