package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.BookBorrowingExtensionException;
import generated.libraryservice.GeneratedUsager;
import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

@Controller
@RequestMapping("/borrowing")
public class BorrowingController extends AbstractController {

    // ==================== New Borrowing ====================
    /**
     * Allows the {@link GeneratedUsager user} to borrow a {@link generated.libraryservice.GeneratedBook book} from a {@link generated.libraryservice.GeneratedLibrary library}.
     *
     * @param request - {@link HttpServletRequest} object to retrieve the previous URL.
     * @param bookReference - The reference of the selected {@link generated.libraryservice.GeneratedBook book}.
     * @param libraryId - The reference number of the selected {@link generated.libraryservice.GeneratedLibrary library}.
     *
     * @return The previous url to stay on same page.
     * */
    @GetMapping("/new")
    public String borrowABook(HttpServletRequest request, @RequestParam String bookReference, @RequestParam int libraryId) {
        GeneratedUsager user = (GeneratedUsager) getAuthentication().getDetails();
        libraryService.borrowABook(libraryId, bookReference, user);

        return Utils.redirectCustomReferer(request);
    }



    // ==================== Return Borrowing ====================
    /**
     * Allows the {@link GeneratedUsager user} to return a {@link generated.libraryservice.GeneratedBook book} borrowed from a {@link generated.libraryservice.GeneratedLibrary library}.
     *
     * @param request - {@link HttpServletRequest} object to retrieve the previous URL.
     * @param bookReference - The reference of the selected {@link generated.libraryservice.GeneratedBook book}.
     * @param libraryId - The reference number of the selected {@link generated.libraryservice.GeneratedLibrary library}.
     * @param borrowingDate - When the {@link generated.libraryservice.GeneratedBook book} was borrowed.
     *
     * @return The previous url to stay on same page.
     * */
    @GetMapping("/return")
    public String returnBook(HttpServletRequest request, @RequestParam String bookReference, @RequestParam int libraryId, @RequestParam XMLGregorianCalendar borrowingDate) {
        GeneratedUsager user = (GeneratedUsager) getAuthentication().getDetails();
        libraryService.returnBook(borrowingDate, libraryId, bookReference, user);

        return Utils.redirectCustomReferer(request);
    }



    // ==================== Extend Borrowing ====================
    /**
     * Allows the {@link GeneratedUsager user} to extend a {@link generated.libraryservice.GeneratedBorrowing borrowing} from a {@link generated.libraryservice.GeneratedLibrary library}.
     *
     * @param request - {@link HttpServletRequest} object to retrieve the previous URL.
     * @param bookReference - The reference of the selected {@link generated.libraryservice.GeneratedBook book}.
     * @param libraryId - The reference number of the selected {@link generated.libraryservice.GeneratedLibrary library}.
     * @param borrowingDate - When the {@link generated.libraryservice.GeneratedBook book} was borrowed.
     *
     * @return The previous url to stay on same page.
     * */
    @GetMapping("/extend")
    public String extendBookBorrowing(HttpServletRequest request, @RequestParam String bookReference, @RequestParam int libraryId, @RequestParam XMLGregorianCalendar borrowingDate) throws BookBorrowingExtensionException {
        GeneratedUsager user = (GeneratedUsager) getAuthentication().getDetails();
        libraryService.extendBookBorrowing(borrowingDate, libraryId, bookReference, user);

        return Utils.redirectCustomReferer(request);
    }

    @ExceptionHandler(BookBorrowingExtensionException.class)
    public String onBookBorrowingExtensionFailure(HttpServletRequest request, BookBorrowingExtensionException bbEx, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", bbEx.getMessage());
        return Utils.onFailure(request, "bookBorrowingExtensionError");
    }

}
