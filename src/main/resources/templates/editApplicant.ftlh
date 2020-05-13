<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<@cmn.page>
    <div class="form-group row">
        <form method="post" action="/editApplicant">
            <div class="mx-2 my-2"><h5>Редактировать заявителя</h5></div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="organization">Название «организации»</label>
                <input class="form-control form-control-sm" id="organization" type="text" name="organization" value="${applicant.organization}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="address">Адрес организации</label>
                <input class="form-control form-control-sm" id="address" type="text" name="address" value="${applicant.address}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="mailingAddress">Почтовый адрес</label>
                <input class="form-control form-control-sm" id="mailingAddress" type="text" name="mailingAddress" value="${applicant.mailingAddress!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="iban">IBAN</label>
                <input class="form-control form-control-sm" id="iban" type="text" name="iban" value="${applicant.iban!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="bank">Название банка</label>
                <input class="form-control form-control-sm" id="bank" type="text" name="bank" value="${applicant.bank!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="bankAddress">Адрес банка</label>
                <input class="form-control form-control-sm" id="bankAddress" type="text" name="bankAddress" value="${applicant.bankAddress!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="bic">BIC</label>
                <input class="form-control form-control-sm" id="bic" type="text" name="bic" value="${applicant.bic!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="unn">УНН</label>
                <input class="form-control form-control-sm" id="unn" type="text" name="unn" value="${applicant.unn!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="okpo">ОКПО</label>
                <input class="form-control form-control-sm" id="okpo" type="text" name="okpo" value="${applicant.okpo!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="telephones">Телефон, телефон (факс)</label>
                <input class="form-control form-control-sm" id="telephones" type="text" name="telephones" value="${applicant.telephones!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="email">Электронная почта</label>
                <input class="form-control form-control-sm" id="email" type="text" name="email" value="${applicant.email!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="contractNumber">Номер договора</label>
                <input class="form-control form-control-sm" id="contractNumber" type="text" name="contractNumber" value="${applicant.contractNumber!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="contractDate">Дата договора</label>
                <input class="form-control form-control-sm" id="contractDate" type="date" name="contractDate" value="${applicant.contractDate!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="headPosition">Должность главы предприятия</label>
                <input class="form-control form-control-sm" id="headPosition" type="text" name="headPosition" value="${applicant.headPosition!""}" required/>
            </div>
            <div class="form-group row ml-2 mb-1">
                <label class="col-form-label" for="headName">И.О. Фамилия главы предприятия</label>
                <input class="form-control form-control-sm" id="headName" type="text" name="headName" value="${applicant.headName!""}" required/>
            </div>
            <div class="mt-2 mb-2">
                <button class="btn btn-outline-success ml-2 my-2" type="submit">Сохранить</button>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-outline-danger my-2" data-toggle="modal" data-target="#deleteApplicant">
                    Удалить
                </button>
                <a class="btn btn-outline-secondary my-2" href="/applicants" role="button">Назад</a>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <!-- Modal -->
    <@del.modalDelete "Applicant">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить заявителя <b>"${applicant.organization}"</b>?</h5></div>
        <div class="ml-3 my-2"><h6><em>Это удалит все органы отбора проб, объекты исследования, образцы, связанные с этим заявителем!</em></h6></div>
        <input type="hidden" name="applicantId" value="${applicant.id}">
    </@del.modalDelete>
</@cmn.page>