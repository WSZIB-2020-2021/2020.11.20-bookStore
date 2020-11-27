package pl.edu.wszib.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.database.IUsersRepository;
import pl.edu.wszib.book.store.model.User;

@Controller
public class UserController {

    @Autowired
    IUsersRepository usersRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login,
                        @RequestParam String pass) {
        User user = new User(login, pass);
        boolean authResult = this.usersRepository.authenticate(user);
        if(authResult) {
            return "redirect:/main";
        } else {
            return "login";
        }
    }
}
