package com;

import com.Interfaces.IHall;

public class List2
{
    private Item2 head;
    private Item2 endElement;
    private int countElement;

    public List2()
    {
        head = new Item2(null);
        endElement = new Item2(null);
        head.setPrevElement(endElement);
        head.setNextElement(endElement);
        endElement.setPrevElement(head);
        endElement.setNextElement(head);
        countElement = 0;
    }

    public List2(int count)
    {
        this();
        for (int i = 0; i < count; i++) {
            ScientificLibraryHall hall = new
                    ScientificLibraryHall("Hall#" + i, count);
            addToEnd(hall);
        }
    }

    public List2(List2 list)
    {
        this();
        Item2 p = list.head.getNextElement();
        while (p != list.endElement)
        {
            this.addToEnd(p.getData());
            p = p.getNextElement();
        }
    }

    public int getCountElement()
    {
        return countElement;
    }

    public boolean isEmpty()
    {
        if (countElement == 0)
        {
            return true;
        }
        return false;
    }

    public void output()
    {
        Item2 p = head.getNextElement();
        while (p != endElement)
        {
            System.out.println(p.getData().toString() + " "); p
                = p.getNextElement();
        }
    }
    public Item2 getItem(int number)
    {
        if (isEmpty() || (number < 0) || (number >= countElement))
        {
            return null;
        }
        int n = 0;
        Item2 p = head.getNextElement();
        while (p != endElement)
        {
            if (n == number)
            {
                return p;
            }
            p = p.getNextElement();
            n++;
        }
        return null;
    }

    public boolean addItem(int number, IHall hall)
    {
        Item2 item = new Item2(hall);
        if (isEmpty())
        {
            addToBeginning(hall);
            return true;
        }
        if (number >= countElement)
        {
            addToEnd(hall);
            return true;
        }
        int n = 0;
        Item2 q = head;
        Item2 p = head.getNextElement();
        while (p != endElement)
        {
            if (n == number)
            {
                q.setNextElement(item);
                item.setNextElement(p);
                countElement++;
                return true;
            }
            q = p;
            p = p.getNextElement();
            n++;
        }
        return false;
    }
    public boolean deleteItem(int number)
    {
        if (isEmpty())
        {
            return false;
        }
        int n = 0;
        Item2 q = head;
        Item2 p = head.getNextElement();
        while (p != endElement)
        {
            if (n == number)
            {
                q.setNextElement(p.getNextElement());
                countElement--;
                return true;
            }
            q = p;
            p = p.getNextElement();
            n++;
        }
        return false;
    }
    private void addToBeginning(IHall hall)
    {
        Item2 p = new Item2(hall);
        p.setPrevElement(head);
        p.setNextElement(head.getNextElement());
        head.getNextElement().setPrevElement(p);
        head.setNextElement(p);
        countElement++;
    }
    private void addToEnd(IHall hall)
    {
        Item2 p = new Item2(hall);
        p.setPrevElement(endElement.getPrevElement());
        p.setNextElement(endElement);
        endElement.getPrevElement().setNextElement(p);
        endElement.setPrevElement(p);
        countElement++;
    }
}
