<#import "parts/common.ftlh" as cmn>

<@cmn.page>
    <script>
        // Script for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>

    <div class="my-2">
        <h5>Пользователь: <i>${username}</i></h5>
    </div>
    <div class="col-md-12">
        <form method="post" class="needs-validation" novalidate>
            <label class="col-form-label" for="password">Изменить пароль:</label>
            <div class="form-group row col-sm-3">
                <input class="form-control ${(passwordError??)?string('is-invalid', '')}" type="password" name="password" placeholder="Пароль" required/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                <#else>
                    <div class="invalid-feedback">
                        Пароль не может быть пустым
                    </div>
                </#if>
            </div>
            <div class="form-group row col-sm-3">
                <input class="form-control ${(passwordError??)?string('is-invalid', '')}" type="password" name="passwordConfirm" placeholder="Подтвердите пароль" required/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                <#else>
                    <div class="invalid-feedback">
                        Подтверждение пароля не может быть пустым
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-success" type="submit">Сохранить</button>
        </form>
    </div>
</@cmn.page>

