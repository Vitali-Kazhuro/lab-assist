package by.bia.labAssist.controller;

import by.bia.labAssist.model.Role;
import by.bia.labAssist.model.User;
import by.bia.labAssist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("user")
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("user/{user}")
    public String userEditForm(@PathVariable User user, Model model){
        if (user.getId().equals(1)){//чтобы нельзя было удалить/изменить начальную админскую учётную запись
            return "errors/adminEditError";
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("user")
    public String userSave(@RequestParam("userId") User user,
                           @RequestParam Map<String, String> form,
                           @RequestParam String username){
        userService.edit(user, username, form);

        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("deleteUser")
    public String userDelete(@RequestParam Integer userId){
        userService.delete(userId);

        return "redirect:/user";
    }

    @GetMapping("user/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        if (user.getId().equals(1)){
            return "errors/adminEditError";
        }

        model.addAttribute("username", user.getUsername());

        return "profile";
    }

    @PostMapping("user/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String passwordConfirm,
                                Model model){
        if (!password.equals(passwordConfirm)){
            model.addAttribute("passwordError", "Пароли не совпадают!");
            model.addAttribute("username", user.getUsername());
            return "profile";
        }

        userService.updateProfile(user, password);

        return "redirect:/";
    }
}
