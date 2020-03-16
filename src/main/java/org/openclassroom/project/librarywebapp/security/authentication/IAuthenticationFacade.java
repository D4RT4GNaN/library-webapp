package org.openclassroom.project.librarywebapp.security.authentication;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
