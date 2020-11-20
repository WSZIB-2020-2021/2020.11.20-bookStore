package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;

public interface IBooksRepository {
    List<Book> getAllBooks();
}