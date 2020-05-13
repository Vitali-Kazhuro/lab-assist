<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<@cmn.page>
    <script src="/static/js/script.js"></script>
    <#include "parts/sortTableModalPopup.ftlh">
    <div class="col-lg-6">
        <form method="post" id="elementForm" action="/addElement">
            <h5>Добавить/редактировать элемент</h5>
            <table class="table">
                <thead class="thead-inverse">
                <tr>
                    <th>Название</th>
                    <th>Символ</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input class="form-control my-2" type="text" name="title" value="<#if elementEdit??>${elementEdit.title}</#if>" placeholder="Название с маленькой буквы" required/></td>
                    <td><input class="form-control my-2" type="text" name="symbol" value="<#if elementEdit??>${elementEdit.symbol}</#if>" placeholder="Символ элемента" /></td>
                </tr>
                </tbody>
            </table>
            <button class="btn btn-outline-success my-1" type="submit" <#if elementEdit??>hidden</#if>>Добавить</button>
            <button class="btn btn-outline-success my-1" formaction="/editElement" <#if elementEdit??><#else>hidden</#if> >Изменить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div class="mt-3 ml-3"><h5>Все элементы:</h5></div>
    <div class="form-group row">
        <form method="post" action="/chooseElement">
            <div class="input-group">
                <select class="custom-select ml-5 mb-1" id="oneSelect" name="elementSelect">
                    <#list allElements as element>
                        <option <#if elementEdit??><#if element.id == elementEdit.id>selected</#if></#if> value="${element.id}">${element.title}</option>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-outline-success ml-1 mb-2" type="submit" id="chooseButton">Выбрать</button>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-danger ml-1 mb-2" data-toggle="modal" data-target="#deleteElement" <#if elementEdit??><#else>hidden</#if>>
                        Удалить
                    </button>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div class="col-lg-6">
        <table class="table table-bordered table-hover" id="allTable">
            <thead class="thead-inverse">
            <tr>
                <th id="0">Название</th>
                <th>Символ</th>
            </tr>
            </thead>
            <tbody>
            <#list allElements as element>
                <tr>
                    <td id="${element.id}">${element.title}</td>
                    <td>${element.symbol}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <!-- Modal -->
    <@del.modalDelete "Element">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить элемент <b>"<#if elementEdit??>${elementEdit.title}</#if>"</b>?</h5>
        <div class="ml-3 my-2"><h6><em>Это возможно только, если он не был задействован в каком-либо протоколе испытаний</em></h6></div>
        <input type="hidden" name="elementId" value="<#if elementEdit??>${elementEdit.id}</#if>">
    </@del.modalDelete>
</@cmn.page>
