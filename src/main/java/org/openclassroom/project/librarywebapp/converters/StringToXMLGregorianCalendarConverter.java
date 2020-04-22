package org.openclassroom.project.librarywebapp.converters;

import org.springframework.core.convert.converter.Converter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class StringToXMLGregorianCalendarConverter implements Converter<String, XMLGregorianCalendar> {
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
