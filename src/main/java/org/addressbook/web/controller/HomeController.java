package org.addressbook.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author alisiikh.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/address/list";
    }
}
