package com.Flock.domain.Club.Entity.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum DayOfWeek {
    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    SATURDAY("토요일"),
    SUNDAY("일요일");

    String description;

    DayOfWeek(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DayOfWeek fromDescription(String description) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getDescription().equals(description)) {
                return day;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }

    public static List<DayOfWeek> from(List<String> descriptions) {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();

        for (String description : descriptions) {
            dayOfWeeks.add(fromDescription(description));
        }

        return dayOfWeeks;
    }

    public static String activityDaysToString(List<DayOfWeek> days) {
        return days.stream()
                .map(DayOfWeek::name)
                .collect(Collectors.joining(","));
    }
}
