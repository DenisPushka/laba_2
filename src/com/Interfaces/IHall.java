package com.Interfaces;

public interface IHall {
    int getCountBook();
    void printBooks();
    float getTotalPrice();
    IBook [] getArrayHall();
    IBook getBook(int number);
    void editBook(int number, IBook book);
    void addBook(int number, IBook book);
    void deleteBook(int number);
    IBook getBestBook();
    String getHallName();
}
