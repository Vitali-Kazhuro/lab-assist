<#import "parts/common.ftlh" as cmn>
<@cmn.page>
<div class="form-group row">
    <form id="applicantForm" method="post" action="/chooseApplicantAndDate">
        <div class="ml-3 mb-3"><h4>Выберите заявителя</h4></div>
        <div class="ml-3 mb-2">Список заявителей:</div>
        <div class="col">
            <div class="row">
                <select class="custom-select ml-3 mb-2" name="applicantSelect">
                    <#list applicants as applicant>
                        <option value="${applicant.id}" data-toggle="tooltip" title="${applicant.organization}">${applicant.organization?truncate(40, "...")}</option>
                    </#list>
                </select>
            </div>
            <div class="row">
                <label for="date" class="col-form-label ml-3">Выберите дату после которой...</label>
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
</@cmn.page>