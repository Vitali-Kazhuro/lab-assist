<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <style>
        .table > thead > tr > th {
            vertical-align: middle;
        }
    </style>
    <div class="form-group row">
        <form method="post" action="/printActAndProtocol">
            <div class="mx-2 my-3"><h4>Формируем акт и протокол</h4></div>
                Выполнены исследовательские работы:
            <div class="my-2">
                ${textCheck?no_esc}
            </div>
            <div class="my-2">
                Протоколы испытаний: ${protocolAndDate}
            </div>
            <div class="my-2">
                Итого выполнено работ на сумму: ${sum}
            </div>
            <div class="row">
                <label <#--for="number"--> class="col-form-label ml-3 mb-2">Номер акта и протокола:</label>
                <div class="col-sm-1">
                    <input id="number" class="form-control ml-1 mb-2" type="number" min="1" step="1" name="number" required>
                </div>
            </div>
            <button class="btn btn-outline-success ml-1 my-2">Вывести акт и протокол</button>
            <button class="btn btn-primary my-2" formmethod="get" formaction="/downloadAct">Скачать акт</button>
            <button class="btn btn-primary my-2" formmethod="get" formaction="/downloadProtocol">Скачать протокол</button>
            <a class="btn btn-outline-secondary my-2" href="/act_and_protocol" role="button">Назад</a>
        </form>
    </div>
    <#--<div class="row">
        <table class="table table-striped">
            <thead class="thead-inverse">
            <tr>
                <th>Номер протокола</th>
                <th>Дата</th>
                <th>Заявитель</th>
                <th>Образец</th>
                <th>Заявленные виды работ</th>
            </tr>
            </thead>
            <tbody>
            <#list allTestReports as testReport>
                <tr>
                    <td><small>${testReport.protocolNumber}</small></td>
                    <td><small>${testReport.dateS}</small></td>
                    <td><small><#if testReport.samples[0]??>${testReport.samples[0].objectOfStudy.samplingAuthority.applicant.organization}</#if></small></td>
                    <td><small><#if testReport.samples[0]??>${testReport.samples[0].objectOfStudy.title} ${testReport.samples[0].series} ${testReport.samples[0].objectOfStudy.producer!""}</small></#if></td>
                    &lt;#&ndash;<td><small><#if testReport.samples[0]??><#list testReport.samples[0].norms as norm>${norm.element.title}<#sep>, </#list></#if></small></td>&ndash;&gt;
                    <td><small><#if testReport.samples[0]??><#list testReport.samples[0].sampleNorms as sampleNorm>${sampleNorm.norm.element.title}<#sep>, </#list></#if></small></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>-->
    <div><h5>Протоколы испытаний, включенные в создаваемые акт и протокол:</h5></div>
    <div class="row">
        <table class="table table-sm table-striped">
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
            <#list allTestReports as thisTestReport>
                <tr>
                    <td id="${thisTestReport.id}"><small><b>${thisTestReport.protocolNumber}</b></small></td>
                    <td><small>${thisTestReport.dateS}</small></td>
                    <td><small><#if thisTestReport.samples[0]??>${thisTestReport.samples[0].objectOfStudy.samplingAuthority.applicant.organization}</#if></small></td>
                    <td><small><#list thisTestReport.samples as sample><#if sample??><div class="mb-2">${sample.objectOfStudy.title} ${sample.series} ${sample.objectOfStudy.producer!""}</div></#if></#list></small></td>
                    <td><small><b><#list thisTestReport.samples as sample><#if sample??><div class="mb-4">${sample.cipher}</div></#if></#list></b></small></td>
                    <td><small><#list thisTestReport.samples as sample><#if sample??><div class="mb-4"><#list sample.sampleNorms as sampleNorm><#if sampleNorm.norm.element.symbol??>${sampleNorm.norm.element.symbol}<#else>${sampleNorm.norm.element.title}</#if><#sep>, </#list></div></#if></#list></small></td>
                    <td><small><#list thisTestReport.samples as sample><#if sample??><div class="mb-2">${sample.objectOfStudy.regulatoryDocument.title}</div></#if></#list></small></td>
                    <td><small>${thisTestReport.startDateS}</small></td>
                    <td><small>${thisTestReport.endDateS}</small></td>
                    <#--<td><small>${thisTestReport.testMethod.title}</small></td>-->
                    <td><small><#list thisTestReport.testMethods as testMethod>${testMethod.title}<#sep>; </#list></small></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@cmn.page>