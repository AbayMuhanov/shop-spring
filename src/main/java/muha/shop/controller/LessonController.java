package muha.shop.controller;

import muha.shop.pojo.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Здесь мы выводили информацию на окно браузера через методы
@RestController
@RequestMapping(path = "/lesson_controller")
public class LessonController {

    @RequestMapping(path = "/first_resource")
    public List<Product> firstResource() {
//        List<String> list = new ArrayList<>();
//        list.add("Value 1#");
//        list.add("Value 2#");
//        list.add("Value 3#");
//        return list;

        List<Product> products = new ArrayList<>();
        products.add(new Product("Смартфоны", "Apple IPhone 12", 950));
        products.add(new Product("Наушники", "Samsung Galaxy Buds 2", 200));
        return products;

    }

    @RequestMapping(path = "second_resource")
    public String secondResource(
            @RequestParam(name = "first_name", required = false) String name,
            @RequestParam(required = false) Integer age) {

        System.out.printf("Name: %s%n", name);
        System.out.printf("Age: %d%n", age);
        return "Second Resource";
    }

    @GetMapping(path = "/third_resource/{login}")
    public String thirdResource(@PathVariable String login) {
        System.out.printf("Login: %s%n", login);
        return "Third resource";
    }

}
