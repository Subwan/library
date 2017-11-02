package com.library.www.Model;

import java.sql.Date;
import java.time.*;

/**
 * Object Book.
 */
public class Book {
    private long id;
    private String name;
    private LocalDate date;
    private boolean availability;

    /**
     * Constructor book with id, used to update book and find all book.
     * @param id
     * @param name
     * @param date
     * @param availability
     */
    public Book(long id, String name, LocalDate date, boolean availability) {
        setId(id);
        setName(name);
        setDate(date);
        setAvailability(availability);
    }

    /**
     * Constructor book without id, used to insert book.
     * @param name
     * @param date
     * @param availability
     */
    public Book(String name, LocalDate date, boolean availability) {
        setName(name);
        setDate(date);
        setAvailability(availability);
    }

    public Book(Long id, String name, Date date, Boolean availability) {
        setId(id);
        setName(name);
        setDate(date.toLocalDate());
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
