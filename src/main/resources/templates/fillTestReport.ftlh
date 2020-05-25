<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <div class="form-group row">
        <form id="testReportForm" method="post" action="/fillTestReport">
            <div class="ml-3 mb-3"><h3>Заполните форму протокола испытаний:</h3></div>
            <div class="form-group row">
                <label class="col-sm-5 col-form-label">Номер протокола</label>
                <div class="col-sm-5">
                    <input class="form-control" type="number" name="protocolNumber" min="1" placeholder="1+ "
                           value="<#if testReport??>${testReport.protocolNumber?c}</#if>" required/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-5 col-form-label">Выберите дату протокола </label>
                <div class="col-sm-5">
                    <input id="date" class="form-control" type="date" name="date"
                           <#if testReport??>value="${testReport.date}"</#if> required>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-5 col-form-label">Наименование ТНПА, устанавливающих методы испытаний</label>
                <div class="col-sm-5">
                    <select class="custom-select mt-2" name="testMethodsSelect" multiple size="2" required>
                        <#list testMethods as testMethod>
                            <option <#if testReport??><#list testReport.testMethods as testMethodSelected>
                                <#if testMethodSelected.id == testMethod.id>selected</#if></#list></#if>
                                    value="${testMethod.id}" data-toggle="tooltip" title="${testMethod.title}">
                                ${testMethod.title?truncate(40, "...")}
                            </option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-5 col-form-label">Начало проведения испытаний</label>
                <div class="col-sm-5">
                    <input class="form-control" type="date" name="startDate" <#if testReport??>value="${testReport.startDate}"</#if> required>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-5 col-form-label">Окончание проведения испытаний</label>
                <div class="col-sm-5">
                    <input class="form-control" type="date" name="endDate" <#if testReport??>value="${testReport.endDate}"</#if> required>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-5 col-form-label">Выберите исполнителя 1</label>
                <div class="col-sm-5">
                    <select class="custom-select mt-2" name="testReportDoer1Select">
                        <#list employees as employee>
                            <option <#if testReport??><#if employee.id == testReport.performers[0].id >selected</#if></#if>
                                    value="${employee.id}">
                                ${employee.name}
                            </option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-5 col-form-label">Выберите исполнителя 2</label>
                <div class="col-sm-5">
                    <select class="custom-select mt-2" name="testReportDoer2Select">
                        <#list employees as employee>
                            <option <#if testReport??><#if testReport.performers[1].id == employee.id >selected</#if></#if>
                                    value="${employee.id}">
                                ${employee.name}
                            </option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="mt-2 mb-2">
                <button class="btn btn-primary mb-2" <#if testReport??>disabled</#if> type="submit">Добавить</button>
                <button class="btn btn-primary mb-2" <#if testReport??><#else>disabled</#if> formaction="/editTestReport">Изменить</button>
                <a class="btn btn-outline-primary mb-2" href="/all_test_reports" role="button">Посмотреть все</a>
                <a class="btn btn-secondary mb-2" href="/objects_of_study" role="button">Назад</a>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
</@cmn.page>