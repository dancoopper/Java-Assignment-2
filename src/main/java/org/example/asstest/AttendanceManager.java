package org.example.asstest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceManager {
    private Map<Integer, List<AttendanceRecord>> attendanceRecords = new HashMap<>();

    public void markAttendance(int employeeId, LocalDate date, boolean isPresent, String reason) {
        attendanceRecords.putIfAbsent(employeeId, new ArrayList<>());
        attendanceRecords.get(employeeId).add(new AttendanceRecord(date, isPresent, reason));
    }

    public List<AttendanceRecord> getAttendance(int employeeId) {
        return attendanceRecords.getOrDefault(employeeId, new ArrayList<>());
    }
}
