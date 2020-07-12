<#import "parts/common.ftlh" as cmn>
<#include "parts/security.ftlh">
<@cmn.page>
    <h3>Добрый день, ${name}!</h3>
    <div class="mt-4">
        <h6>Для начала, заполните журнал контроля состояния окружающей среды за сегодняшнюю дату:</h6>
    </div>
    <div>
        <a href="/weather">• Журнал контроля состояния окружающей среды</a>
    </div>
    <div class="mt-4">
        <h6>Регистрацию нового образца следует начинать отсюда:</h6>
    </div>
    <div>
        <a href="/registerSample">• Регистрация нового образца</a>
    </div>
    <div class="mt-4">
        <h6>Сформировать протокол испытаний для нового или уже зарегистрированного образца можно здесь:</h6>
    </div>
    <div>
        <a href="/startTestReport">• Сформировать протокол испытаний</a>
    </div>
    <#if isAdmin>
        <div class="mx-2 my-2"><h5>Администрирование:</h5></div>
        <div>
            <a href="addAndEditElement">• Добавить/редактировать элемент</a>
        </div>
        <div>
            <a href="/addAndEditEmployee">• Добавить/редактировать сотрудника</a>
        </div>
        <div>
            <a href="/addAndEditTestMethod">• Добавить/редактировать ТНПА методов испытаний</a>
        </div>
        <div>
            <a href="/editRegulatoryDocument">• Добавить/редактировать нормативный документ</a>
        </div>
        <form method="post" action="/shutdown">
            <button class="btn btn-outline-danger btn-sm mt-4" type="submit">Остановить программу</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </#if>
</@cmn.page>