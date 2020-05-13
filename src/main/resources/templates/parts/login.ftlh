<#macro login path isRegisterForm>
    <form action="${path}" method="post" novalidate>
        <div class="form-group row col-sm-3">
            <input class= "form-control ${(usernameError??)?string('is-invalid', '')}" type="text" name="username" placeholder="Имя пользователя" value="<#if user??>${user.username}</#if>" required/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
        <div class="form-group row col-sm-3">
            <input class="form-control ${(passwordError??)?string('is-invalid', '')}" type="password" name="password" placeholder="Пароль" required/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
        <#if isRegisterForm>
            <div class="form-group row col-sm-3">
                <input class="form-control ${(passwordConfirmError??)?string('is-invalid', '')}" type="password" name="passwordConfirm" placeholder="Подтвердите пароль" required/>
                <#if passwordConfirmError??>
                    <div class="invalid-feedback">
                        ${passwordConfirmError}
                    </div>
                </#if>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="input-group">
            <button class="btn btn-primary" type="submit"><#if isRegisterForm>Зарегистрировать<#else>Войти</#if></button>
            <#if isRegisterForm>
                <a class="btn btn-secondary input-group-append ml-1" type="button" href="/user">Назад</a>
            </#if>
            <#--<#if !isRegisterForm>
                <a class="btn btn-outline-success input-group-append ml-1" type="button" href="/registration">Регистрация</a>
            <#else>
                <a class="btn btn-secondary input-group-append ml-1" type="button" href="/login">Назад</a>
            </#if>-->
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-outline-dark" type="submit">Выйти</button>
    </form>
</#macro>