<style>
    .table > thead > tr > th {
        vertical-align: middle;
    }
</style>

<script src="/static/js/script.js"></script>

<div class="row">
    <table class="table table-sm table-striped table-hover" id="allTable">
        <thead class="thead-inverse">
        <tr>
            <th id="0">№</th>
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
        <#list allTestReportsPage.content as thisTestReport>
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
                <td><small><#list thisTestReport.testMethods as testMethod>${testMethod.title}<#sep>; </#list></small></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<#include "sortTableModalPopup.ftlh">