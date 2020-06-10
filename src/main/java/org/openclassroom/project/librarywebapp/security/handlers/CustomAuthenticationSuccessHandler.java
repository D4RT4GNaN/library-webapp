package org.openclassroom.project.librarywebapp.security.handlers;

import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom class called when authentication was successful.
 * */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * Method called when authentication was successful.
     * Using a {@link RedirectStrategy} object to redirect to the previous URL with parameters if necessary.
     *
     * @param request - The {@link HttpServletRequest} object is used to retrieve the previous URL which will be used in the redirection.
     * @param response - The {@link HttpServletResponse} object used in the redirection.
     * @param authentication - The {@link Authentication} object is retrieved when the user logs in successfully.
     * */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Utils.setUseReferer(request, response, redirectStrategy);
    }

}