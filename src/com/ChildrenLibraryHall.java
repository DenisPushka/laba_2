package com;

import com.Errors.InvalidBookCountException;
import com.Interfaces.IBook;
import com.Interfaces.IHall;

public class ChildrenLibraryHall implements IHall
{
    private String name;
    private ChildrenBook[] hall;

    public ChildrenLibraryHall(String name, int count) {
        this.name = name;
        this.hall = new ChildrenBook[count];
    }

    public ChildrenLibraryHall(String name, ChildrenBook[] hall) {
        this.name = name;
        this.hall = hall;
    }

    public String getHallName() {
        return name;
    }

    public int getCountBook() {
        return hall.length;
    }

    public ChildrenBook[] getArrayHall() {
        return hall;
    }

    public void setHall(ChildrenBook[] hall) {
        this.hall = hall;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChildrenLibraryHall[] getArrayBook() {
        ChildrenLibraryHall[] arrayBook = new ChildrenLibraryHall[hall.length];
        for (int i = 0; i < hall.length; i++) {
            //arrayBook[i] = hall[i].toString();
        }
        return arrayBook;
    }

    public void getBookNames() {
        for (int i = 0; i < hall.length; i++) {
            System.out.println(i + ": " + hall[i].getName());
        }
        System.out.println("");
    }

    public void printBooks() {
        System.out.println(getHallName() + ": ");
        for (int i = 0; i < hall.length; i++) {
            System.out.println(i + ": " + hall[i].toString());
        }
        System.out.println("");
    }
    public float getTotalPrice() {
        float totalPrice = 0;
        for (int i = 0; i < hall.length; i++)
            totalPrice += hall[i].getPrice();
        return totalPrice;
    }

    public ChildrenBook getBook(int number) {
        if (number > hall.length && number < 0)
            throw new InvalidBookCountException("Книги с таким номером нет");
        return hall[number];
    }
    public void editBook(int number, IBook book) {
        if (number > hall.length && number < 0)
            throw new InvalidBookCountException("Книги с таким номером нет");
        hall[number] = (ChildrenBook) book;
    }

    public void addBook(int number, IBook book) {
        ChildrenBook[] copy = new ChildrenBook[hall.length + 1];
        for (int i = 0; i < number; i++) {
            copy[i] = hall[i];
        }
        copy[number] = (ChildrenBook) book;
        for (int i = number + 1; i < copy.length; i++) {
            copy[i] = hall[i - 1];
        }
        hall = copy;
    }

    public void deleteBook(int number) {
        if (number > hall.length && number < 0)
            throw new InvalidBookCountException("Книги с таким номером нет");
        ChildrenBook[] copy = new ChildrenBook[hall.length - 1];
        for (int i = 0; i < number; i++) {
            copy[i] = hall[i];
        }
        for (int i = number; i < copy.length; i++) {
            copy[i] = hall[i + 1];
        }
        hall = copy;
        System.out.println("Book №" + number + " of " + name + " is  deleted.");
    }
    public IBook getBestBook() {
        IBook best = hall[0];
        for (int i = 1; i < hall.length; i++) {
            if (hall[i].getPrice() > best.getPrice()) {
                best = hall[i];
            }
        }
        return best;
    }
}
