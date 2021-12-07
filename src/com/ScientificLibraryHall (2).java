package com;

import com.Errors.*;
import com.Interfaces.*;

class ScientificLibraryHall implements IHall
{
    private String nameHall;
    private List list;

    public ScientificLibraryHall(String name, int count)
    {
        this.nameHall = name;
        this.list = new List(count);
    }

    public ScientificLibraryHall(String name, List list)
    {
        this.nameHall = name;
        this.list = list;
    }

    public ScientificLibraryHall(IBook[] bookArr) {
        list = new List();
        for (int i = 0; i < bookArr.length; i++) {
            list.addItem(i + 1, bookArr[i]);
        }
    }

    public String getHallName()
    {
        return nameHall;
    }

    public int getCountBook()
    {
        return list.getCountElement();
    }

    public void setHallName(String name) {
        this.nameHall = name;
    }

    private void add(int number, ScientificBook book)
    {

        list.addItem(number, book);
    }

    public void addBook(int number, IBook book)
    {
        if ((number > getCountBook()) && (number < 0)) {
            throw new InvalidBookCountException("Книги с таким номером нет");
        }
        this.add(number, (ScientificBook) book);
    }

    public void deleteBook(int number)
    {
        if ((number > getCountBook()) && (number < 0)) {
            throw new InvalidBookCountException("Книги с таким номером нет");
        }
        list.deleteItem(number);
    }

    public List getList()
    {
        return list;
    }

    public IBook[] getArrayHall() {

        IBook [] arrayHall = new ScientificBook[list.getCountElement()];
        for ( int i = 0; i < list.getCountElement(); i++)
        {
            arrayHall[i] = list.getItem(i).getData();
        }
        return arrayHall;
    }

    public void getBookNames()
    {
        for (int i = 0; i < list.getCountElement(); i++)
        {
            System.out.println(i + ": " + list.getItem(i).getData().getName());
        }
        System.out.println("");
    }

    public void printBooks()
    {
        System.out.println(getHallName() + ": ");
        for (int i = 0; i < list.getCountElement(); i++)
        {
            System.out.println(i + ": " + list.getItem(i).getData().toString());
        }
        System.out.println("");
    }
    public float getTotalPrice()
    {
        float totalPrice = 0;
        for (int i = 0; i < list.getCountElement(); i++)
        {
            totalPrice += list.getItem(i).getData().getPrice();
        }
        return totalPrice;
    }
    public IBook getBook(int number)
    {
        if ((number > list.getCountElement()) && (number < 0))
            throw new InvalidBookCountException("Книги с таким номером нет");
        return list.getItem(number).getData();
    }

    public void editBook(int number, IBook newBook)
    {
        if ((number > getCountBook()) && (number < 0)) {
            throw new InvalidBookCountException("Книги с таким номером нет");
        }
        IBook book = getBook(number);
        book.setAuthor(newBook.getAuthor());
        book.setName(newBook.getName());
        try {
            book.setPrice(newBook.getPrice());
        } catch (InvalidBookPriceException e) {
            e.printStackTrace();
        }
        book.setYear(newBook.getYear());
    }

    public IBook getBestBook()
    {
        IBook bestBook = list.getItem(0).getData();
        for (int i = 1; i < list.getCountElement(); i++)
        {
            if (list.getItem(i).getData().getPrice() > bestBook.getPrice())
            {
                bestBook = list.getItem(i).getData();
            }
        }
        return bestBook;
    }
}
