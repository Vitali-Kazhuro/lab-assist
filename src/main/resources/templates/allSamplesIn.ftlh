<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
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

    <a style="text-decoration: none; color: #000;" data-toggle="collapse" href="#chooseSample" role="button" aria-expanded="false" aria-controls="chooseSample">
        <h6>Журнал учета образцов, поступивших на испытания 04 ЖО 2.48 (РК приложение Я)</h6>
    </a>
    <div class="collapse show" id="chooseSample">
        <form method="post" id="chooseSampleForm" action="/chooseSample">
            <div class="form-row">
                <div class="form-group col-md-2">
                    <label for="oneSelect" class="mt-1">Выбрать образец Шифр:</label>
                    <select class="custom-select mb-2" id="oneSelect" name="sampleSelect">
                        <#--<#list allSamples as sample>-->
                        <#list allSamplesPage.content as sample>
                            <option <#if editSample??><#if editSample.id == sample.id>selected</#if></#if> value="${sample.id}">${sample.cipher}</option>
                        </#list>
                    </select>
                    <div class="form-group">
                        <button class="btn btn-outline-success" id="chooseButton" type="submit" >Выбрать</button>
                        <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#deleteSample" <#if editSample??><#else>hidden</#if>>
                            Удалить
                        </button>
                    </div>
                </div>
            </div>
        </form>
        <@pgr.pager "all_samples_in" allSamplesPage />
    </div>
    <!-- Modal -->
    <@del.modalDelete "Sample">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить образец Шифр <b><#if editSample??>${editSample.cipher}</#if></b>?</h5></div>
        <input type="hidden" name="sampleId" value="<#if editSample??>${editSample.id}</#if>">
    </@del.modalDelete>

    <div class="row">
        <table class="table table-sm table-striped table-bordered table-hover" id="allTable">
            <thead class="thead-inverse">
            <tr>
                <#--<th><small><b>№ п/п</b></small></th>
                <th><small><b>Дата поступ- ления</b></small></th>
                <th><small><b>Наименование образца</b></small></th>
                <th><small><b>Шифр образца</b></small></th>
                <th><small><b>Количество поступившего</b></small></th>
                <th width="30"><small><b>Отметка о сохран- ности тары</b></small></th>
                <th><small><b>Наименование номер и дата сопроводительного документа</b></small></th>
                <th><small><b>Заявленные виды работ</b></small></th>
                <th width="40"><small><b>Сотрудник получивший образец (ФИО, подпись, дата)</b></small></th>
                <th width="30"><small><b>№ прото- кола испы- таний</b></small></th>
                <th><small><b>Начало испытаний</b></small></th>
                <th><small><b>Окончание испытаний</b></small></th>
                <th><small><b>Отметка об утили- зации</b></small></th>-->
                <th id="0">№ п/п</th>
                <th>Дата поступ- ления</th>
                <th>Наименование образца</th>
                <th>Шифр образца</th>
                <th width="30">Коли- чество посту- пившего</th>
                <th width="30">Отметка о сохран- ности тары</th>
                <th width="200">Наименование, номер и дата сопроводительного документа</th>
                <th width="30">Заяв- ленные виды работ</th>
                <th width="40">Сотрудник получивший образец (ФИО, подпись, дата)</th>
                <th width="30">№ прото- кола испы- таний</th>
                <th>Начало испытаний</th>
                <th>Окончание испытаний</th>
                <th width="30">Отметка об утили- зации</th>
            </tr>
            </thead>
            <tbody>
            <#--<#assign x = allSamplesPage.getNumber()*allSamplesPage.getSize()>-->
            <#assign x = 1 + allSamplesPage.getTotalElements() - (allSamplesPage.getNumber()*allSamplesPage.getSize())>
            <#--<#list allSamples as sample>-->
            <#list allSamplesPage.content as sample>
                <tr>
                    <td id="${sample.id}"><small><b><#assign x-->${x}</b></small></td>
                    <td><small>${sample.testReport.startDateS}</small></td>
                    <td><small>${sample.objectOfStudy.title} ${sample.series} ${sample.objectOfStudy.producer!""}</small></td>
                    <#--<td><button type="submit" form="chooseSampleForm" class="btn btn-link" onclick="selectSample(${sample.id})"><small><b>${sample.cipher}</b></small></button></td>-->
                    <td><small><b>${sample.cipher}</b></small></td>
                    <td><small>${sample.quantity}</small></td>
                    <td><small>Сохр</small></td>
                    <td><small>${sample.samplingReport}<div>${sample.testReport.applicant.organization}</div></small></td>
                    <td><small><#list sample.sampleNorms as sampleNorm><#if sampleNorm.norm.element.symbol??>${sampleNorm.norm.element.symbol}<#else>${sampleNorm.norm.element.title}</#if><#sep>, </#list></small></td>
                    <td></td>
                    <td><small>${sample.testReport.protocolNumber}</small></td>
                    <td><small>${sample.testReport.startDateS}</small></td>
                    <td><small>${sample.testReport.endDateS}</small></td>
                    <td></td>
                </tr>
            </#list>
            <#--<#list allTestReports as testReport>
                <tr>
                    <td><small><b><#assign x=x+1> ${x}</b></small></td>
                    <td><small>${testReport.startDateS}</small></td>
                    <td><small><#list testReport.samples as sample><#if sample??><div class="mb-1">${sample.objectOfStudy.title} ${sample.series} ${sample.objectOfStudy.producer!""}</div></#if></#list></small></td>
                    <td><small><#list testReport.samples as sample><#if sample??><div class="mb-5"><button type="submit" form="chooseSampleForm" class="btn btn-link" onclick="selectSample(${sample.id})"><small><b>${sample.cipher}</b></small></button></div></#if></#list></small></td>
                    <td><small><#if testReport.samples[0]??>${testReport.samples[0].quantity}</#if></small></td>
                    <td><small><#list testReport.samples as sample><#if sample??><div class="mb-5">Сохр</div></#if></#list></small></td>
                    <td><small><#if testReport.samples[0]??>${testReport.samples[0].samplingReport}</#if></small></td>
                    <td><small><#list testReport.samples as sample><#if sample??><div class="mb-4"><#list sample.sampleNorms as sampleNorm>${sampleNorm.norm.element.symbol}<#sep>, </#list></div></#if></#list></small></td>
                    <td></td>
                    <td><small>${testReport.protocolNumber}</small></td>
                    <td><small>${testReport.startDateS}</small></td>
                    <td><small>${testReport.endDateS}</small></td>
                    <td></td>
                </tr>
            </#list>-->
            </tbody>
        </table>
    </div>
    <#include "parts/sortTableModalPopup.ftlh">
</@cmn.page>