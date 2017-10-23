package com.library.www.BL;

import java.time.*;

public class Book {
    private int id;
    private String name;
    private LocalDateTime date;
    private boolean availability;

    public Book(int id, String name, LocalDateTime date, boolean availability) {
        setId(id);
        setName(name);
        setDate(date);
        setAvailability(availability);
    }

    public Book(String name, LocalDateTime date, boolean availability) {
        setName(name);
        setDate(date);
        setAvailability(availability);
    }


    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    private void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }
}
