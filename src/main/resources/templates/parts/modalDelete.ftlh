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
                        <#--<div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить заявителя <b>${applicant.organization}</b>?</h5></div>-->
                        <#--<input type="hidden" value="${applicant.id}">-->
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-danger" form="delete${who}Form">Удалить</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Отменить</button>
            </div>
        </div>
    </div>
</div>

</#macro>