package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.services.IBasketService;
import pl.edu.wszib.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    IBasketService basketService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("books", this.sessionObject.getBasket());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("sum", this.basketService.calculateTotal());
        return "basket";
    }

    @RequestMapping(value = "/addToBasket/{id}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable int id) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        this.basketService.addBookByIdToBasket(id);
        return "redirect:/main";
    }
}
