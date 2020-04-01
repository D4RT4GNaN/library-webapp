package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.Book;
import generated.libraryservice.LibraryService;
import generated.libraryservice.LibraryServiceService;
import generated.libraryservice.Usager;
import org.openclassroom.project.librarywebapp.security.authentication.IAuthenticationFacade;
import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    protected LibraryService libraryService = new LibraryServiceService().getLibraryServicePort();

    public Authentication getAuthentication() {
        return authenticationFacade.getAuthentication();
    }

    protected List<String> getCategories(List<Book> books) {
        Set<String> categories = new HashSet<>();
        for (Book book : books) {
            categories.add(book.getCategory());
        }

        return new ArrayList<>(categories);
    }

    protected Model indexConfig(Model model, String keyword) {
        List<Book> books = libraryService.getBooksWithKeyword(keyword);

        model.addAttribute("books", books);
        model.addAttribute("categories", getCategories(books));

        return model;
    }

    protected Model usagerConfig(Model model, Usager usager, String message, HttpServletRequest request) {
        Authentication authentication = authenticationFacade.getAuthentication();

        if (authentication.getDetails() instanceof Usager) {
            usager = (Usager) authentication.getDetails();
        } else {
            usager = new Usager();
        }

        model.addAttribute("authentication", authentication.getName());
        model.addAttribute("user", usager);
        model.addAttribute("errorMessage", Utils.getErrorMessage(request));

        if (usager.getEmail() != null) {model.addAttribute("user", usager);}
        if (!message.equals("")) {model.addAttribute("errorMessage", message);}

        return model;
    }

}
