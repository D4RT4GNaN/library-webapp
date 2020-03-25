package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.Usager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends AbstractController {

    // ==================== Mapping ====================
    @GetMapping("/")
    public String showAll(Model model, HttpServletRequest request, @ModelAttribute("user")Usager usager, @ModelAttribute("errorMessage")String message) {
        model = indexConfig(model, "");
        model = usagerConfig(model, usager, message, request);

        return "index";
    }



    // ==================== Search Method ====================
    @GetMapping("/search")
    public String search(Model model, @RequestParam("searchString") String searchString, @ModelAttribute("user")Usager usager, @ModelAttribute("errorMessage")String message, HttpServletRequest request) {
        model = indexConfig(model, searchString);
        model = usagerConfig(model, usager, message, request);

        return "index";
    }

}
