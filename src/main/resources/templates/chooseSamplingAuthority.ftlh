<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <div class="form-group row">
        <form id="samplingAuthorityForm" method="post" action="/chooseSamplingAuthority">
            <div class="ml-3 mb-3"><h4>Выберите орган отбора проб</h4></div>
            <div class="ml-3 mb-2">Список органов отбора проб:</div>
            <div class="input-group">
                <select class="custom-select ml-3" name="samplingAuthoritySelect">
                    <#list samplingAuthorities as samplingAuthority>
                        <option <#if samplingAuthoritySelected??><#if samplingAuthoritySelected.id == samplingAuthority.id>selected</#if></#if> value="${samplingAuthority.id}" data-toggle="tooltip" title="${samplingAuthority.title}">${samplingAuthority.title?truncate(40, "...")}</option>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-primary ml-2" type="submit">Выбрать</button>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>

    <a class="btn btn-outline-secondary mb-2" data-toggle="collapse" href="#addSamplingAuthority" role="button" aria-expanded="false" aria-controls="addSamplingAuthority">
        Добавить
    </a>
    <button class="btn btn-outline-primary mb-2" type="submit" form="samplingAuthorityForm" formmethod="post" formaction="/editSamplingAuthorityPage">
        Редактировать
    </button>
    <a class="btn btn-outline-info mb-2" data-toggle="collapse" href="#search" role="button" aria-expanded="false" aria-controls="search">
        Поиск
    </a>
    <a class="btn btn-secondary mb-2" href="/applicants" role="button">Назад</a>

    <div class="collapse" id="search">
        <form class="form-inline" method="get" action="/sampling_authorities">
            <div class="form-group mx-sm-4 my-2">
                <input class="form-control" type="text" name="search" value="${search!""}" placeholder="Поиск">
                <div class="input-group-append">
                    <button class="btn btn-outline-info ml-1" type="submit">Найти</button>
                </div>
            </div>
        </form>
    </div>

    <div class="collapse" id="addSamplingAuthority">
        <div class="form-group row">
            <form method="post" action="/addSamplingAuthority">
                <div class="mx-2 my-2"><h5>Новый орган отбора проб</h5></div>
                <div class="form-row">
                    <div class="col-12">
                        <label class="ml-2" for="title">Название органа отбора проб:</label>
                        <input class="form-control mx-2 mb-2" id="title" type="text" name="title" placeholder="Введите название" required/>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-outline-success mx-2 my-2" type="submit">Добавить</button>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</@cmn.page>