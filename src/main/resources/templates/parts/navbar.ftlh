<#include "security.ftlh">
<#import "login.ftlh" as lgn>

<nav class="navbar navbar-expand-lg navbar navbar-light bg-light">
    <a class="navbar-brand" href="/">Lab Assist</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="registerSample" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Регистрация образца
                    </a>
                    <div class="dropdown-menu" aria-labelledby="registerSample">
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/registerSample")>active</#if>" href="/registerSample" >Начало</a>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/applicants")>active</#if>" href="/applicants" >Заявитель</a>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/samplingAuthorities")>active</#if>" href="/samplingAuthorities">Орган отбора проб</a>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/objectsOfStudy")>active</#if>" href="/objectsOfStudy">Объект исследования</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/fillTestReport")>active</#if>" href="/fillTestReport" >Данные протокола испытаний</a>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/chooseElementAndFillSample")>active</#if>" href="/chooseElementAndFillSample">Элементы и образец</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="testReport" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Протокол испытаний
                    </a>
                    <div class="dropdown-menu" aria-labelledby="testReport">
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/startTestReport")>active</#if>" href="/startTestReport">Начало</a>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/addResults")>active</#if>" href="/addResults" >Результаты анализа</a>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/checkAndPrint")>active</#if>" href="/checkAndPrint">Проверка и вывод</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="journals" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Журналы
                    </a>
                    <div class="dropdown-menu" aria-labelledby="journals">
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/allSamplesIn")>active</#if>" href="/allSamplesIn">Журнал учета поступивших образцов</a>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/allSamplesOut")>active</#if>" href="/allSamplesOut">Журнал регистрации протоколов испытаний</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item <#if springMacroRequestContext.requestUri?contains("/weather")>active</#if>" href="/weather">Журнал контроля состояния окружающей среды</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link <#if springMacroRequestContext.requestUri?contains("/actAndProtocol")>active</#if>" href="/actAndProtocol">Сформировать акт и протокол</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Профиль</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользователей</a>
                </li>
            </#if>
        </ul>

        <#if known>
            <div class="navbar-text mr-3">Вы вошли как ${name}</div>
            <@lgn.logout />
        </#if>
    </div>
</nav>