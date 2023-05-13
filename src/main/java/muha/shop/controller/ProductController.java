package muha.shop.controller;

import muha.shop.entity.*;
import muha.shop.repository.*;
import muha.shop.service.ProductService;
import muha.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderController orderController;

    // Список товарав
    @GetMapping(path = "/list_product")
    public String ListProduct(Model model, @RequestParam(required = false) Long categoryId) {
        List<Product> products;
        Sort sort = Sort.by(
                Sort.Order.asc("category")
        );

        if (categoryId != null) {
            products = categoryRepository.findById(categoryId).orElseThrow().getProducts();
        } else {
            products = productRepository.findAll(sort);
        }
        model.addAttribute("products_att", products);
//        model.addAttribute("user", user);
        return "list_product";
    }

    //Работает   !!!!
    // Выбор категории
    @GetMapping(path = "/chose_category")
    public String selectCategory(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "chose_category";
    }

    // Добавить в список товар
    @GetMapping("/add_product")
    public String productAdd(@RequestParam Long categoryId, Model model) {
        Category category = categoryRepository.findById((categoryId)).orElseThrow();
        model.addAttribute("add_product", category);
        return "add_product";
    }

    @PostMapping("/add_product_post")
    public String createProductPost(
            @RequestParam Long categoryId,
            @RequestParam String productName,
            @RequestParam Integer productPrice
//            @RequestParam List<Long> optionsId,
//            @RequestParam List<String> valuesName
    ) {
        productService.addProductList(categoryId, productName, productPrice
//                optionsId, valuesName
        );
        return "redirect:/product/list_product";
    }


    @GetMapping("/update")
    public String upDateProduct(
            @RequestParam Long productId, Model model) {
        Product product = productRepository.findById(productId).orElseThrow();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("product_up", product);
        model.addAttribute("categories_up", categories);
        return "update_product";
    }

    @PostMapping("/update_post")
    public String updateProductPost(
            @RequestParam Long productId,
            @RequestParam String productName,
            @RequestParam Integer productPrice
    ) {
        productService.updateProduct(productId, productName, productPrice);
        return "redirect:/product/list_product";

    }

    //Работает !!!
    @GetMapping("/info")
    public String info(@RequestParam Long productId, Model model) {
        Product product = productRepository.findById(productId).orElseThrow();
        List<Value> values = product.getValues();
        model.addAttribute("productInfo", product);
        model.addAttribute("values", values);
        return "show_info";
    }

    //Работает !!!
    @PostMapping("/delete_post")
    public String deletePost(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list_product";
    }

    @PostMapping("/order_post")
    public String orderPost(String order) {
        orderController.makeOrder(order);
        return "order_product";
    }


}

