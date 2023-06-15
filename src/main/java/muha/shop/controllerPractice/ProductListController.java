package muha.shop.controllerPractice;

import muha.shop.entity.Category;
import muha.shop.entity.Product;
import muha.shop.repository.CategoryRepository;
import muha.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Здесь мы выводим список Товаров и сотрировали по (Категориям или по Товару)

@Controller
@RequestMapping(path = "/product_list_controller")
public class ProductListController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/product_list")
    public String productListPage(
            @RequestParam Long categoryId, Model model) {
        Sort sort = Sort.by(
                Sort.Order.desc("category_id")
        );

        /*
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        List<Product> products = category.getProducts();
        */

        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        model.addAttribute("products", products);
        return "product_list_controller";
    }
}
