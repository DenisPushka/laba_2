package com;

import com.Errors.InvalidBookPriceException;
import com.Interfaces.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        IBook book1 = new ScientificBook("author-1", "name-1", 1000, 15, 1);
        IBook book2 = new ScientificBook("author-2", "name-2", 2000, 16, 2);
        IBook book3 = new ScientificBook("author-3", "name-3", 3000, 17, 3);
        IBook book4 = new ScientificBook("author-4", "name-4", 4000, 18, 4);
        List list = new List();
        list.addItem(book1);
        list.addItem(book2);
        list.addItem(book3);
        list.addItem(book4);

        IHall hall1 = new ScientificLibraryHall("Hall №1", list);
        IHall hall2 = new ScientificLibraryHall("Hall №2", 0);
        IHall hall3 = new ScientificLibraryHall("Hall №3", 3);

        List2 list2 = new List2();
        list2.addItem(1, hall1);
        list2.addItem(2, hall2);
        list2.addItem(3, hall3);
        ILibrary lib = new ScientificLibrary(list2);

        System.out.println("Вывод библиотеки на экран: ");
        lib.printHalls();
        System.out.println("Добавление новой книги в зал №2: ");
        ScientificBook newBook1 = new ScientificBook("author-6", "name-6", 5500, 2010, 25);
        hall2.addBook(5, newBook1);
        hall2.printBooks();
        System.out.println("Добавление книги под номером \"4\" в библиотеку: ");
        ScientificBook newBook = new ScientificBook("author-5", "name-5", 550, 2016, 15);
        lib.addBook(4, newBook);
        lib.printHalls();

        System.out.println("Удаление книги №4: ");
        lib.deleteBook(4);
        lib.printHalls();
        System.out.println("Автор лучшей книги: " + lib.getBestBook().getAuthor() + "\n");
        lib.costSort();

        try {
            Libraries.writeLibrary(lib, new FileWriter("library.txt"));
            ILibrary libRar = Libraries.readLibrary(new FileReader("library.txt"));
            libRar.printHalls();
            //System.out.println(libRar);
        } catch (IOException | InvalidBookPriceException e) {
            e.printStackTrace();
        }
    }
}

