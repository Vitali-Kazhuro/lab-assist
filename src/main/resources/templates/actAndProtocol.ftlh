<#import "parts/common.ftlh" as cmn>
<@cmn.page>
<div class="form-group col-md-5">
    <form id="applicantForm" method="post" action="/chooseApplicantAndDate">
        <div class="ml-3 mb-3"><h4>Сформировать акт и протокол</h4></div>
        <div class="ml-3 mb-2">Выберите заявителя:</div>
        <div class="col">
            <div class="row">
                <select class="custom-select ml-3 mb-2" name="applicantSelect">
                    <#list applicants as applicant>
                        <option value="${applicant.id}" data-toggle="tooltip" title="${applicant.organization}">${applicant.organization?truncate(40, "...")}</option>
                    </#list>
                </select>
            </div>
            <div class="input-group col-md-12">
                <input class="form-control ml-3" form="searchForm" type="text" name="search" value="${search!""}" placeholder="Поиск">
                <div class="input-group-append">
                    <button class="btn btn-outline-info ml-1" form="searchForm" formmethod="get" formaction="/act_and_protocol" type="submit">Найти</button>
                </div>
            </div>
            <div class="row">
                <label for="date" class="col-form-label ml-3" data-toggle="tooltip" title="Войдут протоколы испытаний, начиная со следующей от выбранной даты">Выберите дату последних составленных акта или протокола</label>
                <input id="date" class="form-control ml-3 mb-2" type="date" name="date" required>
            </div>
            <div class="row">
                <label for="price" class="col-form-label ml-3">Цена анализа</label>
                <input id="price" class="form-control ml-3 mb-2" type="number" name="price" value="72" required>
            </div>
            <div class="row">
                <button class="btn btn-primary ml-3 mb-2" type="submit">Выбрать</button>
            </div>
        </div>
    </form>
</div>
<form class="form-inline" id="searchForm" method="get" action="/act_and_protocol"></form>
</@cmn.page>