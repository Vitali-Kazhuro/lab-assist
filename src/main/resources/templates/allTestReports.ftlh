<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<#import "parts/pager.ftlh" as pgr>
<@cmn.page>
    <#--<style>
        .table > thead > tr > th {
            vertical-align: middle;
        }
    </style>-->
    <div class="form-group row">
        <form method="post" id="chooseTestReportForm" action="/changeTestReport">
            <div class="mx-2 my-3"><h4>Все протоколы испытаний:</h4></div>
            <div class="input-group">
                <label class="ml-2 mt-2">Протокол №</label>
                <select class="custom-select ml-2 mb-1" id="oneSelect" name="testReportSelect">
                    <#--<#list allTestReports as testReport>-->
                    <#list allTestReportsPage.content as testReport>
                        <option <#if editTestReport??><#if editTestReport.id == testReport.id>selected</#if></#if> value="${testReport.id}">${testReport.protocolNumber}</option>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-outline-success ml-1 mb-2" id="chooseButton">Выбрать</button>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-danger ml-1 mb-2" data-toggle="modal" data-target="#deleteTestReport" <#if editTestReport??><#else>hidden</#if>>
                        Удалить
                    </button>
                    <a class="btn btn-outline-secondary ml-1 mb-2" href="/fill_test_report" role="button">Назад</a>
                </div>
            </div>
        </form>
    </div>
    <@pgr.pager "all_test_reports" allTestReportsPage />
    <#include "parts/allTestReportsTable.ftlh">
    <!-- Modal -->
    <@del.modalDelete "TestReport">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить протокол испытаний № <b><#if editTestReport??>${editTestReport.protocolNumber}</#if></b>?</h5></div>
        <div class="ml-3 my-2"><h7><em>Это удалит образец(-цы), связанный(-ые) с данным протоколом!</em></h7></div>
        <input type="hidden" name="testReportId" value="<#if editTestReport??>${editTestReport.id}</#if>">
    </@del.modalDelete>
</@cmn.page>