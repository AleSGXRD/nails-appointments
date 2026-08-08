package com.xrd.nails_appointment.model;

public enum ServiceDuration {

    MINUTES_15(15, "15 min"),
    MINUTES_30(30, "30 min"),
    MINUTES_50(50, "50 min"),
    HOUR_1(60, "1 hour"),
    HOUR_1_30(90, "1 hour 30 min"),
    HOURS_2(120, "2 hours");

    private final int minutes;
    private final String label;

    ServiceDuration(int minutes, String label) {
        this.minutes = minutes;
        this.label = label;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getLabel() {
        return label;
    }
}
