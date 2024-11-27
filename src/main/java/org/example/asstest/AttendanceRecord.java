package org.example.asstest;

import java.time.LocalDate;

public class AttendanceRecord {
    private LocalDate date;
    private boolean isPresent;
    private String reason;

    public AttendanceRecord(LocalDate date, boolean isPresent, String reason) {
        this.date = date;
        this.isPresent = isPresent;
        this.reason = reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public String getReason() {
        return reason;
    }
}
