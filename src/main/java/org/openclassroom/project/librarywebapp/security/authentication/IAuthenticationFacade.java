package org.openclassroom.project.librarywebapp.security.authentication;

import org.springframework.security.core.Authentication;

/**
 * Hide the static access of user {@link Authentication} behind a simple facade.
 * Accessible with @Autowired from Spring.
 * */
public interface IAuthenticationFacade {
    /**
     * User {@link Authentication authentication} getter
     *
     * @return An {@link Authentication} object that contains user information.
     * */
    Authentication getAuthentication();
}
