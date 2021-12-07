package com;

import com.Errors.*;
import com.Interfaces.*;

class ScientificLibrary implements ILibrary {
    private List2 list;

    public ScientificLibrary(List2 list) {
        this.list = list;
    }

    public ScientificLibrary(int hallCount, int[] bookArray) {
        list = new List2();
        for (int i = 0; i < hallCount; i++) {
            ScientificLibraryHall hall = new
                    ScientificLibraryHall("Not defined №" + i, bookArray[i]);
            list.addItem(i, hall);
        }
    }

    public int getHallCount() {
        return list.getCountElement();
    }

    public int getBookCount() {
        int count = 0;
        for (int i = 0; i < list.getCountElement(); i++) {
            count += list.getItem(i).getData().getCountBook();
        }
        return count;
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (int i = 0; i < list.getCountElement(); i++) {
            totalPrice +=
                    list.getItem(i).getData().getTotalPrice();
        }
        return totalPrice;
    }

    public List2 getList() {
        return list;
    }

    public IHall[] getArrayHall() {
        IHall[] arrayHall = new ScientificLibraryHall[list.getCountElement()];
        for (int i = 0; i < list.getCountElement(); i++) {
            arrayHall[i] = list.getItem(i).getData();
        }
        return arrayHall;
    }

    public IHall getHall(int number) {
        if ((number > list.getCountElement()) && (number < 0)) {
            throw new HallIndexOutOfBoundsException("Зала с таким номером нет");
        }
        return list.getItem(number).getData();
    }

    public IBook getBook(int number) {
        if ((number > list.getCountElement()) && (number < 0)) {
            throw new InvalidBookCountException("Книги с таким номером нет");
        }
        int k = 0;
        for (int i = 0; i < list.getCountElement(); i++) {
            IHall hall = getHall(i);
            for (int j = 0; j < hall.getCountBook(); j++) {
                IBook book = hall.getBook(j);
                if (k == number) {
                    return book;
                }
                k++;
            }
        }
        return null;
    }

    public void costSort() {
        IBook[] arr = new ScientificBook[getBookCount()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getBook(i);
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < (arr.length - i); j++) {
                if (arr[j].getPrice() < arr[j + 1].getPrice()) {
                    IBook t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
        System.out.println("");
    }

    public void printHallInfo() {
        for (int i = 0; i < list.getCountElement(); i++) {
            System.out.println(list.getItem(i).getData().getHallName() + ": "
                    + list.getItem(i).getData().getCountBook());
        }
        System.out.println("");
    }

    public void printHalls() {
        for (int i = 0; i < list.getCountElement(); i++) {
            list.getItem(i).getData().printBooks();
        }
    }

    public void changeHall(int number, IHall hall) {
        if ((number > list.getCountElement()) && (number < 0)) {
            throw new HallIndexOutOfBoundsException("Зала с таким номером нет");
        }
        list.getItem(number).setData((ScientificLibraryHall) hall);
    }

    public void changeBook(int number, IBook newBook) {
        if ((number > list.getCountElement()) && (number < 0)) {
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

    public void addBook(int number, IBook book) {
        int temp = 0;
        for (int i = 0; i < list.getCountElement(); i++) {
            if (list.getItem(i).getData().getCountBook() + temp >=
                    number) {
                list.getItem(i).getData().addBook(number - temp,
                        book);
                return;
            } else {
                temp += list.getItem(i).getData().getCountBook();
            }
        }
    }

    public void deleteBook(int number) {
        if ((number > list.getCountElement()) && (number < 0)) {
            throw new InvalidBookCountException("Книги с таким номером нет");
        }
        int temp = 0;
        for (int i = 0; i < list.getCountElement(); i++) {
            if (list.getItem(i).getData().getCountBook() + temp >
                    number) {
                list.getItem(i).getData().deleteBook(number - temp);
                return;
            } else {
                temp += list.getItem(i).getData().getCountBook();
            }
        }
    }

    public IBook getBestBook() {
        IBook best = list.getItem(0).getData().getBestBook();
        for (int i = 1; i < list.getCountElement(); i++) {
            if (best.getPrice() < list.getItem(i).getData().getBestBook().getPrice()) {
                best = list.getItem(i).getData().getBestBook();
            }
        }
        return best;
    }
}
