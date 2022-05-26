package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/rabit")
    public String rabit(){
        return "rabit";
    }
}

