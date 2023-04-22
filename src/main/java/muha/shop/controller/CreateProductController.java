package muha.shop.controller;

import muha.shop.entity.Category;
import muha.shop.entity.Product;
import muha.shop.repository.CategoryRepository;
import muha.shop.repository.OptionRepository;
import muha.shop.repository.ProductRepository;
import muha.shop.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/create")
public class CreateProductController {
    // Здесь мы должны содавать товар через окно браузера
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private ValueRepository valueRepository;


    @GetMapping(path = "/list_product")
    public String ListProduct(Model model, @RequestParam(required = false) Long categoryId) {
        List<Product> products;
        Sort sort = Sort.by(
                Sort.Order.asc("id")
        );
        if (categoryId != null) {
            products = categoryRepository.findById(categoryId).orElseThrow().getProducts();
        } else {
            products = productRepository.findAll(sort);
        }
        model.addAttribute("products", products);
        return "list_product";
    }

    // Список товарав
    @GetMapping(path = "/add_product")
    public String CreateProduct(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "add_product";
    }

    // Cоздание товаара
    @PostMapping(path = "/add_product_post")
    @ResponseBody
    public String product(
            @RequestParam long categoryId,
            @RequestParam String productName,
            @RequestParam int productPrice
//            @RequestParam("option") List<Long> optionsId,
//            @RequestParam("value") List<String> valuesName
    ) {

        Category category = categoryRepository.findById(categoryId).orElseThrow();

        Product product = new Product();
        product.setCategory(category);
        product.setName(productName);
        product.setPrice(productPrice);
        productRepository.save(product);

//        for (int i = 0; i < optionsId.size(); i++) {
//            Option option = optionRepository.findById(optionsId.get(i)).orElseThrow();
//            Value value = new Value();
//            value.setProduct(product);
//            value.setOption(option);
//            value.setValue(valuesName.get(i));
//            valueRepository.save(value);
//        }

        System.out.printf("Category: %s%n", categoryId);
        System.out.printf("Name: %s%n", productName);
        System.out.printf("Price: %d%n", productPrice);

        return "Товар создан успешно";
    }
}
