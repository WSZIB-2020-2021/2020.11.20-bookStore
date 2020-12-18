package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.Book;
import java.util.List;

public interface IBookDAO {
    Book getBookById(int id);
    void updateBook(Book book);
    List<Book> getAllBooks();
}
