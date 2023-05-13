package muha.shop.controller;

import muha.shop.entity.User;
import muha.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/adduser")
    public String AddUser(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping("/adduser_post")
    public String User(){

        return "";
    }

}

