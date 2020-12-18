package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.IBookService;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    @Override
    public Book getBookById(int id) {
        return this.bookDAO.getBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        Book bookFromDB = this.bookDAO.getBookById(book.getId());
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setIsbn(book.getIsbn());
        bookFromDB.setPieces(book.getPieces());
        bookFromDB.setPrice(book.getPrice());

        this.bookDAO.updateBook(bookFromDB);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }
}
