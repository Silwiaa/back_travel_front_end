package com.kodilla.backpacktravelfrontend.domain;

import com.vaadin.flow.data.binder.Binder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private List<Book> books;
    private static BookService bookService;

    private BookService() {
        this.books = exampleData();
    }

    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    private List<Book> exampleData() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Cracking the Coding Interview: 189 Programming Questions and Solutions", "Gayle Laakmann McDowell", "2015", BookType.IT));
        books.add(new Book("Introduction to Algorithms", "The MIT Press", "2009", BookType.IT));
        books.add(new Book("Introduction to the Theory of Computation", "Michael Sipser", "2012", BookType.IT));
        books.add(new Book("Operating System Concepts", "Silberschatz, Galvin, Gagne", "2012", BookType.IT));
        books.add(new Book("The Silent Patient", "Alex Michaelides", "2019", BookType.THRILLER));
        books.add(new Book("The Handmaid's Tale", "Margaret Atwood", "2019", BookType.CLASSIC));
        books.add(new Book("Watch Us Rise", "Renée Watson,  Ellen Hagan", "2019", BookType.POETRY));
        return books;
    }

    public List<Book> findByTitle(String title) {
        return books.stream()
                .filter(p -> p.getTitle().contains(title))
                .collect(Collectors.toList());
    }
}
