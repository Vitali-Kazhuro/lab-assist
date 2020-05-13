<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<#import "parts/pager.ftlh" as pgr>
<@cmn.page>
    <h3>Формирование протокола испытаний</h3>
    <#if testReport??>
        <div class="my-3">
            <h6>В данный момент выбран протокол испытаний <b>№${testReport.protocolNumber}</b> от <b>${testReport.dateS}</b></h6>
        </div>
        <div class="row">
            <table class="table table-striped" >
                <thead class="thead-inverse">
                <tr>
                    <th>№</th>
                    <th>Дата</th>
                    <th>Заявитель</th>
                    <th>Образцы</th>
                    <th width="90">Шифр</th>
                    <th>Элеметы</th>
                    <th>Требования</th>
                    <th>Дата н.и.</th>
                    <th>Дата к.и.</th>
                    <th width="450">Метод испытаний</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td id="${testReport.id}"><small><b>${testReport.protocolNumber}</b></small></td>
                    <td><small>${testReport.dateS}</small></td>
                    <td><small><#if testReport.samples[0]??>${testReport.samples[0].objectOfStudy.samplingAuthority.applicant.organization}</#if></small></td>
                    <td><small><#list testReport.samples as sample><#if sample??><div class="mb-2">${sample.objectOfStudy.title} ${sample.series} ${sample.objectOfStudy.producer!""}</div></#if></#list></small></td>
                    <td><small><b><#list testReport.samples as sample><#if sample??><div class="mb-4">${sample.cipher}</div></#if></#list></b></small></td>
                    <td><small><#list testReport.samples as sample><#if sample??><div class="mb-4"><#list sample.sampleNorms as sampleNorm><#if sampleNorm.norm.element.symbol??>${sampleNorm.norm.element.symbol}<#else>${sampleNorm.norm.element.title}</#if><#sep>, </#list></div></#if></#list></small></td>
                    <td><small><#list testReport.samples as sample><#if sample??><div class="mb-2">${sample.objectOfStudy.regulatoryDocument.title}</div></#if></#list></small></td>
                    <td><small>${testReport.startDateS}</small></td>
                    <td><small>${testReport.endDateS}</small></td>
                    <td><small><#list testReport.testMethods as testMethod>${testMethod.title}<#sep>; </#list></small></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="my-3">
            <h6>Чтобы сформировать данный протокол, нажмите "Далее"</h6>
        </div>
        <div class="my-3">
            <h6>Чтобы изменить данные выбранного протокола, нажмите "Изменить"</h6>
        </div>
        <div class="my-3">
            <h6>Чтобы выбрать другой протокол нажмите "Все протоколы" или перейдите в <a href="/all_samples_in">Журнал учёта поступивших образцов</a> и выберите образец из нужного протокола</h6>
        </div>
        <div class="row">
            <a class="btn btn-outline-success ml-1" data-toggle="collapse" href="#allTestReports" role="button" aria-expanded="false" aria-controls="allTestReports" <#--onclick="addRowHandlers()-->">
                Все протоколы
            </a>
            <a class="btn btn-outline-primary ml-1" href="/fill_test_report" role="button">Изменить</a>
            <a class="btn btn-primary ml-1" href="/add_results" role="button">Далее</a>
        </div>
    <#else>
        <div class="my-3">
            <h6>В данный момент не выбрано протокола испытаний.</h6>
        </div>
        <div class="my-3">
            <h6>Выберете протокол из всех протоколов или перейдите в <a href="/all_samples_in">Журнал учёта поступивших образцов</a> и выберите образец из нужного протокола</h6>
        </div>
        <a class="btn btn-success ml-1" data-toggle="collapse" href="#allTestReports" role="button" aria-expanded="false" aria-controls="allTestReports" <#--onclick="addRowHandlers()-->">
            Все протоколы
        </a>
    </#if>

    <div class="collapse <#if testReport??><#else>show</#if>" id="allTestReports">
        <div class="form-group row">
            <form method="post" id="chooseTestReportForm" action="/selectTestReport">
                <div class="mx-2 my-3"><h4>Все протоколы испытаний:</h4></div>
                <div class="input-group">
                    <label class="ml-2 mt-2">Протокол №</label>
                    <select class="custom-select ml-2 mb-1" id="oneSelect" name="testReportSelect">
                        <#list allTestReportsPage.content as thisTestReport>
                            <option <#if testReport??><#if testReport.id == thisTestReport.id>selected</#if></#if> value="${thisTestReport.id}">${thisTestReport.protocolNumber}</option>
                        </#list>
                    </select>
                    <div class="input-group-append">
                        <button class="btn btn-outline-success ml-1 mb-2" id="chooseButton">Выбрать</button>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-outline-danger ml-1 mb-2" data-toggle="modal" data-target="#deleteTestReport" <#if testReport??><#else>hidden</#if>>
                            Удалить
                        </button>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </div>
        <@pgr.pager "start_test_report" allTestReportsPage />
        <#include "parts/allTestReportsTable.ftlh">
    </div>
    <!-- Modal -->
    <@del.modalDelete "TestReport">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить протокол испытаний № <b><#if testReport??>${testReport.protocolNumber}</#if></b>?</h5></div>
        <div class="ml-3 my-2"><h6><em>Это удалит образец(-цы), связанный(-ые) с данным протоколом!</em></h6></div>
        <input type="hidden" name="testReportId" value="<#if testReport??>${testReport.id}</#if>">
    </@del.modalDelete>
</@cmn.page>