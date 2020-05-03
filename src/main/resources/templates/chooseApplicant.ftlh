<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <div class="form-group row">
        <form id="applicantForm" method="post" action="/chooseApplicant">
            <div class="ml-3 mb-3"><h4>Выберите заявителя</h4></div>
            <div class="ml-3 mb-2">Список заявителей:</div>
            <div class="input-group">
                <select class="custom-select ml-3" name="applicantSelect">
                    <#list applicants as applicant>
                        <option <#if applicantSelected??><#if applicantSelected.id == applicant.id>selected</#if></#if> value="${applicant.id}" data-toggle="tooltip" title="${applicant.organization}">${applicant.organization?truncate(40, "...")}</option>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-primary mx-2" type="submit">Выбрать</button>
                </div>
            </div>
        </form>
    </div>

    <a class="btn btn-outline-secondary mb-2" data-toggle="collapse" href="#addApplicant" role="button" aria-expanded="false" aria-controls="addApplicant">
        Добавить
    </a>
    <button class="btn btn-outline-primary mb-2" type="submit" form="applicantForm" formmethod="post" formaction="/editApplicantPage">
        Редактировать
    </button>

    <a class="btn btn-secondary mb-2" href="/register_sample" role="button">Назад</a>

    <div class="collapse" id="addApplicant">
        <div class="form-group row">
            <form method="post" action="/addApplicant">
                <div class="mx-2 my-2"><h5>Новый заявитель</h5></div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="organization">Название «организации»</label>
                    <input class="form-control form-control-sm" id="organization" type="text" name="organization" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="address">Адрес организации</label>
                    <input class="form-control form-control-sm" id="address" type="text" name="address" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="mailingAddress">Почтовый адрес</label>
                    <input class="form-control form-control-sm" id="mailingAddress" type="text" name="mailingAddress" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="iban">IBAN</label>
                    <input class="form-control form-control-sm" id="iban" type="text" name="iban" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="bank">Название банка</label>
                    <input class="form-control form-control-sm" id="bank" type="text" name="bank" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="bankAddress">Адрес банка</label>
                    <input class="form-control form-control-sm" id="bankAddress" type="text" name="bankAddress" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="bic">BIC</label>
                    <input class="form-control form-control-sm" id="bic" type="text" name="bic" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="unn">УНН</label>
                    <input class="form-control form-control-sm" id="unn" type="text" name="unn" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="okpo">ОКПО</label>
                    <input class="form-control form-control-sm" id="okpo" type="text" name="okpo" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="telephones">Телефон, телефон (факс)</label>
                    <input class="form-control form-control-sm" id="telephones" type="text" name="telephones" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="email">Электронная почта</label>
                    <input class="form-control form-control-sm" id="email" type="text" name="email" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="contractNumber">Номер договора</label>
                    <input class="form-control form-control-sm" id="contractNumber" type="text" name="contractNumber" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="contractDate">Дата договора</label>
                    <input class="form-control form-control-sm" id="contractDate" type="date" name="contractDate" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="headPosition">Должность главы предприятия</label>
                    <input class="form-control form-control-sm" id="headPosition" type="text" name="headPosition" required/>
                </div>
                <div class="form-group row ml-2 mb-1">
                    <label class="col-form-label" for="headName">И.О. Фамилия главы предприятия</label>
                    <input class="form-control form-control-sm" id="headName" type="text" name="headName" required/>
                </div>
                <div class="mt-2 mb-2">
                    <button class="btn btn-outline-success ml-2 my-2" type="submit">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</@cmn.page>