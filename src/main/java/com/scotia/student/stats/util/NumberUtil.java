package com.scotia.student.stats.util;

public interface NumberUtil {
    static Double setDecimals(double number, int numberOfDecimals) {
        return Double.valueOf(String.format("%." + numberOfDecimals + "f", number));
    }
}
