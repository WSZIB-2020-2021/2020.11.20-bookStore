package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.database.IBooksRepository;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    IBooksRepository booksRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        model.addAttribute("books", this.sessionObject.getBasket());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        double sum = 0;
        for (Book book : this.sessionObject.getBasket()) {
            sum = sum + book.getPrice() * book.getPieces();
        }
        model.addAttribute("sum", sum);
        return "basket";
    }

    @RequestMapping(value = "/addToBasket/{isbn}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable String isbn) {

        Book book = this.booksRepository.getBookByISBN(isbn);
        this.sessionObject.addToBasket(book.clone());
        return "redirect:/main";
    }
}
