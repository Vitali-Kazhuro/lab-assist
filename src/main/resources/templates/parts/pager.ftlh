<#macro pager url page>
    <#if page.getTotalPages() gt 7>
        <#assign
        totalPages = page.getTotalPages()
        pageNumber = page.getNumber() + 1

        head = (pageNumber > 4)?then([1, -1], [1, 2, 3, 4, 5])
        tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 4, totalPages - 3, totalPages - 2, totalPages - 1, totalPages])
        bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 3)?then([pageNumber - 1], [])
        bodyAfter = (pageNumber > 4 && pageNumber < totalPages - 3)?then([pageNumber + 1], [])
        optionsl = (pageNumber > totalPages - 3)?then(totalPages,[])

        body = head + bodyBefore + (pageNumber > 4 && pageNumber < totalPages - 3)?then([pageNumber], []) + bodyAfter + tail
        >
    <#else>
        <#assign body = 1..page.getTotalPages()>
    </#if>
    <#--<script>
        function visualizeSize() {
            document.getElementById("size").innerHTML = document.getElementById("sizeRange").value;
        }
        function addSizeToHref() {
            var size = document.getElementById("sizeRange").value;
            var href = document.getElementById("sizeLink").getAttribute("href");
            document.getElementById("sizeLink").setAttribute("href", href+size);
        }
    </script>-->
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#">Строк на странице</a>
            </li>
            <li class="page-item">
                <a class="page-link" id="sizeLink" onclick="addSizeToHref()" href="${url}?page=${page.getNumber()}&size="><span id="size">${page.getSize()}</span></a>
            </li>
            <li>
                <input class="form-control-range mt-2 ml-1" id="sizeRange" type="range" min="10" max="50" step="1" oninput="visualizeSize()" value="${page.getSize()}">
            </li>

            <li class="page-item disabled">
                <a class="page-link ml-2" href="#"<#-- tabindex="-1"-->>Страницы</a>
            </li>
            <#list body as p>
                <#if (p-1)==page.getNumber()>
                    <li class="page-item active">
                        <a class="page-link" href="#">${p}</a>
                    </li>
                <#elseif p == -1>
                    <li class="page-item disabled">
                        <a class="page-link" href="#">...</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="${url}?page=${p-1}&size=${page.getSize()}">${p}</a>
                    </li>
                </#if>
            </#list>
        </ul>
        <#--<ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#">Строк на странице</a>
            </li>
            <li class="page-item">
                <a class="page-link" id="sizeLink" onclick="addSizeToHref()" href="${url}?page=${page.getNumber()}&size="><span id="size">${page.getSize()}</span></a>
            </li>
            <li>
                <input class="form-control-range mt-2 ml-1" id="sizeRange" type="range" min="10" max="50" step="1" oninput="visualizeSize()" value="${page.getSize()}">
            </li>
        </ul>-->
    </nav>
</#macro>