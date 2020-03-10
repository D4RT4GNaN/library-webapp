package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.LibraryService;
import generated.libraryservice.LibraryServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    LibraryService libraryService = new LibraryServiceService().getLibraryServicePort();


    @GetMapping("/")
    public String showAll(Model model) {
        model.addAttribute("books", libraryService.getBooksWithKeyword(""));
        return "index";
    }

}
