package com.Interfaces;

public interface ILibrary {
    int getHallCount();
    int getBookCount();
    float getTotalPrice();
    void printHallInfo();
    IHall[] getArrayHall();
    IHall getHall(int number);
    IBook getBook(int number);
    void costSort();
    void changeHall(int number, IHall hall);
    void changeBook(int number, IBook newBook);
    void addBook(int number, IBook book);
    void deleteBook(int number);
    IBook getBestBook();
    void printHalls();
}
