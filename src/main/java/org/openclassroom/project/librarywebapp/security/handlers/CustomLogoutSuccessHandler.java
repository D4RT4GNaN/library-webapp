package org.openclassroom.project.librarywebapp.security.handlers;

import org.openclassroom.project.librarywebapp.utils.Utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (request.getHeader("referer").contains("parameter")) {
            redirectStrategy.sendRedirect(request, response, "");
        } else {
            this.setUseReferer(request, response);
        }
    }

    private void setUseReferer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = Utils.useCustomReferer(request);
        String targetUrl = Utils.deleteErrorParameter(url);

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

}
