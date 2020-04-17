package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.Usager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;

@Controller
@RequestMapping("/loan")
public class LoanController extends AbstractController {

    // ==================== New Loan ====================
    @GetMapping("/new")
    public String addNewLoan(HttpServletRequest request, @RequestParam String bookReference, @RequestParam int libraryId) throws DatatypeConfigurationException {
        Usager usager = (Usager) getAuthentication().getDetails();
        libraryService.addNewLoan(libraryId, bookReference, usager);

        return "redirect:https://stackoverflow.com/questions/19583540/spring-data-jpa-no-property-found-for-type-exception";
    }

}
