package muha.shop.controllerPractice;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import muha.shop.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/user_task_controller")
public class UserTaskController {

    private static final User[] USERS = new User[]{
            new User("Bill", 66),
            new User("Jeff", 45),
            new User("Max", 17),
            new User("Leon", 25),
            new User("Abay", 21),
    };


    @GetMapping(path = "/get_user_list")
    public List<User> getUserList(
            @RequestParam(required = false) Integer from,
            @RequestParam(required = false) Integer to
    ) {
        List<User> users = new ArrayList<>();

        if (from == null) from = Integer.MIN_VALUE;
        if (to == null) to = Integer.MAX_VALUE;

        for (User user : USERS) {
            if (user.getAge() >= from && user.getAge() <= to) users.add(user);
        }
        return users;
    }


}
