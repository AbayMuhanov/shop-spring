package muha.shop.controller;

import muha.shop.entity.*;
import muha.shop.repository.*;
import muha.shop.service.FeedbackService;
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
    @Autowired
    private FeedbackService feedbackService;

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
        model.addAttribute("products_add", products);
//        model.addAttribute("user", user);
        return "list_product";
    }

    // Выбор категории
    @GetMapping(path = "/chose_category")
    public String selectCategory(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "chose_category";
    }

    // Добавить в список товар
    @GetMapping("/create_product")
    public String productAdd(@RequestParam Long categoryId, Model model) {
        Category category = categoryRepository.findById((categoryId)).orElseThrow();
        model.addAttribute("create_product", category);
        return "create_product";
    }

    @PostMapping("/create_product_post")
    public String createProductPost(
            @RequestParam Long categoryId,
            @RequestParam String productName,
            @RequestParam Integer productPrice,
            @RequestParam(name = "option") List<Long> options,
            @RequestParam(name = "value") List<String> values
    ) {
        productService.addProductList(categoryId, productName, productPrice, options, values);
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
            @RequestParam Integer productPrice,
            @RequestParam("option") List<Long> optionsId,
            @RequestParam("value") List<String> productValues
    ) {
        productService.updateProduct(productId, productName, productPrice, optionsId, productValues);
        return "redirect:/product/list_product";
    }

    @GetMapping("/info")
    public String info(@RequestParam Long productId, Model model) {
        Product product = productRepository.findById(productId).orElseThrow();
        List<Value> values = product.getValues();
        List<Feedback> feedbacks = feedbackService.findAllIsPublishedTrue(productId);
        model.addAttribute("productInfo", product);
        model.addAttribute("values", values);
        model.addAttribute("feedbacks", feedbacks);
//        model.addAttribute("feedback", feedbackService.findFeedback(productId));
        return "show_info";
    }

    @PostMapping("/delete_post")
    public String deletePost(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list_product";
    }

}

