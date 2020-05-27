<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<#import "parts/pager.ftlh" as pgr>
<@cmn.page>
    <style>
        .table > thead > tr > th {
            vertical-align: middle;
        }
        @media print {
            @page {size: auto}
        }
    </style>
    <script src="/static/js/script.js"></script>

    <a style="text-decoration: none; color: #000;" data-toggle="collapse" href="#addWeather" role="button" aria-expanded="false" aria-controls="addWeather">
        <h6>Журнал контроля состояния окружающей среды в помещениях испытательной лаборатории</h6>
    </a>
    <div class="collapse show" id="addWeather">
        <form method="post" id="addWeatherForm" action="/addWeather">
            <div class="form-row">
                <div class="form-group col-md-2 mt-4">
                    <label for="date"><b>Дата контроля</b></label>
                    <input class="form-control" id="date" type="date" name="date" <#if editWeather??>value="${editWeather.date}"</#if> required>
                    <label for="oneSelect" class="mt-5">Редактировать:</label>
                    <select class="custom-select mb-2" id="oneSelect" name="weatherSelect">
                        <#list weatherPage.content as weather>
                            <option <#if editWeather??><#if editWeather.id == weather.id>selected</#if></#if> value="${weather.id}">${weather.dateS}</option>
                        </#list>
                    </select>
                    <div class="form-group">
                        <button class="btn btn-outline-success my-1" id="chooseButton" formaction="/chooseWeather" type="submit" formnovalidate>Выбрать</button>
                    </div>
                </div>
                <div class="form-group col-md-2 ml-5 mr-1">
                    <div><small><b>Температура окруж. воздуха, °С</b></small></div>
                    <label for="k53_10_temperature"><small><em>Комната 53-54</em> 10:00</small></label>
                    <input class="form-control form-control-sm" id="k53_10_temperature" type="number" min="1" step="0.1" name="k53_10_temperature" <#if editWeather??>value="${editWeather.k53_10_temperature?replace(",", ".")}"</#if> value="20" required>
                    <label for="k53_16_temperature"><small><em>Комната 53-54</em> 16:00</small></label>
                    <input class="form-control form-control-sm" id="k53_16_temperature" type="number" min="1" step="0.1" name="k53_16_temperature"<#if editWeather??>value="${editWeather.k53_16_temperature?replace(",", ".")}"</#if> value="20" required>
                    <label for="k42_10_temperature"><small><em>Комната 42</em> 10:00</small></label>
                    <input class="form-control form-control-sm" id="k42_10_temperature" type="number" min="1" step="0.1" name="k42_10_temperature" <#if editWeather??>value="${editWeather.k42_10_temperature?replace(",", ".")}"</#if> value="20" required>
                    <label for="k42_16_temperature"><small><em>Комната 42</em> 16:00</small></label>
                    <input class="form-control form-control-sm" id="k42_16_temperature" type="number" min="1" step="0.1" name="k42_16_temperature" <#if editWeather??>value="${editWeather.k42_16_temperature?replace(",", ".")}"</#if> value="20" required>
                </div>
                <div class="form-group col-md-2 ml-5 mr-1">
                    <div><small><b>Относительная влажн. воздуха, %</b></small></div>
                    <label for="k53_10_humidity"><small><em>Комната 53-54</em> 10:00</small></label>
                    <input class="form-control form-control-sm" id="k53_10_humidity" type="number" min="1" step="0.1" name="k53_10_humidity" <#if editWeather??>value="${editWeather.k53_10_humidity?replace(",", ".")}"</#if> value="40" required>
                    <label for="k53_16_humidity"><small><em>Комната 53-54</em> 16:00</small></label>
                    <input class="form-control form-control-sm" id="k53_16_humidity" type="number" min="1" step="0.1" name="k53_16_humidity" <#if editWeather??>value="${editWeather.k53_16_humidity?replace(",", ".")}"</#if> value="40" required>
                    <label for="k42_10_humidity"><small><em>Комната 42</em> 10:00</small></label>
                    <input class="form-control form-control-sm" id="k42_10_humidity" type="number" min="1" step="0.1" name="k42_10_humidity" <#if editWeather??>value="${editWeather.k42_10_humidity?replace(",", ".")}"</#if> value="40" required>
                    <label for="k42_16_humidity"><small><em>Комната 42</em> 16:00</small></label>
                    <input class="form-control form-control-sm" id="k42_16_humidity" type="number" min="1" step="0.1" name="k42_16_humidity" <#if editWeather??>value="${editWeather.k42_16_humidity?replace(",", ".")}"</#if> value="40" required>
                </div>
                <div class="form-group col-md-2 ml-5 mr-1">
                    <div><small><b>Атмосферное давление, мм рт. ст</b></small></div>
                    <label for="k53_10_pressure"><small><em>Комната 53-54</em> 10:00</small></label>
                    <input class="form-control form-control-sm" id="k53_10_pressure" type="number" min="1" step="1" name="k53_10_pressure" <#if editWeather??>value="${editWeather.k53_10_pressure}"</#if> value="750" required>
                    <label for="k53_16_pressure"><small><em>Комната 53-54</em> 16:00</small></label>
                    <input class="form-control form-control-sm" id="k53_16_pressure" type="number" min="1" step="1" name="k53_16_pressure" <#if editWeather??>value="${editWeather.k53_16_pressure}"</#if> value="750" required>
                    <label for="k42_10_pressure"><small><em>Комната 42</em> 10:00</small></label>
                    <input class="form-control form-control-sm" id="k42_10_pressure" type="number" min="1" step="1" name="k42_10_pressure" <#if editWeather??>value="${editWeather.k42_10_pressure}"</#if> value="750" required>
                    <label for="k42_16_pressure"><small><em>Комната 42</em> 16:00</small></label>
                    <input class="form-control form-control-sm" id="k42_16_pressure" type="number" min="1" step="1" name="k42_16_pressure" <#if editWeather??>value="${editWeather.k42_16_pressure}"</#if> value="750" required>
                </div>
                <div class="form-group col-md-2 mt-5">
                    <div>
                        <button class="btn btn-primary mx-2 my-1" type="submit" <#if editWeather??>disabled</#if>>Добавить</button>
                    </div>
                    <div>
                        <button class="btn btn-primary mx-2 my-1" formaction="/editWeather" type="submit" <#if editWeather??><#else>disabled</#if>>Изменить</button>
                    </div>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-danger mx-2 my-1" data-toggle="modal" data-target="#deleteWeather" <#if editWeather??><#else>hidden</#if>>
                        Удалить
                    </button>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
        <@pgr.pager "weather" weatherPage />
    </div>

    <div class="row">
        <table class="table table-sm table-striped table-bordered table-hover" id="allTable">
            <thead class="thead-inverse">
            <tr>
                <th id="0"><small><b>Дата контроля</b></small></th>
                <th><small><b>Время контроля</b></small></th>
                <th><small><b>Температура окружающего воздуха, °С</b></small></th>
                <th><small><b>Относительная влажность воздуха, %</b></small></th>
                <th><small><b>Атмосферное давление, мм рт. ст</b></small></th>
                <th><small><b>Подпись сотрудника, проводившего контроль</b></small></th>
            </tr>
            </thead>
            <tbody>
            <#list weatherPage.content as weather>
                <tr>
                    <td id="${weather.id}" hidden>${weather.id}</td>
                    <td><small><b>${weather.dateS}</b></small></td>
                    <td><small>
                            <div>Комната 53-54</div>
                            <div>10:00</div>
                            <div>16:00</div>
                            <div>Комната 42</div>
                            <div>10:00</div>
                            <div>16:00</div>
                        </small></td>
                    <td><small>
                            <div> </div>
                            <div>${weather.k53_10_temperature}</div>
                            <div>${weather.k53_16_temperature}</div>
                            <div> </div>
                            <div>${weather.k42_10_temperature}</div>
                            <div>${weather.k42_16_temperature}</div>
                        </small></td>
                    <td><small>
                            <div> </div>
                            <div>${weather.k53_10_humidity}</div>
                            <div>${weather.k53_16_humidity}</div>
                            <div> </div>
                            <div>${weather.k42_10_humidity}</div>
                            <div>${weather.k42_16_humidity}</div>
                        </small></td>
                    <td><small>
                            <div> </div>
                            <div>${weather.k53_10_pressure}</div>
                            <div>${weather.k53_16_pressure}</div>
                            <div> </div>
                            <div>${weather.k42_10_pressure}</div>
                            <div>${weather.k42_16_pressure}</div>
                        </small></td>
                    <td></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <!-- Modal -->
    <@del.modalDelete "Weather">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить запись метеоданных за <b>"<#if editWeather??>${editWeather.dateS!""}</#if>"</b>?</h5></div>
        <input type="hidden" name="editWeatherId" value="<#if editWeather??>${editWeather.id}</#if>">
    </@del.modalDelete>
    <#include "parts/sortTableModalPopup.ftlh">
</@cmn.page>