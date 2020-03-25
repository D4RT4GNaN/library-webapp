package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.EmailExistsException;
import generated.libraryservice.ForgotPasswordException;
import generated.libraryservice.Usager;
import org.openclassroom.project.librarywebapp.exceptions.RegisterException;
import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/usager")
public class UsagerController extends AbstractController {

    // ==================== Forgot Password Methods ====================
    @GetMapping("/new-password")
    public String showResetPasswordForm(Model model, HttpServletRequest request, @RequestParam("errorMessage") String message, @RequestParam("token")String token) {
        model = indexConfig(model, "");
        model = usagerConfig(model, null, message, request);
        model.addAttribute("token", token);

        return "new-password";
    }

    @PostMapping("/forgot-password")
    public String retrievePassword(@RequestParam("email")String email, HttpServletRequest request) throws ForgotPasswordException {
        libraryService.requestPasswordReset(email);
        return Utils.redirectCustomReferer(request);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token")String token, @RequestParam("newPassword")String password, @RequestParam("newConfirm")String confirm) {
        libraryService.resetPassword(token, password, confirm);
        return "redirect:/";
    }

    @ExceptionHandler(ForgotPasswordException.class)
    public String userNotFoundHandler(Exception ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return Utils.onFailure(request, "forgotPasswordError");
    }



    // ==================== Confirm Email Methods ====================
    @GetMapping("/confirm-email")
    public String showConfirmEmailPage(Model model, HttpServletRequest request, @RequestParam("errorMessage") String message, @RequestParam("token")String token) {
        model = indexConfig(model, "");
        model = usagerConfig(model, null, message, request);

        libraryService.validEmailWith(token);

        return "confirm-email";
    }



    // ==================== Login/Logout Methods ====================
    @GetMapping("/failure-login")
    public String onAuthenticationFailure(HttpServletRequest request) {
        return Utils.onFailure(request, "loginError");
    }



    // ==================== Register Methods ====================
    @PostMapping("/register")
    public String registerNewAccount(@ModelAttribute("user") Usager usager, HttpServletRequest request) throws RegisterException {
        try {
            libraryService.addUser(usager);
        } catch (EmailExistsException e) {
            throw new RegisterException(e.getMessage(), e.getFaultInfo(), e.getCause(), usager);
        }
        return Utils.redirectCustomReferer(request);
    }

    @ExceptionHandler(RegisterException.class)
    public String onRegisterFailure(RedirectAttributes redirectAttributes, RegisterException re, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("user", re.getUsager());
        redirectAttributes.addFlashAttribute("errorMessage", re.getMessage());
        return Utils.onFailure(request, "registerError");
    }

}
