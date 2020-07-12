<#import "parts/common.ftlh" as cmn>
<#import "parts/modalDelete.ftlh" as del>
<@cmn.page>
    <div class="form-group col-lg-6">
        <form method="post" action="/addRegulatoryDocument">
            <h5>Добавить нормативный документ</h5>
            <label class="col-form-label my-2">Название нормативного документа в родительном падеже:</label>
            <input class="form-control mr-2 mb-3" type="text" name="title" value="<#if regulatoryDocumentEdit??>${regulatoryDocumentEdit.title}</#if>" required/>
            <button class="btn btn-outline-success my-1" type="submit" <#if regulatoryDocumentEdit??>hidden</#if>>Сохранить</button>
            <button class="btn btn-outline-success my-1" formaction="editRegulatoryDocument" <#if regulatoryDocumentEdit??><#else>hidden</#if> >Изменить</button>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-outline-danger my-2" data-toggle="modal" data-target="#deleteRegulatoryDocument" <#if regulatoryDocumentEdit??><#else>hidden</#if>>
                Удалить
            </button>
            <a class="btn btn-outline-secondary my-1" <#if samplingAuthority??>href="/objectsOfStudy" <#else>href="/editRegulatoryDocument" </#if> role="button">Назад</a>
            <table class="table table-sm table-hover">
                <thead class="thead-inverse">
                <tr>
                    <th>Измеряемый элемент</th>
                    <th>Норма</th>
                    <th>Единицы измерения</th>
                </tr>
                </thead>
                <tbody>
                <#list allElements as element>
                    <tr <#if regulatoryDocumentEdit??><#list regulatoryDocumentEdit.norms as norm><#if norm.element.id == element.id>class="table-secondary"</#if></#list></#if>>
                        <td><label><input class="mt-3" type="checkbox" name="${element.title}" <#if regulatoryDocumentEdit??><#list regulatoryDocumentEdit.norms as norm><#if norm.element.id == element.id>checked</#if></#list></#if> > ${element.title}</label></td>
                        <td><input class="form-control my-2" type="text" name="${element.title}-norm" value="<#if regulatoryDocumentEdit??><#list regulatoryDocumentEdit.norms as norm><#if norm.element.id == element.id>${norm.value}</#if></#list></#if>" placeholder="Введите норму" /></td>
                        <td><input class="form-control my-2" type="text" name="${element.title}-units" value="<#if regulatoryDocumentEdit??><#list regulatoryDocumentEdit.norms as norm><#if norm.element.id == element.id>${norm.units}</#if></#list></#if>" placeholder="Введите еденицы измерения" /></td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <!-- Modal -->
    <@del.modalDelete "RegulatoryDocument">
        <div class="mx-3 my-2"><h5>Вы уверены, что хотите удалить нормативный документ <b>"<#if regulatoryDocumentEdit??>${regulatoryDocumentEdit.title}</#if>"</b>?</h5>
        <div class="ml-3 my-2"><h6><em><b>ОСТОРОЖНО!</b> Это удалит все образцы, у которых был выбран этот нормативный документ!</em></h6></div>
        <input type="hidden" name="regulatoryDocumentId" value="<#if regulatoryDocumentEdit??>${regulatoryDocumentEdit.id}</#if>">
    </@del.modalDelete>
</@cmn.page>
