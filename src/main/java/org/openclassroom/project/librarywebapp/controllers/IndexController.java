package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.Book;
import generated.libraryservice.Comment;
import generated.libraryservice.Usager;
import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class IndexController extends AbstractController {

    // ==================== Mapping ====================
    @GetMapping("/")
    public String showAll(Model model, HttpServletRequest request, @ModelAttribute("user")Usager usager, @ModelAttribute("errorMessage")String message) {
        configModel(model, request, usager, message, "");

        return "index";
    }



    // ==================== Search Method ====================
    @GetMapping("/search")
    public String search(Model model, @RequestParam("searchString") String searchString, @ModelAttribute("user")Usager usager, @ModelAttribute("errorMessage")String message, HttpServletRequest request) {
        configModel(model, request, usager, message, searchString);

        return "index";
    }



    // ==================== Book Detail Ajax Method ====================
    @GetMapping("/book-detail/{reference}")
    public String getBookDetail(Model model, @PathVariable String reference, HttpServletRequest request) {
        model.addAttribute("book", libraryService.getBooksWithKeyword(reference).get(0));
        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("stocks", libraryService.getBookAvailability(reference));
        model.addAttribute("newComment", new Comment());

        return "fragments/structure/book-detail :: book-detail";
    }



    // ==================== Comment Method ====================
    @PostMapping("/book-detail/{reference}/comment")
    public String commentBook(@PathVariable String reference, @ModelAttribute("newComment") Comment comment, Model model) {
        Usager usager = new Usager();

        if (getAuthentication().getDetails() instanceof Usager) {
            usager = (Usager) getAuthentication().getDetails();
        }

        comment.setAuthor(usager);
        comment.setBookReference(reference);

        libraryService.addComment(comment);

        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("book", libraryService.getBooksWithKeyword(reference).get(0));

        return "fragments/structure/comments :: comments";
    }



    // ==================== Private Method ====================
    private void configModel(Model model, HttpServletRequest request, @ModelAttribute("user") Usager usager, @ModelAttribute("errorMessage") String message, String searchString) {
        List<Book> books = libraryService.getBooksWithKeyword(searchString);
        List<Book> booksSublist;
        if (searchString.equals("")) {
            Comparator<Book> comparator = Comparator.comparing(Book::getMark);
            books.sort(comparator);
            Collections.reverse(books);
            booksSublist = books.subList(0,21);
        } else {
            booksSublist = books;
        }

        model.addAttribute("books", booksSublist);
        model.addAttribute("categories", getCategories(books));

        if (getAuthentication().getDetails() instanceof Usager) {
            usager = (Usager) getAuthentication().getDetails();
        }

        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("user", usager);

        String loginErrorMessage = Utils.getErrorMessage(request);
        if (loginErrorMessage != null) { message = loginErrorMessage; }
        if (!message.equals("")) { model.addAttribute("errorMessage", message); }
    }
}
