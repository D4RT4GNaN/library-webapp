package org.openclassroom.project.librarywebapp.converters;

import org.springframework.core.convert.converter.Converter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Custom converter of Spring MVC with Thymeleaf allowing to retrieve a date in {@link XMLGregorianCalendar} format from the JSP.
 * */
public class StringToXMLGregorianCalendarConverter implements Converter<String, XMLGregorianCalendar> {

    /**
     * Converts a string to {@link XMLGregorianCalendar} format.
     *
     * @param from - The source string.
     *
     * @return The date in {@link XMLGregorianCalendar} format or a null if the string is not suitable.
     * */
    @Override
    public XMLGregorianCalendar convert(String from) {
        String[] data = from.split(",");
        try {
            return DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(data[0]);
        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }
}
