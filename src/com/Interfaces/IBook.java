package com.Interfaces;

import com.Errors.InvalidBookPriceException;

public interface IBook {
    void setName(String name) ;
    String getName();

    void setAuthor (String author);
    String getAuthor();

    void setYear ( int year);
    int getYear();

    void setPrice (float price) throws InvalidBookPriceException;
    float getPrice();
}
