package com.library.www.Model;

import java.time.*;

public class Book {
    private long id;
    private String name;
    private LocalDate date;
    private boolean availability;

    public Book(long id, String name, LocalDate date, boolean availability) {
        setId(id);
        setName(name);
        setDate(date);
        setAvailability(availability);
    }

    public Book(String name, LocalDate date, boolean availability) {
        setName(name);
        setDate(date);
        setAvailability(availability);
    }


    private void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }
}
