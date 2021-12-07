package com;

import com.Interfaces.IBook;

public class ScientificBook extends Book implements IBook
{

    private float refInd;

    public float getRefInd() {
        return refInd;
    }

    public void setRefInd(float refInd) {
        this.refInd = refInd;
    }

    public ScientificBook() {
        super();
        refInd = 0;
    }

    public ScientificBook(String author, String name, int refInd) {
        super(author, name);
        this.refInd = refInd;
    }

    public ScientificBook(String author, String name, float price, int year, int refInd) {
        super(author, name, price, year);
        this.refInd = refInd;
    }

    public String toString() {
        return (super.toString() + " " + refInd);
    }
}
