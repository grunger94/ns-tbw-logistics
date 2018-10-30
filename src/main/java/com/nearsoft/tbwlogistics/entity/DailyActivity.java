package com.nearsoft.tbwlogistics.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DailyActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date date;
    private byte durationInHours;
    private String place;
    private byte attendanceLimit;

    public DailyActivity() {}

    public DailyActivity(String name, String description, Date date, byte durationInHours, String place, byte attendanceLimit) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.durationInHours = durationInHours;
        this.place = place;
        this.attendanceLimit = attendanceLimit;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public byte getDurationInHours() {
        return durationInHours;
    }

    public String getPlace() {
        return place;
    }

    public byte getAttendanceLimit() {
        return attendanceLimit;
    }
}
