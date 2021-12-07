package com;

import com.Interfaces.IBook;

public class List {
    private Item head;
    private int countElement;

    public List() {
        head = new Item(new ScientificBook());
        head.setNextElement(head);
        countElement = 0;
    }

    public List(int countElement) {
        this();
        for (int i = 0; i < countElement; i++) {
            ScientificBook book = new ScientificBook();
            addToEnd(book);
        }
    }

    /*public List(List list) {
        this();
        Item newElement = list.head.getNextElement();
        while (newElement != list.head) {
            this.addToEnd(newElement.getItem());
            newElement = newElement.getNextElement();
        }
    }*/

    public int getCountElement() {
        return countElement;
    }

    public boolean isEmpty() {
        if (countElement == 0) {
            return true;
        }
        return false;
    }

    public void output() {
        Item newElement = head.getNextElement();
        while (newElement != head) {
            System.out.println(newElement.getData().toString() + " ");
            newElement = newElement.getNextElement();
        }
    }

    public boolean addItem(IBook book) {
        addToEnd(book);
        return true;
    }

    public Item getItem(int number) {
        if (isEmpty() || (number < 0) || (number >= countElement)) {
            return null;
        }
        int count = 0;
        Item newElement = head.getNextElement();
        while (newElement != head) {
            if (count == number) {
                return newElement;
            }
            newElement = newElement.getNextElement();
            count++;
        }
        return null;
    }

    public boolean addItem(int number, IBook book) {
        Item item = new Item(book);
        if (isEmpty()) {
            addToBeginning(book);
            return true;
        }
        if (number >= countElement) {
            addToEnd(book);
            return true;
        }
        int count = 0;
        Item q = head;
        Item p = head.getNextElement();
        while (p != head) {
            if (count == number) {
                q.setNextElement(item);
                item.setNextElement(p);
                countElement++;
                return true;
            }
            q = p;
            p = p.getNextElement();
            count++;
        }
        return false;
    }

    public boolean deleteItem(int number) {
        if (isEmpty()) {
            return false;
        }
        int count = 0;
        Item q = head;
        Item p = head.getNextElement();
        while (p != head) {
            if (count == number) {
                q.setNextElement(p.getNextElement());
                countElement--;
                return true;
            }
            q = p;
            p = p.getNextElement();
            count++;
        }
        return false;
    }

    private void addToBeginning(IBook book) {
        Item p = new Item(book);
        p.setNextElement(head.getNextElement());
        head.setNextElement(p);
        countElement++;
    }

    private void addToEnd(IBook book) {
        Item p = new Item(book);
        if (head.getNextElement() == head) {
            p.setNextElement(head.getNextElement());
            head.setNextElement(p);
            countElement++;
            return;
        }
        Item tmp = head.getNextElement();
        while (tmp.getNextElement() != head) {
            tmp = tmp.getNextElement();
        }
        tmp.setNextElement(p);
        p.setNextElement(head);
        countElement++;
    }
}

