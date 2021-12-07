package com;


import com.Errors.InvalidBookPriceException;
import com.Interfaces.*;

import java.io.*;
import java.util.Scanner;

import static java.io.StreamTokenizer.TT_WORD;


public class Libraries {

    private static final int TAB = '\t';
    private static final int END_OF_LINE = '\n';

    // Записи данных о библиотеке в байтовый поток
    public static void outputLibrary(ILibrary lib, OutputStream out) throws IOException {
        DataOutputStream outData = new DataOutputStream(out);
        outData.writeInt(lib.getHallCount());
        for (int i = 0; i < lib.getHallCount(); i++) {
            outData.writeInt(lib.getHall(i).getCountBook());
            for (int j = 0; j < lib.getHall(i).getCountBook(); j++) {
                outData.writeBytes(lib.getHall(i).getBook(j).getAuthor());
                outData.writeBytes(lib.getHall(i).getBook(j).getName());
                outData.writeDouble(lib.getHall(i).getBook(j).getPrice());
                outData.writeInt(lib.getHall(i).getBook(j).getYear());
            }
        }
    }

    // Чтения данных о библиотеке из байтового потока
    public static ILibrary inputLibrary(InputStream in) throws IOException, InvalidBookPriceException {
        DataInputStream inData = new DataInputStream(in);
        IHall[] hallArr = new IHall[inData.readInt()];
        for (int i = 0; i < hallArr.length; i++) {
            IBook[] bookArr = new IBook[inData.readInt()];
            for (int j = 0; j < bookArr.length; j++) {
                bookArr[j] = new Book();
                bookArr[j].setAuthor(inData.readUTF());
                bookArr[j].setName(inData.readUTF());
                bookArr[j].setPrice(inData.readFloat());
                bookArr[j].setYear(inData.readInt());
            }
            hallArr[i] = new ScientificLibraryHall(bookArr);
        }
        return new ChildrenLibrary(hallArr);
    }

    // Записи библиотеки в символьный поток
    public static void writeLibrary(ILibrary lib, Writer out) throws IOException {
        PrintWriter prOut = new PrintWriter(out);
        prOut.write(lib.getHallCount() + "\n");
        for (int i = 0; i < lib.getHallCount(); i++) {
            prOut.write(lib.getHall(i).getCountBook() + "\n");
            for (int j = 0; j < lib.getHall(i).getCountBook(); j++) {
                prOut.write(lib.getHall(i).getBook(j).getName() + "\t" +
                        lib.getHall(i).getBook(j).getPrice() + "\t" +
                        lib.getHall(i).getBook(j).getAuthor() + "\t" +
                        lib.getHall(i).getBook(j).getYear() + "\n");
            }
        }
        out.flush();
    }

    // Чтения библиотеки из символьного потока
    public static ILibrary readLibrary(Reader in) throws IOException, InvalidBookPriceException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        IHall[] halls = new IHall[(int) tokenizer.nval];
        String strBuff = "";
        for (int i = 0; i < halls.length; i++) {
            tokenizer.nextToken();
            IBook[] books = new IBook[(int) tokenizer.nval];
            for (int j = 0; j < books.length; j++) {
                books[j] = new ScientificBook();
                tokenizer.nextToken();
                // Считываем словосочетание
                while (tokenizer.ttype == TT_WORD &&
                        tokenizer.ttype != TAB &&
                        tokenizer.ttype != END_OF_LINE) {
                    strBuff += tokenizer.sval + " ";
                    tokenizer.nextToken();
                }
                books[j].setName(strBuff);
                strBuff = "";

                tokenizer.nextToken();
                books[j].setPrice((float) tokenizer.nval);

                // Считываем словосочетание
                while (tokenizer.ttype == TT_WORD &&
                        tokenizer.ttype != TAB &&
                        tokenizer.ttype != END_OF_LINE) {
                    strBuff += tokenizer.sval + " ";
                    tokenizer.nextToken();
                }
                books[j].setAuthor(strBuff);
                strBuff = "";
                books[j].setYear((int) tokenizer.nval);
            }
            halls[i] = new ScientificLibraryHall(books);
        }
        return new ChildrenLibrary(halls);
    }

    // Метод сериализация библиотеки в байтовый поток
    public static void serializeLibrary(ILibrary library, OutputStream out) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(library);
        objOut.flush();
    }

    // Метод десериализации библиотеки из байтового потока
    public static ILibrary deserializeLibrary(InputStream in) throws IOException, ClassNotFoundException {
        return (ILibrary) new ObjectInputStream(in).readObject();
    }

    //метод текстовой записи
    public static void writeLibraryFormat(ILibrary library, Writer out) {
        PrintWriter formOut = new PrintWriter(out);
        formOut.print(library.getHallCount());
        for (int i = 0; i < library.getHallCount(); i++) {
            formOut.print(library.getHall(i).getCountBook());
            for (int j = 0; j < library.getHall(i).getCountBook(); j++) {
                formOut.print(library.getHall(i).getBook(j).getAuthor());
                formOut.print(library.getHall(i).getBook(j).getName());
                formOut.print(library.getHall(i).getBook(j).getPrice());
                formOut.print(library.getHall(i).getBook(j).getYear());
            }
        }
    }

    public static ILibrary readLibrary(Scanner in) throws FileNotFoundException, InvalidBookPriceException {
        IHall[] halls = new IHall[in.nextInt()];
        for (int i = 0; i < halls.length; i++) {
            IBook[] books = new IBook[in.nextInt()];
            for (int j = 0; j < books.length; i++) {
                books[j] = new Book();
                books[j].setAuthor(in.next());
                books[j].setName(in.next());
                books[j].setPrice(in.nextFloat());
                books[j].setYear(in.nextInt());
            }
            halls[i] = new ScientificLibraryHall(books);
        }
        return new ChildrenLibrary(halls);
    }
}