package org.openclassroom.project.librarywebapp.exceptions;

import generated.libraryservice.EmailExistsException;
import generated.libraryservice.UnspecifiedFault;
import generated.libraryservice.Usager;

public class RegisterException extends EmailExistsException {

    private Usager usager;

    public RegisterException(String message, UnspecifiedFault faultInfo) {
        super(message, faultInfo);
    }

    public RegisterException(String message, UnspecifiedFault faultInfo, Throwable cause) {
        super(message, faultInfo, cause);
    }

    public RegisterException(String message, UnspecifiedFault faultInfo, Throwable cause, Usager usager) {
        super(message, faultInfo, cause);
        this.usager = usager;
    }

    public Usager getUsager() {
        return usager;
    }
    public void setUsager(Usager usager) {
        this.usager = usager;
    }
}
