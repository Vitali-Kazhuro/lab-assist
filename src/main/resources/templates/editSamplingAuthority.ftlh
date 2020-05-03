<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<@cmn.page>
    <div class="form-group row">
        <form method="post" action="/editSamplingAuthority">
            <div class="mx-2 my-2"><h5>Редактировать орган отбора проб</h5></div>
            <div class="form-row">
                <div class="col-9">
                    <label class="ml-2" for="title">Название органа отбора проб:</label>
                    <input class="form-control mx-2 mb-2" id="title" type="text" name="title" value="${samplingAuthority.title}" required/>
                </div>
                <div class="col-auto">
                    <button class="btn btn-outline-success ml-2 my-2" type="submit">Сохранить</button>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-danger my-2" data-toggle="modal" data-target="#deleteSamplingAuthority">
                        Удалить
                    </button>
                    <a class="btn btn-outline-secondary my-2" href="/sampling_authorities" role="button">Назад</a>
                </div>
            </div>
        </form>
    </div>
    <!-- Modal -->
    <@del.modalDelete "SamplingAuthority">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить орган отбора проб <b>"${samplingAuthority.title}"</b>?</h5></div>
        <div class="ml-3 my-2"><h7><em>Это удалит все объекты иссдедования и образцы, связанные с этим органом отбора проб!</em></h7></div>
        <input type="hidden" name="samplingAuthorityId" value="${samplingAuthority.id}">
    </@del.modalDelete>
</@cmn.page>