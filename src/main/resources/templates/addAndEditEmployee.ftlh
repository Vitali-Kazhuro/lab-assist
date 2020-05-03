<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<@cmn.page>
    <#--<script>
        function selectFromListAndClick(id) {
            var element = document.getElementById("oneSelect");
            if(id == 0){
                alert("Не нажимайте на шапку таблицы!");
                return;
            }
            element.value = id;
            document.getElementById("chooseButton").click();
        }
        function addRowHandlers() {
            var rows = document.getElementById("allTable").rows;
            for (i = 0; i < rows.length; i++) {
                rows[i].onclick = function(){ return function(){
                    var cell = this.cells[0];
                    var id = cell.id;
                    selectFromListAndClick(id);
                };}(rows[i]);
            }
        }
        window.addEventListener('load', addRowHandlers);
    </script>-->
    <script src="/static/js/script.js"></script>

    <#include "parts/sortTableModalPopup.ftlh">
    <div class="col-lg-6">
        <form method="post" id="employeeForm" action="/addEmployee">
            <h5>Добавить/редактировать сотрудника</h5>
            <table class="table table-sm">
                <thead class="thead-inverse">
                <tr>
                    <th>Фамилия И.О. сотрудника</th>
                    <th>Должность сотрудника</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input class="form-control my-2" type="text" name="name" value="<#if employeeEdit??>${employeeEdit.name}</#if>" placeholder="Фамилия И.О. сотрудника" required/></td>
                    <td><input class="form-control my-2" type="text" name="position" value="<#if employeeEdit??>${employeeEdit.position}</#if>" placeholder="Занимаемая должность" required/></td>
                </tr>
                </tbody>
            </table>
            <button class="btn btn-outline-success my-1" type="submit" <#if employeeEdit??>hidden</#if>>Добавить</button>
            <button class="btn btn-outline-success my-1" formaction="/editEmployee" <#if employeeEdit??><#else>hidden</#if> >Изменить</button>
        </form>
    </div>

    <div class="mt-3 ml-3"><h5>Все сотрудники:</h5></div>
    <div class="form-group row">
        <form method="post" action="/chooseEmployee">
            <div class="input-group">
                <#--<label class="mt-2 ml-4">Сотрудник:</label>-->
                <select class="custom-select ml-5 mb-1" id="oneSelect" name="employeeSelect">
                    <#list allEmployees as employee>
                        <option <#if employeeEdit??><#if employee.id == employeeEdit.id>selected</#if></#if> value="${employee.id}">${employee.name}</option>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-outline-success ml-1 mb-2" type="submit" id="chooseButton">Выбрать</button>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-danger ml-1 mb-2" data-toggle="modal" data-target="#deleteEmployee" <#if employeeEdit??><#else>hidden</#if>>
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
                <th id="0">Фамилия И.О. сотрудника</th>
                <th>Должность сотрудника</th>
            </tr>
            </thead>
            <tbody>
            <#list allEmployees as employee>
                <tr>
                    <td id="${employee.id}">${employee.name}</td>
                    <td>${employee.position}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <!-- Modal -->
    <@del.modalDelete "Employee">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить сотрудника <b>"<#if employeeEdit??>${employeeEdit.name}</#if>"</b>?</h5>
        <div class="ml-3 my-2"><h7><em>Это возможно только, если он не был задействован в каком-либо протоколе испытаний</em></h7></div>
        <input type="hidden" name="employeeId" value="<#if employeeEdit??>${employeeEdit.id}</#if>">
    </@del.modalDelete>
</@cmn.page>
