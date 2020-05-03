<#--<#include "security.ftlh">
<#import "login.ftlh" as lgn>-->

<nav class="navbar navbar-expand-lg navbar navbar-light bg-light">
    <a class="navbar-brand" href="/">Lab Assist</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="registerSample" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Регистрация образца
                </a>
                <div class="dropdown-menu" aria-labelledby="registerSample">
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/register_sample")>active</#if>" href="/register_sample" >Начало</a>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/applicants")>active</#if>" href="/applicants" >Заявитель</a>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/sampling_authorities")>active</#if>" href="/sampling_authorities">Орган отбора проб</a>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/objects_of_study")>active</#if>" href="/objects_of_study">Объект исследования</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/fill_test_report")>active</#if>" href="/fill_test_report" >Данные протокола испытаний</a>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/choose_element_and_fill_sample")>active</#if>" href="/choose_element_and_fill_sample">Элементы и образец</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="testReport" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Протокол испытаний
                </a>
                <div class="dropdown-menu" aria-labelledby="testReport">
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/start_test_report")>active</#if>" href="/start_test_report">Начало</a>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/add_results")>active</#if>" href="/add_results" >Результаты анализа</a>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/check_and_print")>active</#if>" href="/check_and_print">Проверка и вывод</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="journals" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Журналы
                </a>
                <div class="dropdown-menu" aria-labelledby="journals">
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/all_samples_in")>active</#if>" href="/all_samples_in">Журнал учета поступивших образцов</a>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/all_samples_out")>active</#if>" href="/all_samples_out">Журнал регистрации протоколов испытаний</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/weather")>active</#if>" href="/weather">Журнал контроля состояния окружающей среды</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/act_and_protocol")>active</#if>" href="/act_and_protocol">Сформировать акт и протокол</a>
            </li>
            <#--<#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользователей</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Профиль</a>
                </li>
            </#if>-->
        </ul>

        <#--<div class="navbar-text mr-3">Вы вошли как ${name}</div>
        <@lgn.logout />-->
    </div>
</nav>