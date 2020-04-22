package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.Usager;
import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

@Controller
@RequestMapping("/loan")
public class LoanController extends AbstractController {

    // ==================== New Loan ====================
    @GetMapping("/new")
    public String addNewLoan(HttpServletRequest request, @RequestParam String bookReference, @RequestParam int libraryId) {
        Usager usager = (Usager) getAuthentication().getDetails();
        libraryService.addNewLoan(libraryId, bookReference, usager);

        return Utils.redirectCustomReferer(request);
    }



    // ==================== Return Loan ====================
    @GetMapping("/return")
    public String returnLoan(HttpServletRequest request, @RequestParam String bookReference, @RequestParam int libraryId, @RequestParam XMLGregorianCalendar borrowingDate) {
        Usager usager = (Usager) getAuthentication().getDetails();
        libraryService.returnBook(borrowingDate, libraryId, bookReference, usager);

        return Utils.redirectCustomReferer(request);
    }

}
