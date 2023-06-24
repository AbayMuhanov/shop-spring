package muha.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/security_controller")

public class SecurityController {

    @GetMapping(path = "/first_resource")
    public String firstResource() {
        return "SecurityController.firstResource";
    }

    @GetMapping(path = "/second_resource")
    public String secondResource() {
        return "SecurityController.secondResource";

    }
}
