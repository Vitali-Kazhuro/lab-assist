<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <script>
        function disableInput(sampleNormId) {
            document.getElementById(sampleNormId + "-limit").disabled = document.getElementById(sampleNormId + "-limit").disabled !== true;
            document.getElementById(sampleNormId + "-result1").disabled = document.getElementById(sampleNormId + "-result1").disabled !== true;
            document.getElementById(sampleNormId + "-result2").disabled = document.getElementById(sampleNormId + "-result2").disabled !== true;
        }
    </script>
    <div class="form-group col-lg-6">
        <form method="post" action="/addResults">
            <h5>Добавить результаты</h5>
            <table class="table table-hover">
                <thead class="thead-inverse">
                <tr>
                    <th>Измеряемое вещество</th>
                    <th>Параллель 1</th>
                    <th>Параллель 2</th>
                    <th>Предел обнаружения</th>
                </tr>
                </thead>
                <tbody>
                <#list testReport.samples as sample>
                    <tr>
                        <td colspan="4"><em><small>${sample.objectOfStudy.title}</small></em></td>
                    </tr>
                    <#list sample.sampleNorms as sampleNorm>
                        <tr>
                            <td><div class="mt-4">• ${sampleNorm.norm.element.title}</div></td>
                            <td><input class="form-control mt-4" id="${sample.id}${sampleNorm.id}-result1" name="${sample.id}${sampleNorm.id}-result1" type="number" min="0.00001" step="0.00001" value="<#if sampleNorm.result1??>${sampleNorm.result1?c?replace(",", ".")}</#if>" <#if sampleNorm.detectionLimit??>disabled</#if> required/></td>
                            <td><input class="form-control mt-4" id="${sample.id}${sampleNorm.id}-result2" name="${sample.id}${sampleNorm.id}-result2" type="number" min="0.00001" step="0.00001" value="<#if sampleNorm.result2??>${sampleNorm.result2?c?replace(",", ".")}</#if>" <#if sampleNorm.detectionLimit??>disabled</#if> required/></td>
                            <td>
                                <div class="form-inline">
                                    <label><input type="checkbox" id="${sample.id}${sampleNorm.id}-limitCheck" name="${sample.id}${sampleNorm.id}-limitCheck" onclick="disableInput(${sample.id}${sampleNorm.id})" <#if sampleNorm.detectionLimit??>checked</#if>>Меньше предела</label>
                                    <input class="form-control" id="${sample.id}${sampleNorm.id}-limit" name="${sample.id}${sampleNorm.id}-limit" type="text" value="<#if sampleNorm.detectionLimit??>${sampleNorm.detectionLimit}</#if>" <#if sampleNorm.detectionLimit??><#else>disabled</#if> required/>
                                </div>
                            </td>
                        </tr>
                    </#list>
                </#list>
                </tbody>
            </table>
            <button class="btn btn-success my-2" type="submit">Сохранить</button>
            <a class="btn btn-primary" href="/check_and_print" role="button">Далее</a>
        </form>
    </div>
</@cmn.page>
