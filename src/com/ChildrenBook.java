package com;

import com.Interfaces.IBook;

public class ChildrenBook extends Book implements IBook
{
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ChildrenBook() {
        super();
        age = 0;
    }

    public ChildrenBook(String author, String name, int age) {
        super(author, name);
        this.age = age;
    }

    public ChildrenBook(String author, String name, float price, int year, int age)
    {
        super(author, name, price, year);
        this.age = age;
    }

    public String toString()
    {
        return (super.toString() + " " + age);
    }
}
