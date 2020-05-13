<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <div class="ml-3 my-2"><h5>Список пользователей:</h5></div>
    <div class="col-md-6">
        <a class="btn btn-outline-success my-2" type="button" href="/registration">Добавить пользователя</a>
        <table class="table table-sm table-striped table-hover" id="allTable">
            <thead class="thead-inverse">
            <tr>
                <th>Имя пользователя</th>
                <th>Права</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role.title}<#sep>, </#list></td>
                    <td><a href="/user/${user.id}">Изменить</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@cmn.page>