package pl.edu.wszib.book.store.services;

import pl.edu.wszib.book.store.model.Book;
import java.util.List;

public interface IBookService {
    Book getBookById(int id);
    void updateBook(Book book);
    List<Book> getAllBooks();
}
