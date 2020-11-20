package pl.edu.wszib.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

    @RequestMapping(value = "/cos", method = RequestMethod.GET)
    public String httpRequestAction() {
        System.out.println("Odpalilo sie cos !!");
        return "main";
    }

    @RequestMapping(value = "/cos2", method = RequestMethod.GET)
    public String httpRequestAction2() {
        System.out.println("abc1111111111");
        return "main";
    }

    @RequestMapping(value = "/cos3", method = RequestMethod.GET)
    public String httpRequestAction3() {
        System.out.println("abc44444444444444444");
        return "main";
    }
}
