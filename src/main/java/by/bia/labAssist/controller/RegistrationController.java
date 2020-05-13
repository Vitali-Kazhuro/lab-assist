package by.bia.labAssist.controller;

import by.bia.labAssist.model.User;
import by.bia.labAssist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String passwordConfirm,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model){
        boolean isPasswordConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isPasswordConfirmEmpty){
            model.addAttribute("passwordConfirmError", "Подтверждение пароля не может быть пустым");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "registration";
        }

        if (isPasswordConfirmEmpty || bindingResult.hasErrors()){
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            ));
            model.mergeAttributes(errorsMap);
            return "registration";
        }

        if(!userService.addUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует!");
            return "registration";
        }

        return "redirect:/user";
    }
}
