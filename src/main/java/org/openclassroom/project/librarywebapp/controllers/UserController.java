package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.*;
import org.openclassroom.project.librarywebapp.models.Borrowing;
import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

    // ==================== Forgot Password Methods ====================
    /**
     * Displays the offline password change page.
     * Accessible from a link sent by email.
     *
     * @param model - {@link Model} object allowing to pass java objects to Thymeleaf in JSPs.
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     * @param token - A random character string in the URI that identifies the {@link GeneratedUsager user} and limits the use of the function.
     * @param message - All other error messages related to the {@link GeneratedUsager user} except those for login.
     *
     * @return new-password.html
     * */
    @GetMapping("/new-password")
    public String showResetPasswordForm(Model model, HttpServletRequest request, @RequestParam("token") String token, @ModelAttribute("errorMessage") String message) {
        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("token", token);
        model.addAttribute("user", new GeneratedUsager());

        String loginErrorMessage = Utils.getErrorMessage(request);
        if (loginErrorMessage != null) { message = loginErrorMessage; }
        if (!message.equals("")) { model.addAttribute("errorMessage", message); }

        return "new-password";
    }

    /**
     * Sends the email containing a link with a token allowing you to change your password when you have forgotten it.
     *
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     * @param email - The email of the {@link GeneratedUsager user} who has been asked to enter and to which the email will be sent.
     *
     * @return The previous url to stay on same page.
     * */
    @PostMapping("/forgot-password")
    public String retrievePassword(HttpServletRequest request, @RequestParam("email") String email) throws ForgotPasswordException {
        libraryService.requestPasswordReset(email);
        return Utils.redirectCustomReferer(request);
    }

    /**
     * Changes the {@link GeneratedUsager user's} password to the one chosen by the {@link GeneratedUsager user}.
     *
     * @param token - A random character string in the URI that identifies the {@link GeneratedUsager user} and limits the use of the function.
     * @param password - The new password chosen by the {@link GeneratedUsager user}.
     * @param confirm - Confirmation of the new password chosen by the {@link GeneratedUsager user}.
     *
     * @return redirect to the welcome page.
     * */
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
    /**
     * Allows you to validate a {@link GeneratedUsager user} account by clicking on the link sent by email.
     *
     * @param token - A random character string in the URI that identifies the {@link GeneratedUsager user} and limits the use of the function.
     *
     * @return redirect to the welcome page with a success parameter.
     * */
    @GetMapping("/confirm-email")
    public String showConfirmEmailPage(@RequestParam("token") String token) {
        libraryService.validEmailWith(token);
        return "redirect:/?confirmEmailSuccess=true";
    }

    /**
     * Resend the confirmation email with a different token.
     *
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     * @param email - The email of the {@link GeneratedUsager user} who has been asked to enter and to which the email will be sent.
     *
     * @return The previous url to stay on same page.
     * */
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
    /**
     * Passes the error message of connection failure.
     *
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     *
     * @return The previous url to stay on same page with a parameter that return the error message.
     * */
    @GetMapping("/failure-login")
    public String onAuthenticationFailure(HttpServletRequest request) {
        return Utils.onFailure(request, "loginError");
    }



    // ==================== Register Methods ====================
    /**
     * Create a new {@link GeneratedUsager user} with the previously requested information and send a confirmation email.
     *
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     * @param redirectAttributes - {@link RedirectAttributes} object to pass java objects to Thymeleaf through a redirection in the JSP.
     * @param user - Object containing the information of the new {@link GeneratedUsager user} wishing to create an account.
     *
     * @return The previous url to stay on same page.
     * */
    @PostMapping("/register")
    public String registerNewAccount(HttpServletRequest request, RedirectAttributes redirectAttributes, @ModelAttribute("user") GeneratedUsager user) throws RegisterException {
        libraryService.addUser(user);
        redirectAttributes.addFlashAttribute("email", user.getEmail());
        return Utils.redirectCustomReferer(request);
    }

    @ExceptionHandler(RegisterException.class)
    public String onRegisterFailure(RedirectAttributes redirectAttributes, RegisterException re, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("user", re.getFaultInfo().getUsager());
        redirectAttributes.addFlashAttribute("errorMessage", re.getMessage());
        return Utils.onFailure(request, "registerError");
    }



    // ==================== Parameter Methods ====================
    /**
     * Displays the parameters page containing the {@link GeneratedUsager user's} information and the list of his {@link GeneratedBorrowing borrowings}.
     *
     * @param model - {@link Model} object allowing to pass java objects to Thymeleaf in JSPs.
     *
     * @return parameter.html
     * */
    @GetMapping("/parameter")
    public String showUsagerParameter(Model model) {
        GeneratedUsager user = (GeneratedUsager) getAuthentication().getDetails();
        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("user", user);

        List<Borrowing> borrowings = Utils.convert(libraryService.getBorrowingFor(user.getId()));
        List<Borrowing> currentBorrowings = new ArrayList<>();
        List<Borrowing> returnedBorrowings = new ArrayList<>();

        for (Borrowing borrowing : borrowings) {
            if (borrowing.getStatus().equals("RETURNED")) {
                returnedBorrowings.add(borrowing);
            } else {
                currentBorrowings.add(borrowing);
            }
        }

        model.addAttribute("currentBorrowings", currentBorrowings);
        model.addAttribute("returnedBorrowings", returnedBorrowings);

        return "parameter";
    }

    /**
     * Changes the password of the logged-in {@link GeneratedUsager user} to the chosen one.
     *
     * @param redirectAttributes - {@link RedirectAttributes} object to pass java objects to Thymeleaf through a redirection in the JSP.
     * @param user - The {@link GeneratedUsager user} object containing the new password and its confirmation.
     *
     * @return redirect to parameter.html
     * */
    @PostMapping("/change-password")
    public String changePassword(RedirectAttributes redirectAttributes, @ModelAttribute("user") GeneratedUsager user) {
        String email = ((GeneratedUsager) getAuthentication().getDetails()).getEmail();
        libraryService.updatePassword(email, user.getPassword(), user.getConfirmPassword());
        //redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/user/parameter";
    }

    /**
     * Updates the {@link GeneratedUsager user's} information when the {@link GeneratedUsager user} changes it.
     *
     * @param redirectAttributes - {@link RedirectAttributes} object to pass java objects to Thymeleaf through a redirection in the JSP.
     * @param user - The {@link GeneratedUsager user} object containing the new information.
     *
     * @return redirect to parameter.html
     * */
    @PostMapping("/update-user")
    public String updateUser(RedirectAttributes redirectAttributes, @ModelAttribute("user") GeneratedUsager user) throws UpdateUserException {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) getAuthentication();
        String email = ((GeneratedUsager) authentication.getDetails()).getEmail();
        libraryService.updateUserInfos(email, user);
        authentication.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/user/parameter";
    }

    @ExceptionHandler(UpdateUserException.class)
    public String onUpdateUserFailure(HttpServletRequest request, UpdateUserException upEx, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", upEx.getMessage());
        return Utils.onFailure(request, "updateUserError");
    }

}
