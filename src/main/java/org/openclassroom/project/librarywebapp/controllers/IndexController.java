package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.Book;
import generated.libraryservice.LibraryService;
import generated.libraryservice.LibraryServiceService;
import org.openclassroom.project.librarywebapp.security.authentication.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    LibraryService libraryService = new LibraryServiceService().getLibraryServicePort();

    @Autowired
    private IAuthenticationFacade authenticationFacade;


    @GetMapping("/")
    public String showAll(Model model) {
        List<Book> books = libraryService.getBooksWithKeyword("");

        Authentication authentication = authenticationFacade.getAuthentication();

        model.addAttribute("books", books);
        model.addAttribute("categories", getCategories(books));
        model.addAttribute("user", authentication);
        model.addAttribute("anonymousUser", "anonymousUser");

        System.out.println(authentication.getPrincipal());

        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("searchString") String searchString) {
        List<Book> books = libraryService.getBooksWithKeyword(searchString);
        Authentication authentication = authenticationFacade.getAuthentication();

        model.addAttribute("books", books);
        model.addAttribute("categories", getCategories(books));
        model.addAttribute("user", authentication);
        model.addAttribute("anonymousUser", "anonymousUser");

        return "index";
    }


    private List<String> getCategories(List<Book> books) {
        Set<String> categories = new HashSet<>();
        for (Book book : books) {
            categories.add(book.getCategory());
        }

        return new ArrayList<>(categories);
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "logout";
    }

}
