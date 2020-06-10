
package generated.libraryservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LibraryService", targetNamespace = "http://LibraryService/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LibraryService {


    /**
     * 
     * @param generatedUsager
     * @return
     *     returns java.lang.String
     * @throws RegisterException
     */
    @WebMethod(action = "http://LibraryService/addUser")
    @WebResult(name = "token", targetNamespace = "")
    @RequestWrapper(localName = "addUser", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.AddUser")
    @ResponseWrapper(localName = "addUserResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.AddUserResponse")
    @Action(input = "http://LibraryService/addUser", output = "http://LibraryService/LibraryService/addUserResponse", fault = {
        @FaultAction(className = RegisterException.class, value = "http://LibraryService/LibraryService/addUser/Fault/RegisterException")
    })
    public String addUser(
        @WebParam(name = "generatedUsager", targetNamespace = "")
        GeneratedUsager generatedUsager)
        throws RegisterException
    ;

    /**
     * 
     * @param identifier
     * @param password
     * @return
     *     returns generated.libraryservice.GeneratedUsager
     * @throws LoginException
     */
    @WebMethod(action = "http://LibraryService/connectUser")
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "connectUser", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ConnectUser")
    @ResponseWrapper(localName = "connectUserResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ConnectUserResponse")
    @Action(input = "http://LibraryService/connectUser", output = "http://LibraryService/LibraryService/connectUserResponse", fault = {
        @FaultAction(className = LoginException.class, value = "http://LibraryService/LibraryService/connectUser/Fault/LoginException")
    })
    public GeneratedUsager connectUser(
        @WebParam(name = "identifier", targetNamespace = "")
        String identifier,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws LoginException
    ;

    /**
     * 
     * @param token
     * @return
     *     returns boolean
     */
    @WebMethod(action = "http://LibraryService/validEmailWith")
    @WebResult(name = "valid", targetNamespace = "")
    @RequestWrapper(localName = "validEmailWith", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ValidEmailWith")
    @ResponseWrapper(localName = "validEmailWithResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ValidEmailWithResponse")
    @Action(input = "http://LibraryService/validEmailWith", output = "http://LibraryService/LibraryService/validEmailWithResponse")
    public boolean validEmailWith(
        @WebParam(name = "token", targetNamespace = "")
        String token);

    /**
     * 
     * @param email
     * @return
     *     returns boolean
     * @throws EmailSendingException
     */
    @WebMethod(action = "http://LibraryService/resendVerificationEmail")
    @WebResult(name = "sent", targetNamespace = "")
    @RequestWrapper(localName = "resendVerificationEmail", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ResendVerificationEmail")
    @ResponseWrapper(localName = "resendVerificationEmailResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ResendVerificationEmailResponse")
    @Action(input = "http://LibraryService/resendVerificationEmail", output = "http://LibraryService/LibraryService/resendVerificationEmailResponse", fault = {
        @FaultAction(className = EmailSendingException.class, value = "http://LibraryService/LibraryService/resendVerificationEmail/Fault/EmailSendingException")
    })
    public boolean resendVerificationEmail(
        @WebParam(name = "email", targetNamespace = "")
        String email)
        throws EmailSendingException
    ;

    /**
     * 
     * @param email
     * @return
     *     returns java.lang.String
     * @throws ForgotPasswordException
     */
    @WebMethod(action = "http://LibraryService/requestPasswordReset")
    @WebResult(name = "token", targetNamespace = "")
    @RequestWrapper(localName = "requestPasswordReset", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.RequestPasswordReset")
    @ResponseWrapper(localName = "requestPasswordResetResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.RequestPasswordResetResponse")
    @Action(input = "http://LibraryService/requestPasswordReset", output = "http://LibraryService/LibraryService/requestPasswordResetResponse", fault = {
        @FaultAction(className = ForgotPasswordException.class, value = "http://LibraryService/LibraryService/requestPasswordReset/Fault/ForgotPasswordException")
    })
    public String requestPasswordReset(
        @WebParam(name = "email", targetNamespace = "")
        String email)
        throws ForgotPasswordException
    ;

    /**
     * 
     * @param confirmNewPassword
     * @param newPassword
     * @param token
     * @return
     *     returns boolean
     */
    @WebMethod(action = "http://LibraryService/resetPassword")
    @WebResult(name = "reset", targetNamespace = "")
    @RequestWrapper(localName = "resetPassword", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ResetPassword")
    @ResponseWrapper(localName = "resetPasswordResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ResetPasswordResponse")
    @Action(input = "http://LibraryService/resetPassword", output = "http://LibraryService/LibraryService/resetPasswordResponse")
    public boolean resetPassword(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "newPassword", targetNamespace = "")
        String newPassword,
        @WebParam(name = "confirmNewPassword", targetNamespace = "")
        String confirmNewPassword);

    /**
     * 
     * @param confirmNewPassword
     * @param newPassword
     * @param email
     * @return
     *     returns boolean
     */
    @WebMethod(action = "http://LibraryService/updatePassword")
    @WebResult(name = "updated", targetNamespace = "")
    @RequestWrapper(localName = "updatePassword", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.UpdatePassword")
    @ResponseWrapper(localName = "updatePasswordResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.UpdatePasswordResponse")
    @Action(input = "http://LibraryService/updatePassword", output = "http://LibraryService/LibraryService/updatePasswordResponse")
    public boolean updatePassword(
        @WebParam(name = "email", targetNamespace = "")
        String email,
        @WebParam(name = "newPassword", targetNamespace = "")
        String newPassword,
        @WebParam(name = "confirmNewPassword", targetNamespace = "")
        String confirmNewPassword);

    /**
     * 
     * @param usager
     * @param email
     * @return
     *     returns boolean
     * @throws UpdateUserException
     */
    @WebMethod(action = "http://LibraryService/updateUserInfos")
    @WebResult(name = "updated", targetNamespace = "")
    @RequestWrapper(localName = "updateUserInfos", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.UpdateUserInfos")
    @ResponseWrapper(localName = "updateUserInfosResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.UpdateUserInfosResponse")
    @Action(input = "http://LibraryService/updateUserInfos", output = "http://LibraryService/LibraryService/updateUserInfosResponse", fault = {
        @FaultAction(className = UpdateUserException.class, value = "http://LibraryService/LibraryService/updateUserInfos/Fault/UpdateUserException")
    })
    public boolean updateUserInfos(
        @WebParam(name = "email", targetNamespace = "")
        String email,
        @WebParam(name = "usager", targetNamespace = "")
        GeneratedUsager usager)
        throws UpdateUserException
    ;

    /**
     * 
     * @param userID
     * @return
     *     returns java.util.List<generated.libraryservice.GeneratedBorrowing>
     */
    @WebMethod(action = "http://LibraryService/getBorrowingFor")
    @WebResult(name = "booksBorrowed", targetNamespace = "")
    @RequestWrapper(localName = "getBorrowingFor", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBorrowingFor")
    @ResponseWrapper(localName = "getBorrowingForResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBorrowingForResponse")
    @Action(input = "http://LibraryService/getBorrowingFor", output = "http://LibraryService/LibraryService/getBorrowingForResponse")
    public List<GeneratedBorrowing> getBorrowingFor(
        @WebParam(name = "userID", targetNamespace = "")
        int userID);

    /**
     * 
     * @return
     *     returns java.util.List<generated.libraryservice.GeneratedBorrowing>
     */
    @WebMethod(action = "http://LibraryService/checkExpiration")
    @WebResult(name = "overdueBorrowings", targetNamespace = "")
    @RequestWrapper(localName = "checkExpiration", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.CheckExpiration")
    @ResponseWrapper(localName = "checkExpirationResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.CheckExpirationResponse")
    @Action(input = "http://LibraryService/checkExpiration", output = "http://LibraryService/LibraryService/checkExpirationResponse")
    public List<GeneratedBorrowing> checkExpiration();

    /**
     * 
     * @param comment
     * @return
     *     returns generated.libraryservice.GeneratedComment
     */
    @WebMethod(action = "http://LibraryService/addComment")
    @WebResult(name = "savedComment", targetNamespace = "")
    @RequestWrapper(localName = "addComment", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.AddComment")
    @ResponseWrapper(localName = "addCommentResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.AddCommentResponse")
    @Action(input = "http://LibraryService/addComment", output = "http://LibraryService/LibraryService/addCommentResponse")
    public GeneratedComment addComment(
        @WebParam(name = "comment", targetNamespace = "")
        GeneratedComment comment);

    /**
     * 
     * @param bookReference
     * @return
     *     returns java.util.List<generated.libraryservice.GeneratedStock>
     */
    @WebMethod(action = "http://LibraryService/getBookAvailability")
    @WebResult(name = "bookAvailability", targetNamespace = "")
    @RequestWrapper(localName = "getBookAvailability", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBookAvailability")
    @ResponseWrapper(localName = "getBookAvailabilityResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBookAvailabilityResponse")
    @Action(input = "http://LibraryService/getBookAvailability", output = "http://LibraryService/LibraryService/getBookAvailabilityResponse")
    public List<GeneratedStock> getBookAvailability(
        @WebParam(name = "bookReference", targetNamespace = "")
        String bookReference);

    /**
     * 
     * @param libraryId
     * @param usager
     * @param bookReference
     * @return
     *     returns generated.libraryservice.GeneratedBorrowing
     */
    @WebMethod(action = "http://LibraryService/borrowABook")
    @WebResult(name = "borrowing", targetNamespace = "")
    @RequestWrapper(localName = "borrowABook", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.BorrowABook")
    @ResponseWrapper(localName = "borrowABookResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.BorrowABookResponse")
    @Action(input = "http://LibraryService/borrowABook", output = "http://LibraryService/LibraryService/borrowABookResponse")
    public GeneratedBorrowing borrowABook(
        @WebParam(name = "libraryId", targetNamespace = "")
        int libraryId,
        @WebParam(name = "bookReference", targetNamespace = "")
        String bookReference,
        @WebParam(name = "usager", targetNamespace = "")
        GeneratedUsager usager);

    /**
     * 
     * @param libraryId
     * @param usager
     * @param borrowingDate
     * @param bookReference
     * @return
     *     returns boolean
     * @throws BookBorrowingExtensionException
     */
    @WebMethod(action = "http://LibraryService/extendBookBorrowing")
    @WebResult(name = "extended", targetNamespace = "")
    @RequestWrapper(localName = "extendBookBorrowing", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ExtendBookBorrowing")
    @ResponseWrapper(localName = "extendBookBorrowingResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ExtendBookBorrowingResponse")
    @Action(input = "http://LibraryService/extendBookBorrowing", output = "http://LibraryService/LibraryService/extendBookBorrowingResponse", fault = {
        @FaultAction(className = BookBorrowingExtensionException.class, value = "http://LibraryService/LibraryService/extendBookBorrowing/Fault/BookBorrowingExtensionException")
    })
    public boolean extendBookBorrowing(
        @WebParam(name = "borrowingDate", targetNamespace = "")
        XMLGregorianCalendar borrowingDate,
        @WebParam(name = "libraryId", targetNamespace = "")
        int libraryId,
        @WebParam(name = "bookReference", targetNamespace = "")
        String bookReference,
        @WebParam(name = "usager", targetNamespace = "")
        GeneratedUsager usager)
        throws BookBorrowingExtensionException
    ;

    /**
     * 
     * @param libraryId
     * @param usager
     * @param borrowingDate
     * @param bookReference
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://LibraryService/returnBook")
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "returnBook", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ReturnBook")
    @ResponseWrapper(localName = "returnBookResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.ReturnBookResponse")
    @Action(input = "http://LibraryService/returnBook", output = "http://LibraryService/LibraryService/returnBookResponse")
    public String returnBook(
        @WebParam(name = "borrowingDate", targetNamespace = "")
        XMLGregorianCalendar borrowingDate,
        @WebParam(name = "libraryId", targetNamespace = "")
        int libraryId,
        @WebParam(name = "bookReference", targetNamespace = "")
        String bookReference,
        @WebParam(name = "usager", targetNamespace = "")
        GeneratedUsager usager);

    /**
     * 
     * @param keyword
     * @return
     *     returns java.util.List<generated.libraryservice.GeneratedBook>
     */
    @WebMethod(action = "http://LibraryService/getBooksWithKeyword")
    @WebResult(name = "listBooks", targetNamespace = "")
    @RequestWrapper(localName = "getBooksWithKeyword", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBooksWithKeyword")
    @ResponseWrapper(localName = "getBooksWithKeywordResponse", targetNamespace = "http://LibraryService/", className = "generated.libraryservice.GetBooksWithKeywordResponse")
    @Action(input = "http://LibraryService/getBooksWithKeyword", output = "http://LibraryService/LibraryService/getBooksWithKeywordResponse")
    public List<GeneratedBook> getBooksWithKeyword(
        @WebParam(name = "keyword", targetNamespace = "")
        String keyword);

}
