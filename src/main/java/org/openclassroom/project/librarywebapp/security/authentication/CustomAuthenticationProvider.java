package org.openclassroom.project.librarywebapp.security.authentication;

import generated.libraryservice.LibraryService;
import generated.libraryservice.LibraryServiceService;
import generated.libraryservice.Usager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private LibraryService libraryService = new LibraryServiceService().getLibraryServicePort();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Usager usager;
        try {
            usager = libraryService.connectUser(name, password);
        } catch (generated.libraryservice.BadCredentialsException bce) {
            throw new BadCredentialsException(bce.getMessage());
        }

        if(usager != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
            usernamePasswordAuthenticationToken.setDetails(usager);
            return usernamePasswordAuthenticationToken;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
