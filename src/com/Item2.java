package com;

import com.Interfaces.IHall;

public class Item2
{
    private IHall data;
    private Item2 prevElement;
    private Item2 nextElement;

    public Item2(IHall data)
    {
        this.data = data;
    }

    public IHall getData()
    {
        return data;
    }

    public void setData(IHall data)
    {
        this.data = data;
    }

    public Item2 getPrevElement()
    {
        return prevElement;
    }

    public void setPrevElement(Item2 prevElement)
    {
        this.prevElement = prevElement;
    }

    public Item2 getNextElement()
    {
        return nextElement;
    }

    public void setNextElement(Item2 nextElement)
    {
        this.nextElement = nextElement;
    }
}
