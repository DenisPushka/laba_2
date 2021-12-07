package com;

import com.Errors.InvalidBookPriceException;
import com.Interfaces.IBook;

public class Book implements IBook {
    private String author;
    private String name;
    private float price;
    private int year;

    public Book() {
        this("Не определено", "Не определено", 0, 0);
    }

    public Book(String author, String name, float price, int year) {
        this.author = author;
        this.name = name;
        this.price = price;
        this.year = year;
    }

    public Book(String author, String name) {
        this();
        this.author = author;
        this.name = name;
    }

    public String getAuthor() {
        if (author == null) {
            return "null";
        } else {
            return author;
        }
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        if (name == null) {
            return "null";
        } else {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws InvalidBookPriceException {
        if (price < 0)
            throw new InvalidBookPriceException("Некорректная цена");
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return "" + getAuthor() + " " + getName() + " " + getPrice() + " " + getYear();
    }
}

