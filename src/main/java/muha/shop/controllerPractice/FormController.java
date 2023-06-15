package muha.shop.controllerPractice;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/form")
public class FormController {

    @GetMapping
    public String formPage() {
        return "form_page";
    }

    @ResponseBody
    @PostMapping
    public String formAction(
            @RequestParam String login,
            @RequestParam String password
    ) {
        System.out.printf("Login: %s%n", login);
        System.out.printf("Password: %s%n", password);

        return "Успехно";
    }
}
