package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.Book;
import generated.libraryservice.LibraryService;
import generated.libraryservice.LibraryServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class IndexController {

    LibraryService libraryService = new LibraryServiceService().getLibraryServicePort();


    @GetMapping("/")
    public String showAll(Model model) {
        List<Book> books = libraryService.getBooksWithKeyword("");

        model.addAttribute("books", books);
        model.addAttribute("categories", getCategories(books));

        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("searchString") String searchString) {
        List<Book> books = libraryService.getBooksWithKeyword(searchString);

        model.addAttribute("books", books);
        model.addAttribute("categories", getCategories(books));

        return "index";
    }

    private List<String> getCategories(List<Book> books) {
        Set<String> categories = new HashSet<>();
        for (Book book : books) {
            categories.add(book.getCategory());
        }

        return new ArrayList<>(categories);
    }

}
