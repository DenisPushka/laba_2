package com;

import com.Errors.BookIndexOutOfBoundsException;
import com.Errors.HallIndexOutOfBoundsException;
import com.Errors.InvalidBookCountException;
import com.Errors.InvalidBookPriceException;
import com.Interfaces.IBook;
import com.Interfaces.IHall;
import com.Interfaces.ILibrary;

public class ChildrenLibrary implements ILibrary {
    private IHall[] arrayHall;

    public ChildrenLibrary(ChildrenLibraryHall[] arrayHall) {
        this.arrayHall = arrayHall;
    }

    public ChildrenLibrary(int hallCount, int[] bookArray) {
        arrayHall = new ChildrenLibraryHall[hallCount];
        for (int i = 0; i < arrayHall.length; i++) {
            ChildrenLibraryHall hall = new ChildrenLibraryHall("Not defined №" + i, bookArray[i]);
            arrayHall[i] = hall;
        }
    }

    public ChildrenLibrary(IHall[] hallArr) {
        arrayHall = hallArr;
    }

    public int getHallCount() {
        return arrayHall.length;
    }

    public int getBookCount() {
        int count = 0;
        for (int i = 0; i < arrayHall.length; i++) {
            count += arrayHall[i].getCountBook();
        }
        return count;
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (int i = 0; i < arrayHall.length; i++) {
            totalPrice += arrayHall[i].getTotalPrice();
        }
        return totalPrice;
    }

    public IHall[] getArrayHall() {
        return arrayHall;
    }

    public IHall getHall(int number) {
        if (number < arrayHall.length)
            return arrayHall[number];
        else return null;
    }

    public IBook getBook(int number) {
        if (number > arrayHall.length || number < 0)
            throw new InvalidBookCountException("Книги с таким номером нет");
        int k = 0;
        for (int i = 0; i < arrayHall.length; i++) {
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
        IBook[] arr = new ChildrenBook[getBookCount()];
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
        for (int i = 0; i < arrayHall.length; i++) {
            System.out.println(arrayHall[i].getCountBook());
        }
        System.out.println("");
    }

    public void printHalls() {
        for (int i = 0; i < arrayHall.length; i++) {
            arrayHall[i].printBooks();
        }
    }

    public void changeHall(int number, IHall hall) {
        if ((number > arrayHall.length) && (number < 0)) {
            throw new HallIndexOutOfBoundsException("Зал под таким номером не суще-ствует");
        }
        arrayHall[number] = (ChildrenLibraryHall) hall;
    }

    public void changeBook(int number, IBook newBook) {
        if (number > getHallCount())
            throw new BookIndexOutOfBoundsException("Книги по данному номеру не суще-ствует");
        IBook Book = getBook(number);
        Book.setAuthor(newBook.getAuthor());
        Book.setName(newBook.getName());
        try {
            Book.setPrice(newBook.getPrice());
        } catch (InvalidBookPriceException e) {
            e.printStackTrace();
        }
        Book.setYear(newBook.getYear());
    }

    public void addBook(int number, IBook book) {
        int k = 0;
        for (int i = 0; i < arrayHall.length; i++) {
            if (arrayHall[i].getCountBook() + k >= number) {
                arrayHall[i].addBook(number - k, book);
                return;
            } else {
                k += arrayHall[i].getCountBook();
            }
        }
    }

    public void deleteBook(int number) {
        if (number > getHallCount())
            throw new BookIndexOutOfBoundsException("Книги по данному номеру не суще-ствует");
        int k = 0;
        for (int i = 0; i < arrayHall.length; i++) {
            if (arrayHall[i].getCountBook() + k > number) {
                arrayHall[i].deleteBook(number - k);
                return;
            } else {
                k += arrayHall[i].getCountBook();
            }
        }
    }

    public IBook getBestBook() {
        IBook best = arrayHall[0].getBestBook();
        for (int i = 1; i < arrayHall.length; i++) {
            if (best.getPrice() < arrayHall[i].getBestBook().getPrice()) {
                best = arrayHall[i].getBestBook();
            }
        }
        return best;
    }
}

