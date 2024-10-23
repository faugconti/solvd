package travelAgency.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate unmarshal(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    @Override
    public String marshal(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
}
