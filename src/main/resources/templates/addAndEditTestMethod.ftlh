<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<@cmn.page>
    <script src="/static/js/script.js"></script>
    <#include "parts/sortTableModalPopup.ftlh">
    <div class="col-lg-6">
        <form method="post" id="testMethodForm" action="/addTestMethod">
            <h5>Добавить/редактировать ТНПА методов испытаний</h5>
            <table class="table table-sm">
                <thead class="thead-inverse">
                <tr>
                    <th>Название</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input class="form-control my-2" type="text" name="title" value="<#if testMethodEdit??>${testMethodEdit.title}</#if>" placeholder="Введите название" required/></td>
                </tr>
                </tbody>
            </table>
            <button class="btn btn-outline-success my-1" type="submit" <#if testMethodEdit??>hidden</#if>>Добавить</button>
            <button class="btn btn-outline-success my-1" formaction="/editTestMethod" <#if testMethodEdit??><#else>hidden</#if> >Изменить</button>
        </form>
    </div>
    <div class="mt-3 ml-3"><h5>Все ТНПА:</h5></div>
    <div class="form-group row">
        <form method="post" action="/chooseTestMethod">
            <div class="input-group">
                <select class="custom-select ml-5 mb-1" id="oneSelect" name="testMethodSelect">
                    <#list allTestMethods as testMethod>
                        <option <#if testMethodEdit??><#if testMethod.id == testMethodEdit.id>selected</#if></#if> value="${testMethod.id}" data-toggle="tooltip" title="${testMethod.title}">${testMethod.title?truncate(50,"...")}</option>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-outline-success ml-1 mb-2" type="submit" id="chooseButton">Выбрать</button>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-danger ml-1 mb-2" data-toggle="modal" data-target="#deleteTestMethod" <#if testMethodEdit??><#else>hidden</#if>>
                        Удалить
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="col-lg-6">
        <table class="table table-bordered table-hover" id="allTable">
            <thead class="thead-inverse">
            <tr>
                <th id="0">Название</th>
            </tr>
            </thead>
            <tbody>
            <#list allTestMethods as testMethod>
                <tr>
                    <td id="${testMethod.id}">${testMethod.title}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <!-- Modal -->
    <@del.modalDelete "TestMethod">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить ТНПА <b>"<#if testMethodEdit??>${testMethodEdit.title}</#if>"</b>?</h5>
        <div class="ml-3 my-2"><h6><em>Это возможно только, если он не был задействован в каком-либо протоколе испытаний</em></h6></div>
        <input type="hidden" name="testMethodId" value="<#if testMethodEdit??>${testMethodEdit.id}</#if>">
    </@del.modalDelete>
</@cmn.page>
