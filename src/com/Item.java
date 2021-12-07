package com;

import com.Interfaces.IBook;

public class Item
{
    private IBook data;
    private Item nextElement;

    public Item(IBook book)
    {
        nextElement = null;
        data = book;
    }
    public IBook getData(){return data;}
    public void setData(ScientificBook data)
    {
        this.data = data;
    }
    public Item getNextElement()
    {
        return nextElement;
    }
    public void setNextElement(Item nextElement)
    {
        this.nextElement = nextElement;
    }
}
