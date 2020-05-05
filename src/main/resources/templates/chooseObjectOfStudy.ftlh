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
    <a class="btn btn-outline-info mb-2" data-toggle="collapse" href="#search" role="button" aria-expanded="false" aria-controls="search">
        Поиск
    </a>
    <a class="btn btn-secondary mb-2" href="/sampling_authorities" role="button">Назад</a>

    <div class="collapse" id="search">
        <form class="form-inline" method="get" action="/objects_of_study">
            <div class="form-group mx-sm-4 my-2">
                <input class="form-control" type="text" name="searchObjectOfStudy" value="${searchObjectOfStudy!""}" placeholder="Поиск">
                <div class="input-group-append">
                    <button class="btn btn-outline-info ml-1" type="submit">Найти</button>
                </div>
            </div>
        </form>
    </div>

    <div class="collapse <#if newRegDocument>show<#elseif searchRegDocument>show</#if>" id="addObjectOfStudy">
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
                        <button type="submit" class="btn btn-outline-primary mb-2 ml-1" formaction="/editRegulatoryDocumentPage" formnovalidate>
                            Редактировать
                        </button>
                        <button type="submit" class="btn btn-outline-primary mb-2 ml-1" formaction="/newRegulatoryDocument" formnovalidate>
                            Новый
                        </button>
                    </div>
                </div>
            </form>
            <div class="input-group">
                <button class="btn btn-outline-success mt-2 ml-3" form="regulatoryDocumentForm" type="submit">Добавить</button>
                <div class="input-group-append">
                    <form class="form-inline" method="get" action="/objects_of_study">
                        <input class="form-control  ml-5 mt-2" type="text" name="searchRegulatoryDocument" value="${searchRegulatoryDocument!""}" placeholder="Поиск">
                        <div class="input-group-append">
                            <button class="btn btn-outline-info ml-1 mt-2" type="submit">Найти</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@cmn.page>