package org.softuni.eventuresjms.consts;

import java.time.format.DateTimeFormatter;

public final class DateTimeFormat {

    private DateTimeFormat() {}

    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
}
