<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<@cmn.page>
    <div class="form-group row">
        <form method="post" action="/editObjectOfStudy">
            <div class="mx-2 my-2"><h5>Редактировать объект исследования</h5></div>
            <div class="col-9">
                <div class="row">
                    <label class="ml-2" for="title">Название объекта исследования:</label>
                    <input class="form-control ml-2 mb-2" id="title" type="text" name="title" value="${objectOfStudy.title}" required/>
                </div>
                <div class="row">
                    <label class="ml-2" for="producer">Производитель объекта исследования:</label>
                    <input class="form-control ml-2 mb-2" id="producer" type="text" name="producer" value="${objectOfStudy.producer!""}" placeholder="Введите производителя (необязательное)" />
                </div>
                <div class="row">
                    <label class="ml-2" for="samplingAuthority">Орган отбора:</label>
                    <select class="custom-select ml-2 mb-2" id="samplingAuthority" name="samplingAuthoritySelect">
                        <#list samplingAuthorityList as samplingAuthority>
                            <option <#if objectOfStudy.samplingAuthority.id == samplingAuthority.id>selected</#if> value="${samplingAuthority.id}" data-toggle="tooltip" title="${samplingAuthority.title}">${samplingAuthority.title?truncate(40, "...")}</option>
                        </#list>
                    </select>
                </div>
                <div class="row">
                    <label class="ml-2" for="regulatoryDocument">Нормативный документ:</label>
                    <select class="custom-select ml-2 mb-2" id="regulatoryDocument" name="regulatoryDocumentSelect">
                        <#list regulatoryDocumentList as regulatoryDocument>
                            <option <#if objectOfStudy.regulatoryDocument.id == regulatoryDocument.id>selected</#if> value="${regulatoryDocument.id}" data-toggle="tooltip" title="${regulatoryDocument.title}">${regulatoryDocument.title?truncate(40, "...")}</option>
                        </#list>
                    </select>
                </div>
                <div class="row">
                    <button class="btn btn-outline-success ml-2 my-2" type="submit">Сохранить</button>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-danger ml-1 my-2" data-toggle="modal" data-target="#deleteObjectOfStudy">
                        Удалить
                    </button>
                    <a class="btn btn-outline-secondary ml-1 my-2" href="/objects_of_study" role="button">Назад</a>
                </div>
            </div>
        </form>
    </div>
    <!-- Modal -->
    <@del.modalDelete "ObjectOfStudy">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить объект исследования <b>"${objectOfStudy.title}"</b>?</h5></div>
        <div class="ml-3 my-2"><h7><em>Это удалит все образцы, связанные с этим объектом исследования!</em></h7></div>
        <input type="hidden" name="objectOfStudyId" value="${objectOfStudy.id}">
    </@del.modalDelete>
</@cmn.page>