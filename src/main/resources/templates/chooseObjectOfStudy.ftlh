<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <div class="form-group row">
        <form id="objectOfStudyForm" method="post" action="/chooseObjectOfStudy">
            <div class="ml-3 mb-3"><h4>Выберите объект исследования</h4></div>
            <div class="ml-3 mb-2">Список объектов исследования:</div>
            <div class="input-group">
                <select class="custom-select ml-3" name="objectOfStudySelect">
                    <#list objectsOfStudy as objectOfStudy>
                        <option <#if objectOfStudySelected??><#if objectOfStudySelected.id == objectOfStudy.id>selected</#if></#if> value="${objectOfStudy.id}" data-toggle="tooltip" title="${objectOfStudy.title}; ${objectOfStudy.regulatoryDocument.title}">${objectOfStudy.title?truncate(40, "...")}</option>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-primary ml-2" type="submit">Выбрать</button>
                </div>
            </div>
        </form>
    </div>

    <a class="btn btn-outline-secondary mb-2" data-toggle="collapse" href="#addObjectOfStudy" role="button" aria-expanded="false" aria-controls="addObjectOfStudy">
        Добавить
    </a>
    <button class="btn btn-outline-primary mb-2" type="submit" form="objectOfStudyForm" formmethod="post" formaction="/editObjectOfStudyPage">
        Редактировать
    </button>
    <a class="btn btn-secondary mb-2" href="/sampling_authorities" role="button">Назад</a>

    <div class="collapse" id="addObjectOfStudy">
        <div class="form-group row">
            <form id="regulatoryDocumentForm" method="post" action="/addObjectOfStudy">
                <div class="mx-2 my-2"><h5>Новый объект исследования</h5></div>
                <div class="col-12">
                    <label class="ml-2" for="title">Название объекта исследования:</label>
                    <input class="form-control mb-2" id="title" type="text" name="title" placeholder="Введите название" required/>
                </div>
                <div class="col-12">
                    <label class="ml-2" for="producer">Производитель объекта исследования:</label>
                    <input class="form-control mb-2" id="producer" type="text" name="producer" placeholder="Введите производителя (необязательное)"/>
                </div>
                <label class="ml-4" for="regulatoryDocument">Должен соответствовать требованиям:</label>
                <div class="input-group">
                    <select class="custom-select ml-3 mb-2" id="regulatoryDocument" name="regulatoryDocumentSelect">
                        <#list regulatoryDocuments as regulatoryDocument>
                            <option value="${regulatoryDocument.id}" data-toggle="tooltip" title="${regulatoryDocument.title}">${regulatoryDocument.title?truncate(40, "...")}</option>
                        </#list>
                    </select>
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-outline-primary mb-2 ml-1" form="regulatoryDocumentForm" formmethod="post" formaction="/editRegulatoryDocumentPage" formnovalidate>
                            Редактировать
                        </button>
                        <button type="submit" class="btn btn-outline-primary mb-2 ml-1" form="regulatoryDocumentForm" formmethod="post" formaction="/newRegulatoryDocument" formnovalidate>
                            Новый
                        </button>
                    </div>
                </div>
                <div>
                    <button class="btn btn-outline-success my-2 ml-3" type="submit">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</@cmn.page>