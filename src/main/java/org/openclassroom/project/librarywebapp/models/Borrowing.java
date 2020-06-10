package org.openclassroom.project.librarywebapp.models;

import generated.libraryservice.GeneratedBorrowing;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

/**
 * Object inherited from {@link GeneratedBorrowing} allowing a conversion of {@link Date dates} for final posting on the website.
 * */
public class Borrowing extends GeneratedBorrowing {

    // ==================== Attributes ====================
    private Date startDate;
    private Date endDate;



    // ==================== Constructor ====================
    /**
     * Borrowing constructor to retrieve {@link GeneratedBorrowing parent} information and converts {@link Date dates}.
     * */
    public Borrowing(GeneratedBorrowing generatedLoan) {
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



    // ==================== Getters/Setters ====================
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



    // ==================== Methods ====================
    /**
     * Converts a date from {@link XMLGregorianCalendar} format to {@link Date} format.
     *
     * @param cal - The date in {@link XMLGregorianCalendar} format.
     *
     * @return The date in {@link Date} format.
     * */
    private Date toDate(XMLGregorianCalendar cal) {
        return cal.toGregorianCalendar().getTime();
    }
}
