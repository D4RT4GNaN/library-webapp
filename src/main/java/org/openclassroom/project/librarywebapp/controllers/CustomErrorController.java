package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.GeneratedUsager;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController extends AbstractController implements ErrorController {

    /**
     * Retrieves the error status number and returns a customized page.
     *
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     * @param model - {@link Model} object allowing to pass java objects to Thymeleaf in JSPs.
     * @param user - The logged-in user, if there is one.
     *
     * @return A customized error page.
     * */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model, @ModelAttribute("user") GeneratedUsager user) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (getAuthentication().getDetails() instanceof GeneratedUsager) {
            user = (GeneratedUsager) getAuthentication().getDetails();
        }

        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("user", user);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }
        return "error";
    }



    /**
     * Get the error path
     *
     * @return The error path
     * */
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
