<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <div class="form-group row">
        <form id="elementAndSampleForm" method="post" action="/chooseElementAndFillSample">
            <div class="ml-3 mb-3"><h4>Детали исследования</h4></div>
            <div class="ml-3 mb-3">На какие элементы будем исследовать?</div>
            <div>
                <#list allNorms as norm>
                    <div>
                        <label><input class="my-1" type="checkbox" <#if normList??><#list normList as checkedNorm ><#if checkedNorm.id == norm.id>checked</#if></#list></#if> name="${norm.element.title}"> ${norm.element.title}</label>
                    </div>
                </#list>
                <br>
                <div class="ml-3 mb-3">Ещё немного про образец: </div>
                <div class="form-group row">
                    <label class="col-sm-6 col-form-label">Шифр</label>
                    <div class="col-sm-6">
                        <input class="form-control" type="text" name="cipher" placeholder="дд-мм-нп/п-исп.гр" value="<#if sample??>${sample.cipher}</#if>" required/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-6 col-form-label">Серия</label>
                    <div class="col-sm-6">
                        <input class="form-control" type="text" name="series" placeholder="Необязательное" value="<#if sample??>${sample.series!""}</#if>"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-6 col-form-label">Акт отбора проб</label>
                    <div class="col-sm-6">
                        <input class="form-control" type="text" name="samplingReport" placeholder="Полностью" value="<#if sample??>${sample.samplingReport}</#if>"required/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-6 col-form-label">Количество образца</label>
                    <div class="col-sm-6">
                        <input class="form-control" type="text" name="quantity" placeholder="поступивш. на исп." value="<#if sample??>${sample.quantity}</#if>"required/>
                    </div>
                </div>
                <div>
                    <button class="btn btn-primary ml-2 mb-2" <#if sample??>disabled</#if> type="submit">Добавить</button>
                    <button class="btn btn-primary mb-2" <#if sample??><#else>disabled</#if> formaction="/editElementAndSample">Изменить</button>
                    <button class="btn btn-outline-success mb-2" <#if sample??><#else>disabled</#if> formmethod="get" formaction="/add_results">Ввести результаты</button>
                    <a class="btn btn-secondary mb-2" href="/fill_test_report" role="button">Назад</a>
                </div>
            </div>
        </form>
    </div>
</@cmn.page>