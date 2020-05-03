<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <h3>Формирование протокола испытаний</h3>
    <#if testReport?? && sample??>
        <div class="my-3">
            <h6>В данный момент выбран протокол испытаний №${testReport.protocolNumber} от ${testReport.dateS}</h6>
        </div>
        <div class="my-2">
            Заявитель: <b>${applicant.organization}</b>
        </div>
        <div class="my-2">
            Объект исследования: <b>${objectOfStudy.title}</b>
        </div>
        <div class="my-2">
            Начало проведения испытаний: <b>${testReport.startDateS}</b>
        </div>
        <div class="my-2">
            Окончание проведения испытаний: <b>${testReport.endDateS}</b>
        </div>
        <div class="my-3">
            <h6>Чтобы сформировать данный протокол, нажмите "Далее"</h6>
        </div>
        <div class="my-3">
            <h6>Чтобы изменить данные данного протокола, нажмите "Изменить"</h6>
        </div>
        <div class="my-3">
            <h6>Чтобы выбрать другой образец и другой протокол перейдите в <a href="/all_samples_in">Журнал учёта поступивших образцов</a></h6>
        </div>
        <div class="row">
            <a class="btn btn-outline-primary ml-1" href="/fill_test_report" role="button">Изменить</a>
            <a class="btn btn-primary ml-1" href="/add_results" role="button">Далее</a>
        </div>
    <#else>
        <div class="my-3">
            <h6>В данный момент не выбрано протокола испытаний.</h6>
        </div>
        <div class="my-3">
            <h6>Чтобы выбрать образец и сформировать протокол испытаний перейдите в <a href="/all_samples_in">Журнал учёта поступивших образцов</a></h6>
        </div>
    </#if>
</@cmn.page>