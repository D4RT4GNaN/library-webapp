package org.openclassroom.project.librarywebapp.utils;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {

    public static String redirectCustomReferer(HttpServletRequest request) {
        String link = customReferer(request, "redirect:/");
        return link;
    }

    public static String useCustomReferer(HttpServletRequest request) {
        return customReferer(request, "");
    }

    private static String customReferer(HttpServletRequest request, String link) {
        String [] referer = request.getHeader("referer").split("/");

        for (int i = 3; i <= referer.length-1; i++) {
            link += referer[i];
            link += i < referer.length-1 ? "/" : "";
        }

        return link;
    }

    public static String getErrorMessage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;

        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }

        return errorMessage;
    }

    public static String deleteErrorParameter(String url) {
        if (url.contains("Error")) {
            url = url.replaceAll("[?&]+[a-zA-Z]+=true", "");
        }
        return url;
    }

    public static String onFailure(HttpServletRequest request, String failure) {
        String redirectionString = redirectCustomReferer(request);

        if (!redirectionString.contains(failure)) {
            redirectionString = deleteErrorParameter(redirectionString);
            redirectionString += redirectionString.contains("?") ? "&" : "?";
            redirectionString += failure + "=true";
        }

        return redirectionString;
    }

}
