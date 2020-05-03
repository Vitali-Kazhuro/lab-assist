<#import "../parts/common.ftlh" as cmn>
<@cmn.page>
    <h2>Упс... ошибочка вышла...</h2>
    <h5>Данные погоды для дат этого протокола исследований не были заполнены в журнал контроля состояния окружающей среды</h5>
    <div class="mt-4">
        <h6>Заполните  <a href="/weather">журнал контроля состояния окружающей среды</a> с ${testReport.startDateS!""} по ${testReport.endDateS!""}</h6>
    </div>
</@cmn.page>