package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.database.IBooksRepository;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Role;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AdminController {

    @Autowired
    IBooksRepository booksRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{isbn}", method = RequestMethod.GET)
    public String editForm(@PathVariable String isbn, Model model) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != Role.ADMIN) {
            return "redirect:/login";
        }
        Book book = this.booksRepository.getBookByISBN(isbn);
        model.addAttribute("book", book);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "edit";
    }

    @RequestMapping(value = "/edit/{isbn}", method = RequestMethod.POST)
    public String edit(@ModelAttribute Book book) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != Role.ADMIN) {
            return "redirect:/login";
        }
        Book bookFromDB = this.booksRepository.getBookByISBN(book.getIsbn());
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setAuthor(book.getAuthor());
        bookFromDB.setPieces(book.getPieces());
        bookFromDB.setPrice(book.getPrice());

        return "redirect:/main";
    }
}
