<#include "security.ftlh">
<#macro modalDelete who>

<div class="modal fade" id="delete${who}" tabindex="-1" role="dialog" aria-labelledby="delete${who}Title" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="delete${who}Title">Удалить объект из базы данных?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group row">
                    <form id="delete${who}Form" method="post" action="/delete${who}">
                        <#nested>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <#if isAdmin>
                    <button type="submit" class="btn btn-danger" form="delete${who}Form">Удалить</button>
                <#else>
                    <div>
                        У вас нет прав на удаление объекта из базы данных, обратитесь к администратору!
                    </div>
                </#if>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Отменить</button>
            </div>
        </div>
    </div>
</div>

</#macro>