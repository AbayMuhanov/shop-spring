package muha.shop.controllerPractice;

import muha.shop.pojo.Product;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/* Здесь мы выводили информацию в браузерском окно при помощи метода. По адресу ресурса
 http://localhost:8080/lesson_controller/first_resource
*/
@RestController
@RequestMapping(path = "/lesson_controller")
public class LessonController {

    @GetMapping(path = "/first_resource")
    public String firstResource() {
        return "<h1>Test Message</h1>";
    }


    @GetMapping(path = "/first_resource1")
    public List<String> firstResource2() {
        List<String> list = new ArrayList<>();
        list.add("Value 1#");
        list.add("Value 2#");
        list.add("Value 3#");
        list.add("Value 4#");

        return list;
        // return: ["Value 1#","Value 2#","Value 3#","Value 4#"]

    }

    @GetMapping(path = "/first_resource2")
    public List<Product> firstResource3() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Смартфоны", "Apple Iphone 12", 950));
        products.add(new Product("Наушники", "Samsung Galaxy Buds 2", 200));

        return products;

        // return: [{"category":"Смартфоны","name":"Apple Iphone 12","price":950},
        // {"category":"Наушники","name":"Samsung Galaxy Buds 2","price":200}]

    }


    @GetMapping(path = "/second_resource")
    public String secondResource(
            @RequestParam(name = "first_name", required = false) String name,
            @RequestParam(required = false) Integer age) {


        System.out.printf("Name: %s%n", name);
        System.out.printf("Age: %d%n %n", age);

        return "Second Resource";
        // return: Second Resource. Почему-то выводит 2 раза одно и то же ?? ))
    }

    @GetMapping(path = "/third_resource/{login}")
    public String thirdResource(
            @PathVariable String login) {

        System.out.printf("Login: %s%n",login);
        return "Third resource";
    }


}
