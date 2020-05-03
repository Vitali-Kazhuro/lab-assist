<#--Modal popup for tableSort() processing-->
<button type="button" class="btn btn-outline-danger" id="processingButton" data-toggle="modal" data-target="#processing" hidden>
    Processing
</button>
<div class="modal fade" id="processing" tabindex="-1" role="dialog" aria-labelledby="processing" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <button type="button" class="close" id="closeButton" data-dismiss="modal" hidden>X</button>
            <div class="modal-body">
                Обрабатывается, ожидайте...
            </div>
        </div>
    </div>
</div>

<#--<script src="/static/js/script.js"></script>-->
<#--<script>
    function sortTable() {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchCount = 0;
        table = document.getElementById("allTable");
        switching = true;
        dir = "asc";
        while (switching) {
            switching = false;
            rows = table.getElementsByTagName("tr");
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("td")[0];
                y = rows[i + 1].getElementsByTagName("td")[0];
                if (dir == "asc") {
                    if (parseInt(x.innerText) > parseInt(y.innerText)) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (parseInt(x.innerText) < parseInt(y.innerText)) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchCount ++;
            } else {
                if (switchCount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
    function selectFromListAndClick(id) {
        var element = document.getElementById("oneSelect");
        if(id == 0){
            /*alert("Не нажимайте на шапку таблицы!");*/
            sortTable();
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
