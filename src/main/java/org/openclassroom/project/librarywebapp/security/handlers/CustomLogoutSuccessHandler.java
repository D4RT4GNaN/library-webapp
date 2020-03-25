package org.openclassroom.project.librarywebapp.security.handlers;

import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    public CustomLogoutSuccessHandler() {
        super();
        this.setUseReferer(true);
    }
}

