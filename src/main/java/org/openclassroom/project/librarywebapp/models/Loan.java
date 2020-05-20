package org.openclassroom.project.librarywebapp.models;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

public class Loan extends generated.libraryservice.Loan {
    private Date startDate;
    private Date endDate;

    public Loan(generated.libraryservice.Loan generatedLoan) {
        this.setBorrowingDate(generatedLoan.getBorrowingDate());
        this.startDate = toDate(generatedLoan.getBorrowingDate());
        this.book = generatedLoan.getBook();
        this.library = generatedLoan.getLibrary();
        this.usager = generatedLoan.getUsager();
        this.endDate = toDate(generatedLoan.getExpiryDate());
        this.extended = generatedLoan.isExtended();
        this.status = generatedLoan.getStatus();
        this.quantity = generatedLoan.getQuantity();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Date toDate(XMLGregorianCalendar cal) {
        return cal.toGregorianCalendar().getTime();
    }
}
