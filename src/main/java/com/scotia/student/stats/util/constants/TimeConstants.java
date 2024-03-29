package com.scotia.student.stats.util.constants;

import java.time.format.*;

public class TimeConstants {
    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy, HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
}
