<#import "parts/common.ftlh" as cmn>
<#import "parts/pager.ftlh" as pgr>
<@cmn.page>
    <style>
        .table > thead > tr > th {
            vertical-align: middle;
        }
        @media print {
            @page {size: auto}
        }
    </style>
    <script src="/static/js/script.js"></script>
    <#include "parts/sortTableModalPopup.ftlh">
    <a style="text-decoration: none; color: #000;" data-toggle="collapse" href="#chooseSample" role="button" aria-expanded="false" aria-controls="chooseSample">
    <#--<div class="my-1">--><h6>Журнал регистрации протоколов испытаний, выдаваемых ИЛ НПООО "Белинтеланалит" 04 ЖР 2.19 (РК приложение 5)</h6><#--</div>-->
    </a>
    <div class="collapse show" id="chooseSample">
        <@pgr.pager "all_samples_out" allTestReportsPage />
    </div>
    <div class="row">
        <table class="table table-sm table-striped table-bordered" id="allTable">
            <thead class="thead-inverse">
            <tr>
                <th id="0">№ п/п</th>
                <th>Дата регистрации протокола</th>
                <th>Наименование поступившего образца</th>
                <th>Количество поступившего</th>
                <th>Наименование организации заказчика</th>
                <th>Номер и дата договора(гарантийного письма)</th>
                <th>Номер и дата сопроводительного документа</th>
                <th width="40">Сотрудник оформивший протокол (ФИО)</th>
                <th width="40">Предста- витель заказчика (ФИО, дата, подпись)</th>
                <th width="30">Номер прото- кола</th>
            </tr>
            </thead>
            <tbody>
            <#--<#assign x = 0>-->
            <#assign x = 1 + allTestReportsPage.getTotalElements() - (allTestReportsPage.getNumber()*allTestReportsPage.getSize())>
            <#--<#list allSamples as sample>
                <tr>
                    <td><small><b><#assign x=x+1> ${x}</b></small></td>
                    <td><small>${sample.testReport.dateS}</small></td>
                    <td><small>${sample.objectOfStudy.title} ${sample.series} ${sample.objectOfStudy.producer!""}</small></td>
                    <td><small>${sample.quantity}</small></td>
                    <td><small>${sample.objectOfStudy.samplingAuthority.applicant.organization}</small></td>
                    <td><small>Договор ${sample.objectOfStudy.samplingAuthority.applicant.contractNumber} от ${sample.objectOfStudy.samplingAuthority.applicant.contractDateS}г.</small></td>
                    <td><small>${sample.samplingReport}</small></td>
                    <td><small>${sample.testReport.performers[0].name}</small></td>
                    <td></td>
                    <td><small>${sample.testReport.protocolNumber}</small></td>
                </tr>
            </#list>-->
            <#--<#list allTestReports as testReport>-->
            <#list allTestReportsPage.content as testReport>
                <tr>
                    <td><small><b><#assign x--> ${x}</b></small></td>
                    <td><small>${testReport.dateS}</small></td>
                    <td><small><#list testReport.samples as sample><#if sample??><div class="mb-1">${sample.objectOfStudy.title} ${sample.series} ${sample.objectOfStudy.producer!""}</div></#if></#list></small></td>
                    <td><small><#if testReport.samples[0]??>${testReport.samples[0].quantity}</#if></small></td>
                    <td><small><#if testReport.samples[0]??>${testReport.samples[0].objectOfStudy.samplingAuthority.applicant.organization}</#if></small></td>
                    <td><small>Договор <#if testReport.samples[0]??>${testReport.samples[0].objectOfStudy.samplingAuthority.applicant.contractNumber} от ${testReport.samples[0].objectOfStudy.samplingAuthority.applicant.contractDateS}г.</#if></small></td>
                    <td><small><#if testReport.samples[0]??>${testReport.samples[0].samplingReport}</#if></small></td>
                    <td><small>${testReport.performers[0].name}</small></td>
                    <td></td>
                    <td><small>${testReport.protocolNumber}</small></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@cmn.page>