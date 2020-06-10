package org.openclassroom.project.librarywebapp.utils;

import generated.libraryservice.GeneratedBorrowing;
import org.openclassroom.project.librarywebapp.models.Borrowing;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class providing static methods that can be used throughout the project.
 * */
public class Utils {

    // ==================== Public Methods ====================
    /**
     * Method called to leave the user on the same page after a redirection.
     *
     * @param request - The {@link HttpServletRequest} object is used to retrieve the previous URL which will be used in the redirection.
     *
     * @return redirect to the previous url to stay on same page.
     * */
    public static String redirectCustomReferer(HttpServletRequest request) {
        return customReferer(request, "redirect:/");
    }



    /**
     * Method called to leave the user on the same page after sending a request.
     * Using a {@link RedirectStrategy} object to redirect to the previous URL with parameters if necessary.
     *
     * @param request - The {@link HttpServletRequest} object is used to retrieve the previous URL which will be used in the redirection.
     * @param response - The {@link HttpServletResponse} object used in the redirection.
     * */
    public static void setUseReferer(HttpServletRequest request, HttpServletResponse response, RedirectStrategy redirectStrategy) throws IOException {
        String url = customReferer(request, "");
        String targetUrl = Utils.deleteErrorParameter(url);

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }



    /**
     * Retrieves login error messages sent by Spring via {@link AuthenticationException}.
     *
     * @param request - The {@link HttpServletRequest} object is used to retrieve the {@link AuthenticationException} object in session.
     *
     * @return Login error messages sent by Spring if there is one, otherwise an empty string.
     * */
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



    /**
     * Allows you to create a URL containing the error parameters during a failure.
     *
     * @param request - The {@link HttpServletRequest} object is used to retrieve the previous URL which will be used in the redirection.
     * @param failure - The specific cause of the error used as a parameter.
     *
     * @return An URL containing the specific error parameters
     * */
    public static String onFailure(HttpServletRequest request, String failure) {
        String redirectionString = redirectCustomReferer(request);

        if (!redirectionString.contains(failure)) {
            redirectionString = deleteErrorParameter(redirectionString);
            redirectionString += redirectionString.contains("?") ? "&" : "?";
            redirectionString += failure + "=true";
        }

        return redirectionString;
    }



    /**
     * A method for converting the {@link GeneratedBorrowing Borrowing} object from the Web Service to the {@link Borrowing} object of the Web Application.
     *
     * @param generatedBorrowings - The {@link GeneratedBorrowing Borrowing} object from the Web Service.
     *
     * @return An converted {@link Borrowing} object of the Web Application.
     * */
    public static List<Borrowing> convert(List<GeneratedBorrowing> generatedBorrowings) {
        List<Borrowing> borrowings = new ArrayList<>();

        for (GeneratedBorrowing generatedBorrowing : generatedBorrowings) {
            borrowings.add(new Borrowing(generatedBorrowing));
        }

        return borrowings;
    }



    // ==================== Private Methods ====================
    /**
     * Retrieves the URL purged of error parameters from the page from which the request was launched.
     *
     * @param request - The {@link HttpServletRequest} object is used to retrieve the previous URL which will be used in the redirection.
     * @param link - Can be empty if it's just a change of URL or use "redirect:/" if it's a redirection.
     *
     * @return The URL of the page purged of error parameters
     */
    private static String customReferer(HttpServletRequest request, String link) {
        String [] referer = request.getHeader("referer").split("/");

        for (int i = 3; i <= referer.length-1; i++) {
            link += deleteErrorParameter(referer[i]);
            link += i < referer.length-1 ? "/" : "";
        }

        return link;
    }



    /**
     * Removes the error parameters contained in a URL.
     *
     * @param url - The URL containing the error parameters to be removed.
     *
     * @return The URL without the error parameters.
     */
    private static String deleteErrorParameter(String url) {
        if (url.contains("Error")) {
            url = url.replaceAll("[?&]+[a-zA-Z]+=true", "");
        }
        return url;
    }

}
