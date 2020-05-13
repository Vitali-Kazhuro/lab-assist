<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<@cmn.page>
    <div class="my-2">
        <h5>Изменить пользователя:</h5>
    </div>
    <div class="col-md-3">
        <form action="/user" method="post">
            <label class="col-form-label" for="userName">Имя пользователя:</label>
            <input class="form-control mb-3" id="userName" type="text" value="${user.username}" name="username">
            <#list roles as role>
                <div class="my-2">
                    <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role.title}</label>
                </div>
            </#list>
            <input class="form-control" type="hidden" value="${user.id}" name="userId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <div class="input-group my-1">
                <button class="btn btn-outline-success" type="submit">Сохранить</button>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-outline-danger ml-1" data-toggle="modal" data-target="#deleteUser">
                    Удалить
                </button>
                <a class="btn btn-outline-secondary input-group-append ml-1" type="button" href="/user">Назад</a>
            </div>
        </form>
    </div>

    <!-- Modal -->
    <@del.modalDelete "User">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить пользователя <b>"${user.username}"</b>?</h5></div>
        <input type="hidden" name="userId" value="${user.id}">
    </@del.modalDelete>
</@cmn.page>

