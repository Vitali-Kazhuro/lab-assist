<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <h4>Перепроверьте выбранные данные:</h4>
    <div class="my-2">
        Протокол испытаний <b>№ ${testReport.protocolNumber}</b>
    </div>
    <div class="my-2">
        от <b>${testReport.dateS}</b>
    </div>
    <div class="my-2">
        Наименование образца: <b>${samplesName}</b>
    </div>
    <div class="my-2">
        Заявитель на проведение испытаний: <b>${testReport.applicant.organization}</b>.
    </div>
    <div class="my-2">
        Адрес заявителя: <b>${ testReport.applicant.address}</b>.
    </div>
    <div class="my-2">
        Акт отбора образцов (проб): <b>${ testReport.samples[0].samplingReport}</b>.
    </div>
    <div class="my-2">
        Вид испытаний: <b>${testType}</b>.
    </div>
    <div class="my-2">
        Наименование ТНПА, устанавливающей методы испытаний: <b><#--${testReport.testMethod.title}-->${testMethods}</b>.
    </div>
    <div class="my-2">
        Количество образца, поступившего на испытания: <b>${quantity}</b>.
    </div>
    <div class="my-2">
        Начало проведения испытаний: <b>${testReport.startDateS}</b>
    </div>
    <div class="my-2">
        Окончание проведения испытаний: <b>${testReport.endDateS}</b>
    </div>
    <div class="my-2">
        Наименование органа, производившего отбор проб на испытания: <b>${testReport.samples[0].objectOfStudy.samplingAuthority.title}</b>.
    </div>
    Условия проведения испытаний:
    <div class="my-2">
        <b>${weather}</b>
    </div>
    <div class="form-group col-lg-12">
        <table class="table table-bordered">
            <thead class="thead-inverse">
            <tr>
                <th>Шифр      </th>
                <th>Наименование образца</th>
                <th>Наименование элемента</th>
                <th>Нормированное значение показателей, установленных в ТНПА${testReport.notGreater}${testReport.units}</th>
                <th>Фактическое значение содержания${testReport.units}</th>
            </tr>
            </thead>
            <tbody>
            <#list resultTableRowList as resultTableRow>
                <tr>
                    <td>${resultTableRow.cipher}</td>
                    <td>${resultTableRow.sampleName}</td>
                    <td>${resultTableRow.elementNames?replace("\n", "<br>")?no_esc}</td>
                    <td>${resultTableRow.elementNorms?replace("\n", "<br>")?no_esc}</td>
                    <td>${resultTableRow.elementResults?replace("null", "")?replace("\n", "<br>")?no_esc}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div class="my-2">
        Испытание провели:
    </div class="my-2">
    <#list testReport.performers as performer>
        <div class="my-2"><b>${performer.position}  ${performer.name}</b></div>
    </#list>
    <div>
        <b>${conclusionTitle}</b>
    </div>
    <div>
        ${conclusion?replace("\r\n", "<br>")?no_esc}
    </div>

    <div class="form-group row my-3">
        <form method="post" action="/checkAndPrint">
            <button class="btn btn-outline-success ml-2" type="submit">Вывести на сервер</button>
            <button class="btn btn-primary mr-2" formmethod="get" formaction="/downloadTestProtocol">Скачать</button>
            Имя файла: <b>${fileName}</b>
        </form>
    </div>

</@cmn.page>