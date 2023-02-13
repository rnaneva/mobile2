package com.example.mobile2.web;

import com.example.mobile2.model.binding.UserLoginBindingModel;
import com.example.mobile2.model.binding.UserRegisterBindingModel;
import com.example.mobile2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {

        return "auth-register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError("passMismatch",
                            "confirmPassword",
                            "Passwords must be the same.")
            );
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("passMismatch", true)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);

            return "redirect:register";
        }

        userService.userRegister(userRegisterBindingModel);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.loginUser(userLoginBindingModel)) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel).
                    addFlashAttribute("badCredentials", true);

            return "redirect:login";
        }

        return "redirect:/";
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("passMismatch");
        model.addAttribute("badCredentials");
    }
}
