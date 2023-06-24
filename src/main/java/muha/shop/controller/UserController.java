package muha.shop.controller;

import muha.shop.entity.User;
import muha.shop.service.FeedbackService;
import muha.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/login")
    public String LogIn() {
        return "log_in";
    }

    @GetMapping(path = "/create_user")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/create_user_post")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/create_user";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Пользователь с данным логином уже существует");
            return "registration_success";
        }
    }

//    @GetMapping("/success_page")
//    public String registrationSuccess() {
//        return "registration_success";
//    }



//    @GetMapping(path = "/create_admin")
//    public String createAdmin(Model model, User user) {
//        model.addAttribute("admin", user);
//        return "admin/create_admin";
//    }

    // Главная страничка
    @GetMapping(path = "/main_page")
    public String mainPage() {
        return "main_page";
    }

//    @GetMapping(path = "/main_page_admin")
//    public String mainPageAdmin() {
//        return "main_page_admin";
//    }

    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }

    @GetMapping("/user/feedback/delete")
    public String deleteUserFeedback(@RequestParam Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return "redirect:/user/feedback";
    }

    //    Уведомление Пользователь создан
    @GetMapping("/user_success")
    public String createUserPost(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "registration_success";
    }

    //    Уведомление Админ создан
//    @PostMapping(path = "/create_admin_post")
//    public String createAdminPost(@ModelAttribute("admin") User user) {
//        userService.createAdmin(user);
//        return "admin_registration_success";
//    }

}

