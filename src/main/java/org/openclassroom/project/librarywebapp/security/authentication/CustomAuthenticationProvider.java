package org.openclassroom.project.librarywebapp.security.authentication;

import generated.libraryservice.GeneratedUsager;
import generated.libraryservice.LibraryService;
import generated.libraryservice.LibraryServiceService;
import generated.libraryservice.LoginException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;

/**
 * Implementation of the {@link AuthenticationProvider} interface.
 * It is called when the user wants to log in.
 * */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private LibraryService libraryService = new LibraryServiceService().getLibraryServicePort();

    /**
     * Sends the user's username and password to the server to log in.
     *
     * @param authentication - The {@link Authentication} object that contains the user's login information.
     *
     * @return A {@link UsernamePasswordAuthenticationToken} object only if the connection was successful otherwise a null is returned.
     * */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        GeneratedUsager user;
        try {
            user = libraryService.connectUser(name, password);
        } catch (LoginException logEx) {
            throw new BadCredentialsException(logEx.getMessage());
        }

        if(user != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
            usernamePasswordAuthenticationToken.setDetails(user);
            return usernamePasswordAuthenticationToken;
        } else {
            return null;
        }
    }

    /**
     * The {@link Class} argument in this method is really Class extends {@link Authentication}.
     * It will only ever be asked if it supports something that will be passed into the authenticate() method.
     *
     * @param authentication - An {@link Authentication} instance.
     *
     * @return If a {@link ProviderManager} recognise a particular Authentication instance type otherwise it will be skipped.
     * */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
