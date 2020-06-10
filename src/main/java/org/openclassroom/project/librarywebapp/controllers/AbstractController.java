package org.openclassroom.project.librarywebapp.controllers;

import generated.libraryservice.LibraryService;
import generated.libraryservice.LibraryServiceService;
import org.openclassroom.project.librarywebapp.security.authentication.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

/**
 * Abstract class giving access to the webservice and the user in session via the Authentication object
 * */
public abstract class AbstractController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    /**
     * Web Service access
     * */
    protected LibraryService libraryService = new LibraryServiceService().getLibraryServicePort();

    /**
     * Return an Authentication object that contains the user in session
     * */
    public Authentication getAuthentication() {
        return authenticationFacade.getAuthentication();
    }
}
