package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.GeneratedBook;
import generated.libraryservice.GeneratedComment;
import generated.libraryservice.GeneratedUsager;
import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class IndexController extends AbstractController {

    // ==================== Mapping ====================
    /**
     * Show the index page when / is reached
     *
     * @param model - {@link Model} object allowing to pass java objects to Thymeleaf in JSPs.
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     * @param user - {@link GeneratedUsager} object containing the {@link GeneratedUsager user's} information.
     * @param message - All other error messages related to the {@link GeneratedUsager user} except those for login.
     *
     * @return index.html
     * */
    @GetMapping("/")
    public String showAll(Model model,
                          HttpServletRequest request,
                          @ModelAttribute("user") GeneratedUsager user,
                          @ModelAttribute("errorMessage")String message) {
        configModel(model, request, user, message, "");

        return "index";
    }



    // ==================== Search Method ====================
    /**
     * Shows the results of the keyword search.
     * Keywords can be the language, the reference, all or part of the title, the category or the author of the {@link GeneratedBook book}.
     *
     * @param searchString - The searched keyword.
     * @param model - {@link Model} object allowing to pass java objects to Thymeleaf in JSPs.
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     * @param user - {@link GeneratedUsager} object containing the {@link GeneratedUsager user's} information.
     * @param message - All other error messages related to the {@link GeneratedUsager user} except those for login.
     *
     * @return index.html
     * */
    @GetMapping("/search")
    public String search(@RequestParam("searchString") String searchString,
                         Model model,HttpServletRequest request,
                         @ModelAttribute("user")GeneratedUsager user,
                         @ModelAttribute("errorMessage")String message) {
        configModel(model, request, user, message, searchString);

        return "index";
    }



    // ==================== Book Detail Ajax Method ====================
    /**
     * Shows a detail of a {@link GeneratedBook book's} information from its reference.
     * Retrieving {@link GeneratedBook book} information and {@link generated.libraryservice.GeneratedStock stock} availability from the web service.
     * An empty {@link GeneratedComment comment} object and the {@link org.springframework.security.core.Authentication authentication} object are passed to the JSP.
     *
     * @param model - {@link Model} object allowing to pass java objects to Thymeleaf in JSPs.
     * @param reference - The reference of selected {@link GeneratedBook book}.
     *
     * @return book-detail.html
     * */
    @GetMapping("/book-detail/{reference}")
    public String getBookDetail(Model model, @PathVariable String reference) {
        model.addAttribute("book", libraryService.getBooksWithKeyword(reference).get(0));
        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("stocks", libraryService.getBookAvailability(reference));
        model.addAttribute("newComment", new GeneratedComment());

        return "fragments/index/book-detail :: book-detail";
    }



    // ==================== Comment Method ====================
    /**
     * Adding a {@link GeneratedComment comment} to a {@link GeneratedBook book}.
     * The {@link GeneratedComment comment} object is completed by adding the user from the {@link org.springframework.security.core.Authentication authentication} object as well as the reference of the {@link GeneratedBook book} concerned.
     * Returning the {@link org.springframework.security.core.Authentication authentication} object and the {@link GeneratedBook book} object associated with the reference.
     *
     * @param model - {@link Model} object allowing to pass java objects to Thymeleaf in JSPs.
     * @param reference - The reference of selected {@link GeneratedBook book}.
     * @param comment - The {@link GeneratedComment comment} to be added.
     *
     * @return comments.html
     * */
    @PostMapping("/book-detail/{reference}/comment")
    public String commentBook(Model model, @PathVariable String reference, @ModelAttribute("newComment") GeneratedComment comment) {
        GeneratedUsager user = new GeneratedUsager();

        if (getAuthentication().getDetails() instanceof GeneratedUsager) {
            user = (GeneratedUsager) getAuthentication().getDetails();
        }

        comment.setAuthor(user);
        comment.setBookReference(reference);

        libraryService.addComment(comment);

        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("book", libraryService.getBooksWithKeyword(reference).get(0));

        return "fragments/index/comments :: comments";
    }



    // ==================== Private Method ====================
    /**
     * Private method to configure the {@link Model model} object.
     * Add the {@link GeneratedBook book} object, the category of {@link GeneratedBook book}, the {@link org.springframework.security.core.Authentication authentication} object, the {@link GeneratedUsager user} object and error messages to the {@link Model model} object.
     *
     * @param model - {@link Model} object allowing to pass java objects to Thymeleaf in JSPs.
     * @param request - {@link HttpServletRequest} object to retrieve login error messages.
     * @param user - {@link GeneratedUsager} object containing the {@link GeneratedUsager user's} information.
     * @param message - All other error messages related to the {@link GeneratedUsager user} except those for login.
     * @param searchString - The searched keyword.
     * */
    private void configModel(Model model, HttpServletRequest request, @ModelAttribute("user") GeneratedUsager user, @ModelAttribute("errorMessage") String message, String searchString) {
        List<GeneratedBook> books = libraryService.getBooksWithKeyword(searchString);
        List<GeneratedBook> booksSublist;
        if (searchString.equals("")) {
            Comparator<GeneratedBook> comparator = Comparator.comparing(GeneratedBook::getMark);
            books.sort(comparator);
            Collections.reverse(books);
            booksSublist = books.subList(0,21);
        } else {
            booksSublist = books;
        }

        model.addAttribute("books", booksSublist);
        model.addAttribute("categories", getCategories(books));

        if (getAuthentication().getDetails() instanceof GeneratedUsager) {
            user = (GeneratedUsager) getAuthentication().getDetails();
        }

        model.addAttribute("authentication", getAuthentication().getName());
        model.addAttribute("user", user);

        String loginErrorMessage = Utils.getErrorMessage(request);
        if (loginErrorMessage != null) { message = loginErrorMessage; }
        if (!message.equals("")) { model.addAttribute("errorMessage", message); }
    }

    /**
     * Private method to retrieve the list of {@link GeneratedBook book} categories.
     *
     * @param books - The list of {@link GeneratedBook books}.
     *
     * @return An arraylist of {@link GeneratedBook book} category.
     * */
    private List<String> getCategories(List<GeneratedBook> books) {
        Set<String> categories = new HashSet<>();
        for (GeneratedBook book : books) {
            categories.add(book.getCategory());
        }

        return new ArrayList<>(categories);
    }
}
