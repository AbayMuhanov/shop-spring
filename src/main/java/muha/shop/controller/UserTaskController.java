package muha.shop.controller;

import muha.shop.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user_task_controller")
public class UserTaskController {

    private static final User[] USERS = new User[]{
            new User("Bill", 66),
            new User("Jeff", 45),
            new User("Max", 17),
            new User("Leon", 25)

    };

    @GetMapping(path = "/get_user_list")
    public List<User> getUserList(
            @RequestParam(required = false) Integer from,
            @RequestParam(required = false) Integer to
    ) {
        List<User> userList = new ArrayList<>();

        if (from == null) from = Integer.MIN_VALUE;
        if (to == null) to = Integer.MAX_VALUE;

        for (User user : USERS) {
            if (user.getAge() >= from && user.getAge() <= to) userList.add(user);
        }
        return userList;
    }

}
