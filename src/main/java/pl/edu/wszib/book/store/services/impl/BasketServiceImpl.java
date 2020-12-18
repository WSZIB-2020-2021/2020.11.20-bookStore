package pl.edu.wszib.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.IBasketService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Service
public class BasketServiceImpl implements IBasketService {

    @Autowired
    IBookDAO bookDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public double calculateTotal() {
        double sum = 0;
        for (Book book : this.sessionObject.getBasket()) {
            sum = sum + book.getPrice() * book.getPieces();
        }
        return sum;
    }

    @Override
    public void addBookByIdToBasket(int id) {
        Book book = this.bookDAO.getBookById(id);
        for(Book bookFromBasket : this.sessionObject.getBasket()) {
            if(bookFromBasket.getId() == book.getId()) {
                bookFromBasket.setPieces(bookFromBasket.getPieces() + 1);
                return;
            }
        }

        book.setPieces(1);
        this.sessionObject.getBasket().add(book);
    }
}
