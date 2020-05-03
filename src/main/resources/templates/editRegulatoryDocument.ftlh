<#import "parts/common.ftlh" as cmn>
<@cmn.page>
    <div class="col-lg-6">
        <form id="regulatoryDocumentForm" method="post" action="/editRegulatoryDocumentPage">
            <h5>Добавить/редактировать нормативный документ</h5>
            <label class="ml-3" for="regulatoryDocument">Название нормативного документа в родительном падеже:</label>
            <div class="input-group">
                <select class="custom-select ml-3 mb-2" id="regulatoryDocument" name="regulatoryDocumentSelect">
                    <#list regulatoryDocuments as regulatoryDocument>
                        <option value="${regulatoryDocument.id}" data-toggle="tooltip" title="${regulatoryDocument.title}">${regulatoryDocument.title?truncate(40, "...")}</option>
                    </#list>
                </select>
                <div class="input-group-append">
                    <button type="submit" class="btn btn-outline-primary mb-2 ml-1">
                        Редактировать
                    </button>
                    <button type="submit" class="btn btn-outline-primary mb-2 ml-1" formaction="/newRegulatoryDocument">
                        Новый
                    </button>
                </div>
            </div>
        </form>
    </div>
</@cmn.page>