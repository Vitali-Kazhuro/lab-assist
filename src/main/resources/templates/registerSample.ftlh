<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <h3>Регистрация образца</h3>
    <div class="my-3">
        <h6>Для регистрации нового образца, нажмите кнопку "Новый образец"</h6>
    </div>
    <div class="my-3">
        <h6>Для выбора образца из уже зарегестрированных пройдите в <a href="/all_samples_in">Журнал учёта поступивших образцов</a></h6>
    </div>
    <div class="my-3">
        <h6>Для изменения текущего образца нажмите кнопку "Далее"</h6>
    </div>
    <div class="form-group row">
        <form method="post" action="/newSample">
            <button class="btn btn-success ml-2">Новый образец</button>
            <a class="btn btn-outline-primary" href="/applicants" role="button">Далее</a>
        </form>
    </div>
</@cmn.page>