package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.*;

import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String showResetPasswordForm(Model model, HttpServletRequest request, @RequestParam("token") String token, @ModelAttribute("errorMessage") String message) {
        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("token", token);
        model.addAttribute("user", new Usager());

        String loginErrorMessage = Utils.getErrorMessage(request);
        if (loginErrorMessage != null) { message = loginErrorMessage; }
        if (!message.equals("")) { model.addAttribute("errorMessage", message); }

        return "new-password";
    }

    @PostMapping("/forgot-password")
    public String retrievePassword(@RequestParam("email") String email, HttpServletRequest request) throws ForgotPasswordException {
        libraryService.requestPasswordReset(email);
        return Utils.redirectCustomReferer(request);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String password, @RequestParam("newConfirm") String confirm) {
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
    public String showConfirmEmailPage(@RequestParam("token") String token) {
        libraryService.validEmailWith(token);
        return "redirect:/?confirmEmailSuccess=true";
    }

    @PostMapping("/resend-confirm-email")
    public String resendConfirmEmail(HttpServletRequest request, @RequestParam("email") String email) throws EmailSendingException {
        libraryService.resendVerificationEmail(email);
        return Utils.redirectCustomReferer(request);
    }

    @ExceptionHandler(EmailSendingException.class)
    public String onEmailSendingFailure(HttpServletRequest request, EmailSendingException emailEx, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", emailEx.getMessage());
        return Utils.onFailure(request, "emailSendingError");
    }



    // ==================== Login/Logout Methods ====================
    @GetMapping("/failure-login")
    public String onAuthenticationFailure(HttpServletRequest request) {
        return Utils.onFailure(request, "loginError");
    }



    // ==================== Register Methods ====================
    @PostMapping("/register")
    public String registerNewAccount(@ModelAttribute("user") Usager usager, HttpServletRequest request, RedirectAttributes redirectAttributes) throws RegisterException {
        libraryService.addUser(usager);
        redirectAttributes.addFlashAttribute("email", usager.getEmail());
        return Utils.redirectCustomReferer(request);
    }

    @ExceptionHandler(RegisterException.class)
    public String onRegisterFailure(RedirectAttributes redirectAttributes, RegisterException re, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("user", re.getFaultInfo().getUsager());
        redirectAttributes.addFlashAttribute("errorMessage", re.getMessage());
        return Utils.onFailure(request, "registerError");
    }



    // ==================== Parameter Methods ====================
    @GetMapping("/parameter")
    public String showUsagerParameter(Model model, HttpServletRequest request) {
        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("user", getAuthentication().getDetails());
        return "parameter";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute("user") Usager usager, RedirectAttributes redirectAttributes) {
        String email = ((Usager) getAuthentication().getDetails()).getEmail();
        String message = libraryService.updatePassword(email, usager.getPassword(), usager.getConfirmPassword());
        redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/usager/parameter";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") Usager usager, RedirectAttributes redirectAttributes) throws UpdateUserException {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) getAuthentication();
        String email = ((Usager) authentication.getDetails()).getEmail();
        String message = libraryService.updateUserInfos(email, usager);
        authentication.setDetails(usager);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/usager/parameter";
    }

    @ExceptionHandler(UpdateUserException.class)
    public String onUpdateUserFailure(HttpServletRequest request, UpdateUserException upEx, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", upEx.getMessage());
        return Utils.onFailure(request, "updateUserError");
    }

}
