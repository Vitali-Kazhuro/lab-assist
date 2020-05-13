<#import "parts/common.ftlh" as cmn>
<#include "parts/security.ftlh">
<@cmn.page>
    <h3>Добрый день, имя_пользователя!</h3>
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
        <a href="/register_sample">• Регистрация нового образца</a>
    </div>
    <div class="mt-4">
        <h6>Сформировать протокол испытаний для нового или уже зарегистрированного образца можно здесь:</h6>
    </div>
    <div>
        <a href="/start_test_report">• Сформировать протокол испытаний</a>
    </div>
    <#if isAdmin>
        <div class="mx-2 my-2"><h5>Администрирование:</h5></div>
        <div>
            <a href="add_and_edit_element">• Добавить/редактировать элемент</a>
        </div>
        <div>
            <a href="/add_and_edit_employee">• Добавить/редактировать сотрудника</a>
        </div>
        <div>
            <a href="/add_and_edit_test_method">• Добавить/редактировать ТНПА методов испытаний</a>
        </div>
        <div>
            <a href="/edit_regulatory_document">• Добавить/редактировать нормативный документ</a>
        </div>
    </#if>
</@cmn.page>