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



    // ==================== Book Detail Method ====================
    @GetMapping("/book-detail/{reference}")
    public String showBookDetail(Model model, @PathVariable String reference, @ModelAttribute("errorMessage")String message, @ModelAttribute("user")Usager usager, HttpServletRequest request) {
        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("user", usager);
        model.addAttribute("book", libraryService.getBooksWithKeyword(reference).get(0));

        String loginErrorMessage = Utils.getErrorMessage(request);
        if (loginErrorMessage != null) { message = loginErrorMessage; }
        if (!message.equals("")) { model.addAttribute("errorMessage", message); }

        model.addAttribute("stocks", libraryService.getBookAvailability(reference));
        model.addAttribute("newComment", new Comment());

        return "book-detail";
    }



    // ==================== Comment Method ====================
    @PostMapping("/book-detail/{reference}/comment")
    public String commentBook(@PathVariable String reference, @ModelAttribute Comment comment) {
        comment.setAuthor((Usager) getAuthentication().getDetails());
        comment.setBookReference(reference);

        libraryService.addComment(comment);

        return "redirect:/book-detail/" + reference;
    }



    // ==================== Private Method ====================
    private void configModel(Model model, HttpServletRequest request, @ModelAttribute("user") Usager usager, @ModelAttribute("errorMessage") String message, String searchString) {
        List<Book> books = libraryService.getBooksWithKeyword(searchString);

        model.addAttribute("books", books);
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
