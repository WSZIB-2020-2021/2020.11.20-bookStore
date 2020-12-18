package pl.edu.wszib.book.store.database;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;

public interface IBooksRepository {
    List<Book> getAllBooks();
    Book getBookByISBN(String isbn);
    void updateBook(Book book);
    Book getBookById(int id);
}
